package com.codefaucet.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codefaucet.tms.model.domain.Session;

@Repository
public interface ISessionRepository extends JpaRepository<Session, Long> {

	Session findFirstByUserIdOrderByExpirationDesc(long userId);

	Session findByToken(String token);
	
}
