package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.FasilitasJoin;

public interface FasilitasJoinRepository extends JpaRepository<FasilitasJoin, Integer> {
	
	@Query(value = "select * from fasilitasjoin n where n.no_fasilitas = :nofasilitas ", nativeQuery=true)
	List<FasilitasJoin> findByNofasilitas(@Param("nofasilitas") String nofasilitas);

}
