package com.mert.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.FasilitasKredit;
import com.mert.model.KodeEodUnit;
import com.mert.model.RekeningBukuBesar;
import com.mert.model.BukuBesar;
import com.mert.model.EodTanggal;

@Service
public class EodCalculationService {
	
	@Autowired
	private EodProgressService eodProgressService;
	
	@Autowired
	private KodeEodUnitService kodeEodUnitService;
	
	@Autowired
	private EodTanggalService eodTanggalService;
	
	@Autowired
	private EodKalkulasiService eodKalkulasiService;
	
	@Autowired
	private BukuBesarService bukuBesarService;
	
	@Autowired
	private RekeningBukuBesarService rekeningBukuBesarService;
	
	@Autowired
	private FasilitasKreditService fasilitasKreditService;
	
	private String _eodTanggal;
	
	public void requestEodTanggal() throws Exception {
		Date today;
		today = new Date();
		
		try {
			today = eodTanggalService.getDate();
		}
		catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = formatter.format(today);
		this._eodTanggal = strToday;
	}
	
	// Get and Validate Rekening Buku Besar
	private RekeningBukuBesar GetRekeningBukuBesar(String noBukuBesar) throws Exception {
			
		// Inquiry Buku Besar
		BukuBesar bukuBesar = bukuBesarService.findOne(noBukuBesar);
									
		if (bukuBesar == null) {
			throw new Exception("Buku Besar no " + noBukuBesar + " tidak ditemukan!");
		}
			
		// Inquiry Rekening Buku Besar User, create kalau belum ada
		RekeningBukuBesar rekeningBukuBesar = rekeningBukuBesarService.findOne(noBukuBesar);
			
		if (rekeningBukuBesar == null) {
			rekeningBukuBesar = new RekeningBukuBesar();
			rekeningBukuBesar.setNoRekening(noBukuBesar);
			rekeningBukuBesar.setSaldo(0.0);
			rekeningBukuBesarService.save(rekeningBukuBesar);
		}
			
		return rekeningBukuBesar;
	}
	
	// @Async
	public String Test1001() throws Exception {
		
		if (!eodProgressService.Validate("1001"))
			throw new Exception("No progress data found!");
		
		eodProgressService.Start("1001");
		eodProgressService.SetMax("1001", 10);
		
		Thread.sleep(1000);
		
		Integer x = 1;
		for (int i = 1; i < 10; i++) {
			eodProgressService.CountUp("1001");
			x++;
			Date now = new Date();
			System.out.println(x.toString());
			System.out.println(now.toString());
			
			Thread.sleep(1000);
		}
		
		eodProgressService.Finish("1001");
		
		return "Success1001";
	}
	
	public String Calc1001() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1001")) {
			eodProgressService.Start("1001");
		} else {
			throw new Exception("EodProgress not found!");
		}
		
		// Inquiry Units
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1001");
		
		Integer countAllUnit = 0;
		
		// Loop for Units Count
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = fasilitasKreditService.customEodCalculation1001Count(iUnitId, _eodTanggal);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1001", countAllUnit);
		
		Integer progressCount = 0;
		
		// Loop for Units Process
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			String noBukuBesar = kodeEodUnit.getRekBukuBesar();
			
			// Validate and Get RekeningBukuBesar
			RekeningBukuBesar rekeningBukuBesar = this.GetRekeningBukuBesar(noBukuBesar);
			
			Double saldoBukuBesar = rekeningBukuBesar.getSaldo() != null ? rekeningBukuBesar.getSaldo() : 0.0;
			
			// List FasilitasKredit in a Unit
			List<FasilitasKredit> listFasilitasKredit = fasilitasKreditService.customEodCalculation1001(iUnitId, _eodTanggal);
			
			// Loop for every FasilitasKredit
			for (FasilitasKredit fasilitasKredit : listFasilitasKredit) {
				String noFasilitas = fasilitasKredit.getNoFasilitas();
				Double plafond = fasilitasKredit.getEqvPlafond() != null ? fasilitasKredit.getEqvPlafond() : 0.0;
				Double bprovisi = fasilitasKredit.getProvisiAmount() != null ? fasilitasKredit.getProvisiAmount() : 0.0;
				Double badmin = fasilitasKredit.getAmtAdmin() != null ? fasilitasKredit.getAmtAdmin() : 0.0;
				Double bnotaris = fasilitasKredit.getAmtNotaris() != null ? fasilitasKredit.getAmtNotaris() : 0.0;
				Double basuransi = fasilitasKredit.getAmtAsuransi() != null ? fasilitasKredit.getAmtAsuransi() : 0.0;
				Double bappraisal = fasilitasKredit.getAmtAppraisal() != null ? fasilitasKredit.getAmtAppraisal() : 0.0;
				Double bmaterai = fasilitasKredit.getAmtMaterai() != null ? fasilitasKredit.getAmtMaterai() : 0.0;
				
				// CALCULATION HERE
				Double calcResult = plafond - (bprovisi + badmin + bnotaris + basuransi + bappraisal + bmaterai);
				
				saldoBukuBesar = saldoBukuBesar + calcResult;
				rekeningBukuBesar.setSaldo(saldoBukuBesar);
				rekeningBukuBesarService.save(rekeningBukuBesar);
				
				// Insert EodKalkulasi Log
				eodKalkulasiService.newEntry("1001", noFasilitas, calcResult, noBukuBesar, null);
				
				// Count Up Progress
				eodProgressService.CountUp("1001");
			}
			
		}
		
		// Set Progress Finish
		eodProgressService.Finish("1001");
		
		return "Calculation 1001 Finish";
	}

}
