package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.CurrentRekeningKredit;

public interface CurrentRekeningKreditRepository extends JpaRepository<CurrentRekeningKredit, Integer> {
	
	@Query(value = "select * from currentrekeningkredit n where n.prefix = :prefix ", nativeQuery=true)
	CurrentRekeningKredit findByPrefix(@Param("prefix") String prefix);

}
