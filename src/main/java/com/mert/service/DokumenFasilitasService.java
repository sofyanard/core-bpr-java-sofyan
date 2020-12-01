package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.DokumenFasilitas;
import com.mert.repository.DokumenFasilitasRepository;

@Service
public class DokumenFasilitasService {
	
	@Autowired
	private DokumenFasilitasRepository dokumenFasilitasRepository;
	
	public List<DokumenFasilitas> findAll() {
		return dokumenFasilitasRepository.findAll();
	}
	
	public List<DokumenFasilitas> FindByNoFasilitas(String nofasilitas) {
		return dokumenFasilitasRepository.FindByNoFasilitas(nofasilitas);
	}
	
	public DokumenFasilitas findOne(Integer id) {
		return dokumenFasilitasRepository.findOne(id);
	}
	
	public void save(DokumenFasilitas dokumenFasilitas) {
		dokumenFasilitasRepository.save(dokumenFasilitas);
	}
	
	public void delete(Integer id) {
		dokumenFasilitasRepository.delete(id);
	}

}
