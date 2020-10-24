package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.AppUnit;

public interface AppUnitRepository extends JpaRepository<AppUnit, String> {

}
