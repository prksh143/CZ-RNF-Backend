package com.roofnfloor.roofnfloorz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.roofnfloor.roofnfloorz.model.PricingPackage;
@Repository
public interface PricingPackageRepository extends JpaRepository<PricingPackage, Long> {

	@Query(value="Select * from pricing_package where package_code = :code",nativeQuery = true)
	PricingPackage fetchPricingPackageByCode(String code);

	@Query(value="Delete from pricing_package where package_code = :packageCode",nativeQuery = true)
	void deletePackageByCode(String packageCode);
	
	@Modifying
	@Query(value="Truncate table pricing_package CASCADE",nativeQuery = true)
	void truncateTable();
}
