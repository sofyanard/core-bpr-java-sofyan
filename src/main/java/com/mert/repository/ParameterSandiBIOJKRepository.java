package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.ParameterSandiBIOJK;
import com.mert.model.ParameterSandiBIOJKID;

public interface ParameterSandiBIOJKRepository extends JpaRepository<ParameterSandiBIOJK, ParameterSandiBIOJKID> {
	
	List<ParameterSandiBIOJK> findByKategoricode(String kategoricode);
	
	
}
