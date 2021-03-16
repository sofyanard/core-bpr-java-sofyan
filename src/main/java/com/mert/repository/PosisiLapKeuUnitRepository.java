package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.PosisiLapKeuUnit;

public interface PosisiLapKeuUnitRepository extends JpaRepository<PosisiLapKeuUnit, Integer> {
	
	@Query(value = "select * from posisilapkeuunit where posisi_id = :posisiId and unit_id = :unitId ", nativeQuery=true)
	PosisiLapKeuUnit findByPosisiAndUnit(@Param("posisiId") String posisiId, @Param("unitId") String unitId);

}
