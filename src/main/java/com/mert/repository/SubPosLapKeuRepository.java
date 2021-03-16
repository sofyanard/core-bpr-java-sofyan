package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.SubPosLapKeu;

public interface SubPosLapKeuRepository extends JpaRepository<SubPosLapKeu, String> {
	
	@Query(value = "select * from subposlapkeu where pos_id = :posId order by subpos_id ", nativeQuery=true)
	List<SubPosLapKeu> findByPos(@Param("posId") String posId);

}
