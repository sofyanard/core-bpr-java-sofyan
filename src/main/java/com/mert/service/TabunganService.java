package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mert.model.Kategori;
import com.mert.model.Tabungan;
import com.mert.repository.KategoriRepository;
import com.mert.repository.TabunganRepository;

@Service
@Transactional
public class TabunganService {
	
	private final TabunganRepository tabunganRepository;
	
	public TabunganService(TabunganRepository tabunganRepository) {
		this.tabunganRepository = tabunganRepository;
	}
	
	public List<Tabungan> findAll(){
		List<Tabungan> tabungan = new ArrayList<>();
		tabungan = tabunganRepository.findAll();
		return tabungan;
	}

	public Tabungan findTabungan(int NoNasabah){
		return tabunganRepository.findOne(NoNasabah);
	}
	
	public void save(Tabungan tabungan){
		tabunganRepository.save(tabungan);
	}
	
	public void delete(int NoNasabah){
		tabunganRepository.delete(NoNasabah);

	}
}
