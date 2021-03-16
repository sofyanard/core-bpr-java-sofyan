package com.mert.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mert.model.LapKeuDaily;
import com.mert.model.LapKeuMonthly;

@Service
public class EodLaporanKeuanganService {
	
	@Autowired
	private EodTanggalService eodTanggalService;
	
	@Autowired
	private EodProgressService eodProgressService;
	
	@Autowired
	private LapKeuDailyService lapKeuDailyService;
	
	@Autowired
	private LapKeuMonthlyService lapKeuMonthlyService;
	
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
	
	@Async
	public CompletableFuture<String> LaporanKeuanganDaily() throws Exception {
		
		// Crear daily table first
		lapKeuDailyService.deleteAll();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("LKDaily")) {
			eodProgressService.Start("LKDaily");
		} else {
			throw new Exception("Record EodProgress LKDaily not found!");
		}
		
		Integer countAll = 0;
		countAll = lapKeuDailyService.customEodLapKeuCount();
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("LKDaily", countAll);
		
		List<LapKeuDaily> listLapKeuDaily = lapKeuDailyService.customEodLapKeu();
		
		Integer progressCount = 0;
		
		for (LapKeuDaily lapKeuDaily : listLapKeuDaily) {
			
			if (lapKeuDaily.getUnitId() != null && lapKeuDaily.getRekeningBukuBesar() != null) {
				
				LapKeuDaily newLapKeuDaily = new LapKeuDaily();
				newLapKeuDaily.setId(null);
				newLapKeuDaily.setTanggal(lapKeuDaily.getTanggal());
				newLapKeuDaily.setUnitId(lapKeuDaily.getUnitId());
				newLapKeuDaily.setJenisLapKeu(lapKeuDaily.getJenisLapKeu());
				newLapKeuDaily.setPosisiLapKeu(lapKeuDaily.getPosisiLapKeu());
				newLapKeuDaily.setPosLapKeu(lapKeuDaily.getPosLapKeu());
				newLapKeuDaily.setSubPosLapKeu(lapKeuDaily.getSubPosLapKeu());
				newLapKeuDaily.setSubSubPosLapKeu(lapKeuDaily.getSubSubPosLapKeu());
				newLapKeuDaily.setRekeningBukuBesar(lapKeuDaily.getRekeningBukuBesar());
				newLapKeuDaily.setValue(lapKeuDaily.getValue());
				
				lapKeuDailyService.save(newLapKeuDaily);
			}
			
			// Count Up Progress
			progressCount++;
			eodProgressService.SetNow("LKDaily", progressCount);
			String strNote = progressCount.toString() + "/" + countAll.toString() + " item(s) proceed";
			eodProgressService.SetNote("LKDaily", strNote);
		}
		
		// Set Progress Finish
		if (countAll == 0) {
			String strNote = progressCount.toString() + "/" + countAll.toString() + " item(s) proceed";
			eodProgressService.SetNote("LKDaily", strNote);
			eodProgressService.FinishZero("LKDaily");
		}
		else {
			eodProgressService.Finish("LKDaily");
		}
		
		String strResult = "Laporan Keuangan Harian Finish";
		return CompletableFuture.completedFuture(strResult);
		
	}
	
	@Async
	public CompletableFuture<String> LaporanKeuanganMonthly() throws Exception {
		
		// Initiate Progress Status
		if (eodProgressService.Validate("LKMonthly")) {
			eodProgressService.Start("LKMonthly");
		} else {
			throw new Exception("Record EodProgress LKMonthly not found!");
		}
		
		Integer countAll = 0;
		countAll = lapKeuMonthlyService.customEodLapKeuCount();
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("LKMonthly", countAll);
		
		List<LapKeuMonthly> listLapKeuMonthly = lapKeuMonthlyService.customEodLapKeu();
		
		Integer progressCount = 0;
		
		for (LapKeuMonthly lapKeuMonthly : listLapKeuMonthly) {
			
			if (lapKeuMonthly.getUnitId() != null && lapKeuMonthly.getRekeningBukuBesar() != null) {
				
				LapKeuMonthly newLapKeuMonthly = new LapKeuMonthly();
				newLapKeuMonthly.setId(null);
				newLapKeuMonthly.setTanggal(lapKeuMonthly.getTanggal());
				newLapKeuMonthly.setUnitId(lapKeuMonthly.getUnitId());
				newLapKeuMonthly.setJenisLapKeu(lapKeuMonthly.getJenisLapKeu());
				newLapKeuMonthly.setPosisiLapKeu(lapKeuMonthly.getPosisiLapKeu());
				newLapKeuMonthly.setPosLapKeu(lapKeuMonthly.getPosLapKeu());
				newLapKeuMonthly.setSubPosLapKeu(lapKeuMonthly.getSubPosLapKeu());
				newLapKeuMonthly.setSubSubPosLapKeu(lapKeuMonthly.getSubSubPosLapKeu());
				newLapKeuMonthly.setRekeningBukuBesar(lapKeuMonthly.getRekeningBukuBesar());
				newLapKeuMonthly.setValue(lapKeuMonthly.getValue());
				
				lapKeuMonthlyService.save(newLapKeuMonthly);
			}
			
			// Count Up Progress
			progressCount++;
			eodProgressService.SetNow("LKMonthly", progressCount);
			String strNote = progressCount.toString() + "/" + countAll.toString() + " item(s) proceed";
			eodProgressService.SetNote("LKMonthly", strNote);
		}
		
		// Set Progress Finish
		if (countAll == 0) {
			String strNote = progressCount.toString() + "/" + countAll.toString() + " item(s) proceed";
			eodProgressService.SetNote("LKMonthly", strNote);
			eodProgressService.FinishZero("LKMonthly");
		}
		else {
			eodProgressService.Finish("LKMonthly");
		}
		
		String strResult = "Laporan Keuangan Bulanan Finish";
		return CompletableFuture.completedFuture(strResult);
		
	}

}
