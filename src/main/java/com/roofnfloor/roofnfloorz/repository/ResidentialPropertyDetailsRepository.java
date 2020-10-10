package com.roofnfloor.roofnfloorz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.roofnfloor.roofnfloorz.model.ResidentialPropertyDetails;

@Repository
public interface ResidentialPropertyDetailsRepository extends JpaRepository<ResidentialPropertyDetails, Long>{
	
	@Query(value="Select * from  residential_property_details where  pid = :id",nativeQuery = true)
	List<ResidentialPropertyDetails> fetchDetailsByPropertyId(Long id);

	@Modifying
	@Query(value="Truncate table residential_property_details CASCADE",nativeQuery = true)
	void truncateTable();

	@Query(value="Delete from residential_property_details where pid=:propertyId",nativeQuery = true)
	int deletePropertyDetails(Long propertyId);
}
