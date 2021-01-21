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
import com.mert.model.FasilitasKreditOverrideModel;
import com.mert.model.FasilitasKreditPembayaranModel;
import com.mert.model.ParameterKodeBiaya;
import com.mert.model.KodeTran;
import com.mert.model.RekeningBukuBesar;
import com.mert.model.TabunganPembentukanRekening;
import com.mert.model.RekeningKredit;
import com.mert.model.Transaksi4001Input;
import com.mert.model.Transaksi4002Input;
import com.mert.model.Transaksi1001Input;
import com.mert.model.Transaksi1002Input;
import com.mert.model.Transaksi1003Input;
import com.mert.model.Transaksi1004Input;
import com.mert.model.UnitKasStatus;
import com.mert.model.UserBukuBesarKasStatusViewModel;
import com.mert.model.ParameterCore;

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
	
	@Autowired
	private FasilitasKreditOverrideModelService fasilitasKreditOverrideModelService;
	
	@Autowired
	private ParameterKodeBiayaService parameterKodeBiayaService;
	
	@Autowired
	private FasilitasKreditPembayaranModelService fasilitasKreditPembayaranModelService;
	
	@Autowired
	private TabunganPembentukanRekeningService tabunganPembentukanRekeningService;
	
	@Autowired
	private ParameterCoreService parameterCoreService;
	
	@Autowired
	private RekeningKreditService rekeningKreditService;
	
	
	
	// Validate a Unit
	private AppUnit ValidateUnit(String unitId) throws Exception {
		AppUnit appUnit = appUnitService.findOne(unitId);
			
		if (appUnit == null) {
			throw new Exception("Unit " + unitId + " tidak ditemukan!");
		}
			
		return appUnit;
	}
	
	// Validate a User
	private AppUser ValidateUser(String userId) throws Exception {
		AppUser appUser = appUserService.findOne(userId);
		
		if (appUser == null) {
			throw new Exception("User " + userId + " tidak ditemukan!");
		}
		
		return appUser;
	}
	
	// Check if a Unit is Open
	private void CheckIfUnitIsOpen(AppUnit appUnit) throws Exception {
		if (!eodUnitStatusService.checkIfUnitIsOpen(appUnit.getUnitId())) {
			throw new Exception("Unit " + appUnit.getUnitName() + " belum dibuka!");
		}
	}
	
	// Check if a User is Open
	private void CheckIfUserIsOpen(AppUser appUser) throws Exception {
		if (!eodUnitStatusService.checkIfUserIsOpen(appUser.getUserId())) {
			throw new Exception("User " + appUser.getFullName() + " belum dibuka!");
		}
	}
	
	// Get Rekening Buku Besar Unit
	private RekeningBukuBesar GetNoRekBukuBesarUnit(AppUnit appUnit) throws Exception {
		BukuBesar bbUnit = bukuBesarService.findByUnit(appUnit.getUnitId());
		
		if (bbUnit == null) {
			throw new Exception("Buku Besar Unit " + appUnit.getUnitName() + " tidak ditemukan!");
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
		
		return rekBBUnit;
	}
	
	// Get Rekening Buku Besar User
	private RekeningBukuBesar GetNoRekBukuBesarUser(AppUser appUser) throws Exception {
		String noBBUser = appUser.getRekBukuBesar();
		
		if ((noBBUser == null) || (noBBUser.isEmpty())) {
			throw new Exception("Buku Besar User " + appUser.getFullName() + " belum di-set!");
		}
		
		// Inquiry Buku Besar User
		BukuBesar bbUser = bukuBesarService.findOne(noBBUser);
						
		if (bbUser == null) {
			throw new Exception("Buku Besar no " + noBBUser + " tidak ditemukan!");
		}
		
		// Inquiry Rekening Buku Besar User, create kalau belum ada
		RekeningBukuBesar rekBBUser = rekeningBukuBesarService.findOne(noBBUser);
						
		if (rekBBUser == null) {
			rekBBUser = new RekeningBukuBesar();
			rekBBUser.setNoRekening(noBBUser);
			rekBBUser.setSaldo(0.0);
			rekeningBukuBesarService.save(rekBBUser);
		}
		
		return rekBBUser;
	}
	
	// Get Kode Transaksi Properties
	private KodeTran GetKodeTran(String koTran) {
		return kodeTranService.findOne(koTran);
	}
	
	// Get Rekening Buku Besar
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
	
			
	
	private void HistoryDebit (
			String accountType,
			UUID tranRef,
			String koTran,
			AppUser userPost,
			String noRekDebit,
			Double valueDebit,
			String note,
			String otherNote) throws Exception {
		
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
			tranHistory.setNote(note);
			tranHistory.setOtherNote(otherNote);
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
			Double valueKredit,
			String note,
			String otherNote) throws Exception {
		
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
			tranHistory.setNote(note);
			tranHistory.setOtherNote(otherNote);
			tranHistoryService.save(tranHistory);
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	private void HistoryDebit (
			String accountType,
			UUID tranRef,
			String koTran,
			AppUser userPost,
			String noRekDebit,
			Double valueDebit,
			String note) throws Exception {
		
		try {
			this.HistoryDebit(accountType,
					tranRef,
					koTran,
					userPost,
					noRekDebit,
					valueDebit,
					note,
					null);
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
			Double valueKredit,
			String note) throws Exception {
		
		try {
			this.HistoryKredit (
					accountType,
					tranRef,
					koTran,
					userPost,
					noRekKredit,
					valueKredit,
					note,
					null);
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	private void HistoryDebit (
			String accountType,
			UUID tranRef,
			String koTran,
			AppUser userPost,
			String noRekDebit,
			Double valueDebit) throws Exception {
		
		try {
			this.HistoryDebit(accountType,
					tranRef,
					koTran,
					userPost,
					noRekDebit,
					valueDebit,
					null,
					null);
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
			this.HistoryKredit (
					accountType,
					tranRef,
					koTran,
					userPost,
					noRekKredit,
					valueKredit,
					null,
					null);
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
		AppUser userPost = this.ValidateUser(userIdPost);
		
		// Validate User Kasir
		AppUser kasir = this.ValidateUser(userIdKasir);
		
		// Cek Unitnya sudah dibuka atau belum
		this.CheckIfUnitIsOpen(userPost.getUnitId());
				
		// Cek User Kasir sudah dibuka atau belum
		this.CheckIfUserIsOpen(kasir);
		
		// Get Rekening Buku Besar Unit
		RekeningBukuBesar rekBBUnit = this.GetNoRekBukuBesarUnit(userPost.getUnitId());
		
		// Get Rekening Buku Besar Kasir
		RekeningBukuBesar rekBBKasir = this.GetNoRekBukuBesarUser(kasir);
				
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
			this.HistoryDebit("4", trxRef, "4001", userPost, rekBBKasir.getNoRekening(), nominal);
			
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
			this.HistoryKredit("4", trxRef, "4001", userPost, rekBBUnit.getNoRekening(), nominal);
			
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
		AppUser userPost = this.ValidateUser(userIdPost);
		
		// Validate Unit
		AppUnit unit = this.ValidateUnit(unitId);
		
		// Get Rekening Buku Besar Unit
		RekeningBukuBesar rekBBUnit = this.GetNoRekBukuBesarUnit(unit);
		
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
			this.HistoryDebit("4", trxRef, "4002", userPost, rekBBUnit.getNoRekening(), totalSaldo);
			
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
	
	public String Transaksi1001(Transaksi1001Input transaksi1001Input) throws Exception {
		
		// Validasi Input
		
		String userIdPost = transaksi1001Input.getUserIdPost();
		String noReferensi = transaksi1001Input.getNoReferensi();
		Double biayaLain = transaksi1001Input.getBiayaLain();
		String kodeBiaya = transaksi1001Input.getKodeBiaya();
		String note = transaksi1001Input.getNote();
				
		if ((userIdPost == null) || (userIdPost.isEmpty())) {
			throw new Exception("UserId Post tidak boleh kosong!");
		}
				
		if ((noReferensi == null) || (noReferensi.isEmpty())) {
			throw new Exception("No Referensi tidak boleh kosong!");
		}
				
		// kalau biaya lain diisi (> 0.0), kode biaya harus dipilih
		if ((biayaLain != null) && (biayaLain > 0.0) && ((kodeBiaya == null) || (kodeBiaya.isEmpty()))) {
			throw new Exception("Kode Biaya Lain tidak boleh kosong jika Biaya Lain diisi!");
		}
		
		// kalau kode biaya dipilih, biaya lain harus diisi (> 1.0)
		if ((kodeBiaya != null) && (!kodeBiaya.isEmpty()) && ((biayaLain == null) || (biayaLain < 1.0))) {
			throw new Exception("Biaya Lain tidak boleh kosong jika Kode Biaya Lain diisi!");
		}
		
		// Validate User Post
		AppUser userPost = this.ValidateUser(userIdPost);
		
		// Check if Unit is Open
		this.CheckIfUnitIsOpen(userPost.getUnitId());
		
		// Check if User is Open
		this.CheckIfUserIsOpen(userPost);
		
		// Get Rekening Buku Besar User
		RekeningBukuBesar rekBBUser = this.GetNoRekBukuBesarUser(userPost);
		
		// Get Kode Transaksi Properties
		KodeTran kodeTran = this.GetKodeTran("1001");
		
		// Get Rekening Buku Besar Tujuan 1001
		RekeningBukuBesar rekBBTujuan = this.GetRekeningBukuBesar(kodeTran.getBukuBesarKredit());
		
		// Get Rekening Buku Besar Biaya Lain
		RekeningBukuBesar rekBBBiayaLain;
		if ((biayaLain != null) && (biayaLain > 1.0))
		{
			ParameterKodeBiaya parameterKodeBiaya = parameterKodeBiayaService.findById(kodeBiaya);
			rekBBBiayaLain = this.GetRekeningBukuBesar(parameterKodeBiaya.getBukuBesar());
		} else {
			rekBBBiayaLain = null;
		}
		
		// Inquiry Fasilitas Kredit
		FasilitasKreditOverrideModel fasilitasKredit = fasilitasKreditOverrideModelService.findByNoRef(noReferensi);
		String noFasilitas = fasilitasKredit.getNoFasilitas();
		Double ProvisiAmount = fasilitasKredit.getProvisiAmount() != null ? fasilitasKredit.getProvisiAmount() : 0.0;
		Double AmtAdmin = fasilitasKredit.getAmtAdmin() != null ? fasilitasKredit.getAmtAdmin() : 0.0;
		Double AmtNotaris = fasilitasKredit.getAmtNotaris() != null ? fasilitasKredit.getAmtNotaris() : 0.0;
		Double AmtAsuransi = fasilitasKredit.getAmtAsuransi() != null ? fasilitasKredit.getAmtAsuransi() : 0.0;
		Double AmtAppraisal = fasilitasKredit.getAmtAppraisal() != null ? fasilitasKredit.getAmtAppraisal() : 0.0;
		Double TotalBiaya = ProvisiAmount + AmtAdmin + AmtNotaris + AmtAsuransi + AmtAppraisal;
		biayaLain = (biayaLain != null) ? biayaLain : 0.0;
		Double TotalBiayaPlus = TotalBiaya + biayaLain;
		
		// MULAI TRANSAKSI
		try {
			
			// Generate Transaction Ref No
			UUID trxRef = UUID.randomUUID();
			
			// TRANSAKSI DEBIT
			// Field  POST DEBIT : pengurangan (-) pada field Saldo kode buku besar RekgBB Kasir sebesar field  Total Biaya,  Mark as ‘D’
			Double saldoRekBBUser = (rekBBUser.getSaldo() != null) ? rekBBUser.getSaldo() : 0.0;
			saldoRekBBUser = saldoRekBBUser - TotalBiayaPlus;
			rekBBUser.setSaldo(saldoRekBBUser);
			rekeningBukuBesarService.save(rekBBUser);
			
			// Insert Transaction History
			this.HistoryDebit("4", trxRef, "1001", userPost, rekBBUser.getNoRekening(), TotalBiayaPlus, note, noReferensi);
			
			// TRANSAKSI KREDIT
			// ProvisiAmount + AmtAdmin + AmtNotaris + AmtAsuransi + AmtAppraisal
			Double saldoRekBBTujuan = (rekBBTujuan.getSaldo() != null) ? rekBBTujuan.getSaldo() : 0.0;
			saldoRekBBTujuan = saldoRekBBTujuan + TotalBiaya;
			rekBBTujuan.setSaldo(saldoRekBBTujuan);
			rekeningBukuBesarService.save(rekBBTujuan);
			
			// Insert Transaction History
			this.HistoryKredit("4", trxRef, "1001", userPost, rekBBTujuan.getNoRekening(), ProvisiAmount, note, noReferensi);
			this.HistoryKredit("4", trxRef, "1001", userPost, rekBBTujuan.getNoRekening(), AmtAdmin, note, noReferensi);
			this.HistoryKredit("4", trxRef, "1001", userPost, rekBBTujuan.getNoRekening(), AmtNotaris, note, noReferensi);
			this.HistoryKredit("4", trxRef, "1001", userPost, rekBBTujuan.getNoRekening(), AmtAsuransi, note, noReferensi);
			this.HistoryKredit("4", trxRef, "1001", userPost, rekBBTujuan.getNoRekening(), AmtAppraisal, note, noReferensi);
			
			// biayaLain
			if (rekBBBiayaLain != null) {
				Double saldoRekBBBiayaLain = (rekBBBiayaLain.getSaldo() != null) ? rekBBBiayaLain.getSaldo() : 0.0;
				saldoRekBBBiayaLain = saldoRekBBBiayaLain + biayaLain;
				rekBBBiayaLain.setSaldo(saldoRekBBBiayaLain);
				rekeningBukuBesarService.save(rekBBBiayaLain);
				
				// Insert Transaction History
				this.HistoryKredit("4", trxRef, "1001", userPost, rekBBBiayaLain.getNoRekening(), biayaLain, note, noReferensi);
			}
			
			
			// Update Fasilitas Kredit
			FasilitasKreditPembayaranModel fasilitasPembayaran = fasilitasKreditPembayaranModelService.findOne(noFasilitas);
			fasilitasPembayaran.setPembayaranBiayaDate(new Date());
			fasilitasPembayaran.setPembayaranBiayaAmount(TotalBiayaPlus);
			fasilitasPembayaran.setPembayaranBiayaTranRef(trxRef);
			fasilitasKreditPembayaranModelService.save(fasilitasPembayaran);
			
			return trxRef.toString();
			
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	public String Transaksi1002(Transaksi1002Input transaksi1002Input) throws Exception {
		
		// Validasi Input
		
		String userIdPost = transaksi1002Input.getUserIdPost();
		String noReferensi = transaksi1002Input.getNoReferensi();
		Double biayaLain = transaksi1002Input.getBiayaLain();
		String kodeBiaya = transaksi1002Input.getKodeBiaya();
		String note = transaksi1002Input.getNote();
		String noRekBeban = transaksi1002Input.getNoRekBeban();
		String jenisRekBeban = transaksi1002Input.getJenisRekBeban();
				
		if ((userIdPost == null) || (userIdPost.isEmpty())) {
			throw new Exception("UserId Post tidak boleh kosong!");
		}
				
		if ((noReferensi == null) || (noReferensi.isEmpty())) {
			throw new Exception("No Referensi tidak boleh kosong!");
		}
				
		// kalau biaya lain diisi (> 0.0), kode biaya harus dipilih
		if ((biayaLain != null) && (biayaLain > 0.0) && ((kodeBiaya == null) || (kodeBiaya.isEmpty()))) {
			throw new Exception("Kode Biaya Lain tidak boleh kosong jika Biaya Lain diisi!");
		}
		
		// kalau kode biaya dipilih, biaya lain harus diisi (> 1.0)
		if ((kodeBiaya != null) && (!kodeBiaya.isEmpty()) && ((biayaLain == null) || (biayaLain < 1.0))) {
			throw new Exception("Biaya Lain tidak boleh kosong jika Kode Biaya Lain diisi!");
		}
		
		if ((noRekBeban == null) || (noRekBeban.isEmpty())) {
			throw new Exception("No Rekening Beban tidak boleh kosong!");
		}
		
		if ((jenisRekBeban == null) || (jenisRekBeban.isEmpty())) {
			throw new Exception("Jenis Rekening Beban tidak boleh kosong!");
		}
		
		if (!((jenisRekBeban.equals("2")) || (jenisRekBeban.equals("4")))) {
			throw new Exception("Jenis Rekening Beban harus Tabungan atau Buku Besar!");
		}
		
		// Validate User Post
		AppUser userPost = this.ValidateUser(userIdPost);
		
		// Check if Unit is Open
		this.CheckIfUnitIsOpen(userPost.getUnitId());
		
		// Check if User is Open
		this.CheckIfUserIsOpen(userPost);
		
		// Get Kode Transaksi Properties
		KodeTran kodeTran = this.GetKodeTran("1002");
		
		// Get Rekening Buku Besar Tujuan 1002
		RekeningBukuBesar rekBBTujuan = this.GetRekeningBukuBesar(kodeTran.getBukuBesarKredit());
		
		// Get Rekening Buku Besar Biaya Lain
		RekeningBukuBesar rekBBBiayaLain;
		if ((biayaLain != null) && (biayaLain > 1.0))
		{
			ParameterKodeBiaya parameterKodeBiaya = parameterKodeBiayaService.findById(kodeBiaya);
			rekBBBiayaLain = this.GetRekeningBukuBesar(parameterKodeBiaya.getBukuBesar());
		} else {
			rekBBBiayaLain = null;
		}
		
		// Inquiry Fasilitas Kredit
		FasilitasKreditOverrideModel fasilitasKredit = fasilitasKreditOverrideModelService.findByNoRef(noReferensi);
		String noFasilitas = fasilitasKredit.getNoFasilitas();
		Double ProvisiAmount = fasilitasKredit.getProvisiAmount() != null ? fasilitasKredit.getProvisiAmount() : 0.0;
		Double AmtAdmin = fasilitasKredit.getAmtAdmin() != null ? fasilitasKredit.getAmtAdmin() : 0.0;
		Double AmtNotaris = fasilitasKredit.getAmtNotaris() != null ? fasilitasKredit.getAmtNotaris() : 0.0;
		Double AmtAsuransi = fasilitasKredit.getAmtAsuransi() != null ? fasilitasKredit.getAmtAsuransi() : 0.0;
		Double AmtAppraisal = fasilitasKredit.getAmtAppraisal() != null ? fasilitasKredit.getAmtAppraisal() : 0.0;
		Double TotalBiaya = ProvisiAmount + AmtAdmin + AmtNotaris + AmtAsuransi + AmtAppraisal;
		biayaLain = (biayaLain != null) ? biayaLain : 0.0;
		Double TotalBiayaPlus = TotalBiaya + biayaLain;
		
		// Inquiry Rekening Beban
		
		TabunganPembentukanRekening rektabungan = null;
		Double saldotabungan = 0.0;
		RekeningBukuBesar rekbukubesar = null;
		Double saldobukubesar = 0.0;
		
		if (jenisRekBeban.equals("2")) {
			
			rektabungan = tabunganPembentukanRekeningService.findById(noRekBeban);
			
			if (rektabungan == null) {
				throw new Exception("Rekening Tabungan no " + noRekBeban + " tidak ditemukan!");
			}
			
			saldotabungan = rektabungan.getCurrent_saldo() != null ? rektabungan.getCurrent_saldo() : 0.0;
			
			// Inquiry Saldo Minimum from Parameter Core
			Double saldominimum = 0.0;
			try {
				saldominimum = Double.parseDouble(parameterCoreService.findByParamkey("SaldoMinimumTabungan").getParamvalue());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			// Cek apakah saldo cukup
			if (saldotabungan <= TotalBiayaPlus + saldominimum) {
				throw new Exception("Saldo Rekening Beban tidak cukup!");
			}
			
		} else if (jenisRekBeban.equals("4")) {
			
			rekbukubesar = this.GetRekeningBukuBesar(noRekBeban);
			
			saldobukubesar = (rekbukubesar.getSaldo() != null) ? rekbukubesar.getSaldo() : 0.0;
			
		} else {
			throw new Exception("Jenis Rekening Beban harus Tabungan atau Buku Besar!");
		}
		
		
		
		// MULAI TRANSAKSI
		try {
			
			// Generate Transaction Ref No
			UUID trxRef = UUID.randomUUID();
			
			// TRANSAKSI DEBIT
			
			if (jenisRekBeban.equals("2")) {
				
				saldotabungan = saldotabungan - TotalBiayaPlus;
				rektabungan.setCurrent_saldo(saldotabungan);
				tabunganPembentukanRekeningService.save(rektabungan);
				
				// Insert Transaction History
				this.HistoryDebit("2", trxRef, "1002", userPost, rektabungan.getNo_rekg(), TotalBiayaPlus, note, noReferensi);
				
			} else if (jenisRekBeban.equals("4")) {
				
				saldobukubesar = saldobukubesar - TotalBiayaPlus;
				rekbukubesar.setSaldo(saldobukubesar);
				rekeningBukuBesarService.save(rekbukubesar);
				
				// Insert Transaction History
				this.HistoryDebit("4", trxRef, "1002", userPost, rekbukubesar.getNoRekening(), TotalBiayaPlus, note, noReferensi);
				
			} else {
				throw new Exception("Jenis Rekening Beban harus Tabungan atau Buku Besar!");
			}
			
			// TRANSAKSI KREDIT
			// ProvisiAmount + AmtAdmin + AmtNotaris + AmtAsuransi + AmtAppraisal
			Double saldoRekBBTujuan = (rekBBTujuan.getSaldo() != null) ? rekBBTujuan.getSaldo() : 0.0;
			saldoRekBBTujuan = saldoRekBBTujuan + TotalBiaya;
			rekBBTujuan.setSaldo(saldoRekBBTujuan);
			rekeningBukuBesarService.save(rekBBTujuan);
			
			// Insert Transaction History
			this.HistoryKredit("4", trxRef, "1002", userPost, rekBBTujuan.getNoRekening(), ProvisiAmount, note, noReferensi);
			this.HistoryKredit("4", trxRef, "1002", userPost, rekBBTujuan.getNoRekening(), AmtAdmin, note, noReferensi);
			this.HistoryKredit("4", trxRef, "1002", userPost, rekBBTujuan.getNoRekening(), AmtNotaris, note, noReferensi);
			this.HistoryKredit("4", trxRef, "1002", userPost, rekBBTujuan.getNoRekening(), AmtAsuransi, note, noReferensi);
			this.HistoryKredit("4", trxRef, "1002", userPost, rekBBTujuan.getNoRekening(), AmtAppraisal, note, noReferensi);
			
			// biayaLain
			if (rekBBBiayaLain != null) {
				Double saldoRekBBBiayaLain = (rekBBBiayaLain.getSaldo() != null) ? rekBBBiayaLain.getSaldo() : 0.0;
				saldoRekBBBiayaLain = saldoRekBBBiayaLain + biayaLain;
				rekBBBiayaLain.setSaldo(saldoRekBBBiayaLain);
				rekeningBukuBesarService.save(rekBBBiayaLain);
				
				// Insert Transaction History
				this.HistoryKredit("4", trxRef, "1002", userPost, rekBBBiayaLain.getNoRekening(), biayaLain, note, noReferensi);
			}
			
			
			// Update Fasilitas Kredit
			FasilitasKreditPembayaranModel fasilitasPembayaran = fasilitasKreditPembayaranModelService.findOne(noFasilitas);
			fasilitasPembayaran.setPembayaranBiayaDate(new Date());
			fasilitasPembayaran.setPembayaranBiayaAmount(TotalBiayaPlus);
			fasilitasPembayaran.setPembayaranBiayaTranRef(trxRef);
			fasilitasKreditPembayaranModelService.save(fasilitasPembayaran);
	
			return trxRef.toString();
			
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	public String Transaksi1003(Transaksi1003Input transaksi1003Input) throws Exception {
		
		// Validasi Input
		
		String userIdPost = transaksi1003Input.getUserIdPost();
		String noRekKredit = transaksi1003Input.getNoRekKredit();
		String note = transaksi1003Input.getNote();
				
		if ((userIdPost == null) || (userIdPost.isEmpty())) {
			throw new Exception("UserId Post tidak boleh kosong!");
		}
				
		if ((noRekKredit == null) || (noRekKredit.isEmpty())) {
			throw new Exception("No Rek Kredit tidak boleh kosong!");
		}
				
		// Validate User Post
		AppUser userPost = this.ValidateUser(userIdPost);
		
		// Check if Unit is Open
		this.CheckIfUnitIsOpen(userPost.getUnitId());
		
		// Check if User is Open
		this.CheckIfUserIsOpen(userPost);
		
		// Get Rekening Buku Besar User
		RekeningBukuBesar rekBBUser = this.GetNoRekBukuBesarUser(userPost);
		
		// Inquiry Rekening Kredit
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		Double nilaiPencairan = rekeningKredit.getEqvPlafond() != null ? rekeningKredit.getEqvPlafond() : 0.0;
		Double bakiDebet = rekeningKredit.getBakiDebet() != null ? rekeningKredit.getBakiDebet() : 0.0;
		Double disburseAmount = rekeningKredit.getDisburse() != null ? rekeningKredit.getDisburse() : 0.0;
		Double availableAmount = rekeningKredit.getAvailableAmount() != null ? rekeningKredit.getAvailableAmount() : 0.0;
		
		if (nilaiPencairan < 1.0) {
			throw new Exception("Nilai Pencairan tidak boleh kosong!");
		}
		
		// MULAI TRANSAKSI
		try {
			
			// Generate Transaction Ref No
			UUID trxRef = UUID.randomUUID();
			
			// TRANSAKSI DEBIT
			
			// Field  POST DEBIT : Pengurangan (-) pada field Bade rekening  pinjaman yang di input sebesar field  Nilai Pencairan,  Mark as ‘D’
			bakiDebet = bakiDebet - nilaiPencairan;
			rekeningKredit.setBakiDebet(bakiDebet);
			
			// Insert  (+) Nilai Pencairan ke field Disburse
			disburseAmount = disburseAmount + nilaiPencairan;
			rekeningKredit.setDisburse(disburseAmount);
			rekeningKredit.setDisburseDate(new Date());
			
			// ?????
			availableAmount = availableAmount - nilaiPencairan;
			if (availableAmount < 0.0) {
				availableAmount = 0.0;
			}
			rekeningKredit.setAvailableAmount(availableAmount);
			
			rekeningKreditService.save(rekeningKredit);
			
			// Insert Transaction History
			this.HistoryDebit("1", trxRef, "1003", userPost, rekeningKredit.getNoRekening(), nilaiPencairan, note);
			
			// TRANSAKSI KREDIT
			// Field  POST KREDIT : penambahan (+) pada field Saldo kode buku besar RekgBB Kasir sebesar field  Nilai Pencairan,  Mark as ‘K’
			Double saldoRekBBUser = (rekBBUser.getSaldo() != null) ? rekBBUser.getSaldo() : 0.0;
			saldoRekBBUser = saldoRekBBUser + nilaiPencairan;
			rekBBUser.setSaldo(saldoRekBBUser);
			rekeningBukuBesarService.save(rekBBUser);
			
			// Insert Transaction History
			this.HistoryKredit("4", trxRef, "1003", userPost, rekBBUser.getNoRekening(), nilaiPencairan, note);
			

			
			return trxRef.toString();
			
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	public String Transaksi1004(Transaksi1004Input transaksi1004Input) throws Exception {
		
		// Validasi Input
		
		String userIdPost = transaksi1004Input.getUserIdPost();
		String noRekKredit = transaksi1004Input.getNoRekKredit();
		String noRekPenerima = transaksi1004Input.getNoRekPenerima();
		String jenisRekPenerima = transaksi1004Input.getJenisRekPenerima();
		String note = transaksi1004Input.getNote();
				
		if ((userIdPost == null) || (userIdPost.isEmpty())) {
			throw new Exception("UserId Post tidak boleh kosong!");
		}
				
		if ((noRekKredit == null) || (noRekKredit.isEmpty())) {
			throw new Exception("No Rek Kredit tidak boleh kosong!");
		}
		
		if ((noRekPenerima == null) || (noRekPenerima.isEmpty())) {
			throw new Exception("No Rek Penerima tidak boleh kosong!");
		}
		
		if ((jenisRekPenerima == null) || (jenisRekPenerima.isEmpty())) {
			throw new Exception("Jenis Rek Penerima tidak boleh kosong!");
		}
				
		// Validate User Post
		AppUser userPost = this.ValidateUser(userIdPost);
		
		// Check if Unit is Open
		this.CheckIfUnitIsOpen(userPost.getUnitId());
		
		// Check if User is Open
		this.CheckIfUserIsOpen(userPost);
		
		// Inquiry Rekening Kredit
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		Double nilaiPencairan = rekeningKredit.getEqvPlafond() != null ? rekeningKredit.getEqvPlafond() : 0.0;
		Double bakiDebet = rekeningKredit.getBakiDebet() != null ? rekeningKredit.getBakiDebet() : 0.0;
		Double disburseAmount = rekeningKredit.getDisburse() != null ? rekeningKredit.getDisburse() : 0.0;
		Double availableAmount = rekeningKredit.getAvailableAmount() != null ? rekeningKredit.getAvailableAmount() : 0.0;
		
		if (nilaiPencairan < 1.0) {
			throw new Exception("Nilai Pencairan tidak boleh kosong!");
		}
		
		
		
		// Inquiry Rekening Beban
		
		TabunganPembentukanRekening rekeningTabungan = null;
		Double saldoTabungan = 0.0;
		RekeningBukuBesar rekeningBukuBesar = null;
		Double saldoBukuBesar = 0.0;
				
		if (jenisRekPenerima.equals("2")) {
					
			rekeningTabungan = tabunganPembentukanRekeningService.findById(noRekPenerima);
					
			if (rekeningTabungan == null) {
				throw new Exception("Rekening Tabungan no " + noRekPenerima + " tidak ditemukan!");
			}
					
			saldoTabungan = rekeningTabungan.getCurrent_saldo() != null ? rekeningTabungan.getCurrent_saldo() : 0.0;
					
		} else if (jenisRekPenerima.equals("4")) {
					
			rekeningBukuBesar = this.GetRekeningBukuBesar(noRekPenerima);
			
			saldoBukuBesar = (rekeningBukuBesar.getSaldo() != null) ? rekeningBukuBesar.getSaldo() : 0.0;
					
		} else {
			throw new Exception("Jenis Rekening Beban harus Tabungan atau Buku Besar!");
		}
		
		
		
		// MULAI TRANSAKSI
		try {
			
			// Generate Transaction Ref No
			UUID trxRef = UUID.randomUUID();
			
			// TRANSAKSI DEBIT
			// Field  POST DEBIT : Pengurangan (-) pada field Bade rekening  pinjaman yang di input sebesar field  Nilai Pencairan,  Mark as ‘D’
			bakiDebet = bakiDebet - nilaiPencairan;
			rekeningKredit.setBakiDebet(bakiDebet);
			
			// Insert  (+) Nilai Pencairan ke field Disburse
			disburseAmount = disburseAmount + nilaiPencairan;
			rekeningKredit.setDisburse(disburseAmount);
			rekeningKredit.setDisburseDate(new Date());
			
			// ?????
			availableAmount = availableAmount - nilaiPencairan;
			if (availableAmount < 0.0) {
				availableAmount = 0.0;
			}
			rekeningKredit.setAvailableAmount(availableAmount);
			
			rekeningKreditService.save(rekeningKredit);
			
			// Insert Transaction History
			this.HistoryDebit("1", trxRef, "1004", userPost, rekeningKredit.getNoRekening(), nilaiPencairan, note);
			
			
			
			// TRANSAKSI KREDIT
			// Field  POST KREDIT : penambahan (+) pada field Saldo pada rekening buku besar atau rekening tabungan yang diinput pada field Rek. Penerima sebesar field  Nilai Pencairan,  Mark as ‘K’
			
			if (jenisRekPenerima.equals("2")) {
				
				saldoTabungan = saldoTabungan + nilaiPencairan;
				rekeningTabungan.setCurrent_saldo(saldoTabungan);
				tabunganPembentukanRekeningService.save(rekeningTabungan);
				
				// Insert Transaction History
				this.HistoryKredit("2", trxRef, "1004", userPost, noRekPenerima, nilaiPencairan, note);
				
			} else if (jenisRekPenerima.equals("4")) {
				
				saldoBukuBesar = saldoBukuBesar + nilaiPencairan;
				rekeningBukuBesar.setSaldo(saldoBukuBesar);
				rekeningBukuBesarService.save(rekeningBukuBesar);
				
				// Insert Transaction History
				this.HistoryKredit("4", trxRef, "1004", userPost, noRekPenerima, nilaiPencairan, note);
				
			} else {
				throw new Exception("Jenis Rekening Beban harus Tabungan atau Buku Besar!");
			}
			
			// TRANSAKSI SUKSES
			return trxRef.toString();
			
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
}
