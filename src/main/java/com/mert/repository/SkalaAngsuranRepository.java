package com.mert.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
	
}
