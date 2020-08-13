package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mert.model.PemantauanRekTabungan;
import com.mert.model.PemeliharaanTabungan;
import com.mert.repository.PemantauanRekTabunganRepository;
import com.mert.repository.PemeliharaanTabunganRepository;

@Service
@Transactional
public class PemantauanRekTabunganService {

	private final PemantauanRekTabunganRepository pemantauanrektabunganRepository;
	
	public PemantauanRekTabunganService(PemantauanRekTabunganRepository pemantauanrektabunganRepository) {
		this.pemantauanrektabunganRepository = pemantauanrektabunganRepository;
	}
	
	public List<PemantauanRekTabungan> findAll(){
		List<PemantauanRekTabungan> pemantauanrektabungan = new ArrayList<>();
		pemantauanrektabungan = pemantauanrektabunganRepository.findAll();
		return pemantauanrektabungan;
	}
	
	public PemantauanRekTabungan findPemantauanRekTabungan(int NoNasabah){
		return pemantauanrektabunganRepository.findOne(NoNasabah);
	}
	
	public void save(PemantauanRekTabungan pemantauanrektabungan){
		pemantauanrektabunganRepository.save(pemantauanrektabungan);
	}
	
	public void delete(int NoNasabah){
		pemantauanrektabunganRepository.delete(NoNasabah);

	}
}
