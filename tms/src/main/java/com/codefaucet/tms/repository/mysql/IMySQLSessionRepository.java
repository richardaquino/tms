package com.codefaucet.tms.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMySQLSessionRepository extends JpaRepository<Session, Long> {

	Session findByUserIdOrderByExpirationDesc(int userId);

	Session findByToken(String token);

}
