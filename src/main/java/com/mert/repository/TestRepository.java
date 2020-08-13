package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {

}
