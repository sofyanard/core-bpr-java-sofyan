package com.mert.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.TranHistory;

public interface TranHistoryRepository extends JpaRepository<TranHistory, UUID> {
	
	@Query(value = "select * from tranhistory " + 
			"where to_char(tran_date, 'yyyy-MM-dd') = to_char(current_date, 'yyyy-MM-dd') " + 
			"and unit_id = :unitId " + 
			"order by tran_date ", nativeQuery=true)
	List<TranHistory> findByUnitToday(@Param("unitId") String unitId);
	
	@Query(value = "select * from tranhistory " + 
			"where to_char(tran_date, 'yyyy-MM-dd') = to_char(current_date, 'yyyy-MM-dd') " + 
			"and unit_id = :unitId " + 
			"and ko_tran = :koTran " + 
			"order by tran_date ", nativeQuery=true)
	List<TranHistory> findByUnitAndKoTranToday(@Param("unitId") String unitId, @Param("koTran") String koTran);
	
	@Query(value = "select * from tranhistory " + 
			"where tran_ref = :tranRef " + 
			"order by tran_date ", nativeQuery=true)
	List<TranHistory> findByTranRef(@Param("tranRef") String tranRef);

}
