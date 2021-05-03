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
	
	@Query(value = "select * from rekeningkredit n where n.produk = :produkId and status_rekening in ('1','2','3','4','5','7') ", nativeQuery=true)
	List<RekeningKredit> findByProduk(@Param("produkId") String produkId);
	
	
	
	@Query(value = "select n.* from rekeningkredit n " +
			"where n.status_rekening = '1' and n.unit_id = :unitId ", nativeQuery=true)
	List<RekeningKredit> customEodCalculation1011(@Param("unitId") String unitId);
	
	@Query(value = "select count(1) from rekeningkredit n " +
			"where n.status_rekening = '1' and n.unit_id = :unitId ", nativeQuery=true)
	Integer customEodCalculation1011Count(@Param("unitId") String unitId);
	
	@Query(value = "select sum(total_angsuran) from skalaangsuran " + 
			"where no_rekening = :noRek " + 
			"and to_char(due_date, 'yyyy-MM-dd') >= :strDate ", nativeQuery=true)
	Double customEodCalculation1011SisaAngsuran(@Param("noRek") String noRek, @Param("strDate") String strDate);
	
	
	
	@Query(value = "select * from rekeningkredit " +
			"where unit_id = :unitId " +
			"and coalesce(disburse, 0.0) > 0.0 ", nativeQuery=true)
	List<RekeningKredit> customEodPosting4004(@Param("unitId") String unitId);
	
	@Query(value = "select count(1) from rekeningkredit " +
			"where unit_id = :unitId " +
			"and coalesce(disburse, 0.0) > 0.0 ", nativeQuery=true)
	Integer customEodPosting4004Count(@Param("unitId") String unitId);

}
