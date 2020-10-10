package com.roofnfloor.roofnfloorz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.roofnfloor.roofnfloorz.model.ResidentialPropertyAmneties;

@Repository
public interface ResidentialPropertyAmnetiesRepository extends JpaRepository<ResidentialPropertyAmneties, Long>{

	@Query(value="Select * from  residential_property_amneties where  pid = :id",nativeQuery = true)
	List<ResidentialPropertyAmneties> fetchAmnetiesByPropertyId(Long id);

	@Modifying
	@Query(value="Truncate table residential_property_amneties CASCADE",nativeQuery = true)
	void truncateTable();

	@Query(value="Delete from residential_property_amneties where pid=:propertyId",nativeQuery = true)
	int deletePropertyAmneties(Long propertyId);
}
