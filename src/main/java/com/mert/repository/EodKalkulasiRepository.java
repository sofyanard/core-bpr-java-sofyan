package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.EodKalkulasi;

public interface EodKalkulasiRepository extends JpaRepository<EodKalkulasi, Long> {
	
	@Query(value = "select * from eodkalkulasi " + 
			"where to_char(eod_tanggal, 'yyyy-MM-dd') = :strDate " + 
			"and kode_eod = :kodeEod " + 
			"and unit_id = :unitId " + 
			"order by id ", nativeQuery=true)
	List<EodKalkulasi> ListOnTanggalKodeEodUnit(@Param("strDate") String strDate, @Param("kodeEod") String kodeEod, @Param("unitId") String unitId);
	
	@Query(value = "select count(1) from eodkalkulasi " + 
			"where to_char(eod_tanggal, 'yyyy-MM-dd') = :strDate " + 
			"and kode_eod = :kodeEod " + 
			"and unit_id = :unitId ", nativeQuery=true)
	Integer CountOnTanggalKodeEodUnit(@Param("strDate") String strDate, @Param("kodeEod") String kodeEod, @Param("unitId") String unitId);
	
	@Query(value = "select * from eodkalkulasi " + 
			"where to_char(eod_tanggal, 'yyyy-MM-dd') = :strDate " + 
			"and kode_eod = :kodeEod " + 
			"order by id ", nativeQuery=true)
	List<EodKalkulasi> ListOnTanggalKodeEod(@Param("strDate") String strDate, @Param("kodeEod") String kodeEod);
	
	@Query(value = "select count(1) from eodkalkulasi " + 
			"where to_char(eod_tanggal, 'yyyy-MM-dd') = :strDate " + 
			"and kode_eod = :kodeEod ", nativeQuery=true)
	Integer CountOnTanggalKodeEod(@Param("strDate") String strDate, @Param("kodeEod") String kodeEod);

}
