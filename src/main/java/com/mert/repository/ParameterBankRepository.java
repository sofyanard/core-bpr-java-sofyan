package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.ParameterBank;

public interface ParameterBankRepository extends JpaRepository<ParameterBank, String> {

}
