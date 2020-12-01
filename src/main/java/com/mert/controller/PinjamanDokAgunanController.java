package com.mert.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.service.AppUserService;
import com.mert.model.DataAgunan;
import com.mert.service.DataAgunanService;
import com.mert.model.NasabahDokumen;
import com.mert.service.NasabahDokumenService;
import com.mert.model.DokumenAgunan;
import com.mert.model.DokumenAgunanViewModel;
import com.mert.service.DokumenAgunanService;
import com.mert.service.ParameterService;

@Controller
@RequestMapping("/pinjaman/dokagunan")
public class PinjamanDokAgunanController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private NasabahDokumenService nasabahDokumenService;
	
	@Autowired
	private DokumenAgunanService dokumenAgunanService;
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private DataAgunanService dataAgunanService;
	
	// Parameter upload.folder di application.properties
	@Value("${upload.folder}")
	private String uploadFolder;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/index/{nofasilitas}/{noagunan}", method = RequestMethod.GET)
	public ModelAndView Index(@PathVariable String nofasilitas, @PathVariable String noagunan) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("listDokumen", dokumenAgunanService.FindByNoAgunan(noagunan));
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("noagunan", noagunan);
		modelAndView.addObject("mode", "MODE_INDEX");
		modelAndView.addObject("menu", "MENU_PINJAMAN_AGUNAN");
		modelAndView.setViewName("pinjaman/dokumenagunan");
		return modelAndView;
	}
	
	@RequestMapping(value="/create/{nofasilitas}/{noagunan}", method = RequestMethod.GET)
	public ModelAndView Create(@PathVariable String nofasilitas, @PathVariable String noagunan) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		DokumenAgunanViewModel dokumenAgunanViewModel = new DokumenAgunanViewModel();
		modelAndView.addObject("dokumenAgunanViewModel", dokumenAgunanViewModel);
		modelAndView.addObject("kodedokumens", parameterService.listAllKodeDokumen());
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("noagunan", noagunan);
		modelAndView.addObject("mode", "MODE_CREATE");
		modelAndView.addObject("menu", "MENU_PINJAMAN_AGUNAN");
		modelAndView.setViewName("pinjaman/dokumenagunan");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/{nofasilitas}/{noagunan}", method = RequestMethod.POST)
	public ModelAndView Insert(@PathVariable String nofasilitas, @PathVariable String noagunan, @Valid DokumenAgunanViewModel dokumenAgunanViewModel, BindingResult bindingResult, @RequestParam MultipartFile uploadFile) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back To Form
			modelAndView.addObject("dokumenAgunanViewModel", dokumenAgunanViewModel);
			modelAndView.addObject("kodedokumens", parameterService.listAllKodeDokumen());
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.addObject("noagunan", noagunan);
			modelAndView.addObject("mode", "MODE_CREATE");
			modelAndView.addObject("menu", "MENU_PINJAMAN_AGUNAN");
			modelAndView.setViewName("pinjaman/dokumenagunan");
			return modelAndView;
		}
		
		Long nonasabah = dataAgunanService.findOne(noagunan).getNoNasabah();
		
		NasabahDokumen nasabahDokumen = new NasabahDokumen();
		nasabahDokumen.setNonasabah(nonasabah);
		nasabahDokumen.setDokcode(dokumenAgunanViewModel.getDokCode());
		nasabahDokumen.setCaption(dokumenAgunanViewModel.getCaption());
		
		DokumenAgunan dokumenAgunan = new DokumenAgunan();
		dokumenAgunan.setNoAgunan(noagunan);
		
		// File Upload
		try {
			String filetosave = nasabahDokumenService.fileUpload(uploadFile);
			nasabahDokumen.setFilename(filetosave);
			
			// Save NasabahDokumen
			nasabahDokumenService.save(nasabahDokumen);
			
			// Save Dokumen Agunan
			dokumenAgunan.setNasabahDokumen(nasabahDokumen);
			dokumenAgunanService.save(dokumenAgunan);
		} catch (Exception e) {
			modelAndView.addObject("errorMessage", e.getMessage());
			// Back To Form
			modelAndView.addObject("dokumenAgunanViewModel", dokumenAgunanViewModel);
			modelAndView.addObject("kodedokumens", parameterService.listAllKodeDokumen());
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.addObject("noagunan", noagunan);
			modelAndView.addObject("mode", "MODE_CREATE");
			modelAndView.addObject("menu", "MENU_PINJAMAN_AGUNAN");
			modelAndView.setViewName("pinjaman/dokumenagunan");
			return modelAndView;
		}
		
		// Return View
		modelAndView.setViewName("redirect:/pinjaman/dokagunan/index/" + nofasilitas + "/" + noagunan);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{nofasilitas}/{noagunan}/{id}", method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable String nofasilitas, @PathVariable String noagunan, @PathVariable int id) {
		DokumenAgunan dokumenAgunan = dokumenAgunanService.findOne(id);
		Integer nasabahDokumenId = dokumenAgunan.getNasabahDokumen().getId();
		String fileName = dokumenAgunan.getNasabahDokumen().getFilename();
		Path path = Paths.get(uploadFolder + fileName);
		
		// Delete DokumenAgunan
		dokumenAgunanService.delete(id);
		
		// Delete NasabahDokumen
		try {
			nasabahDokumenService.delete(nasabahDokumenId);
		} catch (Exception e) { }
		
		// Delete File
		try {
			Files.deleteIfExists(path);
		} catch(Exception e) { }
		
		// Return View
		ModelAndView modelAndView = new ModelAndView("redirect:/pinjaman/dokagunan/index/" + nofasilitas + "/" + noagunan);
		return modelAndView;
	}
	
	@RequestMapping(value="/inquiry/{nofasilitas}/{noagunan}", method = RequestMethod.GET)
	public ModelAndView Inquiry(@PathVariable String nofasilitas, @PathVariable String noagunan) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("listDokumen", dokumenAgunanService.FindByNoAgunan(noagunan));
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("noagunan", noagunan);
		modelAndView.addObject("mode", "MODE_INQUIRY");
		modelAndView.addObject("menu", "MENU_PINJAMAN_FASILITAS_APPROVAL");
		modelAndView.setViewName("pinjaman/dokumenagunan");
		return modelAndView;
	}
	
	@RequestMapping(value="/notfoundbyfac/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView NotFoundByFacility(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("mode", "MODE_NOTFOUND");
		modelAndView.addObject("menu", "MENU_PINJAMAN_FASILITAS_APPROVAL");
		modelAndView.setViewName("pinjaman/dokumenagunan");
		return modelAndView;
	}
	
	@RequestMapping(value="/inquirybyfac/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView InquiryByFacility(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		DataAgunan dataAgunan = dataAgunanService.findByNoFasilitas(nofasilitas);
		if ((dataAgunan != null) && (dataAgunan.getNoAgunan() != null) && (dataAgunan.getNoAgunan() != "")) {
			modelAndView.setViewName("redirect:/pinjaman/dokagunan/inquiry/" + nofasilitas + "/" + dataAgunan.getNoAgunan());
			return modelAndView;
		} else {
			modelAndView.setViewName("redirect:/pinjaman/dokagunan/notfoundbyfac/" + nofasilitas);
			return modelAndView;
		}
	}
 
}
