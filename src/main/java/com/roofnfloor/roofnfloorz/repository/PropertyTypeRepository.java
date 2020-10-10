package com.roofnfloor.roofnfloorz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.roofnfloor.roofnfloorz.model.PropertyType;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, Long> {

	@Query(value="Select * from property_type where type_code = :propertyType",nativeQuery = true)
	PropertyType fetchPropertyTypeByCode(String propertyType);

	@Modifying
	@Query(value="Truncate table property_type CASCADE",nativeQuery = true)
	void truncateTable();
}
