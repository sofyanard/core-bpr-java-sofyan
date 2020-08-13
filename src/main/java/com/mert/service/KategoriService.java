package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mert.model.Kategori;
import com.mert.model.Task;
import com.mert.repository.KategoriRepository;
import com.mert.repository.TaskRepository;

@Service
@Transactional
public class KategoriService {

	
	private final KategoriRepository kategoriRepository;
	
	public KategoriService(KategoriRepository kategoriRepository) {
		this.kategoriRepository = kategoriRepository;
	}
	
	public List<Kategori> findAll(){
		List<Kategori> kategori = new ArrayList<>();
		kategori = kategoriRepository.findAll();
		return kategori;
	}
	
	public Kategori findKategori(int id){
		return kategoriRepository.findOne(id);
	}
	
	public void save(Kategori kategori){
		kategoriRepository.save(kategori);
	}
	
	public void delete(int id){
		kategoriRepository.delete(id);

	}
}
