package com.mert.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.service.AppUserService;
import com.mert.model.NasabahPerorangan;
import com.mert.model.NasabahBadanUsaha;
import com.mert.model.NasabahCreate;
import com.mert.model.NasabahUpdate;
import com.mert.model.NasabahJobnSpouse;
import com.mert.model.NasabahLaporPerorangan;
import com.mert.model.NasabahLaporBadanUsaha;
import com.mert.model.NasabahBasic;
import com.mert.model.NasabahCatatan;
import com.mert.service.NasabahService;
import com.mert.service.NasabahBasicService;
import com.mert.service.ParameterService;

@Controller
@RequestMapping("/nasabah")
public class NasabahController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private NasabahService nasabahService;
	
	@Autowired
	private NasabahBasicService nasabahBasicService;
	
	@Autowired
	private ParameterService parameterService;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/create/perorangan", method = RequestMethod.GET)
	public ModelAndView CreatePerorangan() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("nasabahPerorangan", new NasabahPerorangan());
		modelAndView.addObject("jenisids", parameterService.listAllJenisID());
		modelAndView.addObject("genders", parameterService.ListGenders());
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("negaras", parameterService.listSandiBIOJKKodeNegara());
		modelAndView.addObject("pendidikans", parameterService.listSandiBIOJKPendidikan());
		modelAndView.addObject("maritals", parameterService.ListAllMarital());
		modelAndView.addObject("homestatuses", parameterService.ListAllHomeStatus());
		modelAndView.setViewName("nasabah/createperorangan");
		return modelAndView;
	}
	
	@RequestMapping(value="/create/badanusaha", method = RequestMethod.GET)
	public ModelAndView CreateBadanUsaha() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("nasabahBadanUsaha", new NasabahBadanUsaha());
		modelAndView.addObject("jenisbus", parameterService.ListAllBadanUsaha());
		modelAndView.addObject("bidusahas", parameterService.listSandiBIOJKByKategori("SEKTOR"));
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("homestatuses", parameterService.ListAllHomeStatus());
		modelAndView.setViewName("nasabah/createbadanusaha");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/perorangan", method = RequestMethod.POST)
	public ModelAndView InsertPerorangan(NasabahPerorangan nasabahPerorangan) {
		// Generate CIF
		Long nonasabah = nasabahService.GetNewCIF(nasabahPerorangan.getNamalengkap());
		// Save Nasabah Perorangan
		nasabahPerorangan.setNonasabah(nonasabah);
		nasabahService.SaveNasabahPerorangan(nasabahPerorangan);
		// Set Create Properties
		NasabahCreate nasabahCreate = nasabahService.FindByIdCreate(nonasabah);
		nasabahCreate.setTipenasabah("1");
		nasabahCreate.setDatecreate(new Date());
		nasabahService.SaveNasabahCreate(nasabahCreate);
		// Generate View
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/edit/perorangan/" + nonasabah);
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/badanusaha", method = RequestMethod.POST)
	public ModelAndView InsertBadanUsaha(NasabahBadanUsaha nasabahBadanUsaha) {
		// Generate CIF
		Long nonasabah = nasabahService.GetNewCIF(nasabahBadanUsaha.getNamalengkap());
		// Save Nasabah Badan Usaha
		nasabahBadanUsaha.setNonasabah(nonasabah);
		nasabahService.SaveNasabahBadanUsaha(nasabahBadanUsaha);
		// Set Create Properties
		NasabahCreate nasabahCreate = nasabahService.FindByIdCreate(nonasabah);
		nasabahCreate.setTipenasabah("2");
		nasabahCreate.setDatecreate(new Date());
		nasabahService.SaveNasabahCreate(nasabahCreate);
		// Generate View
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/edit/badanusaha/" + nonasabah);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/perorangan/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView EditPerorangan(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		NasabahPerorangan nasabahPerorangan = nasabahService.FindByIdPerorangan(nonasabah);
		modelAndView.addObject("nasabahPerorangan", nasabahPerorangan);
		modelAndView.addObject("jenisids", parameterService.listAllJenisID());
		modelAndView.addObject("genders", parameterService.ListGenders());
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("kotas", parameterService.listKotaKabByProv(nasabahPerorangan.getHomeprovinsi()));
		modelAndView.addObject("negaras", parameterService.listSandiBIOJKKodeNegara());
		modelAndView.addObject("pendidikans", parameterService.listSandiBIOJKPendidikan());
		modelAndView.addObject("maritals", parameterService.ListAllMarital());
		modelAndView.addObject("homestatuses", parameterService.ListAllHomeStatus());
		modelAndView.setViewName("nasabah/editperorangan");
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/badanusaha/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView EditBadanUsaha(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		NasabahBadanUsaha nasabahBadanUsaha = nasabahService.FindByIdBadanUsaha(nonasabah);
		modelAndView.addObject("nasabahBadanUsaha", nasabahBadanUsaha);
		modelAndView.addObject("jenisbus", parameterService.ListAllBadanUsaha());
		modelAndView.addObject("bidusahas", parameterService.listSandiBIOJKByKategori("SEKTOR"));
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("kotas", parameterService.listKotaKabByProv(nasabahBadanUsaha.getOfficeprov()));
		modelAndView.addObject("homestatuses", parameterService.ListAllHomeStatus());
		modelAndView.setViewName("nasabah/editbadanusaha");
		return modelAndView;
	}
	
	@RequestMapping(value="/update/perorangan", method = RequestMethod.POST)
	public ModelAndView UpdatePerorangan(NasabahPerorangan nasabahPerorangan) {
		// Save Nasabah Perorangan
		nasabahService.SaveNasabahPerorangan(nasabahPerorangan);
		// Set Update Properties
		NasabahUpdate nasabahUpdate = nasabahService.FindByIdUpdate(nasabahPerorangan.getNonasabah());
		nasabahUpdate.setDateupdate(new Date());
		nasabahService.SaveNasabahUpdate(nasabahUpdate);
		// Generate View
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/edit/perorangan/" + nasabahPerorangan.getNonasabah());
		return modelAndView;
	}
	
	@RequestMapping(value="/update/badanusaha", method = RequestMethod.POST)
	public ModelAndView UpdateBadanUsaha(NasabahBadanUsaha nasabahBadanUsaha) {
		// Save Nasabah Badan Usaha
		nasabahService.SaveNasabahBadanUsaha(nasabahBadanUsaha);
		// Set Update Properties
		NasabahUpdate nasabahUpdate = nasabahService.FindByIdUpdate(nasabahBadanUsaha.getNonasabah());
		nasabahUpdate.setDateupdate(new Date());
		nasabahService.SaveNasabahUpdate(nasabahUpdate);
		// Generate View
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/edit/badanusaha/" + nasabahBadanUsaha.getNonasabah());
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/jobnspouse/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView EditJobnSpouse(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		NasabahJobnSpouse nasabahJobnSpouse = nasabahService.FindByIdJobnSpouse(nonasabah);
		modelAndView.addObject("nasabahJobnSpouse", nasabahJobnSpouse);
		modelAndView.addObject("jobcodes", parameterService.listSandiBIOJKJobCode());
		modelAndView.addObject("bidusahas", parameterService.listSandiBIOJKByKategori("SEKTOR"));
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("kotas", parameterService.listKotaKabByProv(nasabahJobnSpouse.getOfficeprov()));
		modelAndView.addObject("jenisids", parameterService.listAllJenisID());
		modelAndView.addObject("pendidikans", parameterService.listSandiBIOJKPendidikan());
		modelAndView.setViewName("nasabah/editjobnspouse");
		return modelAndView;
	}
	
	@RequestMapping(value="/update/jobnspouse", method = RequestMethod.POST)
	public ModelAndView UpdateBadanUsaha(NasabahJobnSpouse nasabahJobnSpouse) {
		// Save Nasabah Job n Spouse
		nasabahService.SaveNasabahJobnSpouse(nasabahJobnSpouse);
		// Set Update Properties
		NasabahUpdate nasabahUpdate = nasabahService.FindByIdUpdate(nasabahJobnSpouse.getNonasabah());
		nasabahUpdate.setDateupdate(new Date());
		nasabahService.SaveNasabahUpdate(nasabahUpdate);
		// Generate View
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/edit/jobnspouse/" + nasabahJobnSpouse.getNonasabah());
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/laporperorangan/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView EditLaporPerorangan(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		NasabahLaporPerorangan nasabahLaporPerorangan = nasabahService.FindByIdLaporPerorangan(nonasabah);
		modelAndView.addObject("nasabahLaporPerorangan", nasabahLaporPerorangan);
		modelAndView.addObject("golongans", parameterService.listSandiBIOJKGolongan());
		modelAndView.addObject("hubungans", parameterService.listSandiBIOJKHubungan());
		modelAndView.addObject("incomes", parameterService.ListAllSourceIncome());
		modelAndView.setViewName("nasabah/editlaporperorangan");
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/laporbadanusaha/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView EditLaporBadanUsaha(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		NasabahLaporBadanUsaha nasabahLaporBadanUsaha = nasabahService.FindByIdLaporBadanUsaha(nonasabah);
		modelAndView.addObject("nasabahLaporBadanUsaha", nasabahLaporBadanUsaha);
		modelAndView.addObject("golongans", parameterService.listSandiBIOJKGolongan());
		modelAndView.addObject("hubungans", parameterService.listSandiBIOJKHubungan());
		modelAndView.addObject("lembagas", parameterService.listSandiBIOJKPeringkat());
		modelAndView.addObject("incomes", parameterService.ListAllSourceIncome());
		modelAndView.setViewName("nasabah/editlaporbadanusaha");
		return modelAndView;
	}
	
	@RequestMapping(value="/update/laporperorangan", method = RequestMethod.POST)
	public ModelAndView UpdateLaporPerorangan(NasabahLaporPerorangan nasabahLaporPerorangan) {
		// Save Nasabah Lapor Perorangan
		nasabahService.SaveNasabahLaporPerorangan(nasabahLaporPerorangan);
		// Set Update Properties
		NasabahUpdate nasabahUpdate = nasabahService.FindByIdUpdate(nasabahLaporPerorangan.getNonasabah());
		nasabahUpdate.setDateupdate(new Date());
		nasabahService.SaveNasabahUpdate(nasabahUpdate);
		// Generate View
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/edit/laporperorangan/" + nasabahLaporPerorangan.getNonasabah());
		return modelAndView;
	}
	
	@RequestMapping(value="/update/laporbadanusaha", method = RequestMethod.POST)
	public ModelAndView UpdateLaporBadanUsaha(NasabahLaporBadanUsaha nasabahLaporBadanUsaha) {
		// Save Nasabah Lapor Badan Usaha
		nasabahService.SaveNasabahLaporBadanUsaha(nasabahLaporBadanUsaha);
		// Set Update Properties
		NasabahUpdate nasabahUpdate = nasabahService.FindByIdUpdate(nasabahLaporBadanUsaha.getNonasabah());
		nasabahUpdate.setDateupdate(new Date());
		nasabahService.SaveNasabahUpdate(nasabahUpdate);
		// Generate View
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/edit/laporbadanusaha/" + nasabahLaporBadanUsaha.getNonasabah());
		return modelAndView;
	}
	
	@RequestMapping(value="/searchedit", method = RequestMethod.GET)
	public ModelAndView SearchEdit(Long nonasabah, String noid, String nama) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		List<NasabahBasic> listNasabah = nasabahBasicService.searchByProp(nonasabah, noid, nama);
		modelAndView.addObject("listNasabah", listNasabah);
		modelAndView.addObject("nonasabah", nonasabah);
		modelAndView.addObject("noid", noid);
		modelAndView.addObject("nama", nama);
		modelAndView.setViewName("nasabah/searchedit");
		return modelAndView;
	}
	
	@RequestMapping(value="/searchinquiry", method = RequestMethod.GET)
	public ModelAndView SearchInquiry(Long nonasabah, String noid, String nama) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		List<NasabahBasic> listNasabah = nasabahBasicService.searchByProp(nonasabah, noid, nama);
		modelAndView.addObject("listNasabah", listNasabah);
		modelAndView.addObject("nonasabah", nonasabah);
		modelAndView.addObject("noid", noid);
		modelAndView.addObject("nama", nama);
		modelAndView.setViewName("nasabah/searchinquiry");
		return modelAndView;
	}
	
	@RequestMapping(value="/searchcatatan", method = RequestMethod.GET)
	public ModelAndView SearchCatatan(Long nonasabah, String noid, String nama) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		List<NasabahBasic> listNasabah = nasabahBasicService.searchByProp(nonasabah, noid, nama);
		modelAndView.addObject("listNasabah", listNasabah);
		modelAndView.addObject("nonasabah", nonasabah);
		modelAndView.addObject("noid", noid);
		modelAndView.addObject("nama", nama);
		modelAndView.setViewName("nasabah/searchcatatan");
		return modelAndView;
	}
	
	@RequestMapping(value="/inquiry/perorangan/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView InquiryPerorangan(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		NasabahPerorangan nasabahPerorangan = nasabahService.FindByIdPerorangan(nonasabah);
		modelAndView.addObject("nasabahPerorangan", nasabahPerorangan);
		modelAndView.addObject("jenisids", parameterService.listAllJenisID());
		modelAndView.addObject("genders", parameterService.ListGenders());
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("kotas", parameterService.listKotaKabByProv(nasabahPerorangan.getHomeprovinsi()));
		modelAndView.addObject("negaras", parameterService.listSandiBIOJKKodeNegara());
		modelAndView.addObject("pendidikans", parameterService.listSandiBIOJKPendidikan());
		modelAndView.addObject("maritals", parameterService.ListAllMarital());
		modelAndView.addObject("homestatuses", parameterService.ListAllHomeStatus());
		NasabahCatatan nasabahCatatan = nasabahBasicService.findCatatanByNonasabah(nonasabah);
		if (nasabahCatatan == null) {
			nasabahCatatan = new NasabahCatatan();
		}
		modelAndView.addObject("nasabahCatatan", nasabahCatatan);
		modelAndView.setViewName("nasabah/inquiryperorangan");
		return modelAndView;
	}
	
	@RequestMapping(value="/inquiry/badanusaha/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView InquiryBadanUsaha(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		NasabahBadanUsaha nasabahBadanUsaha = nasabahService.FindByIdBadanUsaha(nonasabah);
		modelAndView.addObject("nasabahBadanUsaha", nasabahBadanUsaha);
		modelAndView.addObject("jenisbus", parameterService.ListAllBadanUsaha());
		modelAndView.addObject("bidusahas", parameterService.listSandiBIOJKByKategori("SEKTOR"));
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("kotas", parameterService.listKotaKabByProv(nasabahBadanUsaha.getOfficeprov()));
		modelAndView.addObject("homestatuses", parameterService.ListAllHomeStatus());
		NasabahCatatan nasabahCatatan = nasabahBasicService.findCatatanByNonasabah(nonasabah);
		if (nasabahCatatan == null) {
			nasabahCatatan = new NasabahCatatan();
		}
		modelAndView.addObject("nasabahCatatan", nasabahCatatan);
		modelAndView.setViewName("nasabah/inquirybadanusaha");
		return modelAndView;
	}
	
	@RequestMapping(value="/inquiry/jobnspouse/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView InquiryJobnSpouse(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		NasabahJobnSpouse nasabahJobnSpouse = nasabahService.FindByIdJobnSpouse(nonasabah);
		modelAndView.addObject("nasabahJobnSpouse", nasabahJobnSpouse);
		modelAndView.addObject("jobcodes", parameterService.listSandiBIOJKJobCode());
		modelAndView.addObject("bidusahas", parameterService.listSandiBIOJKByKategori("SEKTOR"));
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("kotas", parameterService.listKotaKabByProv(nasabahJobnSpouse.getOfficeprov()));
		modelAndView.addObject("jenisids", parameterService.listAllJenisID());
		modelAndView.addObject("pendidikans", parameterService.listSandiBIOJKPendidikan());
		modelAndView.setViewName("nasabah/inquiryjobnspouse");
		return modelAndView;
	}
	
	@RequestMapping(value="/inquiry/laporperorangan/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView InquiryLaporPerorangan(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		NasabahLaporPerorangan nasabahLaporPerorangan = nasabahService.FindByIdLaporPerorangan(nonasabah);
		modelAndView.addObject("nasabahLaporPerorangan", nasabahLaporPerorangan);
		modelAndView.addObject("golongans", parameterService.listSandiBIOJKGolongan());
		modelAndView.addObject("hubungans", parameterService.listSandiBIOJKHubungan());
		modelAndView.addObject("incomes", parameterService.ListAllSourceIncome());
		modelAndView.setViewName("nasabah/inquirylaporperorangan");
		return modelAndView;
	}
	
	@RequestMapping(value="/inquiry/laporbadanusaha/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView InquiryLaporBadanUsaha(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		NasabahLaporBadanUsaha nasabahLaporBadanUsaha = nasabahService.FindByIdLaporBadanUsaha(nonasabah);
		modelAndView.addObject("nasabahLaporBadanUsaha", nasabahLaporBadanUsaha);
		modelAndView.addObject("golongans", parameterService.listSandiBIOJKGolongan());
		modelAndView.addObject("hubungans", parameterService.listSandiBIOJKHubungan());
		modelAndView.addObject("lembagas", parameterService.listSandiBIOJKPeringkat());
		modelAndView.addObject("incomes", parameterService.ListAllSourceIncome());
		modelAndView.setViewName("nasabah/inquirylaporbadanusaha");
		return modelAndView;
	}
	
}
