package com.roofnfloor.roofnfloorz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.roofnfloor.roofnfloorz.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long>{

	@Query(value="Select * from user_type where type_code = :code",nativeQuery = true)
	UserType fetchUserTypeByCode(String code);

	@Modifying
	@Query(value="Truncate table user_type CASCADE",nativeQuery = true)
	void truncateTable();
}
