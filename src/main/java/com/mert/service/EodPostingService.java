package com.mert.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.BukuBesar;
import com.mert.model.RekeningBukuBesar;
import com.mert.model.KodeTranUnit;
import com.mert.model.EodKalkulasi;
import com.mert.model.RekeningKredit;
import com.mert.model.EodUnitStatus;

@Service
public class EodPostingService {
	
	@Autowired
	private EodTanggalService eodTanggalService;
	
	@Autowired
	private EodProgressService eodProgressService;
	
	@Autowired
	private BukuBesarService bukuBesarService;
	
	@Autowired
	private RekeningBukuBesarService rekeningBukuBesarService;
	
	@Autowired
	private KodeTranUnitService kodeTranUnitService;
	
	@Autowired
	private EodKalkulasiService eodKalkulasiService;
	
	@Autowired
	private EodPostService eodPostService;
	
	@Autowired
	private RekeningKreditService rekeningKreditService;
	
	@Autowired
	private EodUnitStatusService eodUnitStatusService;
	
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
	
	private void CheckIfAllUnitsAndUsersAreClosed() throws Exception {
		List<EodUnitStatus> listEodUnitStatus = eodUnitStatusService.findOpenUnitsAndUsers();
		if (listEodUnitStatus != null) {
			throw new Exception("Masih ada unit atau user dengan status open!");
		}
	}
		
		
		
	public String Post4004() throws Exception {
			
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Cek apakah masih ada unit atau user yang terbuka
		CheckIfAllUnitsAndUsersAreClosed();
			
		// Initiate Progress Status
		if (eodProgressService.Validate("4004")) {
			eodProgressService.Start("4004");
		} else {
			throw new Exception("Record EodProgress Calculation 4004 not found!");
		}
			
		// Inquiry Units
		List<KodeTranUnit> listKodeTranUnit = kodeTranUnitService.findByKoTran("4004");
			
		Integer countAllUnit = 0;
			
		// Loop for Units Count
		for (KodeTranUnit kodeTranUnit : listKodeTranUnit) {
			String iUnitId = kodeTranUnit.getUnitId();
				
			// Count per Unit
			Integer countUnit = rekeningKreditService.customEodPosting4004Count(iUnitId);
			countAllUnit = countAllUnit + countUnit;
		}
			
		// Set Progress Status Max Value
		eodProgressService.SetMax("4004", countAllUnit);
			
		Integer progressCount = 0;
			
		// Loop for Units Process
		for (KodeTranUnit kodeTranUnit : listKodeTranUnit) {
			String iUnitId = kodeTranUnit.getUnitId();
			String noBukuBesarDebit = kodeTranUnit.getBukuBesarDebit();
				
			// Validate and Get RekeningBukuBesar
			RekeningBukuBesar rekeningBukuBesarDebit = this.GetRekeningBukuBesar(noBukuBesarDebit);
				
			Double saldoBukuBesarDebit = rekeningBukuBesarDebit.getSaldo() != null ? rekeningBukuBesarDebit.getSaldo() : 0.0;
				
			// List RekeningKredit in Unit
			List<RekeningKredit> listRekeningKredit = rekeningKreditService.customEodPosting4004(iUnitId);
				
			// Loop for every RekeningKredit
			for (RekeningKredit rekeningKredit : listRekeningKredit) {
					
				String noRekening = rekeningKredit.getNoRekening();
				Double disburse = rekeningKredit.getDisburse() != null ? rekeningKredit.getDisburse() : 0.0;
					
				// update Saldo BukuBesar
				saldoBukuBesarDebit = saldoBukuBesarDebit - disburse;
				rekeningBukuBesarDebit.setSaldo(saldoBukuBesarDebit);
				rekeningBukuBesarService.save(rekeningBukuBesarDebit);
					
				// Insert EodPost
				eodPostService.newEntry("4004", iUnitId, noRekening, null, noBukuBesarDebit, disburse, null, null, null);
					
				// Count Up Progress
				progressCount++;
				eodProgressService.SetNow("4004", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " item(s) proceed";
				eodProgressService.SetNote("4004", strNote);
					
			}
		}
			
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " item(s) proceed";
			eodProgressService.SetNote("4004", strNote);
			eodProgressService.FinishZero("4004");
		}
		else {
			eodProgressService.Finish("4004");
		}
			
		String strResult = "Posting 4004 Finish";
		return strResult;
	}
	
	
	
