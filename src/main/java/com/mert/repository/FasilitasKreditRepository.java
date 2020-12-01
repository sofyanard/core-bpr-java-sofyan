package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.FasilitasKredit;

public interface FasilitasKreditRepository extends JpaRepository<FasilitasKredit, String> {
	
	@Query(value = "select * from fasilitaskredit n where n.no_nasabah = :nonasabah ", nativeQuery=true)
	List<FasilitasKredit> findByNoNasabah(@Param("nonasabah") Long nonasabah);
	
	@Query(value = "select * from fasilitaskredit n where lower(n.nama_nasabah) like %:namanasabah% ", nativeQuery=true)
	List<FasilitasKredit> findByNamaNasabah(@Param("namanasabah") String namanasabah);

}
