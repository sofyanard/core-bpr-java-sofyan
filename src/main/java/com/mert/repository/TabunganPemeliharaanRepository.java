package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mert.model.TabunganPemeliharaan;

public interface TabunganPemeliharaanRepository extends JpaRepository<TabunganPemeliharaan, String>{

	@Query(value = "select * from rekeningtabungan n where lower(n.no_nasabah) =  :no_nasabah", nativeQuery=true)
	List<TabunganPemeliharaan> findByno_nasabah(@Param("no_nasabah") Long no_nasabah);
	
	TabunganPemeliharaan findOne(String no_rekg);
	
	@Query(value = "select * from rekeningtabungan n where lower(n.nama_nasabah) like %:keyword% ", nativeQuery=true)
	List<TabunganPemeliharaan> findByKeyword(@Param("keyword") String keyword);
}
