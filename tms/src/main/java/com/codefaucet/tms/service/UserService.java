package com.codefaucet.tms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.UserRole;
import com.codefaucet.tms.model.domain.User;
import com.codefaucet.tms.model.dto.UserDTO;
import com.codefaucet.tms.model.service_interface.IPasswordService;
import com.codefaucet.tms.model.service_interface.IUserService;
import com.codefaucet.tms.repository.ISessionRepository;
import com.codefaucet.tms.repository.IUserRepository;
import com.codefaucet.tms.security.UserPrincipal;
import com.codefaucet.tms.service.mapper.UserMapper;
import com.codefaucet.tms.service.validator.UserValidator;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private IPasswordService passwordService;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ISessionRepository sessionRepository;

	@Override
	public TaskResult<UserDTO> create(UserDTO dto) {
		var result = new TaskResult<UserDTO>();
		
		result.setErrors(userValidator.validate(dto));
		if (!result.getErrors().isEmpty()) {
			return result.setFailed();
		}
		
		var plainPassword = passwordService.generateRandom();
		var hashedPassword = passwordService.hash(plainPassword);
		
		var user = new User();
		userMapper.mapToDomain(user, dto);
		user.setPassword(hashedPassword);
		user = userRepository.save(user);
		
		dto = userMapper.mapToDTO(user);
		dto.setPassword(plainPassword);
		
		return result.setSuccessful(userMapper.mapToDTO(user));
	}

	@Override
	public TaskResult<UserDTO> update(UserDTO dto) {
		var result = new TaskResult<UserDTO>();
		
		result.setErrors(userValidator.validate(dto));
		if (!result.getErrors().isEmpty()) {
			return result.setFailed();
		}
		
		var user = userRepository.findById(dto.getId()).get();
		userMapper.mapToDomain(user, dto);
		user = userRepository.save(user);
		
		dto = userMapper.mapToDTO(user);
		dto.setPassword("");
		
		return result.setSuccessful(dto);
	}

	@Override
	public TaskResult<Boolean> setActive(long userId, boolean active) {
		var user = userRepository.findById(userId).get();
		if(user.isActive() != active) {
			user.setActive(active);
			user = userRepository.save(user);
		}
		return new TaskResult<Boolean>().setSuccessful(true);
	}

	@Override
	public List<UserDTO> find(String queryString, UserRole role, boolean includeInactive, int pageNumber, int pageSize) {
		var dtos = new ArrayList<UserDTO>();
		
		var roles = new ArrayList<UserRole>();
		if(role == UserRole.UNKNOWN) {
			roles.add(UserRole.ADMIN);
			roles.add(UserRole.ENCODER);
		}
		else {
			roles.add(role);
		}
		var pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("lastName", "firstName", "middleName"));
		var users = userRepository.find(queryString, roles, includeInactive, pageable);
		users.forEach(item -> {
			var dto = userMapper.mapToDTO(item);
			dto.setPassword("");
			dtos.add(dto);
		});
		
		return dtos;
	}

	@Override
	public UserDTO findById(long userId) {
		var user = userRepository.findById(userId).get();
		var dto = userMapper.mapToDTO(user);
		dto.setPassword("");
		return dto;
	}

	@Override
	public UserDTO findNew() {
		return userMapper.mapToDTO(new User());
	}

	@Override
	public TaskResult<Boolean> changePassword(String authorizationToken, String currentPassword, String newPassword) {
		var result = new TaskResult<Boolean>();

		var session = sessionRepository.findByToken(authorizationToken);
		var user = session.getUser();
		if (!passwordService.matches(currentPassword, user.getPassword())) {
			result.getErrors().put("currentPassword", "Current password is incorrect.");
			return result.setFailed();
		}
		var hashedPassword = passwordService.hash(newPassword);
		user.setPassword(hashedPassword);
		user = userRepository.save(user);

		return result.setSuccessful(true);
	}

	@Override
	public TaskResult<String> resetPassword(long userId) {
		var result = new TaskResult<String>();

		var plainPassword = passwordService.generateRandom();
		var hashedPassword = passwordService.hash(plainPassword);
		
		var user = userRepository.findById(userId).get();
		user.setPassword(hashedPassword);
		user = userRepository.save(user);

		return result.setSuccessful(plainPassword);
	}

	@Override
	public UserPrincipal findUserPrincipalByUserId(long userId) {
		var user = userRepository.findById(userId).get();
		if (user == null) {
			return null;
		}
		return new UserPrincipal(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username '" + username + "' not found.");
		}
		return new UserPrincipal(user);
	}

}
