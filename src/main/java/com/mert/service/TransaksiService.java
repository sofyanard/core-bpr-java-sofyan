package com.mert.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.AppUser;
import com.mert.model.AppUnit;
import com.mert.model.TranHistory;
import com.mert.model.BukuBesar;
import com.mert.model.RekeningBukuBesar;
import com.mert.model.Transaksi4001Input;
import com.mert.model.Transaksi4002Input;
import com.mert.model.UnitKasStatus;
import com.mert.model.UserBukuBesarKasStatusViewModel;

@Service
public class TransaksiService {
	
	@Autowired
	private AppUnitService appUnitService;
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private BukuBesarService bukuBesarService;
	
	@Autowired
	private KodeTranService kodeTranService;
	
	@Autowired
	private EodUnitStatusService eodUnitStatusService;
	
	@Autowired
	private TranHistoryService tranHistoryService;
	
	@Autowired
	private RekeningBukuBesarService rekeningBukuBesarService;
	
	@Autowired
	private UnitKasStatusService unitKasStatusService;
	
	
	
	private void HistoryDebit (
			String accountType,
			UUID tranRef,
			String koTran,
			AppUser userPost,
			String noRekDebit,
			Double valueDebit) throws Exception {
		
		try {
			TranHistory tranHistory = new TranHistory();
			tranHistory.setTranRef(tranRef);
			tranHistory.setKoTran(koTran);
			tranHistory.setTranDate(new Date());
			tranHistory.setAccountType(accountType);
			tranHistory.setUnitId(userPost.getUnitId());
			tranHistory.setUserId(userPost);
			tranHistory.setNoRekDebit(noRekDebit);
			tranHistory.setValueDebit(valueDebit);
			tranHistoryService.save(tranHistory);
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	private void HistoryKredit (
			String accountType,
			UUID tranRef,
			String koTran,
			AppUser userPost,
			String noRekKredit,
			Double valueKredit) throws Exception {
		
		try {
			TranHistory tranHistory = new TranHistory();
			tranHistory.setTranRef(tranRef);
			tranHistory.setKoTran(koTran);
			tranHistory.setTranDate(new Date());
			tranHistory.setAccountType(accountType);
			tranHistory.setUnitId(userPost.getUnitId());
			tranHistory.setUserId(userPost);
			tranHistory.setNoRekKredit(noRekKredit);
			tranHistory.setValueKredit(valueKredit);
			tranHistoryService.save(tranHistory);
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	
	
	public String Transaksi4001(Transaksi4001Input transaksi4001Input) throws Exception {
		
		// Validasi Input
		
		String userIdPost = transaksi4001Input.getUserIdPost();
		String userIdKasir = transaksi4001Input.getUserIdKasir();
		Double nominal = transaksi4001Input.getNominal();
		
		if ((userIdPost == null) || (userIdPost.isEmpty())) {
			throw new Exception("UserId Post tidak boleh kosong!");
		}
		
		if ((userIdKasir == null) || (userIdKasir.isEmpty())) {
			throw new Exception("UserId Kasir tidak boleh kosong!");
		}
		
		if ((nominal == null) || (nominal < 1.0)) {
			throw new Exception("Nominal tidak boleh kosong!");
		}
		
		// Validate User Post
		AppUser userPost = appUserService.findOne(userIdPost);
		
		if (userPost == null) {
			throw new Exception("User " + userIdPost + " tidak ditemukan!");
		}
		
		// Validate User Kasir
		AppUser kasir = appUserService.findOne(userIdKasir);
		
		if (kasir == null) {
			throw new Exception("User " + userIdKasir + " tidak ditemukan!");
		}
		
		// Cek Unitnya sudah dibuka atau belum
		if (!eodUnitStatusService.checkIfUnitIsOpen(userPost.getUnitId().getUnitId())) {
			throw new Exception("Unit " + userPost.getUnitId().getUnitName() + " belum dibuka!");
		}
				
		// Cek User Kasir sudah dibuka atau belum
		if (!eodUnitStatusService.checkIfUserIsOpen(userIdKasir)) {
			throw new Exception("User " + kasir.getFullName() + " belum dibuka!");
		}
		
		// Inquiry Buku Besar Unit
		BukuBesar bbUnit = bukuBesarService.findByUnit(userPost.getUnitId().getUnitId());
		
		if (bbUnit == null) {
			throw new Exception("Buku Besar Unit " + userPost.getUnitId().getUnitName() + " tidak ditemukan!");
		}
		
		String noBBUnit = bbUnit.getBukuBesarId();
		
		// Inquiry Rekening Buku Besar Unit, create kalau belum ada
		RekeningBukuBesar rekBBUnit = rekeningBukuBesarService.findOne(noBBUnit);
						
		if (rekBBUnit == null) {
			rekBBUnit = new RekeningBukuBesar();
			rekBBUnit.setNoRekening(noBBUnit);
			rekBBUnit.setSaldo(0.0);
			rekeningBukuBesarService.save(rekBBUnit);
		}
				
		// No Buku Besar Kasir
		String noBBKasir = kasir.getRekBukuBesar();
				
		if ((noBBKasir == null) || (noBBKasir.isEmpty())) {
			throw new Exception("Buku Besar User " + userIdKasir + " belum di-set!");
		}
				
		// Inquiry Buku Besar Kasir
		BukuBesar bbKasir = bukuBesarService.findOne(noBBKasir);
				
		if (bbKasir == null) {
			throw new Exception("Buku Besar no " + noBBKasir + " tidak ditemukan!");
		}
		
		// Inquiry Rekening Buku Besar Kasir, create kalau belum ada
		RekeningBukuBesar rekBBKasir = rekeningBukuBesarService.findOne(noBBKasir);
				
		if (rekBBKasir == null) {
			rekBBKasir = new RekeningBukuBesar();
			rekBBKasir.setNoRekening(noBBKasir);
			rekBBKasir.setSaldo(0.0);
			rekeningBukuBesarService.save(rekBBKasir);
		}
		
		// MULAI TRANSAKSI
		try {
			
			// Generate Transaction Ref No
			UUID trxRef = UUID.randomUUID();
			
			// TRANSAKSI DEBIT
			// Field  POST DEBIT : pengurangan (-) pada field Saldo kode buku besar  RekgBB Kasir sebesar valus field  Nominal Mark as ‘D’
			Double saldoRekBBKasir = rekBBKasir.getSaldo();
			if (saldoRekBBKasir == null) {
				saldoRekBBKasir = 0.0;
			}
			saldoRekBBKasir = saldoRekBBKasir - nominal;
			rekBBKasir.setSaldo(saldoRekBBKasir);
			rekeningBukuBesarService.save(rekBBKasir);
			
			// Insert Transaction History
			this.HistoryDebit("4", trxRef, "4001", userPost, noBBKasir, nominal);
			
			// TRANSAKSI KREDIT
			// Field  POST KREDIT : penambahan (+) pada field Saldo kode buku besar  RekgBB Kas Unit sebesar valus field  Nominal Mark as ‘K’
			Double saldoRekBBUnit = rekBBUnit.getSaldo();
			if (saldoRekBBUnit == null) {
				saldoRekBBUnit = 0.0;
			}
			saldoRekBBUnit = saldoRekBBUnit + nominal;
			rekBBUnit.setSaldo(saldoRekBBUnit);
			rekeningBukuBesarService.save(rekBBUnit);
			
			// Insert Transaction History
			this.HistoryKredit("4", trxRef, "4001", userPost, noBBUnit, nominal);
			
			// Update Unit Kas Status
			UnitKasStatus unitKasStatus = unitKasStatusService.findByUser(userIdKasir);
			if (unitKasStatus == null) {
				unitKasStatus = new UnitKasStatus();
			}
			unitKasStatus.setUnitId(kasir.getUnitId());
			unitKasStatus.setUserId(kasir);
			unitKasStatus.setKasKeluarTranRef(trxRef);
			unitKasStatus.setKasKeluarDate(new Date());
			unitKasStatus.setKasKeluarAmount(nominal);
			unitKasStatusService.save(unitKasStatus);
			
			return trxRef.toString();
			
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public String Transaksi4002(Transaksi4002Input transaksi4002Input) throws Exception {
		
		// Validasi Input
		
		String userIdPost = transaksi4002Input.getUserIdPost();
		String unitId = transaksi4002Input.getUnitId();
				
		if ((userIdPost == null) || (userIdPost.isEmpty())) {
			throw new Exception("UserId Post tidak boleh kosong!");
		}
				
		if ((unitId == null) || (unitId.isEmpty())) {
			throw new Exception("Unit tidak boleh kosong!");
		}
				
		// Validate User Post
		AppUser userPost = appUserService.findOne(userIdPost);
				
		if (userPost == null) {
			throw new Exception("User " + userIdPost + " tidak ditemukan!");
		}
		
		// Validate Unit
		AppUnit unit = appUnitService.findOne(unitId);
						
		if (unit == null) {
			throw new Exception("Unit " + unit + " tidak ditemukan!");
		}
		
		// Inquiry Buku Besar Unit
		BukuBesar bbUnit = bukuBesarService.findByUnit(unitId);
				
		if (bbUnit == null) {
			throw new Exception("Buku Besar Unit " + unitId + " tidak ditemukan!");
		}
				
		String noBBUnit = bbUnit.getBukuBesarId();
				
		// Inquiry Rekening Buku Besar Unit, create kalau belum ada
		RekeningBukuBesar rekBBUnit = rekeningBukuBesarService.findOne(noBBUnit);
								
		if (rekBBUnit == null) {
			rekBBUnit = new RekeningBukuBesar();
			rekBBUnit.setNoRekening(noBBUnit);
			rekBBUnit.setSaldo(0.0);
			rekeningBukuBesarService.save(rekBBUnit);
		}
		
		// Staging Model untuk User-User yang akan Pooling Kas
		List<UserBukuBesarKasStatusViewModel> listUserBukuBesarKasStatusViewModel = new ArrayList<UserBukuBesarKasStatusViewModel>();
		
		// Inquiry User-User yang akan Pooling Kas
		List<AppUser> listUser = eodUnitStatusService.listOpenBukuBesarKasUsersByUnitToday(unitId);
		
		Double totalSaldo = 0.0;
		Double saldo;
		
		for (AppUser appUser : listUser) {
			
			UserBukuBesarKasStatusViewModel userBukuBesarKasStatusViewModel = new UserBukuBesarKasStatusViewModel();
			userBukuBesarKasStatusViewModel.setAppUser(appUser);
			userBukuBesarKasStatusViewModel.setRekeningBukuBesar(rekeningBukuBesarService.findOne(appUser.getRekBukuBesar()));
			
			saldo = userBukuBesarKasStatusViewModel.getRekeningBukuBesar().getSaldo();
			if (saldo == null) {
				saldo = 0.0;
			}
			if (saldo != 0.0) {
				saldo = saldo * -1;
			}
			userBukuBesarKasStatusViewModel.getRekeningBukuBesar().setSaldo(saldo);
			totalSaldo = totalSaldo + saldo;
			
			listUserBukuBesarKasStatusViewModel.add(userBukuBesarKasStatusViewModel);
		}
		
		// MULAI TRANSAKSI
		try {
			
			// Generate Transaction Ref No
			UUID trxRef = UUID.randomUUID();
			
			// TRANSAKSI DEBIT
			// Debit Rekening Buku Besar Kas Unit
			Double saldoRekBBUnit = rekBBUnit.getSaldo();
			if (saldoRekBBUnit == null) {
				saldoRekBBUnit = 0.0;
			}
			saldoRekBBUnit = saldoRekBBUnit - totalSaldo;
			rekBBUnit.setSaldo(saldoRekBBUnit);
			rekeningBukuBesarService.save(rekBBUnit);
			
			// Insert Transaction History
			this.HistoryDebit("4", trxRef, "4002", userPost, noBBUnit, totalSaldo);
			
			// TRANSAKSI KREDIT
			// Field  POST KREDIT : penambahan (+)pada field Saldo kode buku besar RekgBB Kasir sebesar value field  Nominal ,Mark as ‘K’
			for (UserBukuBesarKasStatusViewModel kasir : listUserBukuBesarKasStatusViewModel) {
				
				AppUser appUserKasir = kasir.getAppUser();
				RekeningBukuBesar rekeningBukuBesarKasir = kasir.getRekeningBukuBesar();
				String noBBKasir = rekeningBukuBesarKasir.getNoRekening();
				
				Double saldoRekBBKasir = rekeningBukuBesarKasir.getSaldo();
				if (saldoRekBBKasir == null) {
					saldoRekBBKasir = 0.0;
				}
				rekeningBukuBesarKasir.setSaldo(0.0);
				rekeningBukuBesarService.save(rekeningBukuBesarKasir);
				
				// Insert Transaction History
				this.HistoryKredit("4", trxRef, "4002", userPost, noBBKasir, saldoRekBBKasir);
				
				// Update Unit Kas Status
				UnitKasStatus unitKasStatus = unitKasStatusService.findByUser(appUserKasir.getUserId());
				if (unitKasStatus == null) {
					unitKasStatus = new UnitKasStatus();
				}
				unitKasStatus.setUnitId(appUserKasir.getUnitId());
				unitKasStatus.setUserId(appUserKasir);
				unitKasStatus.setKasPoolingTranRef(trxRef);
				unitKasStatus.setKasPoolingDate(new Date());
				unitKasStatus.setKasPoolingAmount(saldoRekBBKasir);
				unitKasStatusService.save(unitKasStatus);
			}
			
			return trxRef.toString();
		}
		catch (Exception e) {
			throw e;
		}
	}
	
}
