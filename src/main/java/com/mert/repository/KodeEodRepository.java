package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mert.model.KodeEod;

public interface KodeEodRepository extends JpaRepository<KodeEod, String> {
	
	@Query(value = "select * from kodeeod where seq > 0 order by seq ", nativeQuery=true)
	List<KodeEod> findAllEom();
	
	@Query(value = "select * from kodeeod where seq > 0 and coalesce(eom_only,'') != '1' order by seq ", nativeQuery=true)
	List<KodeEod> findAllEod();

}
