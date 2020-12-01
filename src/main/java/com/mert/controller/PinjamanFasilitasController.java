package com.mert.controller;

import java.text.DateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.service.AppUserService;
import com.mert.model.NasabahBasic;
import com.mert.service.NasabahBasicService;
import com.mert.model.FasilitasKredit;
import com.mert.service.FasilitasKreditService;
import com.mert.model.FasilitasKreditOverrideModel;
import com.mert.service.FasilitasKreditOverrideModelService;
import com.mert.model.FasilitasKreditApprovalModel;
import com.mert.service.FasilitasKreditApprovalModelService;
import com.mert.model.ParameterProduk;
import com.mert.service.ParameterProdukService;
import com.mert.model.ParameterPurpose;
import com.mert.service.ParameterPurposeService;
import com.mert.model.ParameterYesNo;
import com.mert.service.ParameterYesNoService;
import com.mert.model.ParameterPinalti;
import com.mert.service.ParameterPinaltiService;
import com.mert.model.ParameterBank;
import com.mert.service.ParameterBankService;
import com.mert.model.ParameterSegment;
import com.mert.service.ParameterSegmentService;
import com.mert.model.AsuransiPenjaminan;
import com.mert.service.AsuransiPenjaminanService;

@Controller
@RequestMapping("/pinjaman/fasilitas")
public class PinjamanFasilitasController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private NasabahBasicService nasabahBasicService;
	
	@Autowired
	private FasilitasKreditService fasilitasKreditService;
	
	@Autowired
	private FasilitasKreditOverrideModelService fasilitasKreditOverrideModelService;
	
	@Autowired
	private FasilitasKreditApprovalModelService fasilitasKreditApprovalModelService;
	
	@Autowired
	private ParameterProdukService parameterProdukService;
	
	@Autowired
	private ParameterPurposeService parameterPurposeService;
	
	@Autowired
	private ParameterYesNoService parameterYesNoService;
	
	@Autowired
	private ParameterPinaltiService parameterPinaltiService;
	
	@Autowired
	private ParameterBankService parameterBankService;
	
	@Autowired
	private ParameterSegmentService parameterSegmentService;
	
	@Autowired
	private AsuransiPenjaminanService asuransiPenjaminanService;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/searchnew", method = RequestMethod.GET)
	public ModelAndView SearchNew(Long nonasabah, String noid, String nama) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		List<NasabahBasic> listNasabah = nasabahBasicService.searchByProp(nonasabah, noid, nama);
		modelAndView.addObject("listNasabah", listNasabah);
		modelAndView.addObject("nonasabah", nonasabah);
		modelAndView.addObject("noid", noid);
		modelAndView.addObject("nama", nama);
		modelAndView.setViewName("pinjaman/fasilitassearchnew");
		return modelAndView;
	}
	
	@RequestMapping(value="/create/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView Create(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		FasilitasKredit fasilitasKredit = new FasilitasKredit();
		fasilitasKredit.setNoNasabah(nonasabah);
		fasilitasKredit.setNamaNasabah(nasabahBasicService.findByNonasabah(nonasabah).getNamalengkap());
		modelAndView.addObject("fasilitasKredit", fasilitasKredit);
		modelAndView.addObject("listProduk", parameterProdukService.findByType("1"));
		modelAndView.addObject("listPurpose", parameterPurposeService.findAll());
		modelAndView.addObject("listYesNo", parameterYesNoService.findAll());
		modelAndView.addObject("listPinalti", parameterPinaltiService.findAll());
		modelAndView.addObject("listBank", parameterBankService.findAll());
		modelAndView.addObject("listSegment", parameterSegmentService.findAll());
		modelAndView.addObject("nonasabah", nonasabah);
		modelAndView.setViewName("pinjaman/fasilitascreate");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/{nonasabah}", method = RequestMethod.POST)
	public ModelAndView Insert(@PathVariable Long nonasabah, @Valid FasilitasKredit fasilitasKredit, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("fasilitasKredit", fasilitasKredit);
			modelAndView.addObject("listProduk", parameterProdukService.findByType("1"));
			modelAndView.addObject("listPurpose", parameterPurposeService.findAll());
			modelAndView.addObject("listYesNo", parameterYesNoService.findAll());
			modelAndView.addObject("listPinalti", parameterPinaltiService.findAll());
			modelAndView.addObject("listBank", parameterBankService.findAll());
			modelAndView.addObject("listSegment", parameterSegmentService.findAll());
			modelAndView.addObject("nonasabah", nonasabah);
			modelAndView.setViewName("pinjaman/fasilitascreate");
			return modelAndView;
		}
		
		// Generate Facility Id
		fasilitasKredit.setNoFasilitas(fasilitasKreditService.GetNewFacilityId(fasilitasKredit.getNoNasabah(), fasilitasKredit.getProduk().getCode()));
		
		// Save Facility
		fasilitasKreditService.save(fasilitasKredit);
		
		// Generate View
		modelAndView.setViewName("redirect:/pinjaman/fasilitas/edit/" + fasilitasKredit.getNoFasilitas());
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		FasilitasKredit fasilitasKredit = fasilitasKreditService.findOne(nofasilitas);
		modelAndView.addObject("fasilitasKredit", fasilitasKredit);
		modelAndView.addObject("produk", parameterProdukService.findOne(fasilitasKredit.getProduk().getCode()));
		modelAndView.addObject("listPurpose", parameterPurposeService.findAll());
		modelAndView.addObject("listYesNo", parameterYesNoService.findAll());
		modelAndView.addObject("listPinalti", parameterPinaltiService.findAll());
		modelAndView.addObject("listBank", parameterBankService.findAll());
		modelAndView.addObject("listSegment", parameterSegmentService.findAll());
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.setViewName("pinjaman/fasilitasedit");
		return modelAndView;
	}
	
	@RequestMapping(value="/update/{nofasilitas}", method = RequestMethod.POST)
	public ModelAndView Update(@PathVariable String nofasilitas, @Valid FasilitasKredit fasilitasKredit, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("fasilitasKredit", fasilitasKredit);
			modelAndView.addObject("produk", parameterProdukService.findOne(fasilitasKredit.getProduk().getCode()));
			modelAndView.addObject("listPurpose", parameterPurposeService.findAll());
			modelAndView.addObject("listYesNo", parameterYesNoService.findAll());
			modelAndView.addObject("listPinalti", parameterPinaltiService.findAll());
			modelAndView.addObject("listBank", parameterBankService.findAll());
			modelAndView.addObject("listSegment", parameterSegmentService.findAll());
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.setViewName("pinjaman/fasilitasedit");
			return modelAndView;
		}
		
		// Save Facility
		fasilitasKreditService.save(fasilitasKredit);
		
		// Generate View
		modelAndView.setViewName("redirect:/pinjaman/fasilitas/edit/" + nofasilitas);
		return modelAndView;
	}
	
	@RequestMapping(value="/searchapprove", method = RequestMethod.GET)
	public ModelAndView SearchApprove(String nofasilitas, Long nonasabah, String namanasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		List<FasilitasKredit> listFasilitas = fasilitasKreditService.searchbyProp(nofasilitas, nonasabah, namanasabah);
		modelAndView.addObject("listFasilitas", listFasilitas);
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("nonasabah", nonasabah);
		modelAndView.addObject("namanasabah", namanasabah);
		modelAndView.setViewName("pinjaman/fasilitassearchapprove");
		return modelAndView;
	}
	
	@RequestMapping(value="/override/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView Override(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		FasilitasKreditOverrideModel fasilitasKreditOverrideModel = fasilitasKreditOverrideModelService.findOne(nofasilitas);
		modelAndView.addObject("fasilitasKreditOverrideModel", fasilitasKreditOverrideModel);
		FasilitasKreditApprovalModel fasilitasKreditApprovalModel = fasilitasKreditApprovalModelService.findOne(nofasilitas);
		modelAndView.addObject("fasilitasKreditApprovalModel", fasilitasKreditApprovalModel);
		FasilitasKredit fasilitasKredit = fasilitasKreditService.findOne(nofasilitas);
		modelAndView.addObject("fasilitasKredit", fasilitasKredit);
		AsuransiPenjaminan asuransiPenjaminan = asuransiPenjaminanService.findOne(nofasilitas);
		modelAndView.addObject("asuransiPenjaminan", asuransiPenjaminan);
		modelAndView.addObject("produk", parameterProdukService.findOne(fasilitasKreditOverrideModel.getProduk().getCode()));
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.setViewName("pinjaman/fasilitasapprove");
		return modelAndView;
	}
	
	@RequestMapping(value="/override/{nofasilitas}", method = RequestMethod.POST)
	public ModelAndView Override(@PathVariable String nofasilitas, 
			@Valid FasilitasKreditOverrideModel fasilitasKreditOverrideModel, 
			BindingResult bindingResult) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("fasilitasKreditOverrideModel", fasilitasKreditOverrideModel);
			FasilitasKreditApprovalModel fasilitasKreditApprovalModel = fasilitasKreditApprovalModelService.findOne(nofasilitas);
			modelAndView.addObject("fasilitasKreditApprovalModel", fasilitasKreditApprovalModel);
			FasilitasKredit fasilitasKredit = fasilitasKreditService.findOne(nofasilitas);
			modelAndView.addObject("fasilitasKredit", fasilitasKredit);
			AsuransiPenjaminan asuransiPenjaminan = asuransiPenjaminanService.findOne(nofasilitas);
			modelAndView.addObject("asuransiPenjaminan", asuransiPenjaminan);
			modelAndView.addObject("produk", parameterProdukService.findOne(fasilitasKreditOverrideModel.getProduk().getCode()));
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.setViewName("pinjaman/fasilitasapprove");
			return modelAndView;
		}
		
		// Save Facility
		fasilitasKreditOverrideModelService.save(fasilitasKreditOverrideModel);
		
		// Generate View
		modelAndView.setViewName("redirect:/pinjaman/fasilitas/override/" + nofasilitas);
		return modelAndView;
	}
	
	@RequestMapping(value="/approval/{nofasilitas}", method = RequestMethod.POST)
	public ModelAndView Approval(@PathVariable String nofasilitas, 
			@Valid FasilitasKreditApprovalModel fasilitasKreditApprovalModel, 
			BindingResult bindingResult,
			@RequestParam(value="action", required=true) String action) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("fasilitasKreditApprovalModel", fasilitasKreditApprovalModel);
			FasilitasKreditOverrideModel fasilitasKreditOverrideModel = fasilitasKreditOverrideModelService.findOne(nofasilitas);
			modelAndView.addObject("fasilitasKreditOverrideModel", fasilitasKreditOverrideModel);
			FasilitasKredit fasilitasKredit = fasilitasKreditService.findOne(nofasilitas);
			modelAndView.addObject("fasilitasKredit", fasilitasKredit);
			AsuransiPenjaminan asuransiPenjaminan = asuransiPenjaminanService.findOne(nofasilitas);
			modelAndView.addObject("asuransiPenjaminan", asuransiPenjaminan);
			modelAndView.addObject("produk", parameterProdukService.findOne(fasilitasKreditOverrideModel.getProduk().getCode()));
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.setViewName("pinjaman/fasilitasapprove");
			return modelAndView;
		}
		
		// Keputusan
		if (action.equals("approve")) {
			
			fasilitasKreditApprovalModel.setPutusan("TERIMA");
			
			// Generate No Referensi
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String referenceDate = dateFormat.format(date);
			
			FasilitasKredit fasilitasKredit = fasilitasKreditService.findOne(nofasilitas);
			
			fasilitasKreditApprovalModel.setNoRef(
					fasilitasKreditService.GetNewFacilityReference(
							referenceDate, 
							fasilitasKredit.getProduk().getCode(), 
							fasilitasKredit.getNoNasabah(), 
							getUser().getUnitId().getUnitId()));
			
			fasilitasKreditApprovalModelService.save(fasilitasKreditApprovalModel);
			
		} else if (action.equals("reject")) {
			
			fasilitasKreditApprovalModel.setPutusan("TOLAK");
			fasilitasKreditApprovalModelService.save(fasilitasKreditApprovalModel);
			
		}
		
		// Generate View
		modelAndView.setViewName("redirect:/pinjaman/fasilitas/override/" + nofasilitas);
		return modelAndView;
		
	}

}
