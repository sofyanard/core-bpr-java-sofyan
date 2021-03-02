package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.EodProgress;

public interface EodProgressRepository extends JpaRepository<EodProgress, String> {

}
