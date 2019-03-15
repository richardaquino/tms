package com.codefaucet.tms.model.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.User;
import com.codefaucet.tms.model.UserRole;
import com.codefaucet.tms.security.UserPrincipal;

public interface IUserService extends UserDetailsService {

	TaskResult<User> create(User user);

	TaskResult<User> update(User user);

	TaskResult<Boolean> setDeleted(long userId, boolean deleted);

	List<User> find(String queryString, UserRole role, boolean includeInactive, int pageNumber, int pageSize);

	User findById(long userId);

	User findNew();

	TaskResult<Boolean> changePassword(String authorizationToken, String currentPassword, String newPassword);

	TaskResult<String> resetPassword(long userId);

	UserPrincipal findUserPrincipalByUserId(long userId);

}
