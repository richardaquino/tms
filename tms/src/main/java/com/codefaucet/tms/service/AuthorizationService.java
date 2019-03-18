package com.codefaucet.tms.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.UserRole;
import com.codefaucet.tms.model.domain.Session;
import com.codefaucet.tms.model.domain.User;
import com.codefaucet.tms.model.dto.SignInInfoDTO;
import com.codefaucet.tms.model.service_interface.IAuthorizationService;
import com.codefaucet.tms.model.service_interface.IPasswordService;
import com.codefaucet.tms.repository.ISessionRepository;
import com.codefaucet.tms.repository.IUserRepository;

@Service
public class AuthorizationService implements IAuthorizationService {

	@Value("${app.session.lifespan}")
	private int sessionLifespan;

	@Value("${app.session.defaultAdminPassword}")
	private String defaultAdminPassword;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ISessionRepository sessionRepository;

	@Autowired
	private IPasswordService passwordService;

	@Override
	public TaskResult<SignInInfoDTO> signIn(String username, String password) {
		var result = new TaskResult<SignInInfoDTO>();

		if (username == null || username.isBlank()) {
			result.getErrors().put("username", "Username is required.");
		}
		if (username == null || password.isBlank()) {
			result.getErrors().put("password", "Password is required.");
		}
		if (!result.getErrors().isEmpty()) {
			return result.setFailed();
		}

		var user = userRepository.findByUsername(username);
		if (user == null) {
			var returnFailed = true;
			if (username.equalsIgnoreCase("admin")) {
				if (userRepository.count() == 0) {
					user = new User(UserRole.ADMIN, "admin", passwordService.hash(defaultAdminPassword), "admin",
							"admin", "admin");
					user = userRepository.save(user);
					returnFailed = false;
				}
			}
			if (returnFailed) {
				return result.setFailed("Username or password is incorrect.");
			}
		}
		if (!passwordService.matches(password, user.getPassword())) {
			return result.setFailed("Username or password is incorrect.");
		}

		if (!user.isActive()) {
			return result.setFailed("User account inactive.");
		}

		var session = sessionRepository.findFirstByUserIdOrderByExpirationDesc(user.getId());

		var sessionValid = false;
		if (session != null) {
			var timeNow = LocalDateTime.now();
			if (timeNow.isBefore(session.getExpiration())) {
				sessionValid = true;
			}
		}

		if (!sessionValid) {
			var token = UUID.randomUUID().toString();
			var expiration = LocalDateTime.now().plusMinutes(sessionLifespan);
			session = new Session(token, expiration);
			session.setUser(user);
			session = sessionRepository.save(session);
		}

		var signInInfo = new SignInInfoDTO(session.getToken(), user.getId(), user.getUsername(), user.getRole());
		return result.setSuccessful(signInInfo);
	}

	@Override
	public TaskResult<SignInInfoDTO> validateSession(String token) {
		var result = new TaskResult<SignInInfoDTO>();

		var session = sessionRepository.findByToken(token);
		if (session == null) {
			return result.setFailed("Session not found.");
		}

		var timeNow = LocalDateTime.now();
		if (timeNow.isAfter(session.getExpiration())) {
			return result.setFailed("Session expired.");
		}

		var user = session.getUser();

		if (!user.isActive()) {
			return result.setFailed("User account inactive.");
		}

		session.setExpiration(timeNow.plusMinutes(sessionLifespan));
		session = sessionRepository.save(session);

		var signInInfo = new SignInInfoDTO(session.getToken(), user.getId(), user.getUsername(), user.getRole());
		return result.setSuccessful(signInInfo);
	}

}
