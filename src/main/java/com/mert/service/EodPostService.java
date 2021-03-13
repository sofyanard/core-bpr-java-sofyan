package com.mert.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.EodPost;
import com.mert.repository.EodPostRepository;

@Service
public class EodPostService {
	
	@Autowired
	private EodPostRepository eodPostRepository;
	
	@Autowired
	private EodTanggalService eodTanggalService;
	
	public List<EodPost> findAll() {
		return eodPostRepository.findAll();
	}
	
	public EodPost findOne(Long id) {
		return eodPostRepository.findOne(id);
	}
	
	public void save(EodPost eodPost) {
		eodPostRepository.save(eodPost);
	}
	
	public void delete(Long id) {
		eodPostRepository.delete(id);
	}
	
	public void newEntry(
			String koTran,
			String unitId,
			String postObject,
			String subObject,
			String noRekDebit,
			Double valueDebit,
			String noRekKredit,
			Double valueKredit,
			String note) {
		
		EodPost eodPost = new EodPost();
		eodPost.setEodTanggal(eodTanggalService.getDate());
		eodPost.setKoTran(koTran);
		eodPost.setUnitId(unitId);
		eodPost.setPostObject(postObject);
		eodPost.setSubObject(subObject);
		eodPost.setNoRekDebit(noRekDebit);
		eodPost.setValueDebit(valueDebit);
		eodPost.setNoRekKredit(noRekKredit);
		eodPost.setValueKredit(valueKredit);
		eodPost.setTranDate(new Date());
		eodPost.setNote(note);
		
		eodPostRepository.save(eodPost);
	}

}
