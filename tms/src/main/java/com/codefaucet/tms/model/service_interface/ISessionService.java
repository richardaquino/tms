package com.codefaucet.tms.model.service_interface;

import com.codefaucet.tms.model.dto.SessionDTO;

public interface ISessionService {

	SessionDTO findByToken(String authorizationToken);

	SessionDTO update(SessionDTO session);

}
