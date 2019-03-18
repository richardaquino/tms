package com.codefaucet.tms.model.service_interface;

import com.codefaucet.tms.model.TaskResult;
import com.codefaucet.tms.model.dto.SignInInfoDTO;

public interface IAuthorizationService {

	TaskResult<SignInInfoDTO> signIn(String username, String password);

	TaskResult<SignInInfoDTO> validateSession(String token);

}
