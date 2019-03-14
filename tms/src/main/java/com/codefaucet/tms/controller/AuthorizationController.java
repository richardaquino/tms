package com.codefaucet.tms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codefaucet.tms.model.SignInInfo;
import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.service.IAuthorizationService;
import com.codefaucet.tms.model.service.IEncoderService;

@RestController
@RequestMapping("auth")
public class AuthorizationController {
	
	private IAuthorizationService authorizationService;
	
	private IEncoderService encoderService;
	
	@PostMapping("signIn")
	public TaskResult<SignInInfo> signIn(@RequestHeader("Authorization") String encodedCredentials) {
		var plainCredentials = encoderService.decode(encodedCredentials).split(":");
		var username = plainCredentials[0];
		var password = plainCredentials[1];
		return authorizationService.signIn(username, password);
	}
	
	@PostMapping("validateSession")
	public SignInInfo validateSession(@RequestHeader("Authorization") String authorizationToken)
	{
		return authorizationService.validateSession(authorizationToken);
	}
	
}
