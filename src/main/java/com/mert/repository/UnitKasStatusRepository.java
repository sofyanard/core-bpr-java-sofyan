package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.UnitKasStatus;

public interface UnitKasStatusRepository extends JpaRepository<UnitKasStatus, Integer> {
	
	// ATTENTION! all string date format must be: YYYY-MM-DD (ex: 2020-01-01, 2020-12-31)
	
	@Query(value = "select * from unitkasstatus n where n.unit_id = :unitId and to_char(n.kaskeluar_date, 'YYYY-MM-DD') = :strDate order by n.user_id ", nativeQuery=true)
	List<UnitKasStatus> findByUnitAndDate(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select * from unitkasstatus n where n.user_id = :userId and to_char(n.kaskeluar_date, 'YYYY-MM-DD') = :strDate limit 1 ", nativeQuery=true)
	UnitKasStatus findByUserAndDate(@Param("userId") String userId, @Param("strDate") String strDate);

}
