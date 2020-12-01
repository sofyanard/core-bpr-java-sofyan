package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.NasabahBasic;
import com.mert.model.NasabahCatatan;
import com.mert.repository.NasabahBasicRepository;
import com.mert.repository.NasabahCatatanRepository;

@Service
public class NasabahBasicService {
	
	@Autowired
	private NasabahBasicRepository nasabahBasicRepository;
	
	@Autowired
	private NasabahCatatanRepository nasabahCatatanRepository;
	
	
	
	public List<NasabahBasic> findAll() {
		return nasabahBasicRepository.findAll();
	}
	
	public List<NasabahBasic> findByKeyword(String keyword) {
		return nasabahBasicRepository.findByKeyword(keyword.toLowerCase());
	}
	
	public NasabahBasic findByNonasabah(Long nonasabah) {
		return nasabahBasicRepository.findByNonasabah(nonasabah);
	}
	
	public List<NasabahBasic> searchByProp(Long nonasabah, String noid, String nama) {
		if (nonasabah != null) {
			NasabahBasic nasabahBasic = nasabahBasicRepository.findByNonasabah(nonasabah);
			List<NasabahBasic> listNasabahBasic = new ArrayList<NasabahBasic>();
			listNasabahBasic.add(nasabahBasic);
			return listNasabahBasic;
		} else if ((noid != "") && (noid != null)) {
			return nasabahBasicRepository.findByNoid(noid);
		} else if ((nama != "") && (nama != null)) {
			return nasabahBasicRepository.findByKeyword(nama.toLowerCase());
		} else {
			//return nasabahBasicRepository.findAll();
			return new ArrayList<NasabahBasic>();
		}
	}
	
	public NasabahCatatan findCatatanByNonasabah(Long nonasabah) {
		return nasabahCatatanRepository.findOne(nonasabah);
	}

}
