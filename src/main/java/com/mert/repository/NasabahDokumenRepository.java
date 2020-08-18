package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.NasabahDokumen;

public interface NasabahDokumenRepository extends JpaRepository<NasabahDokumen, Integer> {
	List<NasabahDokumen> findByNonasabah(Long nonasabah);
}
