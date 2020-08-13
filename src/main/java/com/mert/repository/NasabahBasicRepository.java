package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.NasabahBasic;

public interface NasabahBasicRepository extends JpaRepository<NasabahBasic, Long> {
	
	NasabahBasic findByNonasabah(Long nonasabah);
	
	@Query(value = "select * from datanasabah n where n.namalengkap like %:keyword% ", nativeQuery=true)
	List<NasabahBasic> findByKeyword(@Param("keyword") String keyword);
}
