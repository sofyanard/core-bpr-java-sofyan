package com.mert.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.model.FasilitasKreditOverrideModel;
import com.mert.model.FasilitasKreditPembayaranModel;
import com.mert.model.ParameterKodeBiaya;
import com.mert.model.TabunganPembentukanRekening;
import com.mert.model.RekeningBukuBesar;
import com.mert.model.RekeningKredit;
import com.mert.model.DataTagihan;
import com.mert.model.Transaksi1001Input;
import com.mert.model.Transaksi1002Input;
import com.mert.model.Transaksi1003Input;
import com.mert.model.Transaksi1004Input;
import com.mert.model.Transaksi1005Input;
import com.mert.model.Transaksi1006Input;
import com.mert.model.Transaksi1007Input;
import com.mert.model.Transaksi1008Input;
import com.mert.service.AppUserService;
import com.mert.service.FasilitasKreditOverrideModelService;
import com.mert.service.FasilitasKreditPembayaranModelService;
import com.mert.service.ParameterKodeBiayaService;
import com.mert.service.TabunganPembentukanRekeningService;
import com.mert.service.TransaksiService;
import com.mert.service.ParameterProductTypeService;
import com.mert.service.RekeningBukuBesarService;
import com.mert.service.RekeningKreditService;
import com.mert.service.DataTagihanService;

