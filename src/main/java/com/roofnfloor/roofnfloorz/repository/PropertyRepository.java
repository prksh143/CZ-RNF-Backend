package com.roofnfloor.roofnfloorz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.roofnfloor.roofnfloorz.model.Property;
import com.roofnfloor.roofnfloorz.model.PropertyAddress;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{

	@Query(value="Select * from property",nativeQuery = true)
	List<Property> fetchFeaturedPropertyList();

	@Query(value="Select * from property where is_rent = false and lower(property_title) LIKE %:location%",nativeQuery = true)
	List<Property> fetchAllBuyProperty(String location);

	@Query(value="Select * from property where is_rent = true and lower(property_title) LIKE %:location% ",nativeQuery = true)
	List<Property> fetchAllRentProperty(String location);

	@Query(value="Select * from property where property_type = 2",nativeQuery = true)
	List<Property> fetchAllCommercialProperty();
	
	@Query(value="Select * from property where is_project = true  and lower(property_title) LIKE %:location%",nativeQuery = true)
	List<Property> fetchAllProjectProperty(String location);
	
	@Modifying
	@Query(value="Truncate table property CASCADE",nativeQuery = true)
	void truncateTable();
}
