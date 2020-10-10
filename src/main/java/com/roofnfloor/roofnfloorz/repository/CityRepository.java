package com.roofnfloor.roofnfloorz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.roofnfloor.roofnfloorz.model.City;
import com.roofnfloor.roofnfloorz.model.Property;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	@Query(value = "Select * from city where city_code = :cityCode", nativeQuery = true)
	City fetchCityByCityCode(String cityCode);

	@Query(value = "Delete from city where city_code = :cityCode", nativeQuery = true)
	void deleteCityByCityCode(String cityCode);

	@Modifying
	@Query(value = "truncate table city cascade", nativeQuery = true)
	void truncateTable();


	@Query(value="Select * from city where lower(city_name) LIKE %:location%",nativeQuery = true)
	List<City> fetchAllSimilarCities(String location);

}
