package com.mert.controller;

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
import com.mert.service.AppUserService;
import com.mert.model.FasilitasKredit;
import com.mert.service.FasilitasKreditService;
import com.mert.model.AsuransiPenjaminan;
import com.mert.service.AsuransiPenjaminanService;
import com.mert.service.ParameterService;
import com.mert.service.ParameterGolonganPenjaminService;

@Controller
@RequestMapping("/pinjaman/jaminan")
public class PinjamanJaminanController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private FasilitasKreditService fasilitasKreditService;
	
	@Autowired
	private AsuransiPenjaminanService asuransiPenjaminanService;
	
	@Autowired
	private ParameterGolonganPenjaminService parameterGolonganPenjaminService;
	
	@Autowired
	private ParameterService parameterService;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/create/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView Create(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		// kalau data penjaminan sudah ada. redirect ke method Edit
		if (asuransiPenjaminanService.exists(nofasilitas)) {
			modelAndView.setViewName("redirect:/pinjaman/jaminan/edit/" + nofasilitas);
			return modelAndView;
		}
		
		FasilitasKredit fasilitasKredit = fasilitasKreditService.findOne(nofasilitas);
		
		AsuransiPenjaminan asuransiPenjaminan = new AsuransiPenjaminan();
		asuransiPenjaminan.setNoFasilitas(nofasilitas);
		asuransiPenjaminan.setNoNasabah(fasilitasKredit.getNoNasabah());
		asuransiPenjaminan.setNamaNasabah(fasilitasKredit.getNamaNasabah());
		
		modelAndView.addObject("asuransiPenjaminan", asuransiPenjaminan);
		modelAndView.addObject("listGolPenjamin", parameterGolonganPenjaminService.findAll());
		modelAndView.addObject("jenisids", parameterService.listAllJenisID());
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.setViewName("pinjaman/jaminancreate");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/{nofasilitas}", method = RequestMethod.POST)
	public ModelAndView Insert(@PathVariable String nofasilitas, @Valid AsuransiPenjaminan asuransiPenjaminan, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("asuransiPenjaminan", asuransiPenjaminan);
			modelAndView.addObject("listGolPenjamin", parameterGolonganPenjaminService.findAll());
			modelAndView.addObject("jenisids", parameterService.listAllJenisID());
			modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.setViewName("pinjaman/jaminancreate");
			return modelAndView;
		}
		
		// Save Data Penjaminan
		asuransiPenjaminanService.save(asuransiPenjaminan);
		
		// Generate View
		modelAndView.setViewName("redirect:/pinjaman/jaminan/edit/" + nofasilitas);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		AsuransiPenjaminan asuransiPenjaminan = asuransiPenjaminanService.findOne(nofasilitas);
		
		modelAndView.addObject("asuransiPenjaminan", asuransiPenjaminan);
		modelAndView.addObject("listGolPenjamin", parameterGolonganPenjaminService.findAll());
		modelAndView.addObject("jenisids", parameterService.listAllJenisID());
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("kotas", parameterService.listKotaKabByProv(asuransiPenjaminan.getProvinsi().getProvinsicode()));
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.setViewName("pinjaman/jaminanedit");
		return modelAndView;
	}
	
	@RequestMapping(value="/update/{nofasilitas}", method = RequestMethod.POST)
	public ModelAndView Update(@PathVariable String nofasilitas, @Valid AsuransiPenjaminan asuransiPenjaminan, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("asuransiPenjaminan", asuransiPenjaminan);
			modelAndView.addObject("listGolPenjamin", parameterGolonganPenjaminService.findAll());
			modelAndView.addObject("jenisids", parameterService.listAllJenisID());
			modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.setViewName("pinjaman/jaminancreate");
			return modelAndView;
		}
		
		// Save Data Penjaminan
		asuransiPenjaminanService.save(asuransiPenjaminan);
		
		// Generate View
		modelAndView.setViewName("redirect:/pinjaman/jaminan/edit/" + nofasilitas);
		return modelAndView;
	}

}
