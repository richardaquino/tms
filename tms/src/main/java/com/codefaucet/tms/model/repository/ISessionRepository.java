package com.codefaucet.tms.model.repository;

import com.codefaucet.tms.model.Session;

public interface ISessionRepository {

	Session findByUserOrderByExpirationDesc(long id);

}
