package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mert.model.PemeliharaanTabungan;


@Repository("pemeliharaantabunganRepository")
public interface PemeliharaanTabunganRepository extends JpaRepository<PemeliharaanTabungan, Integer>{

}
