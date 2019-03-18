package com.codefaucet.tms.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codefaucet.tms.model.UserRole;
import com.codefaucet.tms.model.domain.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	@Query(
		"select u from User u "
		+ "where "
		+ "u.role in :roles "
		+ "and (:includeInactive = true or u.active = true) "
		+ "and ("
		+ "u.lastName like concat(:queryString, '%') "
		+ "or u.firstName like concat(:queryString, '%') "
		+ "or u.middleName like concat(:queryString, '%') "
		+ "or u.username like concat(:queryString, '%') "
		+ ")")
	List<User> find(
		@Param("queryString") String queryString, 
		@Param("roles") List<UserRole> roles,
		@Param("includeInactive") boolean includeInactive, 
		Pageable pageable
	);

}
