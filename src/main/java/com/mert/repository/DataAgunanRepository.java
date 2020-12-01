package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.DataAgunan;

public interface DataAgunanRepository extends JpaRepository<DataAgunan, String> {
	
	@Query(value = "select * from dataagunan n where n.no_fasilitas = :nofasilitas ", nativeQuery=true)
	List<DataAgunan> findByNoFasilitas(@Param("nofasilitas") String nofasilitas);

}
