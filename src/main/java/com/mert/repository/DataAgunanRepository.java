package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.DataAgunan;

public interface DataAgunanRepository extends JpaRepository<DataAgunan, String> {
	
	@Query(value = "select * from dataagunan n where n.no_fasilitas = :nofasilitas ", nativeQuery=true)
	List<DataAgunan> findByNoFasilitas(@Param("nofasilitas") String nofasilitas);
	
	
	
	@Query(value = "select a.* from dataagunan a " + 
			"join fasilitaskredit f on a.no_fasilitas = f.no_fasilitas " + 
			"join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			"where r.unit_id = :unitId " + 
			"and to_char(a.appraisal_date, 'yyyy-MM-dd') >= to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 year'), 'yyyy-MM-dd') " + 
			"and to_char(a.appraisal_date, 'yyyy-MM-dd') <= to_char(to_date(:strDate, 'yyyy-MM-dd') + (interval '1 year'), 'yyyy-MM-dd') ", nativeQuery=true)
	List<DataAgunan> customEodCalculation1012(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select count(1) from dataagunan a " + 
			"join fasilitaskredit f on a.no_fasilitas = f.no_fasilitas " + 
			"join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			"where r.unit_id = :unitId " + 
			"and to_char(a.appraisal_date, 'yyyy-MM-dd') >= to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 year'), 'yyyy-MM-dd') " + 
			"and to_char(a.appraisal_date, 'yyyy-MM-dd') <= to_char(to_date(:strDate, 'yyyy-MM-dd') + (interval '1 year'), 'yyyy-MM-dd') ", nativeQuery=true)
	Integer customEodCalculation1012Count(@Param("unitId") String unitId, @Param("strDate") String strDate);

}
