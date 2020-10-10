package com.roofnfloor.roofnfloorz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.roofnfloor.roofnfloorz.model.Contacted;

@Repository
public interface ContactedRepository  extends JpaRepository<Contacted, Long> {

	@Modifying
	@Query(value="truncate  table contacted cascade",nativeQuery = true)
	void truncateTable();
}
