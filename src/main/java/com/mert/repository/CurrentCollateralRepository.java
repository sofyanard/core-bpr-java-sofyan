package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.CurrentCollateral;

public interface CurrentCollateralRepository extends JpaRepository<CurrentCollateral, Integer> {
	
	@Query(value = "select * from currentcollateral n where n.nasabah_id = :nasabahId and n.jenisagunan_id = :jenisAgunanId ", nativeQuery=true)
	CurrentCollateral findByNasabahIdAndJenisAgunanId(@Param("nasabahId") Long nasabahId, @Param("jenisAgunanId") String jenisAgunanId);

}
