package com.codefaucet.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codefaucet.tms.model.SignInInfo;
import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.service.IAuthorizationService;
import com.codefaucet.tms.model.service.IEncoderService;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
	
	@Autowired
	private IAuthorizationService authorizationService;
	
	@Autowired
	private IEncoderService encoderService;
	
	@PostMapping("/signIn")
	public TaskResult<SignInInfo> signIn(@RequestHeader("Authorization") String encodedCredentials) {
		encodedCredentials = encodedCredentials.substring(6);
		var plainCredentials = encoderService.decode(encodedCredentials).split(":");
		var username = plainCredentials[0];
		var password = plainCredentials[1];
		return authorizationService.signIn(username, password);
	}
	
	@PostMapping("/validateSession")
	public TaskResult<SignInInfo> validateSession(@RequestHeader("Authorization") String authorizationToken)
	{
		authorizationToken = authorizationToken.substring(7);
		return authorizationService.validateSession(authorizationToken);
	}
	
}
