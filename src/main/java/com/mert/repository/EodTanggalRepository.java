package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.EodTanggal;

public interface EodTanggalRepository extends JpaRepository<EodTanggal, String> {
	
}
