package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mert.model.EodLapKeu;

public interface EodLapKeuRepository extends JpaRepository<EodLapKeu, String> {
	
	@Query(value = "select * from eodlapkeu ", nativeQuery=true)
	List<EodLapKeu> findAllEom();
	
	@Query(value = "select * from eodlapkeu where coalesce(eom_only,'') != '1' ", nativeQuery=true)
	List<EodLapKeu> findAllEod();

}
