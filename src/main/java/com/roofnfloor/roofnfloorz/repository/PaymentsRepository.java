package com.roofnfloor.roofnfloorz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.roofnfloor.roofnfloorz.model.Payments;
@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {

	@Modifying
	@Query(value="truncate table payments cascade",nativeQuery = true)
	void truncateTable();
}
