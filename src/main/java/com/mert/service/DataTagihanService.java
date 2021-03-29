package com.mert.service;

import java.util.List;

import com.mert.model.DataTagihan;
import com.mert.repository.DataTagihanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataTagihanService {
	
	@Autowired
	private DataTagihanRepository dataTagihanRepository;
	
	public List<DataTagihan> findAll() {
		return dataTagihanRepository.findAll();
	}
	
	public DataTagihan findOne(Integer id) {
		return dataTagihanRepository.findOne(id);
	}
	
	public void save(DataTagihan dataTagihan) {
		dataTagihanRepository.save(dataTagihan);
	}
	
	public void delete(Integer id) {
		dataTagihanRepository.delete(id);
	}
	
	public List<DataTagihan> findByNoRekening(String noRekening) {
		return dataTagihanRepository.findByNoRekening(noRekening);
	}
	
	public DataTagihan findByNoRekeningAndDueDate(String noRekening, String strDate) {
		return dataTagihanRepository.findByNoRekeningAndDueDate(noRekening, strDate);
	}
	
	public DataTagihan findFirstNotPaid(String noRekening) {
		return dataTagihanRepository.findFirstNotPaid(noRekening);
	}
	
	public Double sumPokokByNoRekening(String noRekening) {
		return dataTagihanRepository.sumPokokByNoRekening(noRekening);
	}
	
	public Double sumBungaByNoRekening(String noRekening) {
		return dataTagihanRepository.sumBungaByNoRekening(noRekening);
	}
	
	public Double sumDendaPokokByNoRekening(String noRekening) {
		return dataTagihanRepository.sumDendaPokokByNoRekening(noRekening);
	}
	
	public Double sumDendaBungaByNoRekening(String noRekening) {
		return dataTagihanRepository.sumDendaBungaByNoRekening(noRekening);
	}
	
	public Double sumLainnyaByNoRekening(String noRekening) {
		return dataTagihanRepository.sumLainnyaByNoRekening(noRekening);
	}
	
	
	
	public Integer customEodCalculation1005A(String unitId, String strDate) {
		return dataTagihanRepository.customEodCalculation1005A(unitId, strDate);
	}
	
	public List<String> customEodCalculation1005B(String unitId, String strDate) {
		return dataTagihanRepository.customEodCalculation1005B(unitId, strDate);
	}
	
	public List<DataTagihan> customEodCalculation1005C(String unitId, String strDate, String noRek) {
		return dataTagihanRepository.customEodCalculation1005C(unitId, strDate, noRek);
	}
	

	
	public Integer customEodCalculation1006A(String unitId, String strDate) {
		return dataTagihanRepository.customEodCalculation1006A(unitId, strDate);
	}
	
	public List<String> customEodCalculation1006B(String unitId, String strDate) {
		return dataTagihanRepository.customEodCalculation1006B(unitId, strDate);
	}
	
	public List<DataTagihan> customEodCalculation1006C(String unitId, String strDate, String noRek) {
		return dataTagihanRepository.customEodCalculation1006C(unitId, strDate, noRek);
	}
	
	
	
	public Integer customEodCalculation1007A(String unitId, String strDate) {
		return dataTagihanRepository.customEodCalculation1007A(unitId, strDate);
	}
	
	public List<String> customEodCalculation1007B(String unitId, String strDate) {
		return dataTagihanRepository.customEodCalculation1007B(unitId, strDate);
	}
	
	public List<DataTagihan> customEodCalculation1007C(String unitId, String strDate, String noRek) {
		return dataTagihanRepository.customEodCalculation1007C(unitId, strDate, noRek);
	}
	
	
	
	public Integer customEodCalculation1008A(String unitId, String strDate) {
		return dataTagihanRepository.customEodCalculation1008A(unitId, strDate);
	}
	
	public List<String> customEodCalculation1008B(String unitId, String strDate) {
		return dataTagihanRepository.customEodCalculation1008B(unitId, strDate);
	}
	
	public List<DataTagihan> customEodCalculation1008C(String unitId, String strDate, String noRek) {
		return dataTagihanRepository.customEodCalculation1008C(unitId, strDate, noRek);
	}

}
