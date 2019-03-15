package com.codefaucet.tms.model.service;

import com.codefaucet.tms.model.Session;

public interface ISessionService {

	Session findByToken(String authorizationToken);

	Session update(Session session);

}
