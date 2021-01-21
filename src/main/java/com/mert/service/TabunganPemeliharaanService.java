package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.TabunganPemeliharaan;
import com.mert.model.TabunganUpdate;
import com.mert.model.NasabahPerorangan;
import com.mert.model.NasabahUpdate;
import com.mert.model.TabunganPemantauanRek;
import com.mert.model.TabunganPembentukanRekening;
import com.mert.repository.TabunganPemeliharaanRepository;
import com.mert.repository.TabunganUpdateRepository;
import com.mert.repository.TabunganPembentukanRekeningRepository;

@Service
@Transactional
public class TabunganPemeliharaanService {
	
	private final TabunganPemeliharaanRepository tabunganPemeliharaanRepository;
	
	@Autowired
	private TabunganUpdateRepository tabunganUpdateRepository;
	
	public TabunganPemeliharaanService(TabunganPemeliharaanRepository tabunganPemeliharaanRepository) {
		this.tabunganPemeliharaanRepository = tabunganPemeliharaanRepository;
	}
	
	public List<TabunganPemeliharaan> findAll(){
		List<TabunganPemeliharaan> tabunganPemeliharaan = new ArrayList<>();
		tabunganPemeliharaan = tabunganPemeliharaanRepository.findAll();
		return tabunganPemeliharaan;
	}
	
	public TabunganPemeliharaan findPemeliharaanTabungan(String no_rekg){
		return tabunganPemeliharaanRepository.findOne(no_rekg);
	}
	
	public List<TabunganPemeliharaan> findByKeyword(String keyword) {
		return tabunganPemeliharaanRepository.findByKeyword(keyword.toLowerCase());
	}
	
	public List<TabunganPemeliharaan> searchByProp(Long no_nasabah, String nama_nasabah, String no_rekg) {
		if (no_rekg != null) {
			TabunganPemeliharaan tabunganPemeliharaan = tabunganPemeliharaanRepository.findOne(no_rekg);
			List<TabunganPemeliharaan> listTabunganPemeliharaan = new ArrayList<TabunganPemeliharaan>();
			listTabunganPemeliharaan.add(tabunganPemeliharaan);
			return listTabunganPemeliharaan;
		} else if ((no_nasabah != null)) {
			return tabunganPemeliharaanRepository.findByno_nasabah(no_nasabah);
		} else if ((nama_nasabah != "") && (nama_nasabah != null)) {
			return tabunganPemeliharaanRepository.findByKeyword(nama_nasabah.toLowerCase());
		} else {
			//return nasabahBasicRepository.findAll();
			return new ArrayList<TabunganPemeliharaan>();
		}
	}
	
	public TabunganPemeliharaan FindByIdTabunganPemeliharaan(String no_rekg) {
		return tabunganPemeliharaanRepository.findOne(no_rekg);
	}

	public void save(TabunganPemeliharaan tabunganPemeliharaan){
		tabunganPemeliharaanRepository.save(tabunganPemeliharaan);
	}
	
	public void SaveTabunganPemeliharaan(TabunganPemeliharaan tabunganPemeliharaan) {
		tabunganPemeliharaanRepository.save(tabunganPemeliharaan);
	}
	
	public void SaveTabunganUpdate(TabunganUpdate tabunganUpdate) {
		tabunganUpdateRepository.save(tabunganUpdate);
	}
	
	public TabunganUpdate FindByIdUpdate(String no_rekg) {
		return tabunganUpdateRepository.findOne(no_rekg);
	}
	
	public void delete(String no_rekg){
		tabunganPemeliharaanRepository.delete(no_rekg);

	}
}
