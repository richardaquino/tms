package com.codefaucet.tms.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codefaucet.tms.model.service.ISessionService;
import com.codefaucet.tms.model.service.IUserService;

public class SessionAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private ISessionService sessionService;

	@Autowired
	private IUserService userService;

	@Value("${app.session.lifespan}")
	private int sessionLifespan;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var authorizationToken = request.getHeader("Authorization");
		if (authorizationToken == null || authorizationToken.isEmpty() || authorizationToken.startsWith("Basic ")) {
			filterChain.doFilter(request, response);
			return;
		}

		authorizationToken = authorizationToken.substring(7, authorizationToken.length());
		var session = sessionService.findByToken(authorizationToken);
		if (session != null) {
			var userDetails = userService.findUserPrincipalByUserId(session.getUserId());
			((UserPrincipal) userDetails).setSessionKey(authorizationToken);
			var authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			var timeNow = LocalDateTime.now();
			if (timeNow.isAfter(session.getExpiration())) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "SESSION_EXPIRED");
			} else {
				session.setExpiration(timeNow.plusMinutes(sessionLifespan));
				session = sessionService.update(session);
			}
		}

		filterChain.doFilter(request, response);
	}

}
