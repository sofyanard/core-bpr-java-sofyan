package com.mert.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.TranHistory;

public interface TranHistoryRepository extends JpaRepository<TranHistory, UUID> {

}
