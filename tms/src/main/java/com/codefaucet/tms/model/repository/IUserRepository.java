package com.codefaucet.tms.model.repository;

import java.util.List;

import com.codefaucet.tms.model.User;
import com.codefaucet.tms.model.UserRole;

public interface IUserRepository {

	User findByUsername(String username);

	boolean usersExists();

	User create(User user);

	User findById(long userId);

	User update(User user);

	void setDeleted(long userId, boolean deleted);

	List<User> find(String queryString, UserRole role, boolean includeInactive, int pageNumber, int pageSize);

	void changePassword(long id, String hashedPassword);

}
