package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mert.model.PemeliharaanTabungan;
import com.mert.model.Tabungan;
import com.mert.repository.PemeliharaanTabunganRepository;
import com.mert.repository.TabunganRepository;

@Service
@Transactional
public class PemeliharaanTabunganService {
	
	private final PemeliharaanTabunganRepository pemeliharaantabunganRepository;
	
	public PemeliharaanTabunganService(PemeliharaanTabunganRepository pemeliharaantabunganRepository) {
		this.pemeliharaantabunganRepository = pemeliharaantabunganRepository;
	}
	
	public List<PemeliharaanTabungan> findAll(){
		List<PemeliharaanTabungan> pemeliharaantabungan = new ArrayList<>();
		pemeliharaantabungan = pemeliharaantabunganRepository.findAll();
		return pemeliharaantabungan;
	}
	
	public PemeliharaanTabungan findPemeliharaanTabungan(int NoNasabah){
		return pemeliharaantabunganRepository.findOne(NoNasabah);
	}

	public void save(PemeliharaanTabungan pemeliharaantabungan){
		pemeliharaantabunganRepository.save(pemeliharaantabungan);
	}
	
	public void delete(int NoNasabah){
		pemeliharaantabunganRepository.delete(NoNasabah);

	}
}
