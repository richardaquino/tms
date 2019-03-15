package com.codefaucet.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.User;
import com.codefaucet.tms.model.UserRole;
import com.codefaucet.tms.model.repository.ISessionRepository;
import com.codefaucet.tms.model.repository.IUserRepository;
import com.codefaucet.tms.model.service.IPasswordService;
import com.codefaucet.tms.model.service.IUserService;
import com.codefaucet.tms.service.validator.UserValidator;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private IPasswordService passwordService;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ISessionRepository sessionRepository;

	@Override
	public TaskResult<User> create(User user) {
		var result = new TaskResult<User>();
		result.setErrors(userValidator.validate(user));
		if (!result.getErrors().isEmpty()) {
			return result.setFailed();
		}
		var plainPassword = passwordService.generateRandom();
		var hashedPassword = passwordService.hash(plainPassword);
		user.setPassword(hashedPassword);
		user = userRepository.create(user);
		user.setPassword(plainPassword);
		return result.setSuccessful(user);
	}

	@Override
	public TaskResult<User> update(User user) {
		var result = new TaskResult<User>();
		result.setErrors(userValidator.validate(user));
		if (!result.getErrors().isEmpty()) {
			return result.setFailed();
		}
		user = userRepository.update(user);
		user.setPassword("");
		return result.setSuccessful(user);
	}

	@Override
	public TaskResult<Boolean> setDeleted(long userId, boolean deleted) {
		userRepository.setDeleted(userId, deleted);
		return new TaskResult<Boolean>().setSuccessful(true);
	}

	@Override
	public List<User> find(String queryString, UserRole role, boolean includeInactive, int pageNumber, int pageSize) {
		var users = userRepository.find(queryString, role, includeInactive, pageNumber, pageSize);
		users.forEach(item -> item.setPassword(""));
		return users;
	}

	@Override
	public User findById(long userId) {
		var user = userRepository.findById(userId);
		user.setPassword("");
		return user;
	}

	@Override
	public User findNew() {
		return new User(UserRole.UNKNOWN, "", "", "", "", "");
	}

	@Override
	public TaskResult<Boolean> changePassword(String authorizationToken, String currentPassword, String newPassword) {
		var result = new TaskResult<Boolean>();

		var session = sessionRepository.findByToken(authorizationToken);
		var user = userRepository.findById(session.getUserId());
		if (!passwordService.matches(currentPassword, user.getPassword())) {
			result.getErrors().put("currentPassword", "Current password is incorrect.");
			return result.setFailed();
		}
		var hashedPassword = passwordService.hash(newPassword);
		userRepository.changePassword(user.getId(), hashedPassword);

		return result.setSuccessful(true);
	}

	@Override
	public TaskResult<String> resetPassword(long userId) {
		var result = new TaskResult<String>();

		var plainPassword = passwordService.generateRandom();
		var hashedPassword = passwordService.hash(plainPassword);
		userRepository.changePassword(userId, hashedPassword);

		return result.setSuccessful(plainPassword);
	}

}
