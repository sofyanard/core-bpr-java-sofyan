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
	
	
	
	@Query(value = "select f.* " + 
			" from fasilitaskredit f " + 
			" join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			" where coalesce(r.disburse, 0.0) <= 0.0 " + 
			" and r.unit_id = :unitId " +
			" and to_char(f.pembayaran_biaya_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	List<FasilitasKredit> customEodCalculation1001(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select count(1) " + 
			" from fasilitaskredit f " + 
			" join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			" where coalesce(r.disburse, 0.0) <= 0.0 " + 
			" and r.unit_id = :unitId " +
			" and to_char(f.pembayaran_biaya_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	Integer customEodCalculation1001Count(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	
	
	@Query(value = "select f.* " + 
			" from fasilitaskredit f " + 
			" join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			" where r.status_rekening = '1' " + 
			" and r.unit_id = :unitId " +
			" and to_char(f.pembayaran_biaya_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	List<FasilitasKredit> customEodCalculation1003(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select count(1) " + 
			" from fasilitaskredit f " + 
			" join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			" where r.status_rekening = '1' " + 
			" and r.unit_id = :unitId " +
			" and to_char(f.pembayaran_biaya_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	Integer customEodCalculation1003Count(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	
	
	@Query(value = "select f.* from fasilitaskredit f " + 
			"join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			"join skalaangsuran a on r.no_rekening = a.no_rekening " + 
			"where r.unit_id = :unitId and a.bulan_ke > 0 " + 
			"and to_char(a.due_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	List<FasilitasKredit> customEodCalculation1009(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select count(1) from fasilitaskredit f " + 
			"join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			"join skalaangsuran a on r.no_rekening = a.no_rekening " + 
			"where r.unit_id = :unitId and a.bulan_ke > 0 " + 
			"and to_char(a.due_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	Integer customEodCalculation1009Count(@Param("unitId") String unitId, @Param("strDate") String strDate);

}
