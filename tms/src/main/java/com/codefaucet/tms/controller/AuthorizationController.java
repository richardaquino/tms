package com.codefaucet.tms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codefaucet.tms.model.SignInInfo;
import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.service.IAuthorizationService;

@RestController
@RequestMapping("auth")
public class AuthorizationController {
	
	private IAuthorizationService authorizationService;
	
	@PostMapping("signIn")
	public TaskResult<SignInInfo> signIn() {
		var username = "";
		var password = "";
		return authorizationService.signIn(username, password);
	}
	
	@PostMapping("validateSession")
	public SignInInfo validateSession()
	{
		var token = "";
		return authorizationService.validateSession(token);
	}
	
}
