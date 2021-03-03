package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.FasilitasKredit;
import com.mert.repository.FasilitasKreditRepository;
import com.mert.model.CurrentFacility;
import com.mert.repository.CurrentFacilityRepository;
import com.mert.model.CurrentFacilityReference;
import com.mert.repository.CurrentFacilityReferenceRepository;

@Service
public class FasilitasKreditService {
	
	@Autowired
	private FasilitasKreditRepository fasilitasKreditRepository;
	
	@Autowired
	private CurrentFacilityRepository currentFacilityRepository;
	
	@Autowired
	private CurrentFacilityReferenceRepository currentFacilityReferenceRepository;
	
	public List<FasilitasKredit> findAll() {
		return fasilitasKreditRepository.findAll();
	}
	
	public FasilitasKredit findOne(String id) {
		return fasilitasKreditRepository.findOne(id);
	}
	
	public void save(FasilitasKredit fasilitasKredit) {
		fasilitasKreditRepository.save(fasilitasKredit);
	}
	
	public void delete(String id) {
		fasilitasKreditRepository.delete(id);
	}
	
	public List<FasilitasKredit> findByNoNasabah(Long nonasabah) {
		return fasilitasKreditRepository.findByNoNasabah(nonasabah);
	}
	
	public List<FasilitasKredit> findByNamaNasabah(String namanasabah) {
		return fasilitasKreditRepository.findByNamaNasabah(namanasabah);
	}
	
	public List<FasilitasKredit> searchbyProp(String nofasilitas, Long nonasabah, String namanasabah) {
		if ((nofasilitas != "") && (nofasilitas != null)) {
			FasilitasKredit fasilitasKredit = fasilitasKreditRepository.findOne(nofasilitas);
			List<FasilitasKredit> listFasilitasKredit = new ArrayList<FasilitasKredit>();
			listFasilitasKredit.add(fasilitasKredit);
			return listFasilitasKredit;
		} else if (nonasabah != null) {
			return fasilitasKreditRepository.findByNoNasabah(nonasabah);
		} else if ((namanasabah != "") && (namanasabah != null)) {
			return fasilitasKreditRepository.findByNamaNasabah(namanasabah);
		} else {
			return new ArrayList<FasilitasKredit>();
		}
	}
	
	public String GetNewFacilityId(Long nasabahId, String produkId) {
		String facilityId = "";
		String strNasabahId = "";
		String strIndex = "";
		
		CurrentFacility currentFacility = currentFacilityRepository.findByNasabahIdAndProdukId(nasabahId, produkId);
		if (currentFacility == null) {
			currentFacility = new CurrentFacility();
			currentFacility.setNasabahId(nasabahId);
			currentFacility.setProdukId(produkId);
			currentFacility.setLastIndex(1);
			currentFacilityRepository.save(currentFacility);
			
			strNasabahId = (nasabahId > 1000000000) ? nasabahId.toString() : ("0" + nasabahId.toString());
			facilityId = produkId + strNasabahId + this.AddZeroLeading(1, 5);
		} else {
			Integer nextIndex = currentFacility.getLastIndex() + 1;
			currentFacility.setLastIndex(nextIndex);
			currentFacilityRepository.save(currentFacility);
			
			strNasabahId = (nasabahId > 1000000000) ? nasabahId.toString() : ("0" + nasabahId.toString());
			facilityId = produkId + strNasabahId + this.AddZeroLeading(nextIndex, 5);
		}
		return facilityId;
	}
	
	public String GetNewFacilityReference(
			String referenceDate,
			String produkId,
			Long nasabahId,
			String unitId) {
		
		String facilityRef = "";
		String strNasabahId = "";
		String strIndex = "";
		
		CurrentFacilityReference currentFacilityReference = 
				currentFacilityReferenceRepository.findByProps(referenceDate, produkId, nasabahId, unitId);
		
		if (currentFacilityReference == null) {
			currentFacilityReference = new CurrentFacilityReference();
			currentFacilityReference.setReferenceDate(referenceDate);
			currentFacilityReference.setProdukId(produkId);
			currentFacilityReference.setNasabahId(nasabahId);
			currentFacilityReference.setUnitId(unitId);
			currentFacilityReference.setLastIndex(1);
			currentFacilityReferenceRepository.save(currentFacilityReference);
			
			strNasabahId = (nasabahId > 1000000000) ? nasabahId.toString() : ("0" + nasabahId.toString());
			facilityRef = referenceDate + produkId + strNasabahId + unitId + this.AddZeroLeading(1, 4);
		} else {
			Integer nextIndex = currentFacilityReference.getLastIndex() + 1;
			currentFacilityReference.setLastIndex(nextIndex);
			currentFacilityReferenceRepository.save(currentFacilityReference);
			
			strNasabahId = (nasabahId > 1000000000) ? nasabahId.toString() : ("0" + nasabahId.toString());
			facilityRef = referenceDate + produkId + strNasabahId + unitId + this.AddZeroLeading(nextIndex, 4);
		}
		
		return facilityRef;
	}
	
	private String AddZeroLeading(Integer input, Integer numdigit) {
		Integer targetLength = numdigit;
		String leadedInput = input.toString();
		
		while (leadedInput.length() < targetLength) {
			leadedInput = "0" + leadedInput;
		}
		
		return leadedInput;
	}
	
	public List<FasilitasKredit> customEodCalculation1001(String unitId, String strDate) {
		return fasilitasKreditRepository.customEodCalculation1001(unitId, strDate);
	}
	
	public Integer customEodCalculation1001Count(String unitId, String strDate) {
		return fasilitasKreditRepository.customEodCalculation1001Count(unitId, strDate);
	}

}
