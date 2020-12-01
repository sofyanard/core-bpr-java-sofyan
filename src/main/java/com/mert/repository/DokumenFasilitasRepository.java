package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.DokumenFasilitas;

public interface DokumenFasilitasRepository extends JpaRepository<DokumenFasilitas, Integer> {
	
	@Query(value = "select * from dokfasilitaskredit n join dokdatanasabah m on n.doknasabah_id = m.id where n.no_fasilitas = :nofasilitas ", nativeQuery=true)
	List<DokumenFasilitas> FindByNoFasilitas(@Param("nofasilitas") String nofasilitas);

}
