package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.SubSubPosLapKeuUnit;

public interface SubSubPosLapKeuUnitRepository extends JpaRepository<SubSubPosLapKeuUnit, Integer> {
	
	@Query(value = "select * from subsubposlapkeuunit where subsubpos_id = :subSubPosId and unit_id = :unitId ", nativeQuery=true)
	SubSubPosLapKeuUnit findBySubSubPosAndUnit(@Param("subSubPosId") String subSubPosId, @Param("unitId") String unitId);

}
