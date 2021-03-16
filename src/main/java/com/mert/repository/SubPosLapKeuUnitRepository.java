package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.SubPosLapKeuUnit;

public interface SubPosLapKeuUnitRepository extends JpaRepository<SubPosLapKeuUnit, Integer> {
	
	@Query(value = "select * from subposlapkeuunit where subpos_id = :subPosId and unit_id = :unitId ", nativeQuery=true)
	SubPosLapKeuUnit findBySubPosAndUnit(@Param("subPosId") String subPosId, @Param("unitId") String unitId);

}
