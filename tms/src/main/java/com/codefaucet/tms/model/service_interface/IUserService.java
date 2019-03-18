package com.codefaucet.tms.model.service_interface;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.UserRole;
import com.codefaucet.tms.model.dto.UserDTO;
import com.codefaucet.tms.security.UserPrincipal;

public interface IUserService extends UserDetailsService {

	TaskResult<UserDTO> create(UserDTO user);

	TaskResult<UserDTO> update(UserDTO user);

	TaskResult<Boolean> setActive(long userId, boolean deleted);

	List<UserDTO> find(String queryString, UserRole role, boolean includeInactive, int pageNumber, int pageSize);

	UserDTO findById(long userId);

	UserDTO findNew();

	TaskResult<Boolean> changePassword(String authorizationToken, String currentPassword, String newPassword);

	TaskResult<String> resetPassword(long userId);

	UserPrincipal findUserPrincipalByUserId(long userId);

}
