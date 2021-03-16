package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.EodLapKeuUnit;

public interface EodLapKeuUnitRepository extends JpaRepository<EodLapKeuUnit, Integer> {
	
	@Query(value = "select * from eodlapkeuunit where lapkeu_id = :lapKeuId ", nativeQuery=true)
	List<EodLapKeuUnit> findByLapKeu(@Param("lapKeuId") String lapKeuId);

}
