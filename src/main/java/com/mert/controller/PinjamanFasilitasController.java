package com.mert.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import com.mert.model.AppUnit;
import com.mert.service.AppUnitService;
import com.mert.model.NasabahBasic;
import com.mert.service.NasabahBasicService;
import com.mert.model.FasilitasKredit;
import com.mert.service.FasilitasKreditService;
import com.mert.model.FasilitasKreditOverrideModel;
import com.mert.service.FasilitasKreditOverrideModelService;
import com.mert.model.FasilitasKreditApprovalModel;
import com.mert.service.FasilitasKreditApprovalModelService;
import com.mert.model.FasilitasKreditPembayaranModel;
import com.mert.service.FasilitasKreditPembayaranModelService;
import com.mert.model.FasilitasKreditAktifasiModel;
import com.mert.service.FasilitasKreditAktifasiModelService;
import com.mert.model.RekeningKredit;
import com.mert.service.RekeningKreditService;
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
import com.mert.model.StatusRekg;
import com.mert.service.StatusRekgService;

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
	private FasilitasKreditPembayaranModelService fasilitasKreditPembayaranModelService;
	
	@Autowired
	private FasilitasKreditAktifasiModelService fasilitasKreditAktifasiModelService;
	
	@Autowired
	private RekeningKreditService rekeningKreditService;
	
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
	
	@Autowired
	private StatusRekgService statusRekgService;
	
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
	
	@RequestMapping(value="/aktifasisearch", method = RequestMethod.GET)
	public ModelAndView AktifasiSearch(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("mode", "MODE_SEARCH");
		modelAndView.setViewName("pinjaman/aktifasi");
		return modelAndView;
	}
	
	@RequestMapping(value="/aktifasisearch", method = RequestMethod.POST)
	public ModelAndView AktifasiSearchPost(String noReferensi) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		FasilitasKreditOverrideModel fasilitasKredit = fasilitasKreditOverrideModelService.findByNoRef(noReferensi);
		
		if (fasilitasKredit == null) {
			String errMsg = "Fasilitas dengan No Referensi " + noReferensi + " tidak ditemukan!";
			modelAndView.setViewName("redirect:/pinjaman/fasilitas/aktifasisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		FasilitasKreditAktifasiModel fasilitasKredit2 = fasilitasKreditAktifasiModelService.findOne(fasilitasKredit.getNoFasilitas());
		
		if ((fasilitasKredit2.getNoRekening() != null) && (!fasilitasKredit2.getNoRekening().isEmpty())) {
			String noRek = fasilitasKredit2.getNoRekening();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String activeDate = formatter.format(fasilitasKredit2.getAktifasiDate());
			
			String errMsg = "Fasilitas dengan No Referensi " + noReferensi + 
					" sudah diaktifkan pada " + activeDate + 
					" No Rekening " + noRek + "!";
			modelAndView.setViewName("redirect:/pinjaman/fasilitas/aktifasisearch?errMsg=" + errMsg);
			return modelAndView;
		}
		
		modelAndView.setViewName("redirect:/pinjaman/fasilitas/aktifasi/" + fasilitasKredit.getNoFasilitas());
		return modelAndView;
		
	}
	
	@RequestMapping(value="/aktifasi/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView Aktifasi(@PathVariable String nofasilitas, String errMsg, String sccMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		AppUnit appUnit = getUser().getUnitId();
		modelAndView.addObject("appUnit", appUnit);
		
		FasilitasKreditOverrideModel fasilitasKredit = fasilitasKreditOverrideModelService.findOne(nofasilitas);
		modelAndView.addObject("fasilitasKredit", fasilitasKredit);
		
		FasilitasKreditPembayaranModel fasilitasKredit2 = fasilitasKreditPembayaranModelService.findOne(nofasilitas);
		modelAndView.addObject("fasilitasKredit2", fasilitasKredit2);
		
		FasilitasKreditAktifasiModel fasilitasKredit3 = fasilitasKreditAktifasiModelService.findOne(nofasilitas);
		modelAndView.addObject("fasilitasKredit3", fasilitasKredit3);
		
		RekeningKredit rekeningKredit = rekeningKreditService.findByNoFasilitas(nofasilitas);
		modelAndView.addObject("rekeningKredit", rekeningKredit);
		
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.addObject("sccMsg", sccMsg);
		modelAndView.addObject("mode", "MODE_AKTIFASI");
		modelAndView.setViewName("pinjaman/aktifasi");
		return modelAndView;
	}
	
	@RequestMapping(value="/aktifasi/{nofasilitas}", method = RequestMethod.POST)
	public ModelAndView AktifasiPost(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		try {
			this.CreateRekeningFromFasilitas(nofasilitas);
		}
		catch (Exception e) {
			String errMsg = e.getMessage();
			modelAndView.setViewName("redirect:/pinjaman/fasilitas/aktifasi/" + nofasilitas + "?errMsg=" + errMsg);
			return modelAndView;
		}
		
		String sccMsg = "Pembentukan dan Aktifasi Rekening Sukses!";
		modelAndView.setViewName("redirect:/pinjaman/fasilitas/aktifasi/" + nofasilitas + "?sccMsg=" + sccMsg);
		return modelAndView;
	}
	
	private void CreateRekeningFromFasilitas(String nofasilitas) throws Exception {
		
		try {
			FasilitasKredit fasilitasKredit = fasilitasKreditService.findOne(nofasilitas);
			
			String prefix = getUser().getUnitId().getUnitId().substring(0, 3);
			
			RekeningKredit rekeningKredit = new RekeningKredit();
			rekeningKredit.setNoRekening(rekeningKreditService.GetNewNoRekening(prefix));
			rekeningKredit.setNoFasilitas(nofasilitas);
			rekeningKredit.setNoNasabah(fasilitasKredit.getNoNasabah());
			rekeningKredit.setNamaNasabah(fasilitasKredit.getNamaNasabah());
			rekeningKredit.setUnitId(getUser().getUnitId());
			rekeningKredit.setProduk(fasilitasKredit.getProduk());
			rekeningKredit.setValuta(fasilitasKredit.getValuta());
			rekeningKredit.setPlafond(fasilitasKredit.getPlafond());
			rekeningKredit.setKurs(fasilitasKredit.getKurs());
			rekeningKredit.setEqvPlafond(fasilitasKredit.getEqvPlafond());
			rekeningKredit.setTenor(fasilitasKredit.getTenor());
			rekeningKredit.setBungaPersen(fasilitasKredit.getBungaPersen());
			rekeningKredit.setHitungBunga(fasilitasKredit.getHitungBunga());
			rekeningKredit.setPinaltiBungaPersen(fasilitasKredit.getPinaltiBungaPersen());
			rekeningKredit.setPinaltiPokokPersen(fasilitasKredit.getPinaltiPokokPersen());
			rekeningKredit.setPinaltiLunasPersen(fasilitasKredit.getPinaltiLunasPersen());
			rekeningKredit.setPinaltiFlag(fasilitasKredit.getPinaltiFlag());
			
			StatusRekg statusRekg = statusRekgService.findByCode("1");
			rekeningKredit.setStatusRekening(statusRekg);
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			Date todayWithZeroTime = formatter.parse(formatter.format(today));
			rekeningKredit.setDueDate(todayWithZeroTime);
			
			rekeningKreditService.save(rekeningKredit);
			
			// Update Fasilitas Kredit
			FasilitasKreditAktifasiModel fasilitasKredit2 = fasilitasKreditAktifasiModelService.findOne(nofasilitas);
			fasilitasKredit2.setNoRekening(rekeningKredit.getNoRekening());
			fasilitasKredit2.setAktifasiDate(new Date());
			fasilitasKreditAktifasiModelService.save(fasilitasKredit2);

		}
		catch (Exception e) {
			throw e;
		}
		
	}

}
