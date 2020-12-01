package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.ParameterProduk;

public interface ParameterProdukRepository extends JpaRepository<ParameterProduk, String> {
	
	@Query(value = "select * from produk p where p.type = :productTypeId ", nativeQuery=true)
	List<ParameterProduk> findByType(@Param("productTypeId") String productTypeId);

}
