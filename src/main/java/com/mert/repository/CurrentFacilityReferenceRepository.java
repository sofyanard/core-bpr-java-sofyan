package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.CurrentFacilityReference;

public interface CurrentFacilityReferenceRepository extends JpaRepository<CurrentFacilityReference, Integer> {
	
	@Query(value = "select * from currentfacilityreference n "
			+ " where n.reference_date = :referenceDate "
			+ " and n.produk_id = :produkId "
			+ " and n.nasabah_id = :nasabahId "
			+ " and n.unit_id = :unitId ", nativeQuery=true)
	CurrentFacilityReference findByProps(
			@Param("referenceDate") String referenceDate,
			@Param("produkId") String produkId,
			@Param("nasabahId") Long nasabahId,
			@Param("unitId") String unitId);

}
