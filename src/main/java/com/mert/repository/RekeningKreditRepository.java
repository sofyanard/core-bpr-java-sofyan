package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.RekeningKredit;

public interface RekeningKreditRepository extends JpaRepository<RekeningKredit, String> {
	
	@Query(value = "select * from rekeningkredit n where n.no_fasilitas = :nofasilitas ", nativeQuery=true)
	RekeningKredit findByNoFasilitas(@Param("nofasilitas") String nofasilitas);

}
