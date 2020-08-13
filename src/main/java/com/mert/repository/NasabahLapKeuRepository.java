package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.NasabahLapKeu;

public interface NasabahLapKeuRepository extends JpaRepository<NasabahLapKeu, Integer> {
	List<NasabahLapKeu> findByNonasabah(Long nonasabah);
}
