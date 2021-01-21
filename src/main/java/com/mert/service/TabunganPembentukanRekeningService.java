package com.mert.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.NasabahCatatan;
import com.mert.model.TabunganPemantauanRek;
import com.mert.model.TabunganPembentukanRekening;
import com.mert.repository.TabunganPemantauanRekRepository;
import com.mert.repository.TabunganPembentukanRekeningRepository;

@Service
@Transactional
public class TabunganPembentukanRekeningService {
	
	@Autowired
	private TabunganPemantauanRekRepository tabunganPemantauanRekRepository;
	
	private final TabunganPembentukanRekeningRepository tabunganpembentukanrekeningRepository;
	
	public TabunganPembentukanRekeningService(TabunganPembentukanRekeningRepository tabunganpembentukanrekeningRepository) {
		this.tabunganpembentukanrekeningRepository = tabunganpembentukanrekeningRepository;
	}
	
	public List<TabunganPembentukanRekening> findAll(){
		List<TabunganPembentukanRekening> tabunganpembentukanRekening = new ArrayList<>();
		tabunganpembentukanRekening = tabunganpembentukanrekeningRepository.findAll();
		return tabunganpembentukanRekening;
	}
	
	public TabunganPembentukanRekening findById(String no_rekg) {
		return tabunganpembentukanrekeningRepository.findOne(no_rekg);
	}

	public TabunganPembentukanRekening findTabunganPembentukanRekening(String no_rekg){
		return tabunganpembentukanrekeningRepository.findOne(no_rekg);
	}
	
	public void save(TabunganPembentukanRekening tabunganpembentukanRekening){
		tabunganpembentukanrekeningRepository.save(tabunganpembentukanRekening);
	}
	
	public void delete(String no_rekg){
		tabunganpembentukanrekeningRepository.delete(no_rekg);

	}
	
	public String generateRekening(String appUnit ) {
			String noRek="";
			int noAkhir=0;
			Random randomNumbers = new Random();
			String kodeTabungan="2";
			
			List<TabunganPemantauanRek> tabunganpemantauanRek = new ArrayList<>();
			tabunganpemantauanRek = tabunganPemantauanRekRepository.findAll();
			if (tabunganpemantauanRek.size()==0) {
				noAkhir=1;
				
			}
			else {
				noAkhir= tabunganpemantauanRek.size()+1;
			}
			String no= String.format("%05d",noAkhir);
			noRek=appUnit.substring(0,3)+kodeTabungan+ no+String.valueOf(randomNumbers.nextInt(10));
			
			return noRek;
		
	}
}
