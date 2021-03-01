package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.KodeEodUnit;


public interface KodeEodUnitRepository extends JpaRepository<KodeEodUnit, Integer> {
	
	@Query(value = "select * from kodeeodunit n where n.kode_eod = :kodeEod order by n.unit_id ", nativeQuery=true)
	List<KodeEodUnit> findByKodeEod(@Param("kodeEod") String kodeEod);
	
	@Query(value = "select * from kodeeodunit n where n.kode_eod = :kodeEod and n.unit_id = :unitId order by n.unit_id ", nativeQuery=true)
	KodeEodUnit findByKodeEodAndUnit(@Param("kodeEod") String kodeEod, @Param("unitId") String unitId);

}
