package com.codefaucet.tms.repository.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codefaucet.tms.model.Session;
import com.codefaucet.tms.model.repository.ISessionRepository;

@Service
public class SessionRepository implements ISessionRepository {

	@Autowired
	private IMySQLSessionRepository sessionRepository;

	@Override
	public Session findLatestByUserId(long userId) {
		var session = sessionRepository.findByUserIdOrderByExpirationDesc((int) userId);
		return new Session(session.getId(), session.getToken(), session.getExpiration(), session.getUser().getId());
	}

	@Override
	public Session create(Session sessionModel) {
		var dbSession = new com.codefaucet.tms.repository.mysql.Session(sessionModel.getToken(),
				sessionModel.getExpiration());
		dbSession = sessionRepository.save(dbSession);
		return new Session(dbSession.getId(), dbSession.getToken(), dbSession.getExpiration(),
				dbSession.getUser().getId());
	}

	@Override
	public Session findByToken(String token) {
		var session = sessionRepository.findByToken(token);
		return new Session(session.getId(), session.getToken(), session.getExpiration(), session.getUser().getId());
	}

	@Override
	public Session update(Session sessionModel) {
		var session = sessionRepository.findById(sessionModel.getId()).get();
		session.setExpiration(sessionModel.getExpiration());
		session = sessionRepository.save(session);
		return new Session(session.getId(), session.getToken(), session.getExpiration(), session.getUser().getId());
	}

}
