package com.codefaucet.tms.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.Session;
import com.codefaucet.tms.model.SignInInfo;
import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.User;
import com.codefaucet.tms.model.UserRole;
import com.codefaucet.tms.model.repository.ISessionRepository;
import com.codefaucet.tms.model.repository.IUserRepository;
import com.codefaucet.tms.model.service.IAuthorizationService;
import com.codefaucet.tms.model.service.IPasswordService;

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
	public TaskResult<SignInInfo> signIn(String username, String password) {
		var result = new TaskResult<SignInInfo>();

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
				if (!userRepository.usersExists()) {
					user = new User(UserRole.ADMIN, "admin", passwordService.hash(defaultAdminPassword), "admin",
							"admin", "admin");
					user = userRepository.create(user);
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

		var session = sessionRepository.findLatestByUserId(user.getId());
		if (session == null) {
			var token = UUID.randomUUID().toString();
			var expiration = LocalDateTime.now().plusMinutes(sessionLifespan);
			session = new Session(token, expiration);
			session.setUserId(user.getId());
			session = sessionRepository.create(session);
		}

		var signInInfo = new SignInInfo(session.getToken(), user.getId(), user.getUsername(), user.getRole());
		return result.setSuccessful(signInInfo);
	}

	@Override
	public TaskResult<SignInInfo> validateSession(String token) {
		var result = new TaskResult<SignInInfo>();
		var session = sessionRepository.findByToken(token);
		if (session == null) {
			return result.setFailed("Session not found.");
		}

		var timeNow = LocalDateTime.now();
		if (timeNow.isAfter(session.getExpiration())) {
			return result.setFailed("Session expired.");
		}

		var user = userRepository.findById(session.getUserId());

		if (!user.isActive()) {
			return result.setFailed("User account inactive.");
		}

		session.setExpiration(timeNow.plusMinutes(sessionLifespan));
		session = sessionRepository.update(session);

		var signInInfo = new SignInInfo(session.getToken(), user.getId(), user.getUsername(), user.getRole());
		return result.setSuccessful(signInInfo);
	}

}