@Controller
@RequestMapping("/kasir/pinjaman")
public class KasirTransaksiPinjamanController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private TransaksiService transaksiService;
	
	@Autowired
	private FasilitasKreditOverrideModelService fasilitasKreditOverrideModelService;
	
	@Autowired
	private FasilitasKreditPembayaranModelService fasilitasKreditPembayaranModelService;
	
	@Autowired
	private ParameterKodeBiayaService parameterKodeBiayaService;
	
	@Autowired
	private TabunganPembentukanRekeningService tabunganPembentukanRekeningService;
	
	@Autowired
	private ParameterProductTypeService parameterProductTypeService;
	
	@Autowired
	private RekeningBukuBesarService rekeningBukuBesarService;
	
	@Autowired
	private RekeningKreditService rekeningKreditService;
	
	@Autowired
	private DataTagihanService dataTagihanService;

	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView Home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.setViewName("kasir/pinjaman/home");
		return modelAndView;
	}
	
	/* 1001 */
	
	@RequestMapping(value="/biayaadminkredittunaisearch", method = RequestMethod.GET)
	public ModelAndView BiayaAdminKreditTunaiSearch(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("mode", "MODE_SEARCH");
		modelAndView.setViewName("kasir/pinjaman/biayaadminkredittunai");
		return modelAndView;
	}
	
	@RequestMapping(value="/biayaadminkredittunaisearch", method = RequestMethod.POST)
	public ModelAndView BiayaAdminKreditTunaiSearchPost(String noReferensi) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		FasilitasKreditOverrideModel fasilitasKredit = fasilitasKreditOverrideModelService.findByNoRef(noReferensi);
		
		if (fasilitasKredit == null) {
			String errMsg = "Fasilitas dengan No Referensi " + noReferensi + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkredittunaisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		FasilitasKreditPembayaranModel fasilitasKredit2 = fasilitasKreditPembayaranModelService.findOne(fasilitasKredit.getNoFasilitas());
		
		if ((fasilitasKredit2.getPembayaranBiayaTranRef() != null) &&
				(fasilitasKredit2.getPembayaranBiayaDate() != null) &&
				(fasilitasKredit2.getPembayaranBiayaAmount() != null)) {
			
			String trxRef = fasilitasKredit2.getPembayaranBiayaTranRef().toString();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String paydate = formatter.format(fasilitasKredit2.getPembayaranBiayaDate());
			String payamount = fasilitasKredit2.getPembayaranBiayaAmount().toString();
			
			String errMsg = "Biaya-biaya atas Fasilitas dengan No Referensi " + noReferensi + 
					" sudah dibayar pada " + paydate + 
					" sejumlah " + payamount + 
					" Transaction Ref No " + trxRef + "!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkredittunaisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkredittunai/" + fasilitasKredit.getNoRef());
		return modelAndView;
	}
	
	@RequestMapping(value="/biayaadminkredittunai/{noReferensi}", method = RequestMethod.GET)
	public ModelAndView BiayaAdminKreditTunai(@PathVariable String noReferensi, String errMsg, String sccMsg, String validUrl) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		Transaksi1001Input transaksi1001Input = new Transaksi1001Input();
		transaksi1001Input.setUserIdPost(getUser().getUserId());
		transaksi1001Input.setNoReferensi(noReferensi);
		modelAndView.addObject("transaksi1001Input", transaksi1001Input);
		
		List<ParameterKodeBiaya> listKodeBiaya = parameterKodeBiayaService.findAll();
		modelAndView.addObject("listKodeBiaya", listKodeBiaya);
		
		FasilitasKreditOverrideModel fasilitasKredit = fasilitasKreditOverrideModelService.findByNoRef(noReferensi);
		modelAndView.addObject("fasilitasKredit", fasilitasKredit);
		
		modelAndView.addObject("noReferensi", noReferensi);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("validUrl", validUrl);
		modelAndView.addObject("mode", "MODE_POSTING");
		modelAndView.setViewName("kasir/pinjaman/biayaadminkredittunai");
		return modelAndView;
	}
	
	@RequestMapping(value="/biayaadminkredittunai/{noReferensi}", method = RequestMethod.POST)
	public ModelAndView BiayaAdminKreditTunaiPost(@PathVariable String noReferensi, @Valid Transaksi1001Input transaksi1001Input, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkredittunai/" + noReferensi + "?errMsg=Invalid Input!");
			return modelAndView;
		}

		String trxRefNo;
		try {
			trxRefNo = transaksiService.Transaksi1001(transaksi1001Input);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkredittunai/" + noReferensi + "?errMsg=" + e.getMessage());
			return modelAndView;
		}
		
		String sccMsg = "Posting Success, Transaction Ref No : " + trxRefNo;
		String validUrl = "/kasir/validasi/printtext/" + trxRefNo;
		modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkredittunai/" + noReferensi + "?sccMsg=" + sccMsg + "&validUrl=" + validUrl);
		return modelAndView;
	}
	
	/* 1002 */
	
	@RequestMapping(value="/biayaadminkreditrekeningsearch", method = RequestMethod.GET)
	public ModelAndView BiayaAdminKreditRekeningSearch(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("listProductType", parameterProductTypeService.findAll());
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("mode", "MODE_SEARCH");
		modelAndView.setViewName("kasir/pinjaman/biayaadminkreditrekening");
		return modelAndView;
	}
	
	@RequestMapping(value="/biayaadminkreditrekeningsearch", method = RequestMethod.POST)
	public ModelAndView BiayaAdminKreditRekeningSearchPost(String noReferensi, String noRekBeban, String jenisRekBeban) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		FasilitasKreditOverrideModel fasilitasKredit = fasilitasKreditOverrideModelService.findByNoRef(noReferensi);
		
		if (fasilitasKredit == null) {
			String errMsg = "Fasilitas dengan No Referensi " + noReferensi + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekeningsearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		FasilitasKreditPembayaranModel fasilitasKredit2 = fasilitasKreditPembayaranModelService.findOne(fasilitasKredit.getNoFasilitas());
		
		if ((fasilitasKredit2.getPembayaranBiayaTranRef() != null) &&
				(fasilitasKredit2.getPembayaranBiayaDate() != null) &&
				(fasilitasKredit2.getPembayaranBiayaAmount() != null)) {
			
			String trxRef = fasilitasKredit2.getPembayaranBiayaTranRef().toString();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String paydate = formatter.format(fasilitasKredit2.getPembayaranBiayaDate());
			String payamount = fasilitasKredit2.getPembayaranBiayaAmount().toString();
			
			String errMsg = "Biaya-biaya atas Fasilitas dengan No Referensi " + noReferensi + 
					" sudah dibayar pada " + paydate + 
					" sejumlah " + payamount + 
					" Transaction Ref No " + trxRef + "!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekeningsearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		if (jenisRekBeban.equals("2")) {
			
			// Beban Rekening Tabungan
			
			TabunganPembentukanRekening rektabungan = tabunganPembentukanRekeningService.findById(noRekBeban);
			
			if (rektabungan == null) {
				String errMsg = "Tabungan dengan No Rekening " + noRekBeban + " tidak ditemukan!";
				modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekeningsearch?errMsg=" + errMsg);
				return modelAndView;
			}
			
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekening/" + fasilitasKredit.getNoRef() + "/" + rektabungan.getNo_rekg() + "/" + jenisRekBeban);
			return modelAndView;
			
		} else if (jenisRekBeban.equals("4")) {
			
			// Beban Rekening Buku Besar
			
			RekeningBukuBesar rekbukubesar = rekeningBukuBesarService.findOne(noRekBeban);
			
			if (rekbukubesar == null) {
				String errMsg = "Buku Besar dengan No Rekening " + noRekBeban + " tidak ditemukan!";
				modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekeningsearch?errMsg=" + errMsg);
				return modelAndView;
			}
			
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekening/" + fasilitasKredit.getNoRef() + "/" + rekbukubesar.getNoRekening() + "/" + jenisRekBeban);
			return modelAndView;
			
		}
			
		// Beban Lainnya
			
		String errMsg = "Jenis Rekening Beban tidak valid!";
		modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekeningsearch?errMsg=" + errMsg);
		return modelAndView;
			
	}
	
	@RequestMapping(value="/biayaadminkreditrekening/{noReferensi}/{noRekBeban}/{jenisRekBeban}", method = RequestMethod.GET)
	public ModelAndView BiayaAdminKreditRekening(@PathVariable String noReferensi, @PathVariable String noRekBeban, @PathVariable String jenisRekBeban, String errMsg, String sccMsg, String validUrl) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		Transaksi1002Input transaksi1002Input = new Transaksi1002Input();
		transaksi1002Input.setUserIdPost(getUser().getUserId());
		transaksi1002Input.setNoReferensi(noReferensi);
		transaksi1002Input.setNoRekBeban(noRekBeban);
		transaksi1002Input.setJenisRekBeban(jenisRekBeban);
		modelAndView.addObject("transaksi1002Input", transaksi1002Input);
		
		List<ParameterKodeBiaya> listKodeBiaya = parameterKodeBiayaService.findAll();
		modelAndView.addObject("listKodeBiaya", listKodeBiaya);
		
		FasilitasKreditOverrideModel fasilitasKredit = fasilitasKreditOverrideModelService.findByNoRef(noReferensi);
		modelAndView.addObject("fasilitasKredit", fasilitasKredit);
		
		if (jenisRekBeban.equals("2")) {
			// Beban Rekening Tabungan
			TabunganPembentukanRekening rektabungan = tabunganPembentukanRekeningService.findById(noRekBeban);
			modelAndView.addObject("rektabungan", rektabungan);
		} else if (jenisRekBeban.equals("4")) {
			// Beban Rekening Buku Besar
			RekeningBukuBesar rekbukubesar = rekeningBukuBesarService.findOne(noRekBeban);
			modelAndView.addObject("rekbukubesar", rekbukubesar);
		} else {
			// Bukan Tabungan & Bukan Buku Besar --> throws error
			String errMsg2 = "Jenis Rekening Beban tidak valid!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekeningsearch?errMsg=" + errMsg2);
			return modelAndView;
		}
		
		modelAndView.addObject("noReferensi", noReferensi);
		modelAndView.addObject("noRekBeban", noRekBeban);
		modelAndView.addObject("jenisRekBeban", jenisRekBeban);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("validUrl", validUrl);
		modelAndView.addObject("mode", "MODE_POSTING");
		modelAndView.setViewName("kasir/pinjaman/biayaadminkreditrekening");
		return modelAndView;
	}
	
	@RequestMapping(value="/biayaadminkreditrekening/{noReferensi}/{noRekBeban}/{jenisRekBeban}", method = RequestMethod.POST)
	public ModelAndView BiayaAdminKreditRekeningPost(@PathVariable String noReferensi, @PathVariable String noRekBeban, @PathVariable String jenisRekBeban, @Valid Transaksi1002Input transaksi1002Input, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekening/" + noReferensi + "/" + noRekBeban + "/" + jenisRekBeban + "?errMsg=Invalid Input!");
			return modelAndView;
		}

		String trxRefNo;
		try {
			trxRefNo = transaksiService.Transaksi1002(transaksi1002Input);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekening/" + noReferensi + "/" + noRekBeban + "/" + jenisRekBeban + "?errMsg=" + e.getMessage());
			return modelAndView;
		}
		
		String sccMsg = "Posting Success, Transaction Ref No : " + trxRefNo;
		String validUrl = "/kasir/validasi/printtext/" + trxRefNo;
		modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkreditrekening/" + noReferensi + "/" + noRekBeban + "/" + jenisRekBeban + "?sccMsg=" + sccMsg + "&validUrl=" + validUrl);
		return modelAndView;
	}
	
	/* 1003 */

	@RequestMapping(value="/pencairankredittunaisearch", method = RequestMethod.GET)
	public ModelAndView PencairanKreditTunaiSearch(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("mode", "MODE_SEARCH");
		modelAndView.setViewName("kasir/pinjaman/pencairankredittunai");
		return modelAndView;
	}
	
	@RequestMapping(value="/pencairankredittunaisearch", method = RequestMethod.POST)
	public ModelAndView PencairanKreditTunaiSearchPost(String noRekKredit) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		
		// RekeningKredit tidak ada
		if (rekeningKredit == null) {
			String errMsg = "Rekening Kredit dengan No " + noRekKredit + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankredittunaisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		// Kredit sudah pernah dicairkan
		if ((rekeningKredit.getDisburse() != null) ||
				(rekeningKredit.getDisburseDate() != null)) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String disburseDate = (rekeningKredit.getDisburseDate() != null) ? formatter.format(rekeningKredit.getDisburseDate()) : "";
			String disburseAmount = (rekeningKredit.getDisburse() != null) ? rekeningKredit.getDisburse().toString() : "";
			
			String errMsg = "Kredit dengan No Rekening " + noRekKredit + 
					" sudah dicairkan pada " + disburseDate + 
					" sejumlah " + disburseAmount + " !";
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankredittunaisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankredittunai/" + noRekKredit);
		return modelAndView;
	}
	
	@RequestMapping(value="/pencairankredittunai/{noRekKredit}", method = RequestMethod.GET)
	public ModelAndView PencairanKreditTunai(@PathVariable String noRekKredit, String errMsg, String sccMsg, String validUrl) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		Transaksi1003Input transaksi1003Input = new Transaksi1003Input();
		transaksi1003Input.setUserIdPost(getUser().getUserId());
		transaksi1003Input.setNoRekKredit(noRekKredit);
		modelAndView.addObject("transaksi1003Input", transaksi1003Input);
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		modelAndView.addObject("rekeningKredit", rekeningKredit);
		
		modelAndView.addObject("noRekKredit", noRekKredit);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("validUrl", validUrl);
		modelAndView.addObject("mode", "MODE_POSTING");
		modelAndView.setViewName("kasir/pinjaman/pencairankredittunai");
		return modelAndView;
	}
	
	@RequestMapping(value="/pencairankredittunai/{noRekKredit}", method = RequestMethod.POST)
	public ModelAndView PencairanKreditTunaiPost(@PathVariable String noRekKredit, @Valid Transaksi1003Input transaksi1003Input, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankredittunai/" + noRekKredit + "?errMsg=Invalid Input!");
			return modelAndView;
		}
		
		String trxRefNo;
		try {
			trxRefNo = transaksiService.Transaksi1003(transaksi1003Input);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankredittunai/" + noRekKredit + "?errMsg=" + e.getMessage());
			return modelAndView;
		}
		
		String sccMsg = "Posting Success, Transaction Ref No : " + trxRefNo;
		String validUrl = "/kasir/validasi/printtext/" + trxRefNo;
		modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankredittunai/" + noRekKredit + "?sccMsg=" + sccMsg + "&validUrl=" + validUrl);
		return modelAndView;
	}
	
	/* 1004 */

	@RequestMapping(value="/pencairankreditrekeningsearch", method = RequestMethod.GET)
	public ModelAndView PencairanKreditRekeningSearch(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("listProductType", parameterProductTypeService.findAll());
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("mode", "MODE_SEARCH");
		modelAndView.setViewName("kasir/pinjaman/pencairankreditrekening");
		return modelAndView;
	}
	
	@RequestMapping(value="/pencairankreditrekeningsearch", method = RequestMethod.POST)
	public ModelAndView PencairanKreditRekeningSearchPost(String noRekKredit, String noRekPenerima, String jenisRekPenerima) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		
		// RekeningKredit tidak ada
		if (rekeningKredit == null) {
			String errMsg = "Rekening Kredit dengan No " + noRekKredit + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankredittunaisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		// Kredit sudah pernah dicairkan
		if ((rekeningKredit.getDisburse() != null) ||
				(rekeningKredit.getDisburseDate() != null)) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String disburseDate = (rekeningKredit.getDisburseDate() != null) ? formatter.format(rekeningKredit.getDisburseDate()) : "";
			String disburseAmount = (rekeningKredit.getDisburse() != null) ? rekeningKredit.getDisburse().toString() : "";
			
			String errMsg = "Kredit dengan No Rekening " + noRekKredit + 
					" sudah dicairkan pada " + disburseDate + 
					" sejumlah " + disburseAmount + " !";
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankredittunaisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		if (jenisRekPenerima.equals("2")) {
			
			// Penerima Rekening Tabungan
			
			TabunganPembentukanRekening rekeningTabungan = tabunganPembentukanRekeningService.findById(noRekPenerima);
			
			if (rekeningTabungan == null) {
				String errMsg = "Tabungan dengan No Rekening " + noRekPenerima + " tidak ditemukan!";
				modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankreditrekeningsearch?errMsg=" + errMsg);
				return modelAndView;
			}
			
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankreditrekening/" + noRekKredit + "/" + noRekPenerima + "/" + jenisRekPenerima);
			return modelAndView;
			
		} else if (jenisRekPenerima.equals("4")) {
			
			// Penerima Rekening Buku Besar
			
			RekeningBukuBesar rekeningBukuBesar = rekeningBukuBesarService.findOne(noRekPenerima);
			
			if (rekeningBukuBesar == null) {
				String errMsg = "Buku Besar dengan No Rekening " + noRekPenerima + " tidak ditemukan!";
				modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankreditrekeningsearch?errMsg=" + errMsg);
				return modelAndView;
			}
			
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankreditrekening/" + noRekKredit + "/" + noRekPenerima + "/" + jenisRekPenerima);
			return modelAndView;
			
		}
			
		// Beban Lainnya
			
		String errMsg = "Jenis Rekening Penerima tidak valid!";
		modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankreditrekeningsearch?errMsg=" + errMsg);
		return modelAndView;
		
	}
	
	@RequestMapping(value="/pencairankreditrekening/{noRekKredit}/{noRekPenerima}/{jenisRekPenerima}", method = RequestMethod.GET)
	public ModelAndView PencairanKreditRekening(@PathVariable String noRekKredit, @PathVariable String noRekPenerima, @PathVariable String jenisRekPenerima, String errMsg, String sccMsg, String validUrl) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		Transaksi1004Input transaksi1004Input = new Transaksi1004Input();
		transaksi1004Input.setUserIdPost(getUser().getUserId());
		transaksi1004Input.setNoRekKredit(noRekKredit);
		transaksi1004Input.setNoRekPenerima(noRekPenerima);
		transaksi1004Input.setJenisRekPenerima(jenisRekPenerima);
		modelAndView.addObject("transaksi1004Input", transaksi1004Input);
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		modelAndView.addObject("rekeningKredit", rekeningKredit);
		
		if (jenisRekPenerima.equals("2")) {
			// Penerima Rekening Tabungan
			TabunganPembentukanRekening rekeningTabungan = tabunganPembentukanRekeningService.findById(noRekPenerima);
			modelAndView.addObject("rekeningTabungan", rekeningTabungan);
		} else if (jenisRekPenerima.equals("4")) {
			// Penerima Rekening Buku Besar
			RekeningBukuBesar rekeningBukuBesar = rekeningBukuBesarService.findOne(noRekPenerima);
			modelAndView.addObject("rekeningBukuBesar", rekeningBukuBesar);
		} else {
			// Bukan Tabungan & Bukan Buku Besar --> throws error
			String errMsg2 = "Jenis Rekening Penerima tidak valid!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankreditrekeningsearch?errMsg=" + errMsg2);
			return modelAndView;
		}
		
		modelAndView.addObject("noRekKredit", noRekKredit);
		modelAndView.addObject("noRekPenerima", noRekPenerima);
		modelAndView.addObject("jenisRekPenerima", jenisRekPenerima);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("validUrl", validUrl);
		modelAndView.addObject("mode", "MODE_POSTING");
		modelAndView.setViewName("kasir/pinjaman/pencairankreditrekening");
		return modelAndView;
	}
	
	@RequestMapping(value="/pencairankreditrekening/{noRekKredit}/{noRekPenerima}/{jenisRekPenerima}", method = RequestMethod.POST)
	public ModelAndView PencairanKreditRekeningPost(@PathVariable String noRekKredit, @PathVariable String noRekPenerima, @PathVariable String jenisRekPenerima, @Valid Transaksi1004Input transaksi1004Input, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankreditrekening/" + noRekKredit + "/" + noRekPenerima + "/" + jenisRekPenerima + "?errMsg=Invalid Input!");
			return modelAndView;
		}
		
		String trxRefNo;
		try {
			trxRefNo = transaksiService.Transaksi1004(transaksi1004Input);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankreditrekening/" + noRekKredit + "/" + noRekPenerima + "/" + jenisRekPenerima + "?errMsg=" + e.getMessage());
			return modelAndView;
		}
		
		String sccMsg = "Posting Success, Transaction Ref No : " + trxRefNo;
		String validUrl = "/kasir/validasi/printtext/" + trxRefNo;
		modelAndView.setViewName("redirect:/kasir/pinjaman/pencairankreditrekening/" + noRekKredit + "/" + noRekPenerima + "/" + jenisRekPenerima + "?sccMsg=" + sccMsg + "&validUrl=" + validUrl);
		return modelAndView;
	}
	
	/* 1005 */
	
	@RequestMapping(value="/angsurankredittunaisearch", method = RequestMethod.GET)
	public ModelAndView AngsuranKreditTunaiSearch(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("mode", "MODE_SEARCH");
		modelAndView.setViewName("kasir/pinjaman/angsurankredittunai");
		return modelAndView;
	}
	
	@RequestMapping(value="/angsurankredittunaisearch", method = RequestMethod.POST)
	public ModelAndView AngsuranKreditTunaiSearchPost(String noRekKredit) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		// Mencari Rekening Kredit
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		
		// Jika RekeningKredit tidak ditemukan
		if (rekeningKredit == null) {
			String errMsg = "Rekening Kredit dengan No " + noRekKredit + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankredittunaisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		// Mencari Data Tagihan, 1 record paling terdahulu yang belum dibayar
		DataTagihan dataTagihan = dataTagihanService.findFirstNotPaid(noRekKredit);
		
		// Jika Tagihan tidak ditemukan
		if (dataTagihan == null) {
			String errMsg = "Tagihan atas Rekening No " + noRekKredit + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankredittunaisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankredittunai/" + noRekKredit);
		return modelAndView;
	}
	
	@RequestMapping(value="/angsurankredittunai/{noRekKredit}", method = RequestMethod.GET)
	public ModelAndView AngsuranKreditTunai(@PathVariable String noRekKredit, String errMsg, String sccMsg, String validUrl) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		Transaksi1005Input transaksi1005Input = new Transaksi1005Input();
		transaksi1005Input.setUserIdPost(getUser().getUserId());
		transaksi1005Input.setNoRekKredit(noRekKredit);
		modelAndView.addObject("transaksi1005Input", transaksi1005Input);
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		modelAndView.addObject("rekeningKredit", rekeningKredit);
		
		DataTagihan dataTagihan = dataTagihanService.findFirstNotPaid(noRekKredit);
		if (dataTagihan == null)
			dataTagihan = new DataTagihan();
		
		Date dueDate = dataTagihan.getDueDate();
		Double angsuranPokok = dataTagihan.getPokok() != null ? dataTagihan.getPokok() : 0.0;
		Double angsuranBunga = dataTagihan.getBunga() != null ? dataTagihan.getBunga() : 0.0;
		Double jumlahAngsuran = angsuranPokok + angsuranBunga;
		Double dendaPokok = dataTagihan.getDendaPokok() != null ? dataTagihan.getDendaPokok() : 0.0;
		Double dendaBunga = dataTagihan.getDendaBunga() != null ? dataTagihan.getDendaBunga() : 0.0;
		Double jumlahDenda = dendaPokok + dendaBunga;
		Double lainnya = dataTagihan.getLainnya() != null ? dataTagihan.getLainnya() : 0.0;
		Double totalTagihan = angsuranPokok + angsuranBunga + dendaPokok + dendaBunga + lainnya;
		
		modelAndView.addObject("dueDate", dueDate);
		modelAndView.addObject("angsuranPokok", angsuranPokok);
		modelAndView.addObject("angsuranBunga", angsuranBunga);
		modelAndView.addObject("jumlahAngsuran", jumlahAngsuran);
		modelAndView.addObject("dendaPokok", dendaPokok);
		modelAndView.addObject("dendaBunga", dendaBunga);
		modelAndView.addObject("jumlahDenda", jumlahDenda);
		modelAndView.addObject("lainnya", lainnya);
		modelAndView.addObject("totalTagihan", totalTagihan);
		
		modelAndView.addObject("noRekKredit", noRekKredit);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("validUrl", validUrl);
		modelAndView.addObject("mode", "MODE_POSTING");
		modelAndView.setViewName("kasir/pinjaman/angsurankredittunai");
		return modelAndView;
	}
	
	@RequestMapping(value="/angsurankredittunai/{noRekKredit}", method = RequestMethod.POST)
	public ModelAndView AngsuranKreditTunaiPost(@PathVariable String noRekKredit, @Valid Transaksi1005Input transaksi1005Input, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankredittunai/" + noRekKredit + "?errMsg=Invalid Input!");
			return modelAndView;
		}
		
		String trxRefNo;
		try {
			trxRefNo = transaksiService.Transaksi1005(transaksi1005Input);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankredittunai/" + noRekKredit + "?errMsg=" + e.getMessage());
			return modelAndView;
		}
		
		String sccMsg = "Posting Success, Transaction Ref No : " + trxRefNo;
		String validUrl = "/kasir/validasi/printtext/" + trxRefNo;
		modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankredittunai/" + noRekKredit + "?sccMsg=" + sccMsg + "&validUrl=" + validUrl);
		return modelAndView;
	}
	
	/* 1006 */
	
	@RequestMapping(value="/angsurankreditrekeningsearch", method = RequestMethod.GET)
	public ModelAndView AngsuranKreditRekeningSearch(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("listProductType", parameterProductTypeService.findAll());
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("mode", "MODE_SEARCH");
		modelAndView.setViewName("kasir/pinjaman/angsurankreditrekening");
		return modelAndView;
	}
	
	@RequestMapping(value="/angsurankreditrekeningsearch", method = RequestMethod.POST)
	public ModelAndView AngsuranKreditRekeningSearchPost(String noRekKredit, String noRekBeban, String jenisRekBeban) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		// Mencari Rekening Kredit
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		
		// Jika RekeningKredit tidak ditemukan
		if (rekeningKredit == null) {
			String errMsg = "Rekening Kredit dengan No " + noRekKredit + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekeningsearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		// Mencari Data Tagihan, 1 record paling terdahulu yang belum dibayar
		DataTagihan dataTagihan = dataTagihanService.findFirstNotPaid(noRekKredit);
		
		// Jika Tagihan tidak ditemukan
		if (dataTagihan == null) {
			String errMsg = "Tagihan atas Rekening No " + noRekKredit + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekeningsearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		if (jenisRekBeban.equals("2")) {
			
			// Beban Rekening Tabungan
			
			TabunganPembentukanRekening rektabungan = tabunganPembentukanRekeningService.findById(noRekBeban);
			
			if (rektabungan == null) {
				String errMsg = "Tabungan dengan No Rekening " + noRekBeban + " tidak ditemukan!";
				modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekeningsearch?errMsg=" + errMsg);
				return modelAndView;
			}
			
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekening/" + noRekKredit + "/" + rektabungan.getNo_rekg() + "/" + jenisRekBeban);
			return modelAndView;
			
		} else if (jenisRekBeban.equals("4")) {
			
			// Beban Rekening Buku Besar
			
			RekeningBukuBesar rekbukubesar = rekeningBukuBesarService.findOne(noRekBeban);
			
			if (rekbukubesar == null) {
				String errMsg = "Buku Besar dengan No Rekening " + noRekBeban + " tidak ditemukan!";
				modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekeningsearch?errMsg=" + errMsg);
				return modelAndView;
			}
			
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekening/" + noRekKredit + "/" + rekbukubesar.getNoRekening() + "/" + jenisRekBeban);
			return modelAndView;
			
		}
		
		// Beban Lainnya
		
		String errMsg = "Jenis Rekening Beban tidak valid!";
		
		modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekeningsearch?errMsg=" + errMsg);
		return modelAndView;
	}
	
	@RequestMapping(value="/angsurankreditrekening/{noRekKredit}/{noRekBeban}/{jenisRekBeban}", method = RequestMethod.GET)
	public ModelAndView AngsuranKreditRekening(@PathVariable String noRekKredit, @PathVariable String noRekBeban, @PathVariable String jenisRekBeban, String errMsg, String sccMsg, String validUrl) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		Transaksi1006Input transaksi1006Input = new Transaksi1006Input();
		transaksi1006Input.setUserIdPost(getUser().getUserId());
		transaksi1006Input.setNoRekKredit(noRekKredit);
		transaksi1006Input.setNoRekBeban(noRekBeban);
		transaksi1006Input.setJenisRekBeban(jenisRekBeban);
		modelAndView.addObject("transaksi1006Input", transaksi1006Input);
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		modelAndView.addObject("rekeningKredit", rekeningKredit);
		
		DataTagihan dataTagihan = dataTagihanService.findFirstNotPaid(noRekKredit);
		if (dataTagihan == null)
			dataTagihan = new DataTagihan();
		
		Date dueDate = dataTagihan.getDueDate();
		Double angsuranPokok = dataTagihan.getPokok() != null ? dataTagihan.getPokok() : 0.0;
		Double angsuranBunga = dataTagihan.getBunga() != null ? dataTagihan.getBunga() : 0.0;
		Double jumlahAngsuran = angsuranPokok + angsuranBunga;
		Double dendaPokok = dataTagihan.getDendaPokok() != null ? dataTagihan.getDendaPokok() : 0.0;
		Double dendaBunga = dataTagihan.getDendaBunga() != null ? dataTagihan.getDendaBunga() : 0.0;
		Double jumlahDenda = dendaPokok + dendaBunga;
		Double lainnya = dataTagihan.getLainnya() != null ? dataTagihan.getLainnya() : 0.0;
		Double totalTagihan = angsuranPokok + angsuranBunga + dendaPokok + dendaBunga + lainnya;
		
		modelAndView.addObject("dueDate", dueDate);
		modelAndView.addObject("angsuranPokok", angsuranPokok);
		modelAndView.addObject("angsuranBunga", angsuranBunga);
		modelAndView.addObject("jumlahAngsuran", jumlahAngsuran);
		modelAndView.addObject("dendaPokok", dendaPokok);
		modelAndView.addObject("dendaBunga", dendaBunga);
		modelAndView.addObject("jumlahDenda", jumlahDenda);
		modelAndView.addObject("lainnya", lainnya);
		modelAndView.addObject("totalTagihan", totalTagihan);
		
		if (jenisRekBeban.equals("2")) {
			// Beban Rekening Tabungan
			TabunganPembentukanRekening rektabungan = tabunganPembentukanRekeningService.findById(noRekBeban);
			modelAndView.addObject("rektabungan", rektabungan);
		} else if (jenisRekBeban.equals("4")) {
			// Beban Rekening Buku Besar
			RekeningBukuBesar rekbukubesar = rekeningBukuBesarService.findOne(noRekBeban);
			modelAndView.addObject("rekbukubesar", rekbukubesar);
		} else {
			// Bukan Tabungan & Bukan Buku Besar --> throws error
			String errMsg2 = "Jenis Rekening Beban tidak valid!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekeningsearch?errMsg=" + errMsg2);
			return modelAndView;
		}
		
		modelAndView.addObject("noRekKredit", noRekKredit);
		modelAndView.addObject("noRekBeban", noRekBeban);
		modelAndView.addObject("jenisRekBeban", jenisRekBeban);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("validUrl", validUrl);
		modelAndView.addObject("mode", "MODE_POSTING");
		modelAndView.setViewName("kasir/pinjaman/angsurankreditrekening");
		return modelAndView;
	}
	
	@RequestMapping(value="/angsurankreditrekening/{noRekKredit}/{noRekBeban}/{jenisRekBeban}", method = RequestMethod.POST)
	public ModelAndView AngsuranKreditRekeningPost(@PathVariable String noRekKredit, @PathVariable String noRekBeban, @PathVariable String jenisRekBeban, @Valid Transaksi1006Input transaksi1006Input, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekening/" + noRekKredit + "/" + noRekBeban + "/" + jenisRekBeban + "?errMsg=Invalid Input!");
			return modelAndView;
		}
		
		String trxRefNo;
		try {
			trxRefNo = transaksiService.Transaksi1006(transaksi1006Input);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekening/" + noRekKredit + "/" + noRekBeban + "/" + jenisRekBeban + "?errMsg=" + e.getMessage());
			return modelAndView;
		}
		
		String sccMsg = "Posting Success, Transaction Ref No : " + trxRefNo;
		String validUrl = "/kasir/validasi/printtext/" + trxRefNo;
		modelAndView.setViewName("redirect:/kasir/pinjaman/angsurankreditrekening/" + noRekKredit + "/" + noRekBeban + "/" + jenisRekBeban + "?sccMsg=" + sccMsg + "&validUrl=" + validUrl);
		return modelAndView;
	}
	
	/* 1007 */
	
	@RequestMapping(value="/pelunasankredittunaisearch", method = RequestMethod.GET)
	public ModelAndView PelunasanKreditTunaiSearch(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("mode", "MODE_SEARCH");
		modelAndView.setViewName("kasir/pinjaman/pelunasankredittunai");
		return modelAndView;
	}
	
	@RequestMapping(value="/pelunasankredittunaisearch", method = RequestMethod.POST)
	public ModelAndView PelunasanKreditTunaiSearchPost(String noRekKredit) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		// Mencari Rekening Kredit
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		
		// Jika RekeningKredit tidak ditemukan
		if (rekeningKredit == null) {
			String errMsg = "Rekening Kredit dengan No " + noRekKredit + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankredittunaisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankredittunai/" + noRekKredit);
		return modelAndView;
	}
	
	@RequestMapping(value="/pelunasankredittunai/{noRekKredit}", method = RequestMethod.GET)
	public ModelAndView PelunasanKreditTunai(@PathVariable String noRekKredit, String errMsg, String sccMsg, String validUrl) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		Transaksi1007Input transaksi1007Input = new Transaksi1007Input();
		transaksi1007Input.setUserIdPost(getUser().getUserId());
		transaksi1007Input.setNoRekKredit(noRekKredit);
		modelAndView.addObject("transaksi1007Input", transaksi1007Input);
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		modelAndView.addObject("rekeningKredit", rekeningKredit);
		
		Double bakiDebet = rekeningKredit.getBakiDebet() != null ? rekeningKredit.getBakiDebet() * -1 : 0.0;
		Double nilaiPinalti = rekeningKredit.getNilaiPinalti() != null ? rekeningKredit.getNilaiPinalti() * -1 : 0.0;
		Double totalPelunasan = bakiDebet + nilaiPinalti;
		
		modelAndView.addObject("bakiDebet", bakiDebet);
		modelAndView.addObject("nilaiPinalti", nilaiPinalti);
		modelAndView.addObject("totalPelunasan", totalPelunasan);
		
		modelAndView.addObject("noRekKredit", noRekKredit);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("validUrl", validUrl);
		modelAndView.addObject("mode", "MODE_POSTING");
		modelAndView.setViewName("kasir/pinjaman/pelunasankredittunai");
		return modelAndView;
	}
	
	@RequestMapping(value="/pelunasankredittunai/{noRekKredit}", method = RequestMethod.POST)
	public ModelAndView PelunasanKreditTunaiPost(@PathVariable String noRekKredit, @Valid Transaksi1007Input transaksi1007Input, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankredittunai/" + noRekKredit + "?errMsg=Invalid Input!");
			return modelAndView;
		}
		
		String trxRefNo;
		try {
			trxRefNo = transaksiService.Transaksi1007(transaksi1007Input);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankredittunai/" + noRekKredit + "?errMsg=" + e.getMessage());
			return modelAndView;
		}
		
		String sccMsg = "Posting Success, Transaction Ref No : " + trxRefNo;
		String validUrl = "/kasir/validasi/printtext/" + trxRefNo;
		modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankredittunai/" + noRekKredit + "?sccMsg=" + sccMsg + "&validUrl=" + validUrl);
		return modelAndView;
	}
	
	/* 1008 */
	
	@RequestMapping(value="/pelunasankreditrekeningsearch", method = RequestMethod.GET)
	public ModelAndView PelunasanKreditRekeningSearch(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("listProductType", parameterProductTypeService.findAll());
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("mode", "MODE_SEARCH");
		modelAndView.setViewName("kasir/pinjaman/pelunasankreditrekening");
		return modelAndView;
	}
	
	@RequestMapping(value="/pelunasankreditrekeningsearch", method = RequestMethod.POST)
	public ModelAndView PelunasanKreditRekeningSearchPost(String noRekKredit, String noRekBeban, String jenisRekBeban) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		// Mencari Rekening Kredit
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		
		// Jika RekeningKredit tidak ditemukan
		if (rekeningKredit == null) {
			String errMsg = "Rekening Kredit dengan No " + noRekKredit + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankreditrekeningsearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		if (jenisRekBeban.equals("2")) {
			
			// Beban Rekening Tabungan
			
			TabunganPembentukanRekening rektabungan = tabunganPembentukanRekeningService.findById(noRekBeban);
			
			if (rektabungan == null) {
				String errMsg = "Tabungan dengan No Rekening " + noRekBeban + " tidak ditemukan!";
				modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankreditrekeningsearch?errMsg=" + errMsg);
				return modelAndView;
			}
			
			modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankreditrekening/" + noRekKredit + "/" + rektabungan.getNo_rekg() + "/" + jenisRekBeban);
			return modelAndView;
			
		} else if (jenisRekBeban.equals("4")) {
			
			// Beban Rekening Buku Besar
			
			RekeningBukuBesar rekbukubesar = rekeningBukuBesarService.findOne(noRekBeban);
			
			if (rekbukubesar == null) {
				String errMsg = "Buku Besar dengan No Rekening " + noRekBeban + " tidak ditemukan!";
				modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankreditrekeningsearch?errMsg=" + errMsg);
				return modelAndView;
			}
			
			modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankreditrekening/" + noRekKredit + "/" + rekbukubesar.getNoRekening() + "/" + jenisRekBeban);
			return modelAndView;
			
		}
		
		// Beban Lainnya
		
		String errMsg = "Jenis Rekening Beban tidak valid!";
		
		modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankreditrekeningsearch?errMsg=" + errMsg);
		return modelAndView;
	}
	
	@RequestMapping(value="/pelunasankreditrekening/{noRekKredit}/{noRekBeban}/{jenisRekBeban}", method = RequestMethod.GET)
	public ModelAndView PelunasanKreditRekening(@PathVariable String noRekKredit, @PathVariable String noRekBeban, @PathVariable String jenisRekBeban, String errMsg, String sccMsg, String validUrl) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		Transaksi1008Input transaksi1008Input = new Transaksi1008Input();
		transaksi1008Input.setUserIdPost(getUser().getUserId());
		transaksi1008Input.setNoRekKredit(noRekKredit);
		transaksi1008Input.setNoRekBeban(noRekBeban);
		transaksi1008Input.setJenisRekBeban(jenisRekBeban);
		modelAndView.addObject("transaksi1008Input", transaksi1008Input);
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekKredit);
		modelAndView.addObject("rekeningKredit", rekeningKredit);
		
		Double bakiDebet = rekeningKredit.getBakiDebet() != null ? rekeningKredit.getBakiDebet() * -1 : 0.0;
		Double nilaiPinalti = rekeningKredit.getNilaiPinalti() != null ? rekeningKredit.getNilaiPinalti() * -1 : 0.0;
		Double totalPelunasan = bakiDebet + nilaiPinalti;
		
		modelAndView.addObject("bakiDebet", bakiDebet);
		modelAndView.addObject("nilaiPinalti", nilaiPinalti);
		modelAndView.addObject("totalPelunasan", totalPelunasan);
		
		if (jenisRekBeban.equals("2")) {
			// Beban Rekening Tabungan
			TabunganPembentukanRekening rektabungan = tabunganPembentukanRekeningService.findById(noRekBeban);
			modelAndView.addObject("rektabungan", rektabungan);
		} else if (jenisRekBeban.equals("4")) {
			// Beban Rekening Buku Besar
			RekeningBukuBesar rekbukubesar = rekeningBukuBesarService.findOne(noRekBeban);
			modelAndView.addObject("rekbukubesar", rekbukubesar);
		} else {
			// Bukan Tabungan & Bukan Buku Besar --> throws error
			String errMsg2 = "Jenis Rekening Beban tidak valid!";
			modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankreditrekeningsearch?errMsg=" + errMsg2);
			return modelAndView;
		}
		
		modelAndView.addObject("noRekKredit", noRekKredit);
		modelAndView.addObject("noRekBeban", noRekBeban);
		modelAndView.addObject("jenisRekBeban", jenisRekBeban);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("validUrl", validUrl);
		modelAndView.addObject("mode", "MODE_POSTING");
		modelAndView.setViewName("kasir/pinjaman/pelunasankreditrekening");
		return modelAndView;
	}
	
	@RequestMapping(value="/pelunasankreditrekening/{noRekKredit}/{noRekBeban}/{jenisRekBeban}", method = RequestMethod.POST)
	public ModelAndView PelunasanKreditRekeningPost(@PathVariable String noRekKredit, @PathVariable String noRekBeban, @PathVariable String jenisRekBeban, @Valid Transaksi1008Input transaksi1008Input, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankreditrekening/" + noRekKredit + "/" + noRekBeban + "/" + jenisRekBeban + "?errMsg=Invalid Input!");
			return modelAndView;
		}
		
		String trxRefNo;
		try {
			trxRefNo = transaksiService.Transaksi1008(transaksi1008Input);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankreditrekening/" + noRekKredit + "/" + noRekBeban + "/" + jenisRekBeban + "?errMsg=" + e.getMessage());
			return modelAndView;
		}
		
		String sccMsg = "Posting Success, Transaction Ref No : " + trxRefNo;
		String validUrl = "/kasir/validasi/printtext/" + trxRefNo;
		modelAndView.setViewName("redirect:/kasir/pinjaman/pelunasankreditrekening/" + noRekKredit + "/" + noRekBeban + "/" + jenisRekBeban + "?sccMsg=" + sccMsg + "&validUrl=" + validUrl);
		return modelAndView;
	}
	
}
