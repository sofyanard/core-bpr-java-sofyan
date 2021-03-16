package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.SubSubPosLapKeu;

public interface SubSubPosLapKeuRepository extends JpaRepository<SubSubPosLapKeu, String> {
	
	@Query(value = "select * from subsubposlapkeu where subpos_id = :subPosId order by subsubpos_id ", nativeQuery=true)
	List<SubSubPosLapKeu> findBySubPos(@Param("subPosId") String subPosId);

}
