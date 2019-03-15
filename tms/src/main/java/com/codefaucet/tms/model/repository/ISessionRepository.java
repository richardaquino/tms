package com.codefaucet.tms.model.repository;

import com.codefaucet.tms.model.Session;

public interface ISessionRepository {

	Session findLatestByUserId(long userId);

	Session create(Session session);

	Session findByToken(String token);

	Session update(Session session);

}
