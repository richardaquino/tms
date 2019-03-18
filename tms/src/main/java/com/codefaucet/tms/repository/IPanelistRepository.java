package com.codefaucet.tms.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codefaucet.tms.model.domain.Panelist;

@Repository
public interface IPanelistRepository extends JpaRepository<Panelist, Long> {

	@Query(
		"select p from Panelist p "
		+ "where "
		+ "(:includeInactive or p.active) "
		+ "and ("
		+ "p.lastName like concat(:queryString, '%') "
		+ "or p.firstName like concat(:queryString, '%') "
		+ "or p.middleName like concat(:queryString, '%') "
		+ "or p.title like concat(:queryString, '%') "
		+ ")"
	)
	List<Panelist> find(
		@Param("queryString") String queryString, 
		@Param("includeInactive") boolean includeInactive, 
		Pageable pageable
	);

}
