package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mert.model.PemantauanRekTabungan;

@Repository("pemantauanrektabunganRepository")
public interface PemantauanRekTabunganRepository extends JpaRepository<PemantauanRekTabungan, Integer>{

}
