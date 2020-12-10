package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.BukuBesar;

public interface BukuBesarRepository extends JpaRepository<BukuBesar, String> {
	
	@Query(value = "select * from bukubesar where unit_id = :unitId limit 1 ", nativeQuery=true)
	BukuBesar findByUnit(@Param("unitId") String unitId);

}
