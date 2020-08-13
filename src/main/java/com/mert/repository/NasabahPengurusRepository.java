package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.NasabahPengurus;

public interface NasabahPengurusRepository extends JpaRepository<NasabahPengurus, Integer> {
	List<NasabahPengurus> findByNonasabah(Long nonasabah);
}
