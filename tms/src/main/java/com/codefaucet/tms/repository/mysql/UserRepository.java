package com.codefaucet.tms.repository.mysql;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.User;
import com.codefaucet.tms.model.UserRole;
import com.codefaucet.tms.model.repository.IUserRepository;

@Service
public class UserRepository implements IUserRepository {

	@Autowired
	private IMySQLUserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		var user = userRepository.findByUsername(username);
		return new User(user.getId(), user.isActive(), user.getRole(), user.getUsername(), user.getPassword(),
				user.getLastName(), user.getFirstName(), user.getMiddleName());
	}

	@Override
	public boolean usersExists() {
		return userRepository.count() > 0;
	}

	@Override
	public User create(User userModel) {
		var user = new com.codefaucet.tms.repository.mysql.User(userModel.getRole(), userModel.getUsername(),
				userModel.getPassword(), userModel.getLastName(), userModel.getFirstName(), userModel.getMiddleName());
		user = userRepository.save(user);
		return new User(user.getId(), user.isActive(), user.getRole(), user.getUsername(), user.getPassword(),
				user.getLastName(), user.getFirstName(), user.getMiddleName());
	}

	@Override
	public User findById(long userId) {
		var user = userRepository.findById(userId).get();
		return new User(user.getId(), user.isActive(), user.getRole(), user.getUsername(), user.getPassword(),
				user.getLastName(), user.getFirstName(), user.getMiddleName());
	}

	@Override
	public User update(User userModel) {
		var user = userRepository.findById(userModel.getId()).get();
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setMiddleName(userModel.getMiddleName());
		user.setRole(userModel.getRole());
		user.setUsername(userModel.getUsername());
		user = userRepository.save(user);
		return new User(user.getId(), user.isActive(), user.getRole(), user.getUsername(), user.getPassword(),
				user.getLastName(), user.getFirstName(), user.getMiddleName());
	}

	@Override
	public void setDeleted(long userId, boolean deleted) {
		var user = userRepository.findById(userId).get();
		user.setActive(!deleted);
		userRepository.save(user);
	}

	@Override
	public List<User> find(String queryString, UserRole role, boolean includeInactive, int pageNumber, int pageSize) {
		var pageable = PageRequest.of(pageNumber - 1, pageSize);
		var users = userRepository.find(queryString, role, includeInactive, pageable);
		return users.stream().map(item -> new User(item.getId(), item.isActive(), item.getRole(), item.getUsername(),
				"", item.getLastName(), item.getFirstName(), item.getMiddleName())).collect(Collectors.toList());
	}

	@Override
	public void changePassword(long userId, String hashedPassword) {
		var user = userRepository.findById(userId).get();
		user.setPassword(hashedPassword);
		userRepository.save(user);
	}

}
