package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.PosisiLapKeu;

public interface PosisiLapKeuRepository extends JpaRepository<PosisiLapKeu, String> {
	
	@Query(value = "select * from posisilapkeu where jenis_id = :jenisId order by posisi_id ", nativeQuery=true)
	List<PosisiLapKeu> findByJenis(@Param("jenisId") String jenisId);

}
