package com.roofnfloor.roofnfloorz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.roofnfloor.roofnfloorz.model.PropertyAddress;

@Repository
public interface PropertyAddressRepository extends JpaRepository<PropertyAddress, Long>{

	@Query(value="Select * from  property_address where  city = :cityCode",nativeQuery = true)
	List<PropertyAddress> fetchFeaturedPropertyList(Long cityCode);
	
	@Query(value="Select * from  property_address where  city = :cityCode",nativeQuery = true)
	List<PropertyAddress> fetchAllPropertyList(Long cityCode);

	@Query(value="Select * from  property_address where  pid = :id",nativeQuery = true)
	List<PropertyAddress> fetchAddressByPropertyId(Long id);

	@Modifying
	@Query(value="Truncate table property_address CASCADE",nativeQuery = true)
	void truncateTable();

	@Query(value="Delete from property_address where pid=:propertyId",nativeQuery = true)
	int deletePropertyAddress(Long propertyId);

	@Query(value="Select * from property_address where lower(landmark) LIKE %:location% OR lower(address_line) LIKE %:location%",nativeQuery = true)
	List<PropertyAddress> fetchAllSimilarAddress(String location);
}
