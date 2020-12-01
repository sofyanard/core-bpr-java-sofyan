package com.mert.controller;

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
import com.mert.service.AppUserService;
import com.mert.model.FasilitasKredit;
import com.mert.service.FasilitasKreditService;
import com.mert.model.DataAgunan;
import com.mert.service.DataAgunanService;
import com.mert.service.ParameterJenisAgunanService;
import com.mert.service.ParameterStatusAgunanService;
import com.mert.service.ParameterLembagaRatingService;
import com.mert.service.ParameterService;
import com.mert.service.ParameterYesNoService;
import com.mert.service.ParameterPengikatanService;

@Controller
@RequestMapping("/pinjaman/agunan")
public class PinjamanAgunanController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private FasilitasKreditService fasilitasKreditService;
	
	@Autowired
	private DataAgunanService dataAgunanService;
	
	@Autowired
	private ParameterJenisAgunanService parameterJenisAgunanService;
	
	@Autowired
	private ParameterStatusAgunanService parameterStatusAgunanService;
	
	@Autowired
	private ParameterPengikatanService parameterPengikatanService;
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private ParameterYesNoService parameterYesNoService;
	
	@Autowired
	private ParameterLembagaRatingService parameterLembagaRatingService;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/searchedit", method = RequestMethod.GET)
	public ModelAndView SearchEdit(String nofasilitas, Long nonasabah, String namanasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		List<FasilitasKredit> listFasilitas = fasilitasKreditService.searchbyProp(nofasilitas, nonasabah, namanasabah);
		modelAndView.addObject("listFasilitas", listFasilitas);
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("nonasabah", nonasabah);
		modelAndView.addObject("namanasabah", namanasabah);
		modelAndView.setViewName("pinjaman/agunansearchedit");
		return modelAndView;
	}
	
	@RequestMapping(value="/create/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView Create(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		FasilitasKredit fasilitasKredit = fasilitasKreditService.findOne(nofasilitas);
		
		// kalau data agunan sudah ada. redirect ke method Edit
		DataAgunan dataAgunan = dataAgunanService.findByNoFasilitas(nofasilitas);
		if ((dataAgunan.getNoAgunan() != null) && (dataAgunan.getNoAgunan() != "")) {
			modelAndView.setViewName("redirect:/pinjaman/agunan/edit/" + nofasilitas + "/" + dataAgunan.getNoAgunan());
			return modelAndView;
		}
		
		dataAgunan = new DataAgunan();
		dataAgunan.setNoFasilitas(nofasilitas);
		dataAgunan.setNoNasabah(fasilitasKredit.getNoNasabah());
		dataAgunan.setNamaNasabah(fasilitasKredit.getNamaNasabah());
		
		modelAndView.addObject("dataAgunan", dataAgunan);
		modelAndView.addObject("listJenisAgunan", parameterJenisAgunanService.findAll());
		modelAndView.addObject("listStatusAgunan", parameterStatusAgunanService.findAll());
		modelAndView.addObject("listSertifikat", parameterService.listAllKodeDokumen());
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("listLembagaRating", parameterLembagaRatingService.findAll());
		modelAndView.addObject("listPengikatan", parameterPengikatanService.findAll());
		modelAndView.addObject("listYesNo", parameterYesNoService.findAll());
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.setViewName("pinjaman/agunancreate");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/{nofasilitas}", method = RequestMethod.POST)
	public ModelAndView Insert(@PathVariable String nofasilitas, @Valid DataAgunan dataAgunan, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("dataAgunan", dataAgunan);
			modelAndView.addObject("listJenisAgunan", parameterJenisAgunanService.findAll());
			modelAndView.addObject("listStatusAgunan", parameterStatusAgunanService.findAll());
			modelAndView.addObject("listSertifikat", parameterService.listAllKodeDokumen());
			modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
			modelAndView.addObject("listLembagaRating", parameterLembagaRatingService.findAll());
			modelAndView.addObject("listPengikatan", parameterPengikatanService.findAll());
			modelAndView.addObject("listYesNo", parameterYesNoService.findAll());
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.setViewName("pinjaman/agunancreate");
			return modelAndView;
		}
		
		// Generate Agunan Id
		dataAgunan.setNoAgunan(dataAgunanService.GetNewCollateralId(dataAgunan.getNoNasabah(), dataAgunan.getJenisAgunan().getJenisAgunanId()));
		
		// Save Data Agunan
		dataAgunanService.save(dataAgunan);
		
		// Generate View
		modelAndView.setViewName("redirect:/pinjaman/agunan/edit/" + nofasilitas + "/" + dataAgunan.getNoAgunan());
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{nofasilitas}/{noagunan}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable String nofasilitas, @PathVariable String noagunan) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		DataAgunan dataAgunan = dataAgunanService.findOne(noagunan);
		modelAndView.addObject("dataAgunan", dataAgunan);
		modelAndView.addObject("jenisAgunan", parameterJenisAgunanService.findOne(dataAgunan.getJenisAgunan().getJenisAgunanId()));
		modelAndView.addObject("listStatusAgunan", parameterStatusAgunanService.findAll());
		modelAndView.addObject("listSertifikat", parameterService.listAllKodeDokumen());
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("kotas", parameterService.listKotaKabByProv(dataAgunan.getProvinsi().getProvinsicode()));
		modelAndView.addObject("listLembagaRating", parameterLembagaRatingService.findAll());
		modelAndView.addObject("listPengikatan", parameterPengikatanService.findAll());
		modelAndView.addObject("listYesNo", parameterYesNoService.findAll());
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("noagunan", noagunan);
		modelAndView.setViewName("pinjaman/agunanedit");
		return modelAndView;
	}
	
	@RequestMapping(value="/update/{nofasilitas}/{noagunan}", method = RequestMethod.POST)
	public ModelAndView Update(@PathVariable String nofasilitas, @PathVariable String noagunan, @Valid DataAgunan dataAgunan, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("dataAgunan", dataAgunan);
			modelAndView.addObject("jenisAgunan", parameterJenisAgunanService.findOne(dataAgunan.getJenisAgunan().getJenisAgunanId()));
			modelAndView.addObject("listStatusAgunan", parameterStatusAgunanService.findAll());
			modelAndView.addObject("listSertifikat", parameterService.listAllKodeDokumen());
			modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
			modelAndView.addObject("kotas", parameterService.listKotaKabByProv(dataAgunan.getProvinsi().getProvinsicode()));
			modelAndView.addObject("listLembagaRating", parameterLembagaRatingService.findAll());
			modelAndView.addObject("listPengikatan", parameterPengikatanService.findAll());
			modelAndView.addObject("listYesNo", parameterYesNoService.findAll());
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.addObject("noagunan", noagunan);
			modelAndView.setViewName("pinjaman/agunanedit");
			return modelAndView;
		}
		
		// Save Data Agunan
		dataAgunanService.save(dataAgunan);
		
		// Generate View
		modelAndView.setViewName("redirect:/pinjaman/agunan/edit/" + nofasilitas + "/" + noagunan);
		return modelAndView;
	}

}
