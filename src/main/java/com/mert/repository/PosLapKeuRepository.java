package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.PosLapKeu;

public interface PosLapKeuRepository extends JpaRepository<PosLapKeu, String> {
	
	@Query(value = "select * from poslapkeu where posisi_id = :posisiId order by pos_id ", nativeQuery=true)
	List<PosLapKeu> findByPosisi(@Param("posisiId") String posisiId);

}
