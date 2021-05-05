package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.EodUnitStatus;
import com.mert.model.AppUser;

public interface EodUnitStatusRepository extends JpaRepository<EodUnitStatus, Integer> {
	
	// ATTENTION! all string date format must be: YYYY-MM-DD (ex: 2020-01-01, 2020-12-31)
	
	@Query(value = "select * from eodunitstatus n where n.unit_id = :unitId and to_char(n.date_buka, 'YYYY-MM-DD') = :strDate order by n.type_id, n.user_id ", nativeQuery=true)
	List<EodUnitStatus> findAllByUnitAndOpenDate(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select * from eodunitstatus n where n.unit_id = :unitId and to_char(n.date_buka, 'YYYY-MM-DD') = :strDate and n.type_id = '1' order by date_buka desc limit 1 ", nativeQuery=true)
	EodUnitStatus findUnitByUnitAndOpenDate(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select * from eodunitstatus n where n.unit_id = :unitId and to_char(n.date_buka, 'YYYY-MM-DD') = :strDate and n.type_id = '2' order by n.user_id ", nativeQuery=true)
	List<EodUnitStatus> findUsersByUnitAndOpenDate(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select * from eodunitstatus n where n.user_id = :userId and to_char(n.date_buka, 'YYYY-MM-DD') = :strDate order by date_buka desc limit 1 ", nativeQuery=true)
	EodUnitStatus findUserByUserAndOpenDate(@Param("userId") String userId, @Param("strDate") String strDate);
	
	@Query(value = "select * from eodunitstatus n where to_char(n.date_buka, 'YYYY-MM-DD') = :strDate and n.type_id = '1' order by n.unit_id ", nativeQuery=true)
	List<EodUnitStatus> findUnitsByOpenDate(@Param("strDate") String strDate);
	
	@Query(value = "select * from eodunitstatus n where to_char(n.date_buka, 'YYYY-MM-DD') = :strDate order by n.unit_id, n.user_id ", nativeQuery=true)
	List<EodUnitStatus> findAllByOpenDate(@Param("strDate") String strDate);
	
	@Query(value = "select * from eodunitstatus n "
			+ " where to_char(n.date_buka, 'YYYY-MM-DD') = :strDate "
			+ " and ((coalesce(status_unit,'0') != '0') or (coalesce(status_user,'0') != '0')) "
			+ " order by n.unit_id, n.user_id ", nativeQuery=true)
	List<EodUnitStatus> findAllOpenByOpenDate(@Param("strDate") String strDate);
	
}
