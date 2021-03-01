package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.KodeTranUnit;

public interface KodeTranUnitRepository extends JpaRepository<KodeTranUnit, Integer> {
	
	@Query(value = "select * from kodetranunit n where n.ko_tran = :koTran order by n.unit_id ", nativeQuery=true)
	List<KodeTranUnit> findByKoTran(@Param("koTran") String koTran);
	
	@Query(value = "select * from kodetranunit n where n.ko_tran = :koTran and n.unit_id = :unitId order by n.unit_id ", nativeQuery=true)
	KodeTranUnit findByKoTranAndUnit(@Param("koTran") String koTran, @Param("unitId") String unitId);

}
