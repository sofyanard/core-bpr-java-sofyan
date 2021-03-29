package com.mert.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.SkalaAngsuran;

public interface SkalaAngsuranRepository extends JpaRepository<SkalaAngsuran, Integer> {
	
	// ATTENTION! all string date format must be: YYYY-MM-DD (ex: 2020-01-01, 2020-12-31)
	
	@Query(value = "select * from skalaangsuran n where n.no_rekening = :noRekening order by n.bulan_ke ", nativeQuery=true)
	List<SkalaAngsuran> findByNoRekening(@Param("noRekening") String noRekening);
	
	@Query(value = "select * from skalaangsuran n where n.no_rekening = :noRekening and n.bulan_ke = :bulanKe ", nativeQuery=true)
	SkalaAngsuran findByNoRekeningAndBulanKe(@Param("noRekening") String noRekening, @Param("bulanKe") Integer bulanKe);
	
	@Query(value = "select * from skalaangsuran n where n.no_rekening = :noRekening and to_char(n.due_date, 'YYYY-MM-DD') = :strDate ", nativeQuery=true)
	SkalaAngsuran findByNoRekeningAndDueDate(@Param("noRekening") String noRekening, @Param("strDate") String strDate);
	
	@Query(value = "select * from skalaangsuran n where to_char(n.due_date, 'YYYY-MM-DD') = :strDate ", nativeQuery=true)
	List<SkalaAngsuran> findByDueDate(@Param("strDate") String strDate);
	
	@Modifying
	@Query(value = "delete from skalaangsuran n where n.no_rekening = :noRekening ", nativeQuery=true)
	void deleteByNoRekening(@Param("noRekening") String noRekening);
	
	@Query(value = "select s.* from skalaangsuran s " + 
			"join rekeningkredit r on s.no_rekening = r.no_rekening " + 
			"where coalesce(r.disburse, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(s.due_date, 'yyyy-MM-dd') = to_char(to_date(:strDate, 'yyyy-MM-dd') + (interval '3 day'), 'yyyy-MM-dd') " + 
			"and s.bulan_ke > 0 ", nativeQuery=true)
	List<SkalaAngsuran> customEodCalculation1002(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select count(1) from skalaangsuran s " + 
			"join rekeningkredit r on s.no_rekening = r.no_rekening " + 
			"where coalesce(r.disburse, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(s.due_date, 'yyyy-MM-dd') = to_char(to_date(:strDate, 'yyyy-MM-dd') + (interval '3 day'), 'yyyy-MM-dd') " + 
			"and s.bulan_ke > 0 ", nativeQuery=true)
	Integer customEodCalculation1002Count(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
}
