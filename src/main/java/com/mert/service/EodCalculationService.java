package com.mert.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mert.model.KodeEodUnit;
import com.mert.model.ParameterKolektibilitas;
import com.mert.model.RekeningBukuBesar;
import com.mert.model.BukuBesar;
import com.mert.model.EodTanggal;
import com.mert.model.FasilitasKredit;
import com.mert.model.RekeningKredit;
import com.mert.model.SkalaAngsuran;
import com.mert.model.DataTagihan;

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
	
	@Autowired
	private RekeningKreditService rekeningKreditService;
	
	@Autowired
	private SkalaAngsuranService skalaAngsuranService;
	
	@Autowired
	private DataTagihanService dataTagihanService;
	
	@Autowired
	private ParameterKolektibilitasService parameterKolektibilitasService;
	
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
	
	private String DateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = formatter.format(date);
		return strToday;
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
		
		String strResult = "Success1001";
		return strResult;
	}
	
	
	
	// @Async
	public String Calc1001() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1001")) {
			eodProgressService.Start("1001");
		} else {
			throw new Exception("Record EodProgress Calculation 1001 not found!");
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
				progressCount++;
				eodProgressService.SetNow("1001", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Fasilitas Kredit proceed";
				eodProgressService.SetNote("1001", strNote);
			}
			
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Fasilitas Kredit proceed";
			eodProgressService.SetNote("1001", strNote);
			eodProgressService.FinishZero("1001");
		}
		else {
			eodProgressService.Finish("1001");
		}
		
		String strResult = "Calculation 1001 Finish";
		return strResult;
	}
	
	
	
	public String Calc1002() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1002")) {
			eodProgressService.Start("1002");
		} else {
			throw new Exception("Record EodProgress Calculation 1002 not found!");
		}
		
		// Inquiry Units
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1002");
		
		Integer countAllUnit = 0;
		
		// Loop for Units Count
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = skalaAngsuranService.customEodCalculation1002Count(iUnitId, _eodTanggal);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1002", countAllUnit);
				
		Integer progressCount = 0;
		
		// Loop for Units Process
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			String noBukuBesar = kodeEodUnit.getRekBukuBesar();
			
			// Validate and Get RekeningBukuBesar
			RekeningBukuBesar rekeningBukuBesar = this.GetRekeningBukuBesar(noBukuBesar);
			
			Double saldoBukuBesar = rekeningBukuBesar.getSaldo() != null ? rekeningBukuBesar.getSaldo() : 0.0;
			
			// List SkalaAngsuran in a Unit
			List<SkalaAngsuran> listSkalaAngsuran = skalaAngsuranService.customEodCalculation1002(iUnitId, _eodTanggal);
					
			// Loop for every SkalaAngsuran
			for (SkalaAngsuran skalaAngsuran : listSkalaAngsuran) {
				String noRekening = skalaAngsuran.getNoRekening();
				Date dueDate = skalaAngsuran.getDueDate();
				Double angsuranPokok = skalaAngsuran.getAngsuranPokok() != null ? skalaAngsuran.getAngsuranPokok() : 0.0;
				Double angsuranBunga = skalaAngsuran.getAngsuranBunga() != null ? skalaAngsuran.getAngsuranBunga() : 0.0;
				
				RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
				
				Double totalPokok = rekeningKredit.getTotalPokok() != null ? rekeningKredit.getTotalPokok() : 0.0;
				Double totalBunga = rekeningKredit.getTotalBunga() != null ? rekeningKredit.getTotalBunga() : 0.0;
				Double totalDendaPokok = rekeningKredit.getTotalDendaPokok() != null ? rekeningKredit.getTotalDendaPokok() : 0.0;
				Double totalDendaBunga = rekeningKredit.getTotalDendaBunga() != null ? rekeningKredit.getTotalDendaBunga() : 0.0;
				Double totalLainnya = rekeningKredit.getTotalLainnya() != null ? rekeningKredit.getTotalLainnya() : 0.0;
				Double totalKewajiban = rekeningKredit.getTotalKewajiban() != null ? rekeningKredit.getTotalKewajiban() : 0.0;
				
				// insert new record DataTagihan
				DataTagihan dataTagihan = new DataTagihan();
				dataTagihan.setRekeningKredit(rekeningKredit);
				dataTagihan.setDueDate(dueDate);
				dataTagihan.setPokok(angsuranPokok);
				dataTagihan.setBunga(angsuranBunga);
				dataTagihanService.save(dataTagihan);
				
				// update total pokok, total bunga, & total kewajiban
				totalPokok = totalPokok + angsuranPokok;
				totalBunga = totalBunga + angsuranBunga;
				rekeningKredit.setTotalPokok(totalPokok);
				rekeningKredit.setTotalBunga(totalBunga);
				totalKewajiban = totalPokok + totalBunga + totalDendaPokok + totalDendaBunga + totalLainnya;
				rekeningKredit.setTotalKewajiban(totalKewajiban);
				rekeningKreditService.save(rekeningKredit);
				
				// update Saldo BukuBesar
				saldoBukuBesar = saldoBukuBesar + angsuranBunga;
				rekeningBukuBesar.setSaldo(saldoBukuBesar);
				rekeningBukuBesarService.save(rekeningBukuBesar);
				
				// Insert EodKalkulasi Log
				eodKalkulasiService.newEntry("1002", noRekening, angsuranBunga, noBukuBesar, null);
				
				// Count Up Progress
				progressCount++;
				eodProgressService.SetNow("1002", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
				eodProgressService.SetNote("1002", strNote);
			}
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
			eodProgressService.SetNote("1002", strNote);
			eodProgressService.FinishZero("1002");
		}
		else {
			eodProgressService.Finish("1002");
		}
		
		String strResult = "Calculation 1002 Finish";
		return strResult;
				
	}
	
	
	
	public String Calc1003() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1003")) {
			eodProgressService.Start("1003");
		} else {
			throw new Exception("Record EodProgress Calculation 1003 not found!");
		}
		
		// Inquiry Units
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1003");
		
		Integer countAllUnit = 0;
		
		// Loop for Units Count
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = fasilitasKreditService.customEodCalculation1003Count(iUnitId, _eodTanggal);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1003", countAllUnit);
		
		Integer progressCount = 0;
		
		// Loop for Units Process
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			String noBukuBesar = kodeEodUnit.getRekBukuBesar();
			
			// Validate and Get RekeningBukuBesar
			RekeningBukuBesar rekeningBukuBesar = this.GetRekeningBukuBesar(noBukuBesar);
			
			Double saldoBukuBesar = rekeningBukuBesar.getSaldo() != null ? rekeningBukuBesar.getSaldo() : 0.0;
			
			// List FasilitasKredit in a Unit
			List<FasilitasKredit> listFasilitasKredit = fasilitasKreditService.customEodCalculation1003(iUnitId, _eodTanggal);
			
			// Loop for every FasilitasKredit
			for (FasilitasKredit fasilitasKredit : listFasilitasKredit) {
				String noFasilitas = fasilitasKredit.getNoFasilitas();
				Double bprovisi = fasilitasKredit.getProvisiAmount() != null ? fasilitasKredit.getProvisiAmount() : 0.0;
				Integer tenor = fasilitasKredit.getTenor();
				
				// CALCULATION HERE
				Double calcResult = bprovisi / tenor;
				
				fasilitasKredit.setAccrualProvisi(calcResult);
				fasilitasKreditService.save(fasilitasKredit);
				
				saldoBukuBesar = saldoBukuBesar + calcResult;
				rekeningBukuBesar.setSaldo(saldoBukuBesar);
				rekeningBukuBesarService.save(rekeningBukuBesar);
				
				// Insert EodKalkulasi Log
				eodKalkulasiService.newEntry("1003", noFasilitas, calcResult, noBukuBesar, null);
				
				// Count Up Progress
				progressCount++;
				eodProgressService.SetNow("1003", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Fasilitas Kredit proceed";
				eodProgressService.SetNote("1003", strNote);
			}
			
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Fasilitas Kredit proceed";
			eodProgressService.SetNote("1003", strNote);
			eodProgressService.FinishZero("1003");
		}
		else {
			eodProgressService.Finish("1003");
		}
		
		String strResult = "Calculation 1003 Finish";
		return strResult;
	}
	
	
	
	public String Calc1004() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1004")) {
			eodProgressService.Start("1004");
		} else {
			throw new Exception("Record EodProgress Calculation 1004 not found!");
		}
		
		// Inquiry Units
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1004");
		
		Integer countAllUnit = 0;
		
		// Loop for Units Count
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = fasilitasKreditService.customEodCalculation1003Count(iUnitId, _eodTanggal);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1004", countAllUnit);
		
		Integer progressCount = 0;
		
		// Loop for Units Process
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			String noBukuBesar = kodeEodUnit.getRekBukuBesar();
			
			// Validate and Get RekeningBukuBesar
			RekeningBukuBesar rekeningBukuBesar = this.GetRekeningBukuBesar(noBukuBesar);
			
			Double saldoBukuBesar = rekeningBukuBesar.getSaldo() != null ? rekeningBukuBesar.getSaldo() : 0.0;
			
			// List FasilitasKredit in a Unit
			List<FasilitasKredit> listFasilitasKredit = fasilitasKreditService.customEodCalculation1003(iUnitId, _eodTanggal);
			
			// Loop for every FasilitasKredit
			for (FasilitasKredit fasilitasKredit : listFasilitasKredit) {
				String noFasilitas = fasilitasKredit.getNoFasilitas();
				Double plafond = fasilitasKredit.getEqvPlafond() != null ? fasilitasKredit.getEqvPlafond() : 0.0;
				Double badmin = fasilitasKredit.getAmtAdmin() != null ? fasilitasKredit.getAmtAdmin() : 0.0;
				Double bnotaris = fasilitasKredit.getAmtNotaris() != null ? fasilitasKredit.getAmtNotaris() : 0.0;
				Double basuransi = fasilitasKredit.getAmtAsuransi() != null ? fasilitasKredit.getAmtAsuransi() : 0.0;
				Double bappraisal = fasilitasKredit.getAmtAppraisal() != null ? fasilitasKredit.getAmtAppraisal() : 0.0;
				Double bmaterai = fasilitasKredit.getAmtMaterai() != null ? fasilitasKredit.getAmtMaterai() : 0.0;
				Integer tenor = fasilitasKredit.getTenor();
				
				// CALCULATION HERE
				Double calcResult = (badmin + bnotaris + basuransi + bappraisal + bmaterai) / tenor;
				
				fasilitasKredit.setAccrualAdmin(calcResult);
				fasilitasKreditService.save(fasilitasKredit);
				
				saldoBukuBesar = saldoBukuBesar + calcResult;
				rekeningBukuBesar.setSaldo(saldoBukuBesar);
				rekeningBukuBesarService.save(rekeningBukuBesar);
				
				// Insert EodKalkulasi Log
				eodKalkulasiService.newEntry("1004", noFasilitas, calcResult, noBukuBesar, null);
				
				// Count Up Progress
				progressCount++;
				eodProgressService.SetNow("1004", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Fasilitas Kredit proceed";
				eodProgressService.SetNote("1004", strNote);
			}
			
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Fasilitas Kredit proceed";
			eodProgressService.SetNote("1004", strNote);
			eodProgressService.FinishZero("1004");
		}
		else {
			eodProgressService.Finish("1004");
		}
		
		String strResult = "Calculation 1004 Finish";
		return strResult;
	}
	
	
	
	public String Calc1005() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1005")) {
			eodProgressService.Start("1005");
		} else {
			throw new Exception("Record EodProgress Calculation 1005 not found!");
		}
		
		// List Unit-Unit yang ada di Kode EOD
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1005");
		
		Integer countAllUnit = 0;
		
		// Loop per Unit (untuk counting)
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = dataTagihanService.customEodCalculation1005A(iUnitId, _eodTanggal);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1005", countAllUnit);
				
		Integer progressCount = 0;
		
		// Loop per Unit
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			String noBukuBesar = kodeEodUnit.getRekBukuBesar();
			
			// Validate and Get RekeningBukuBesar
			RekeningBukuBesar rekeningBukuBesar = this.GetRekeningBukuBesar(noBukuBesar);
			
			Double saldoBukuBesar = rekeningBukuBesar.getSaldo() != null ? rekeningBukuBesar.getSaldo() : 0.0;
			
			// List No Rekening per Unit
			List<String> listRekening = dataTagihanService.customEodCalculation1005B(iUnitId, _eodTanggal);
			
			for(String noRekening : listRekening) {
				
				RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
				
				Double pinaltiPokok = rekeningKredit.getPinaltiPokokPersen() != null ? rekeningKredit.getPinaltiPokokPersen() : 0.0;
				Double totalPokok = rekeningKredit.getTotalPokok() != null ? rekeningKredit.getTotalPokok() : 0.0;
				Double totalDendaPokok = rekeningKredit.getTotalDendaPokok() != null ? rekeningKredit.getTotalDendaPokok() : 0.0;
				Double totalKewajiban = rekeningKredit.getTotalKewajiban() != null ? rekeningKredit.getTotalKewajiban() : 0.0;
				
				// List DataTagihan per Rekening
				List<DataTagihan> listDataTagihan = dataTagihanService.customEodCalculation1005C(iUnitId, _eodTanggal, noRekening);
				
				// Loop for every DataTagihan
				for (DataTagihan dataTagihan : listDataTagihan) {
					Double dendaPokok = dataTagihan.getDendaPokok() != null ? dataTagihan.getDendaPokok() : 0.0;
					Date dueDate = dataTagihan.getDueDate();
					
					// CALCULATION HERE
					Double calcResult = ((pinaltiPokok / 100.0) / 366.0) * totalPokok;
					dendaPokok = dendaPokok + calcResult;
					totalDendaPokok = totalDendaPokok + calcResult;
					totalKewajiban = totalKewajiban + calcResult;
					
					// update DataTagihan
					dataTagihan.setDendaPokok(dendaPokok);
					dataTagihanService.save(dataTagihan);
					
					// update TotalDendaPokok, TotalKewajiban
					rekeningKredit.setTotalDendaPokok(totalDendaPokok);
					rekeningKredit.setTotalKewajiban(totalKewajiban);
					rekeningKreditService.save(rekeningKredit);
					
					// update Saldo BukuBesar
					saldoBukuBesar = saldoBukuBesar + calcResult;
					rekeningBukuBesar.setSaldo(saldoBukuBesar);
					rekeningBukuBesarService.save(rekeningBukuBesar);
					
					// Insert EodKalkulasi Log
					eodKalkulasiService.newEntry("1005", noRekening + "/" + this.DateToString(dueDate), calcResult, noBukuBesar, null);
					
				}
				
				// Count Up Progress per Rekening
				progressCount++;
				eodProgressService.SetNow("1005", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
				eodProgressService.SetNote("1005", strNote);
				
			}
			
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
			eodProgressService.SetNote("1005", strNote);
			eodProgressService.FinishZero("1005");
		}
		else {
			eodProgressService.Finish("1005");
		}
		
		String strResult = "Calculation 1005 Finish";
		return strResult;
				
	}
	
	
	
	public String Calc1006() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1006")) {
			eodProgressService.Start("1006");
		} else {
			throw new Exception("Record EodProgress Calculation 1006 not found!");
		}
		
		// List Unit-Unit yang ada di Kode EOD
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1006");
		
		Integer countAllUnit = 0;
		
		// Loop per Unit (untuk counting)
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = dataTagihanService.customEodCalculation1006A(iUnitId, _eodTanggal);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1006", countAllUnit);
				
		Integer progressCount = 0;
		
		// Loop per Unit
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			String noBukuBesar = kodeEodUnit.getRekBukuBesar();
			
			// Validate and Get RekeningBukuBesar
			RekeningBukuBesar rekeningBukuBesar = this.GetRekeningBukuBesar(noBukuBesar);
			
			Double saldoBukuBesar = rekeningBukuBesar.getSaldo() != null ? rekeningBukuBesar.getSaldo() : 0.0;
			
			// List No Rekening per Unit
			List<String> listRekening = dataTagihanService.customEodCalculation1006B(iUnitId, _eodTanggal);
			
			for(String noRekening : listRekening) {
				
				RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
				
				Double pinaltiBunga = rekeningKredit.getPinaltiBungaPersen() != null ? rekeningKredit.getPinaltiBungaPersen() : 0.0;
				Double totalBunga = rekeningKredit.getTotalBunga() != null ? rekeningKredit.getTotalBunga() : 0.0;
				Double totalDendaBunga = rekeningKredit.getTotalDendaBunga() != null ? rekeningKredit.getTotalDendaBunga() : 0.0;
				Double totalKewajiban = rekeningKredit.getTotalKewajiban() != null ? rekeningKredit.getTotalKewajiban() : 0.0;
				
				// List DataTagihan per Rekening
				List<DataTagihan> listDataTagihan = dataTagihanService.customEodCalculation1006C(iUnitId, _eodTanggal, noRekening);
				
				// Loop for every DataTagihan
				for (DataTagihan dataTagihan : listDataTagihan) {
					Double dendaBunga = dataTagihan.getDendaBunga() != null ? dataTagihan.getDendaBunga() : 0.0;
					Date dueDate = dataTagihan.getDueDate();
					
					// CALCULATION HERE
					Double calcResult = ((pinaltiBunga / 100.0) / 366.0) * totalBunga;
					dendaBunga = dendaBunga + calcResult;
					totalDendaBunga = totalDendaBunga + calcResult;
					totalKewajiban = totalKewajiban + calcResult;
					
					// update DataTagihan
					dataTagihan.setDendaBunga(dendaBunga);
					dataTagihanService.save(dataTagihan);
					
					// update TotalDendaBunga, TotalKewajiban
					rekeningKredit.setTotalDendaBunga(totalDendaBunga);
					rekeningKredit.setTotalKewajiban(totalKewajiban);
					rekeningKreditService.save(rekeningKredit);
					
					// update Saldo BukuBesar
					saldoBukuBesar = saldoBukuBesar + calcResult;
					rekeningBukuBesar.setSaldo(saldoBukuBesar);
					rekeningBukuBesarService.save(rekeningBukuBesar);
					
					// Insert EodKalkulasi Log
					eodKalkulasiService.newEntry("1006", noRekening + "/" + this.DateToString(dueDate), calcResult, noBukuBesar, null);
					
				}
				
				// Count Up Progress per Rekening
				progressCount++;
				eodProgressService.SetNow("1006", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
				eodProgressService.SetNote("1006", strNote);
				
			}
			
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
			eodProgressService.SetNote("1006", strNote);
			eodProgressService.FinishZero("1006");
		}
		else {
			eodProgressService.Finish("1006");
		}
		
		String strResult = "Calculation 1006 Finish";
		return strResult;
				
	}
	
	
	
	public String Calc1007() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1007")) {
			eodProgressService.Start("1007");
		} else {
			throw new Exception("Record EodProgress Calculation 1007 not found!");
		}
		
		// List Unit-Unit yang ada di Kode EOD
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1007");
		
		Integer countAllUnit = 0;
		
		// Loop per Unit (untuk counting)
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = dataTagihanService.customEodCalculation1007A(iUnitId, _eodTanggal);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1007", countAllUnit);
				
		Integer progressCount = 0;
		
		// Loop per Unit
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// List No Rekening per Unit
			List<String> listRekening = dataTagihanService.customEodCalculation1007B(iUnitId, _eodTanggal);
			
			for(String noRekening : listRekening) {
				
				RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
				Double totalKewajiban = rekeningKredit.getTotalKewajiban() != null ? rekeningKredit.getTotalKewajiban() : 0.0;
				
				Integer maxDpd = 0;
				Integer iDpd = null;
				
				// List DataTagihan per Rekening
				List<DataTagihan> listDataTagihan = dataTagihanService.customEodCalculation1007C(iUnitId, _eodTanggal, noRekening);
				
				// Loop for every DataTagihan
				for (DataTagihan dataTagihan : listDataTagihan) {
					Date dueDate = dataTagihan.getDueDate();
					Date dDueDate = this.removeTime(dueDate);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String sDueDate = sdf.format(dDueDate);
					
					// CALCULATION HERE
					LocalDate dEodDate = LocalDate.parse(_eodTanggal);
					LocalDate dDDueDate = LocalDate.parse(sDueDate);
					long dpd = ChronoUnit.DAYS.between(dDDueDate, dEodDate);
					iDpd = (int)dpd;
					if (totalKewajiban <= 0.001) {
						iDpd = 0;
					}
					
					// update DataTagihan
					dataTagihan.setDpd(iDpd);
					dataTagihanService.save(dataTagihan);
					
					if (iDpd > maxDpd) {
						maxDpd = iDpd;
					}
					
					// Insert EodKalkulasi Log
					Double dDpd = new Double(iDpd);
					eodKalkulasiService.newEntry("1007", noRekening + "/" + this.DateToString(dueDate), dDpd, null, "DPD");
					
				}
				
				// Calculate Kolektibilitas
				ParameterKolektibilitas kolektibilitas;
				if (totalKewajiban <= 0.001) {
					kolektibilitas = parameterKolektibilitasService.findOne("1");
				} else {
					if ((iDpd >= 1) && (iDpd <= 90)) {
						kolektibilitas = parameterKolektibilitasService.findOne("2");
					} else if ((iDpd >= 91) && (iDpd <= 120)) {
						kolektibilitas = parameterKolektibilitasService.findOne("3");
					} else if ((iDpd >= 121) && (iDpd <= 180)) {
						kolektibilitas = parameterKolektibilitasService.findOne("4");
					} else if (iDpd > 180) {
						kolektibilitas = parameterKolektibilitasService.findOne("5");
					} else {
						kolektibilitas = parameterKolektibilitasService.findOne("5");
					}
				}
				
				// Update RekeningKredit
				rekeningKredit.setStatusKolektibilitas(kolektibilitas);
				rekeningKreditService.save(rekeningKredit);
				
				// Insert EodKalkulasi Log
				Double dKolektibilitas = new Double(kolektibilitas.getKolektibilitasId());
				eodKalkulasiService.newEntry("1007", noRekening, dKolektibilitas, null, "Kolektibilitas");
				
				// Count Up Progress per Rekening
				progressCount++;
				eodProgressService.SetNow("1007", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
				eodProgressService.SetNote("1007", strNote);
				
			}
			
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
			eodProgressService.SetNote("1007", strNote);
			eodProgressService.FinishZero("1007");
		}
		else {
			eodProgressService.Finish("1007");
		}
		
		String strResult = "Calculation 1007 Finish";
		return strResult;
				
	}
	
	
	
	public String Calc1008() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1008")) {
			eodProgressService.Start("1008");
		} else {
			throw new Exception("Record EodProgress Calculation 1008 not found!");
		}
		
		// List Unit-Unit yang ada di Kode EOD
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1008");
		
		Integer countAllUnit = 0;
		
		// Loop per Unit (untuk counting)
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = dataTagihanService.customEodCalculation1008A(iUnitId, _eodTanggal);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1008", countAllUnit);
				
		Integer progressCount = 0;
		
		// Loop per Unit
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			String noBukuBesar = kodeEodUnit.getRekBukuBesar();
			
			// Validate and Get RekeningBukuBesar
			RekeningBukuBesar rekeningBukuBesar = this.GetRekeningBukuBesar(noBukuBesar);
			
			Double saldoBukuBesar = rekeningBukuBesar.getSaldo() != null ? rekeningBukuBesar.getSaldo() : 0.0;
			
			// List No Rekening per Unit
			List<String> listRekening = dataTagihanService.customEodCalculation1008B(iUnitId, _eodTanggal);
			
			for(String noRekening : listRekening) {
				
				// List DataTagihan per Rekening
				List<DataTagihan> listDataTagihan = dataTagihanService.customEodCalculation1008C(iUnitId, _eodTanggal, noRekening);
				
				// Loop for every DataTagihan
				for (DataTagihan dataTagihan : listDataTagihan) {
					Double bunga = dataTagihan.getBunga() != null ? dataTagihan.getBunga() : 0.0;
					Date dueDate = dataTagihan.getDueDate();
					
					// update Saldo BukuBesar
					saldoBukuBesar = saldoBukuBesar + bunga;
					rekeningBukuBesar.setSaldo(saldoBukuBesar);
					rekeningBukuBesarService.save(rekeningBukuBesar);
					
					// Insert EodKalkulasi Log
					eodKalkulasiService.newEntry("1008", noRekening + "/" + this.DateToString(dueDate), bunga, noBukuBesar, null);
					
				}
				
				// Count Up Progress per Rekening
				progressCount++;
				eodProgressService.SetNow("1008", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
				eodProgressService.SetNote("1008", strNote);
				
			}
			
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
			eodProgressService.SetNote("1008", strNote);
			eodProgressService.FinishZero("1008");
		}
		else {
			eodProgressService.Finish("1008");
		}
		
		String strResult = "Calculation 1008 Finish";
		return strResult;
				
	}
	
	
	
	public String Calc1009() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1009")) {
			eodProgressService.Start("1009");
		} else {
			throw new Exception("Record EodProgress Calculation 1009 not found!");
		}
		
		// Inquiry Units
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1009");
		
		Integer countAllUnit = 0;
		
		// Loop for Units Count
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = fasilitasKreditService.customEodCalculation1009Count(iUnitId, _eodTanggal);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1009", countAllUnit);
		
		Integer progressCount = 0;
		
		// Loop for Units Process
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// List FasilitasKredit in a Unit
			List<FasilitasKredit> listFasilitasKredit = fasilitasKreditService.customEodCalculation1009(iUnitId, _eodTanggal);
			
			// Loop for every FasilitasKredit
			for (FasilitasKredit fasilitasKredit : listFasilitasKredit) {
				String noFasilitas = fasilitasKredit.getNoFasilitas();
				Double accrualProvisi = fasilitasKredit.getAccrualProvisi() != null ? fasilitasKredit.getAccrualProvisi() : 0.0;
				
				// Insert EodKalkulasi Log
				eodKalkulasiService.newEntry("1009", noFasilitas, accrualProvisi, null, null);
				
				// Count Up Progress
				progressCount++;
				eodProgressService.SetNow("1009", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Fasilitas Kredit proceed";
				eodProgressService.SetNote("1009", strNote);
			}
			
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Fasilitas Kredit proceed";
			eodProgressService.SetNote("1009", strNote);
			eodProgressService.FinishZero("1009");
		}
		else {
			eodProgressService.Finish("1009");
		}
		
		String strResult = "Calculation 1009 Finish";
		return strResult;
	}
	
	
	
	public String Calc1010() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1010")) {
			eodProgressService.Start("1010");
		} else {
			throw new Exception("Record EodProgress Calculation 1010 not found!");
		}
		
		// Inquiry Units
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1010");
		
		Integer countAllUnit = 0;
		
		// Loop for Units Count
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = fasilitasKreditService.customEodCalculation1009Count(iUnitId, _eodTanggal);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1010", countAllUnit);
		
		Integer progressCount = 0;
		
		// Loop for Units Process
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// List FasilitasKredit in a Unit
			List<FasilitasKredit> listFasilitasKredit = fasilitasKreditService.customEodCalculation1009(iUnitId, _eodTanggal);
			
			// Loop for every FasilitasKredit
			for (FasilitasKredit fasilitasKredit : listFasilitasKredit) {
				String noFasilitas = fasilitasKredit.getNoFasilitas();
				Double accrualAdmin = fasilitasKredit.getAccrualAdmin() != null ? fasilitasKredit.getAccrualAdmin() : 0.0;
				
				// Insert EodKalkulasi Log
				eodKalkulasiService.newEntry("1010", noFasilitas, accrualAdmin, null, null);
				
				// Count Up Progress
				progressCount++;
				eodProgressService.SetNow("1010", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Fasilitas Kredit proceed";
				eodProgressService.SetNote("1010", strNote);
			}
			
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Fasilitas Kredit proceed";
			eodProgressService.SetNote("1010", strNote);
			eodProgressService.FinishZero("1010");
		}
		else {
			eodProgressService.Finish("1010");
		}
		
		String strResult = "Calculation 1010 Finish";
		return strResult;
	}
	
	
	
	public String Calc1011() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Initiate Progress Status
		if (eodProgressService.Validate("1011")) {
			eodProgressService.Start("1011");
		} else {
			throw new Exception("Record EodProgress Calculation 1011 not found!");
		}
		
		// Inquiry Units
		List<KodeEodUnit> listKodeEodUnit = kodeEodUnitService.findByKodeEod("1011");
		
		Integer countAllUnit = 0;
		
		// Loop for Units Count
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = rekeningKreditService.customEodCalculation1011Count(iUnitId);
			countAllUnit = countAllUnit + countUnit;
		}
		
		// Set Progress Status Max Value
		eodProgressService.SetMax("1011", countAllUnit);
		
		Integer progressCount = 0;
		
		// Loop for Units Process
		for(KodeEodUnit kodeEodUnit : listKodeEodUnit) {
			String iUnitId = kodeEodUnit.getUnitId();
			
			// List FasilitasKredit in a Unit
			List<RekeningKredit> listRekeningKredit = rekeningKreditService.customEodCalculation1011(iUnitId);
			
			// Loop for every FasilitasKredit
			for (RekeningKredit rekeningKredit : listRekeningKredit) {
				String noRekening = rekeningKredit.getNoRekening();
				Double bakiDebet = rekeningKredit.getBakiDebet() != null ? rekeningKredit.getBakiDebet() : 0.0;
				Double pinaltiLunasPersen = rekeningKredit.getPinaltiLunasPersen() != null ? rekeningKredit.getPinaltiLunasPersen() : 0.0;
				pinaltiLunasPersen = pinaltiLunasPersen / 100.0;
				String pinaltiFlag = rekeningKredit.getPinaltiFlag().getPinaltiId();
				Double sisaAngsuran = 0.0;
				Double nilaiPinalti = 0.0;
				
				// CALCULATION HERE
				if (pinaltiFlag.equals("A")) { /* Berdasarkan Sisa Angsuran */
					
					sisaAngsuran = rekeningKreditService.customEodCalculation1011SisaAngsuran(noRekening, _eodTanggal);
					nilaiPinalti = pinaltiLunasPersen * sisaAngsuran;
					
				} else if (pinaltiFlag.equals("B")) { /* Berdasarkan Baki Debet */
					
					nilaiPinalti = pinaltiLunasPersen * bakiDebet;
					
				} else {
					throw new Exception("Pinalti Flag kosong! No Rekening : " + noRekening);
				}
				
				// Update Nilai Pinalti to table Rekening Kredit
				rekeningKredit.setNilaiPinalti(nilaiPinalti);
				rekeningKreditService.save(rekeningKredit);
				
				// Insert EodKalkulasi Log
				eodKalkulasiService.newEntry("1011", noRekening, nilaiPinalti, null, null);
				
				// Count Up Progress
				progressCount++;
				eodProgressService.SetNow("1011", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
				eodProgressService.SetNote("1011", strNote);
			}
			
		}
		
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " Rekening Kredit proceed";
			eodProgressService.SetNote("1011", strNote);
			eodProgressService.FinishZero("1011");
		}
		else {
			eodProgressService.Finish("1011");
		}
		
		String strResult = "Calculation 1011 Finish";
		return strResult;
	}
	
	
	
	public String Calc1012() throws Exception{
		
		
		return "";
	}
	
	
	
	public String Calc1013() throws Exception{
		
		
		return "";
	}
	
	
	
	public String Calc1014() throws Exception{
		
		
		return "";
	}
	
	
	
	public String Calc1015() throws Exception{
		
		
		return "";
	}
	
	
	
	public String Calc1016() throws Exception{
		
		
		return "";
	}

}