	public String Post4005() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Cek apakah masih ada unit atau user yang terbuka
		CheckIfAllUnitsAndUsersAreClosed();
			
		// Initiate Progress Status
		if (eodProgressService.Validate("4005")) {
			eodProgressService.Start("4005");
		} else {
			throw new Exception("Record EodProgress Calculation 4005 not found!");
		}
			
		// Inquiry Units
		List<KodeTranUnit> listKodeTranUnit = kodeTranUnitService.findByKoTran("4005");
			
		Integer countAllUnit = 0;
			
		// Loop for Units Count
		for (KodeTranUnit kodeTranUnit : listKodeTranUnit) {
			String iUnitId = kodeTranUnit.getUnitId();
				
			// Count per Unit
			Integer countUnit = eodKalkulasiService.CountOnTanggalKodeEodUnit(_eodTanggal, "1008", iUnitId);
			countAllUnit = countAllUnit + countUnit;
		}
			
		// Set Progress Status Max Value
		eodProgressService.SetMax("4005", countAllUnit);
			
		Integer progressCount = 0;
			
		// Loop for Units Process
		for (KodeTranUnit kodeTranUnit : listKodeTranUnit) {
			String iUnitId = kodeTranUnit.getUnitId();
			String noBukuBesarDebit = kodeTranUnit.getBukuBesarDebit();
			String noBukuBesarKredit = kodeTranUnit.getBukuBesarKredit();
				
			// Validate and Get RekeningBukuBesar Debit
			RekeningBukuBesar rekeningBukuBesarDebit = this.GetRekeningBukuBesar(noBukuBesarDebit);
			Double saldoBukuBesarDebit = rekeningBukuBesarDebit.getSaldo() != null ? rekeningBukuBesarDebit.getSaldo() : 0.0;
			
			// Validate and Get RekeningBukuBesar Kredit
			RekeningBukuBesar rekeningBukuBesarKredit = this.GetRekeningBukuBesar(noBukuBesarKredit);
			Double saldoBukuBesarKredit = rekeningBukuBesarKredit.getSaldo() != null ? rekeningBukuBesarKredit.getSaldo() : 0.0;
				
			// List EodKalkulasi in Unit
			List<EodKalkulasi> listEodKalkulasi = eodKalkulasiService.ListOnTanggalKodeEodUnit(_eodTanggal, "1008", iUnitId);
				
			// Loop for every RekeningKredit
			for (EodKalkulasi eodKalkulasi : listEodKalkulasi) {
					
				Date eodTanggal = eodKalkulasi.getEodTanggal();
				String calcObject = eodKalkulasi.getCalcObject();
				String subObject = eodKalkulasi.getSubObject();
				Double calcValue = eodKalkulasi.getCalcValue() != null ? eodKalkulasi.getCalcValue() : 0.0;
					
				// TRANSAKSI DEBIT
				saldoBukuBesarDebit = saldoBukuBesarDebit - calcValue;
				rekeningBukuBesarDebit.setSaldo(saldoBukuBesarDebit);
				rekeningBukuBesarService.save(rekeningBukuBesarDebit);
					
				// Insert EodPost Debit
				eodPostService.newEntry("4005", iUnitId, calcObject, subObject, noBukuBesarDebit, calcValue, null, null, null);
				
				// TRANSAKSI KREDIT
				saldoBukuBesarKredit = saldoBukuBesarKredit + calcValue;
				rekeningBukuBesarKredit.setSaldo(saldoBukuBesarKredit);
				rekeningBukuBesarService.save(rekeningBukuBesarKredit);
					
				// Insert EodPost Debit
				eodPostService.newEntry("4005", iUnitId, calcObject, subObject, null, null, noBukuBesarKredit, calcValue, null);
					
				// Count Up Progress
				progressCount++;
				eodProgressService.SetNow("4005", progressCount);
				String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " item(s) proceed";
				eodProgressService.SetNote("4005", strNote);
					
			}
		}
			
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " item(s) proceed";
			eodProgressService.SetNote("4005", strNote);
			eodProgressService.FinishZero("4005");
		}
		else {
			eodProgressService.Finish("4005");
		}
			
		String strResult = "Posting 4005 Finish";
		return strResult;
	}
	
	
	
	public String Post4006() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Cek apakah masih ada unit atau user yang terbuka
		CheckIfAllUnitsAndUsersAreClosed();
			
		// Initiate Progress Status
		if (eodProgressService.Validate("4006")) {
			eodProgressService.Start("4006");
		} else {
			throw new Exception("Record EodProgress Calculation 4006 not found!");
		}
			
		// Inquiry Units
		List<KodeTranUnit> listKodeTranUnit = kodeTranUnitService.findByKoTran("4006");
			
		Integer countAllUnit = 0;
			
		// Loop for Units Count
		for (KodeTranUnit kodeTranUnit : listKodeTranUnit) {
			String iUnitId = kodeTranUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = eodKalkulasiService.CountOnTanggalKodeEodUnit(_eodTanggal, "1009", iUnitId);
			countAllUnit = countAllUnit + countUnit;
		}
			
		// Set Progress Status Max Value
		eodProgressService.SetMax("4006", countAllUnit);
			
		Integer progressCount = 0;
			
		// Loop for Units Process
		for (KodeTranUnit kodeTranUnit : listKodeTranUnit) {
			String iUnitId = kodeTranUnit.getUnitId();
			String noBukuBesarDebit = kodeTranUnit.getBukuBesarDebit();
			String noBukuBesarKredit = kodeTranUnit.getBukuBesarKredit();
			
			// Validate and Get RekeningBukuBesar Debit
			RekeningBukuBesar rekeningBukuBesarDebit = this.GetRekeningBukuBesar(noBukuBesarDebit);
			Double saldoBukuBesarDebit = rekeningBukuBesarDebit.getSaldo() != null ? rekeningBukuBesarDebit.getSaldo() : 0.0;
			
			// Validate and Get RekeningBukuBesar Kredit
			RekeningBukuBesar rekeningBukuBesarKredit = this.GetRekeningBukuBesar(noBukuBesarKredit);
			Double saldoBukuBesarKredit = rekeningBukuBesarKredit.getSaldo() != null ? rekeningBukuBesarKredit.getSaldo() : 0.0;
			
			// List EodKalkulasi in Unit
			List<EodKalkulasi> listEodKalkulasi = eodKalkulasiService.ListOnTanggalKodeEodUnit(_eodTanggal, "1009", iUnitId);
			
			// Loop for every RekeningKredit
			for (EodKalkulasi eodKalkulasi : listEodKalkulasi) {
				
			Date eodTanggal = eodKalkulasi.getEodTanggal();
			String calcObject = eodKalkulasi.getCalcObject();
			String subObject = eodKalkulasi.getSubObject();
			Double calcValue = eodKalkulasi.getCalcValue() != null ? eodKalkulasi.getCalcValue() : 0.0;
				
			// TRANSAKSI DEBIT
			saldoBukuBesarDebit = saldoBukuBesarDebit - calcValue;
			rekeningBukuBesarDebit.setSaldo(saldoBukuBesarDebit);
			rekeningBukuBesarService.save(rekeningBukuBesarDebit);
				
			// Insert EodPost Debit
			eodPostService.newEntry("4006", iUnitId, calcObject, subObject, noBukuBesarDebit, calcValue, null, null, null);
			
			// TRANSAKSI KREDIT
			saldoBukuBesarKredit = saldoBukuBesarKredit + calcValue;
			rekeningBukuBesarKredit.setSaldo(saldoBukuBesarKredit);
			rekeningBukuBesarService.save(rekeningBukuBesarKredit);
				
			// Insert EodPost Debit
			eodPostService.newEntry("4006", iUnitId, calcObject, subObject, null, null, noBukuBesarKredit, calcValue, null);
				
			// Count Up Progress
			progressCount++;
			eodProgressService.SetNow("4006", progressCount);
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " item(s) proceed";
			eodProgressService.SetNote("4006", strNote);
				
			}
		}
			
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " item(s) proceed";
			eodProgressService.SetNote("4006", strNote);
			eodProgressService.FinishZero("4006");
		}
		else {
			eodProgressService.Finish("4006");
		}
			
		String strResult = "Posting 4006 Finish";
		return strResult;
	}
	
	
	
	public String Post4007() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Cek apakah masih ada unit atau user yang terbuka
		CheckIfAllUnitsAndUsersAreClosed();
			
		// Initiate Progress Status
		if (eodProgressService.Validate("4007")) {
			eodProgressService.Start("4007");
		} else {
			throw new Exception("Record EodProgress Calculation 4007 not found!");
		}
			
		// Inquiry Units
		List<KodeTranUnit> listKodeTranUnit = kodeTranUnitService.findByKoTran("4007");
			
		Integer countAllUnit = 0;
			
		// Loop for Units Count
		for (KodeTranUnit kodeTranUnit : listKodeTranUnit) {
			String iUnitId = kodeTranUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = eodKalkulasiService.CountOnTanggalKodeEodUnit(_eodTanggal, "1010", iUnitId);
			countAllUnit = countAllUnit + countUnit;
		}
			
		// Set Progress Status Max Value
		eodProgressService.SetMax("4007", countAllUnit);
			
		Integer progressCount = 0;
			
		// Loop for Units Process
		for (KodeTranUnit kodeTranUnit : listKodeTranUnit) {
			String iUnitId = kodeTranUnit.getUnitId();
			String noBukuBesarDebit = kodeTranUnit.getBukuBesarDebit();
			String noBukuBesarKredit = kodeTranUnit.getBukuBesarKredit();
			
			// Validate and Get RekeningBukuBesar Debit
			RekeningBukuBesar rekeningBukuBesarDebit = this.GetRekeningBukuBesar(noBukuBesarDebit);
			Double saldoBukuBesarDebit = rekeningBukuBesarDebit.getSaldo() != null ? rekeningBukuBesarDebit.getSaldo() : 0.0;
			
			// Validate and Get RekeningBukuBesar Kredit
			RekeningBukuBesar rekeningBukuBesarKredit = this.GetRekeningBukuBesar(noBukuBesarKredit);
			Double saldoBukuBesarKredit = rekeningBukuBesarKredit.getSaldo() != null ? rekeningBukuBesarKredit.getSaldo() : 0.0;
			
			// List EodKalkulasi in Unit
			List<EodKalkulasi> listEodKalkulasi = eodKalkulasiService.ListOnTanggalKodeEodUnit(_eodTanggal, "1010", iUnitId);
			
			// Loop for every RekeningKredit
			for (EodKalkulasi eodKalkulasi : listEodKalkulasi) {
				
			Date eodTanggal = eodKalkulasi.getEodTanggal();
			String calcObject = eodKalkulasi.getCalcObject();
			String subObject = eodKalkulasi.getSubObject();
			Double calcValue = eodKalkulasi.getCalcValue() != null ? eodKalkulasi.getCalcValue() : 0.0;
				
			// TRANSAKSI DEBIT
			saldoBukuBesarDebit = saldoBukuBesarDebit - calcValue;
			rekeningBukuBesarDebit.setSaldo(saldoBukuBesarDebit);
			rekeningBukuBesarService.save(rekeningBukuBesarDebit);
				
			// Insert EodPost Debit
			eodPostService.newEntry("4007", iUnitId, calcObject, subObject, noBukuBesarDebit, calcValue, null, null, null);
			
			// TRANSAKSI KREDIT
			saldoBukuBesarKredit = saldoBukuBesarKredit + calcValue;
			rekeningBukuBesarKredit.setSaldo(saldoBukuBesarKredit);
			rekeningBukuBesarService.save(rekeningBukuBesarKredit);
				
			// Insert EodPost Debit
			eodPostService.newEntry("4007", iUnitId, calcObject, subObject, null, null, noBukuBesarKredit, calcValue, null);
				
			// Count Up Progress
			progressCount++;
			eodProgressService.SetNow("4007", progressCount);
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " item(s) proceed";
			eodProgressService.SetNote("4007", strNote);
				
			}
		}
			
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " item(s) proceed";
			eodProgressService.SetNote("4007", strNote);
			eodProgressService.FinishZero("4007");
		}
		else {
			eodProgressService.Finish("4007");
		}
			
		String strResult = "Posting 4007 Finish";
		return strResult;
	}
	
	
	
	public String Post4008() throws Exception {
		
		// Request EodTanggal
		this.requestEodTanggal();
		
		// Cek apakah masih ada unit atau user yang terbuka
		CheckIfAllUnitsAndUsersAreClosed();
			
		// Initiate Progress Status
		if (eodProgressService.Validate("4008")) {
			eodProgressService.Start("4008");
		} else {
			throw new Exception("Record EodProgress Calculation 4008 not found!");
		}
			
		// Inquiry Units
		List<KodeTranUnit> listKodeTranUnit = kodeTranUnitService.findByKoTran("4008");
			
		Integer countAllUnit = 0;
			
		// Loop for Units Count
		for (KodeTranUnit kodeTranUnit : listKodeTranUnit) {
			String iUnitId = kodeTranUnit.getUnitId();
			
			// Count per Unit
			Integer countUnit = eodKalkulasiService.CountOnTanggalKodeEodUnit(_eodTanggal, "1013", iUnitId);
			countAllUnit = countAllUnit + countUnit;
		}
			
		// Set Progress Status Max Value
		eodProgressService.SetMax("4008", countAllUnit);
			
		Integer progressCount = 0;
			
		// Loop for Units Process
		for (KodeTranUnit kodeTranUnit : listKodeTranUnit) {
			String iUnitId = kodeTranUnit.getUnitId();
			String noBukuBesarDebit = kodeTranUnit.getBukuBesarDebit();
			String noBukuBesarKredit = kodeTranUnit.getBukuBesarKredit();
			
			// Validate and Get RekeningBukuBesar Debit
			RekeningBukuBesar rekeningBukuBesarDebit = this.GetRekeningBukuBesar(noBukuBesarDebit);
			Double saldoBukuBesarDebit = rekeningBukuBesarDebit.getSaldo() != null ? rekeningBukuBesarDebit.getSaldo() : 0.0;
			
			// Validate and Get RekeningBukuBesar Kredit
			RekeningBukuBesar rekeningBukuBesarKredit = this.GetRekeningBukuBesar(noBukuBesarKredit);
			Double saldoBukuBesarKredit = rekeningBukuBesarKredit.getSaldo() != null ? rekeningBukuBesarKredit.getSaldo() : 0.0;
			
			// List EodKalkulasi in Unit
			List<EodKalkulasi> listEodKalkulasi = eodKalkulasiService.ListOnTanggalKodeEodUnit(_eodTanggal, "1013", iUnitId);
			
			// Loop for every RekeningKredit
			for (EodKalkulasi eodKalkulasi : listEodKalkulasi) {
				
			Date eodTanggal = eodKalkulasi.getEodTanggal();
			String calcObject = eodKalkulasi.getCalcObject();
			String subObject = eodKalkulasi.getSubObject();
			Double calcValue = eodKalkulasi.getCalcValue() != null ? eodKalkulasi.getCalcValue() : 0.0;
				
			// TRANSAKSI DEBIT
			saldoBukuBesarDebit = saldoBukuBesarDebit - calcValue;
			rekeningBukuBesarDebit.setSaldo(saldoBukuBesarDebit);
			rekeningBukuBesarService.save(rekeningBukuBesarDebit);
				
			// Insert EodPost Debit
			eodPostService.newEntry("4008", iUnitId, calcObject, subObject, noBukuBesarDebit, calcValue, null, null, null);
			
			// TRANSAKSI KREDIT
			saldoBukuBesarKredit = saldoBukuBesarKredit + calcValue;
			rekeningBukuBesarKredit.setSaldo(saldoBukuBesarKredit);
			rekeningBukuBesarService.save(rekeningBukuBesarKredit);
				
			// Insert EodPost Debit
			eodPostService.newEntry("4008", iUnitId, calcObject, subObject, null, null, noBukuBesarKredit, calcValue, null);
				
			// Count Up Progress
			progressCount++;
			eodProgressService.SetNow("4008", progressCount);
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " item(s) proceed";
			eodProgressService.SetNote("4008", strNote);
				
			}
		}
			
		// Set Progress Finish
		if (countAllUnit == 0) {
			String strNote = progressCount.toString() + "/" + countAllUnit.toString() + " item(s) proceed";
			eodProgressService.SetNote("4008", strNote);
			eodProgressService.FinishZero("4008");
		}
		else {
			eodProgressService.Finish("4008");
		}
			
		String strResult = "Posting 4008 Finish";
		return strResult;
	}

}
