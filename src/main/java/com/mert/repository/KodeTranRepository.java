package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mert.model.KodeTran;

public interface KodeTranRepository extends JpaRepository<KodeTran, String> {
	
	@Query(value = "select * from kodetran where post_type = 'eod' order by ko_tran ", nativeQuery=true)
	List<KodeTran> ListEodPosting();

}
