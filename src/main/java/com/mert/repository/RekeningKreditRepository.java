package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.RekeningKredit;

public interface RekeningKreditRepository extends JpaRepository<RekeningKredit, String> {
	
	@Query(value = "select * from rekeningkredit n where n.no_fasilitas = :nofasilitas ", nativeQuery=true)
	RekeningKredit findByNoFasilitas(@Param("nofasilitas") String nofasilitas);
	
	@Query(value = "select * from rekeningkredit n where n.no_nasabah = :nonasabah ", nativeQuery=true)
	List<RekeningKredit> findByNoNasabah(@Param("nonasabah") Long nonasabah);
	
	@Query(value = "select * from rekeningkredit n where lower(n.nama_nasabah) like %:namanasabah% ", nativeQuery=true)
	List<RekeningKredit> findByNamaNasabah(@Param("namanasabah") String namanasabah);

}
