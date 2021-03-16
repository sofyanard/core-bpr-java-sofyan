package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.PosLapKeuUnit;

public interface PosLapKeuUnitRepository extends JpaRepository<PosLapKeuUnit, Integer> {
	
	@Query(value = "select * from poslapkeuunit where pos_id = :posId and unit_id = :unitId ", nativeQuery=true)
	PosLapKeuUnit findByPosAndUnit(@Param("posId") String posId, @Param("unitId") String unitId);

}
