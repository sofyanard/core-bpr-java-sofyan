package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mert.model.Kategori;


@Repository("ketegoriRepository")
public interface KategoriRepository extends JpaRepository<Kategori, Integer>{

}
