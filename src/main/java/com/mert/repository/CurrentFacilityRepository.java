package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.CurrentFacility;

public interface CurrentFacilityRepository extends JpaRepository<CurrentFacility, Integer> {
	
	@Query(value = "select * from currentfacility n where n.nasabah_id = :nasabahId and n.produk_id = :produkId ", nativeQuery=true)
	CurrentFacility findByNasabahIdAndProdukId(@Param("nasabahId") Long nasabahId, @Param("produkId") String produkId);

}
