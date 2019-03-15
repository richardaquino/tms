package com.codefaucet.tms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.User;
import com.codefaucet.tms.model.UserRole;
import com.codefaucet.tms.model.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@PostMapping("/create")
	public TaskResult<User> create(@RequestBody User user) {
		return userService.create(user);
	}
	
	@PostMapping("/update")
	public TaskResult<User> update(@RequestBody User user) {
		return userService.update(user);
	}
	
	@PostMapping("/delete")
	public TaskResult<Boolean> delete(@RequestBody long userId) {
		return userService.setDeleted(userId, true);
	}
	
	@PostMapping("/restore")
	public TaskResult<Boolean> restore(@RequestBody long userId) {
		return userService.setDeleted(userId, false);
	}
	
	@PostMapping("/find")
	public List<User> find(@RequestBody Map<String, Object> parameter) {
		var queryString = (String)parameter.get("queryString");
		var role = UserRole.valueOf((String)parameter.get("role"));
		var includeInactive = (boolean)parameter.get("includeInactive");
		var pageNumber = (int)parameter.get("pageNumber");
		var pageSize = (int)parameter.get("pageSize");
		return userService.find(queryString, role, includeInactive, pageNumber, pageSize);
	}
	
	@PostMapping("/findById")
	public User findById(@RequestBody long userId) {
		return userService.findById(userId);
	}
	
	@PostMapping("/findNew")
	public User findNew() {
		return userService.findNew();
	}
	
	@PostMapping("/changePassword")
	public TaskResult<Boolean> changePassword(@RequestHeader("Authorization") String authorizationToken, @RequestBody Map<String, Object> parameter) {
		authorizationToken = authorizationToken.substring(7);
		var currentPassword = (String)parameter.get("currentPassword");
		var newPassword = (String)parameter.get("newPassword");
		return userService.changePassword(authorizationToken, currentPassword, newPassword);
	}
	
	@PostMapping("/resetPassword")
	public TaskResult<String> resetPassword(@RequestBody long userId) {
		return userService.resetPassword(userId);
	}
	
	@GetMapping("/test")
	public String test() {
		return "api test";
	}
	
}
