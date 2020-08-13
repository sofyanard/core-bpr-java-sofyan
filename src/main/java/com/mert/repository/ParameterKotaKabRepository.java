package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.ParameterKotaKab;

public interface ParameterKotaKabRepository extends JpaRepository<ParameterKotaKab, String> {
	List<ParameterKotaKab> findByProvinsicode(String provinsicode);
}
