package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.DataTagihan;

public interface DataTagihanRepository extends JpaRepository<DataTagihan, Integer> {
	
	// ATTENTION! all string date format must be: YYYY-MM-DD (ex: 2020-01-01, 2020-12-31)
	
	@Query(value = "select * from datatagihan n where n.no_rekening = :noRekening order by n.due_date ", nativeQuery=true)
	List<DataTagihan> findByNoRekening(@Param("noRekening") String noRekening);
	
	@Query(value = "select * from datatagihan n where n.no_rekening = :noRekening and to_char(n.due_date, 'YYYY-MM-DD') = :strDate ", nativeQuery=true)
	DataTagihan findByNoRekeningAndDueDate(@Param("noRekening") String noRekening, @Param("strDate") String strDate);
	
	@Query(value = "select d.* from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where coalesce(d.pokok, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') < to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 month'), 'yyyy-MM-dd') ", nativeQuery=true)
	List<DataTagihan> customEodCalculation1005(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select count(1) from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where coalesce(d.pokok, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') < to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 month'), 'yyyy-MM-dd') ", nativeQuery=true)
	Integer customEodCalculation1005Count(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select d.* from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where coalesce(d.bunga, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') < to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 month'), 'yyyy-MM-dd') ", nativeQuery=true)
	List<DataTagihan> customEodCalculation1006(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select count(1) from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where coalesce(d.bunga, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') < to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 month'), 'yyyy-MM-dd') ", nativeQuery=true)
	Integer customEodCalculation1006Count(@Param("unitId") String unitId, @Param("strDate") String strDate);

}
