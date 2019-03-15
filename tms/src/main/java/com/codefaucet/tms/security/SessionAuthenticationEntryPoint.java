package com.codefaucet.tms.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class SessionAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws IOException, ServletException {
		var message = authException.getMessage();
		if (message.toLowerCase().startsWith("bad credentials")) {
			message = "INVALID_CREDENTIALS";
		}
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
	}

}