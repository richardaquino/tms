package com.codefaucet.tms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.Session;
import com.codefaucet.tms.model.repository.ISessionRepository;
import com.codefaucet.tms.model.service.ISessionService;

@Service
public class SessionService implements ISessionService {

	@Autowired
	private ISessionRepository sessionRepository;

	@Override
	public Session findByToken(String authorizationToken) {
		return sessionRepository.findByToken(authorizationToken);
	}

	@Override
	public Session update(Session session) {
		return sessionRepository.update(session);
	}

}
