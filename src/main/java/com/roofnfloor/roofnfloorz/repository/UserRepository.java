package com.roofnfloor.roofnfloorz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.roofnfloor.roofnfloorz.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value="Select * from users where mobile = :mobile",nativeQuery = true)
	User fetchUserByMobile(String mobile);

	@Query(value="Select * from users where email = :email",nativeQuery = true)
	User fetchUserByEmail(String email);
	
	@Query(value="Select * from users where email = :email and password =:password",nativeQuery = true)
	User loginUser(String email, String password);

	@Modifying
	@Query(value="Truncate table users CASCADE",nativeQuery = true)
	void truncateTable();
}
