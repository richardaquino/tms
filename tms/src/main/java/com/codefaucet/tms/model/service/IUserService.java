package com.codefaucet.tms.model.service;

import java.util.List;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.User;
import com.codefaucet.tms.model.UserRole;

public interface IUserService {

	TaskResult<User> create(User user);

	TaskResult<User> update(User user);

	TaskResult<Boolean> setDeleted(long userId, boolean deleted);

	List<User> find(String queryString, UserRole role, boolean includeInactive, int pageNumber, int pageSize);

	User findById(long userId);

	User findNew();

	TaskResult<Boolean> changePassword(String authorizationToken, String currentPassword, String newPassword);

	TaskResult<String> resetPassword(long userId);

}
