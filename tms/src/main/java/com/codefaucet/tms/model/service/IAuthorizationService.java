package com.codefaucet.tms.model.service;

import com.codefaucet.tms.model.SignInInfo;
import com.codefaucet.tms.model.TaskResult;

public interface IAuthorizationService {

	TaskResult<SignInInfo> signIn(String username, String password);

	SignInInfo validateSession(String token);

}
