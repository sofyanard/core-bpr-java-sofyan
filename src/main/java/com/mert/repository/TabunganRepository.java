package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mert.model.Kategori;
import com.mert.model.Tabungan;

@Repository("tabunganRepository")
public interface TabunganRepository extends JpaRepository<Tabungan, Integer>{

}
