package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.ParameterSandiBIOJK;

public interface ParameterSandiBIOJKRepository extends JpaRepository<ParameterSandiBIOJK, String> {
	List<ParameterSandiBIOJK> findByKategoricode(String kategoricode);
}
