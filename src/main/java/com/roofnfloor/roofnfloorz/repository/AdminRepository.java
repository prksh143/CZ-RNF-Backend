package com.roofnfloor.roofnfloorz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.roofnfloor.roofnfloorz.model.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Long>{

	@Query(value="Select * from admin where email = :email and password =:password",nativeQuery = true)
	List<Admin> loginUser(String email, String password);

	@Modifying
	@Query(value="Truncate table admin CASCADE",nativeQuery = true)
	void truncateTable();

	@Query(value="Select * from admin where email = :email",nativeQuery = true)
	Admin fetchAdminByEmail(String email);
}
