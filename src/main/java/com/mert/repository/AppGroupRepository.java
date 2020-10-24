package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.AppGroup;

public interface AppGroupRepository extends JpaRepository<AppGroup, String> {

}
