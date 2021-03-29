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
	
	
	
	// mencari 1 record tagihan paling terdahulu yang belum dibayar berdasarkan no rekening
	@Query(value = "select * from datatagihan " + 
			"where no_rekening = :noRekening " + 
			"and coalesce(paid_status,'') not in ('Bayar', 'Hapus') " + 
			"order by due_date limit 1 ", nativeQuery=true)
	DataTagihan findFirstNotPaid(@Param("noRekening") String noRekening);
	
	
	
	@Query(value = "select sum(pokok) from datatagihan where no_rekening = :noRekening ", nativeQuery=true)
	Double sumPokokByNoRekening(@Param("noRekening") String noRekening);
	
	@Query(value = "select sum(bunga) from datatagihan where no_rekening = :noRekening ", nativeQuery=true)
	Double sumBungaByNoRekening(@Param("noRekening") String noRekening);
	
	@Query(value = "select sum(denda_pokok) from datatagihan where no_rekening = :noRekening ", nativeQuery=true)
	Double sumDendaPokokByNoRekening(@Param("noRekening") String noRekening);
	
	@Query(value = "select sum(denda_bunga) from datatagihan where no_rekening = :noRekening ", nativeQuery=true)
	Double sumDendaBungaByNoRekening(@Param("noRekening") String noRekening);
	
	@Query(value = "select sum(lainnya) from datatagihan where no_rekening = :noRekening ", nativeQuery=true)
	Double sumLainnyaByNoRekening(@Param("noRekening") String noRekening);
	
	
	
	@Query(value = "select count(distinct d.no_rekening) from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where coalesce(d.pokok, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') < to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 month'), 'yyyy-MM-dd') ", nativeQuery=true)
	Integer customEodCalculation1005A(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select distinct d.no_rekening from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where coalesce(d.pokok, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') < to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 month'), 'yyyy-MM-dd') ", nativeQuery=true)
	List<String> customEodCalculation1005B(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select d.* from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where coalesce(d.pokok, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') < to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 month'), 'yyyy-MM-dd') " +
			"and d.no_rekening = :noRek order by d.due_date ", nativeQuery=true)
	List<DataTagihan> customEodCalculation1005C(@Param("unitId") String unitId, @Param("strDate") String strDate, @Param("noRek") String noRek);
	
	
	
	@Query(value = "select count(distinct d.no_rekening) from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where coalesce(d.bunga, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') < to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 month'), 'yyyy-MM-dd') ", nativeQuery=true)
	Integer customEodCalculation1006A(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select distinct d.no_rekening from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where coalesce(d.bunga, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') < to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 month'), 'yyyy-MM-dd') ", nativeQuery=true)
	List<String> customEodCalculation1006B(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select d.* from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where coalesce(d.bunga, 0.0) > 0.0 " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') < to_char(to_date(:strDate, 'yyyy-MM-dd') - (interval '1 month'), 'yyyy-MM-dd') " +
			"and d.no_rekening = :noRek order by d.due_date ", nativeQuery=true)
	List<DataTagihan> customEodCalculation1006C(@Param("unitId") String unitId, @Param("strDate") String strDate, @Param("noRek") String noRek);
	
	
	
	@Query(value = "select count(distinct d.no_rekening) from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where (coalesce(d.paid_status, '') != 'Bayar' or coalesce(d.paid_status, '') != 'Hapus') " + 
			"and r.status_rekening in ('1','2','3','4','5','7','8') " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') <= :strDate ", nativeQuery=true)
	Integer customEodCalculation1007A(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select distinct d.no_rekening from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where (coalesce(d.paid_status, '') != 'Bayar' or coalesce(d.paid_status, '') != 'Hapus') " + 
			"and r.status_rekening in ('1','2','3','4','5','7','8') " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') <= :strDate ", nativeQuery=true)
	List<String> customEodCalculation1007B(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select d.* from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where (coalesce(d.paid_status, '') != 'Bayar' or coalesce(d.paid_status, '') != 'Hapus') " + 
			"and r.status_rekening in ('1','2','3','4','5','7','8') " + 
			"and r.unit_id = :unitId " + 
			"and to_char(d.due_date, 'yyyy-MM-dd') <= :strDate " +
			"and d.no_rekening = :noRek order by d.due_date ", nativeQuery=true)
	List<DataTagihan> customEodCalculation1007C(@Param("unitId") String unitId, @Param("strDate") String strDate, @Param("noRek") String noRek);
	
	
	
	@Query(value = "select count(distinct d.no_rekening) from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where r.unit_id = :unitId " + 
			"and to_char(d.paid_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	Integer customEodCalculation1008A(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select distinct d.no_rekening from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where r.unit_id = :unitId " + 
			"and to_char(d.paid_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	List<String> customEodCalculation1008B(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select d.* from datatagihan d " + 
			"join rekeningkredit r on d.no_rekening = r.no_rekening " + 
			"where r.unit_id = :unitId " + 
			"and to_char(d.paid_date, 'yyyy-MM-dd') = :strDate " + 
			"and d.no_rekening = :noRek order by d.due_date ", nativeQuery=true)
	List<DataTagihan> customEodCalculation1008C(@Param("unitId") String unitId, @Param("strDate") String strDate, @Param("noRek") String noRek);

}
