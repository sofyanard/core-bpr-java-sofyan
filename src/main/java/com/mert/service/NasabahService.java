package com.mert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.NasabahPerorangan;
import com.mert.model.NasabahBadanUsaha;
import com.mert.model.NasabahCIF;
import com.mert.model.NasabahCreate;
import com.mert.model.NasabahUpdate;
import com.mert.model.NasabahJobnSpouse;
import com.mert.model.NasabahLaporPerorangan;
import com.mert.model.NasabahLaporBadanUsaha;
import com.mert.repository.NasabahPeroranganRepository;
import com.mert.repository.NasabahBadanUsahaRepository;
import com.mert.repository.NasabahCIFRepository;
import com.mert.repository.NasabahCreateRepository;
import com.mert.repository.NasabahUpdateRepository;
import com.mert.repository.NasabahJobnSpouseRepository;
import com.mert.repository.NasabahLaporPeroranganRepository;
import com.mert.repository.NasabahLaporBadanUsahaRepository;

@Service
public class NasabahService {
	
	@Autowired
	private NasabahPeroranganRepository nasabahPeroranganRepository;
	
	@Autowired
	private NasabahBadanUsahaRepository nasabahBadanUsahaRepository;
	
	@Autowired
	private NasabahCIFRepository nasabahCIFRepository;
	
	@Autowired
	private NasabahCreateRepository nasabahCreateRepository;
	
	@Autowired
	private NasabahUpdateRepository nasabahUpdateRepository;
	
	@Autowired
	private NasabahJobnSpouseRepository nasabahJobnSpouseRepository;
	
	@Autowired
	private NasabahLaporPeroranganRepository nasabahLaporPeroranganRepository;
	
	@Autowired
	private NasabahLaporBadanUsahaRepository nasabahLaporBadanUsahaRepository;
	
	
	
	public Long GetNewCIF(String nama) {
		String prefix = nama.substring(0, 1).toUpperCase();
		NasabahCIF nasabahCIF = nasabahCIFRepository.findOne(prefix);
		if (nasabahCIF == null) { return new Long(0); }
		Long proceedCIF = nasabahCIF.getLastcif();
		proceedCIF = proceedCIF + 1;
		nasabahCIF.setLastcif(proceedCIF);
		nasabahCIFRepository.save(nasabahCIF);
		
		return proceedCIF;
	}
	
	public void SaveNasabahPerorangan(NasabahPerorangan nasabahPerorangan) {
		nasabahPeroranganRepository.save(nasabahPerorangan);
	}
	
	public void SaveNasabahBadanUsaha(NasabahBadanUsaha nasabahBadanUsaha) {
		nasabahBadanUsahaRepository.save(nasabahBadanUsaha);
	}
	
	public NasabahPerorangan FindByIdPerorangan(Long nonasabah) {
		return nasabahPeroranganRepository.findOne(nonasabah);
	}
	
	public NasabahBadanUsaha FindByIdBadanUsaha(Long nonasabah) {
		return nasabahBadanUsahaRepository.findOne(nonasabah);
	}
	
	public void SaveNasabahCreate(NasabahCreate nasabahCreate) {
		nasabahCreateRepository.save(nasabahCreate);
	}
	
	public void SaveNasabahUpdate(NasabahUpdate nasabahUpdate) {
		nasabahUpdateRepository.save(nasabahUpdate);
	}
	
	public NasabahCreate FindByIdCreate(Long nonasabah) {
		return nasabahCreateRepository.findOne(nonasabah);
	}
	
	public NasabahUpdate FindByIdUpdate(Long nonasabah) {
		return nasabahUpdateRepository.findOne(nonasabah);
	}
	
	public void SaveNasabahJobnSpouse(NasabahJobnSpouse nasabahJobnSpouse) {
		nasabahJobnSpouseRepository.save(nasabahJobnSpouse);
	}
	
	public NasabahJobnSpouse FindByIdJobnSpouse(Long nonasabah) {
		return nasabahJobnSpouseRepository.findOne(nonasabah);
	}
	
	public void SaveNasabahLaporPerorangan(NasabahLaporPerorangan nasabahLaporPerorangan) {
		nasabahLaporPeroranganRepository.save(nasabahLaporPerorangan);
	}
	
	public void SaveNasabahLaporBadanUsaha(NasabahLaporBadanUsaha nasabahLaporBadanUsaha) {
		nasabahLaporBadanUsahaRepository.save(nasabahLaporBadanUsaha);
	}
	
	public NasabahLaporPerorangan FindByIdLaporPerorangan(Long nonasabah) {
		return nasabahLaporPeroranganRepository.findOne(nonasabah);
	}
	
	public NasabahLaporBadanUsaha FindByIdLaporBadanUsaha(Long nonasabah) {
		return nasabahLaporBadanUsahaRepository.findOne(nonasabah);
	}

}
