package com.mert.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.service.AppUserService;
import com.mert.service.TransaksiService;
import com.mert.service.EodUnitStatusService;
import com.mert.model.ParameterTipePecahan;
import com.mert.service.ParameterTipePecahanService;
import com.mert.model.Transaksi4001Input;
import com.mert.model.Transaksi4002Input;
import com.mert.model.UserBukuBesarKasStatusViewModel;
import com.mert.service.RekeningBukuBesarService;

@Controller
public class KasirTransaksiKasController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private TransaksiService transaksiService;
	
	@Autowired
	private EodUnitStatusService eodUnitStatusService;
	
	@Autowired
	private ParameterTipePecahanService parameterTipePecahanService;
	
	@Autowired
	private RekeningBukuBesarService rekeningBukuBesarService;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/kasir/transaksikas", method = RequestMethod.GET)
	public ModelAndView TransaksiKas() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.setViewName("kasir/transaksikas");
		return modelAndView;
	}
	
	@RequestMapping(value="/kasir/kaskeluar", method = RequestMethod.GET)
	public ModelAndView KasKeluar(String errMsg, String sccMsg, String validUrl) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		Transaksi4001Input transaksi4001Input = new Transaksi4001Input();
		transaksi4001Input.setUserIdPost(getUser().getUserId());
		modelAndView.addObject("transaksi4001Input", transaksi4001Input);
		List<AppUser> listUser = eodUnitStatusService.listOpenBukuBesarUsersByUnitToday(getUser().getUnitId().getUnitId());
		modelAndView.addObject("listUser", listUser);
		List<ParameterTipePecahan> listPecahan = parameterTipePecahanService.findAll();
		modelAndView.addObject("listPecahan", listPecahan);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("validUrl", validUrl);
		modelAndView.setViewName("kasir/kaskeluar");
		return modelAndView;
	}
	
	@RequestMapping(value="/kasir/kaskeluar", method = RequestMethod.POST)
	public ModelAndView KasKeluarPost(@Valid Transaksi4001Input transaksi4001Input, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/kasir/kaskeluar?errMsg=Invalid Input!");
			return modelAndView;
		}

		String trxRefNo;
		try {
			trxRefNo = transaksiService.Transaksi4001(transaksi4001Input);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/kaskeluar?errMsg=" + e.getMessage());
			return modelAndView;
		}
		
		String sccMsg = "Posting Success, Transaction Ref No : " + trxRefNo;
		String validUrl = "/kasir/validasi/printtext/" + trxRefNo;
		modelAndView.setViewName("redirect:/kasir/kaskeluar?sccMsg=" + sccMsg + "&validUrl=" + validUrl);
		return modelAndView;
	}
	
	@RequestMapping(value="/kasir/kaspooling", method = RequestMethod.GET)
	public ModelAndView KasPooling(String errMsg, String sccMsg, String validUrl) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		Transaksi4002Input transaksi4002Input = new Transaksi4002Input();
		transaksi4002Input.setUserIdPost(getUser().getUserId());
		transaksi4002Input.setUnitId(getUser().getUnitId().getUnitId());
		modelAndView.addObject("transaksi4002Input", transaksi4002Input);
		
		List<UserBukuBesarKasStatusViewModel> listUserBukuBesarKasStatusViewModel = new ArrayList<UserBukuBesarKasStatusViewModel>();
		
		// List<AppUser> listUser = eodUnitStatusService.listOpenBukuBesarUsersByUnitToday(getUser().getUnitId().getUnitId());
		List<AppUser> listUser = eodUnitStatusService.listOpenBukuBesarKasUsersByUnitToday(getUser().getUnitId().getUnitId());
		
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
		
		modelAndView.addObject("listUserBukuBesarKasStatusViewModel", listUserBukuBesarKasStatusViewModel);
		
		modelAndView.addObject("totalSaldo", totalSaldo.toString());
		
		List<ParameterTipePecahan> listPecahan = parameterTipePecahanService.findAll();
		modelAndView.addObject("listPecahan", listPecahan);
		
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("validUrl", validUrl);
		
		modelAndView.setViewName("kasir/kaspooling");
		return modelAndView;
	}
	
	@RequestMapping(value="/kasir/kaspooling", method = RequestMethod.POST)
	public ModelAndView KasPoolingPost(@Valid Transaksi4002Input transaksi4002Input, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("redirect:/kasir/kaspooling?errMsg=Invalid Input!");
			return modelAndView;
		}

		String trxRefNo;
		try {
			trxRefNo = transaksiService.Transaksi4002(transaksi4002Input);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/kaspooling?errMsg=" + e.getMessage());
			return modelAndView;
		}
		
		String sccMsg = "Posting Success, Transaction Ref No : " + trxRefNo;
		String validUrl = "/kasir/validasi/printtext/" + trxRefNo;
		modelAndView.setViewName("redirect:/kasir/kaspooling?sccMsg=" + sccMsg + "&validUrl=" + validUrl);
		return modelAndView;
	}
}
