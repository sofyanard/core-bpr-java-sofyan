package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mert.model.NasabahBasic;
import com.mert.model.TabunganPemantauanRek;
import com.mert.model.TabunganPemeliharaan;
import com.mert.repository.TabunganPemantauanRekRepository;
import com.mert.repository.TabunganPemeliharaanRepository;

@Service
@Transactional
public class TabunganPemantauanRekService {

	private final TabunganPemantauanRekRepository tabunganpemantauanRekRepository;
	
	public TabunganPemantauanRekService(TabunganPemantauanRekRepository tabunganpemantauanRekRepository) {
		this.tabunganpemantauanRekRepository = tabunganpemantauanRekRepository;
	}
	
	public List<TabunganPemantauanRek> findAll(){
		List<TabunganPemantauanRek> tabunganpemantauanRek = new ArrayList<>();
		tabunganpemantauanRek = tabunganpemantauanRekRepository.findAll();
		return tabunganpemantauanRek;
	}
	
	public TabunganPemantauanRek findTabunganPemantauanRek(String no_rekg){
		return tabunganpemantauanRekRepository.findOne(no_rekg);
	}
	
	public List<TabunganPemantauanRek> findByKeyword(String keyword) {
		return tabunganpemantauanRekRepository.findByKeyword(keyword.toLowerCase());
	}
	
	public List<TabunganPemantauanRek> searchByProp(Long no_nasabah, String nama_nasabah, String no_rekg) {
		if (no_rekg != null) {
			TabunganPemantauanRek tabunganpemantauanRek = tabunganpemantauanRekRepository.findOne(no_rekg);
			List<TabunganPemantauanRek> listTabunganPemantauanRek = new ArrayList<TabunganPemantauanRek>();
			listTabunganPemantauanRek.add(tabunganpemantauanRek);
			return listTabunganPemantauanRek;
		} else if ((no_nasabah != null)) {
			return tabunganpemantauanRekRepository.findByno_nasabah(no_nasabah);
		} else if ((nama_nasabah != "") && (nama_nasabah != null)) {
			return tabunganpemantauanRekRepository.findByKeyword(nama_nasabah.toLowerCase());
		} else {
			//return nasabahBasicRepository.findAll();
			return new ArrayList<TabunganPemantauanRek>();
		}
	}
	
	public void save(TabunganPemantauanRek tabunganpemantauanRek){
		tabunganpemantauanRekRepository.save(tabunganpemantauanRek);
	}
	
	public void delete(String no_rekg){
		tabunganpemantauanRekRepository.delete(no_rekg);

	}
}
