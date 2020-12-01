package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.DokumenAgunan;

public interface DokumenAgunanRepository extends JpaRepository<DokumenAgunan, Integer> {
	
	@Query(value = "select * from dokagunan n join dokdatanasabah m on n.doknasabah_id = m.id where n.no_agunan = :noagunan ", nativeQuery=true)
	List<DokumenAgunan> FindByNoAgunan(@Param("noagunan") String noagunan);

}
