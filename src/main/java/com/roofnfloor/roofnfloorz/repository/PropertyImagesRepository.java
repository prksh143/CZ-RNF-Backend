package com.roofnfloor.roofnfloorz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.roofnfloor.roofnfloorz.model.PropertyImages;

@Repository
public interface PropertyImagesRepository extends JpaRepository<PropertyImages, Long>{

	@Modifying
	@Query(value="Truncate table property_images CASCADE",nativeQuery = true)
	void truncateTable();

	@Modifying
	@Query(value="Delete from property_images where pid=:propertyId",nativeQuery = true)
	void deletePropertyImages(Long propertyId);

	@Query(value="Select * from property_images where pid=:propertyId",nativeQuery = true)
	List<PropertyImages> fetchImagesByPropertyId(Long propertyId);

}
