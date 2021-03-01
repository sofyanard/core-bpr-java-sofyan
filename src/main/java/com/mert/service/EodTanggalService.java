package com.mert.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.EodTanggal;
import com.mert.repository.EodTanggalRepository;

@Service
public class EodTanggalService {
	
	@Autowired
	private EodTanggalRepository eodTanggalRepository;
	
	public Date getDate() {
		Date result;
		result = new Date();
		try {
			result = eodTanggalRepository.findOne("EODTanggal").getEodTanggalDate();
		}
		catch (Exception ex) {
			System.out.println("eodTanggalRepository ERRORRR : " + ex.getMessage());
		}
		return result;
	}
	
	public void setDate(Date date) {
		date = removeTime(date);
		EodTanggal eodTanggal = eodTanggalRepository.findOne("EODTanggal");
		eodTanggal.setEodTanggalDate(date);
		eodTanggalRepository.save(eodTanggal);
	}
	
	public static Date removeTime(Date date) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime(); 
    }

}
