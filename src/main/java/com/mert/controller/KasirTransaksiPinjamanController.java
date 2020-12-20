package com.mert.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.mert.model.Transaksi1001Input;
import com.mert.service.AppUserService;
import com.mert.service.FasilitasKreditOverrideModelService;
import com.mert.service.FasilitasKreditPembayaranModelService;
import com.mert.service.ParameterKodeBiayaService;
import com.mert.service.TransaksiService;

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
	public ModelAndView BiayaAdminKreditTunai(@PathVariable String noReferensi, String errMsg, String sccMsg) {
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
		modelAndView.addObject("mode", "MODE_POSTING");
		modelAndView.setViewName("/kasir/pinjaman/biayaadminkredittunai");
		return modelAndView;
	}
	
	@RequestMapping(value="/biayaadminkredittunai/{noReferensi}", method = RequestMethod.POST)
	public ModelAndView BiayaAdminKreditTunai(@PathVariable String noReferensi, @Valid Transaksi1001Input transaksi1001Input, BindingResult bindingResult) {
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
		modelAndView.setViewName("redirect:/kasir/pinjaman/biayaadminkredittunai/" + noReferensi + "?sccMsg=" + sccMsg);
		return modelAndView;
	}

}
