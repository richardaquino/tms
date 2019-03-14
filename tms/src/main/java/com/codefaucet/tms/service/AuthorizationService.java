package com.codefaucet.tms.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.codefaucet.tms.model.Session;
import com.codefaucet.tms.model.SignInInfo;
import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.repository.ISessionRepository;
import com.codefaucet.tms.model.repository.IUserRepository;
import com.codefaucet.tms.model.service.IAuthorizationService;
import com.codefaucet.tms.model.service.IPasswordService;

public class AuthorizationService implements IAuthorizationService {

	@Value("${app.session.lifespan}")
	private int sessionLifespan;
	
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
			return result.setFailed("Username or password is incorrect.");
		}
		if (!passwordService.matches(password, user.getPassword())) {
			return result.setFailed("Username or password is incorrect.");
		}
		var session = sessionRepository.findByUserOrderByExpirationDesc(user.getId());
		if (session == null) {
			var token = UUID.randomUUID().toString();
			var expiration = LocalDateTime.now().plusMinutes(sessionLifespan);
			session = new Session(token, expiration);
		}

		return null;
	}

	@Override
	public SignInInfo validateSession(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
