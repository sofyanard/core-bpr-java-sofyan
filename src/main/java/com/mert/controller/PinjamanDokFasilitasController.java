package com.mert.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import com.mert.model.FasilitasKredit;
import com.mert.service.FasilitasKreditService;
import com.mert.model.NasabahDokumen;
import com.mert.service.NasabahDokumenService;
import com.mert.model.DokumenFasilitas;
import com.mert.model.DokumenFasilitasViewModel;
import com.mert.service.DokumenFasilitasService;
import com.mert.service.ParameterService;

@Controller
@RequestMapping("/pinjaman/dokfasilitas")
public class PinjamanDokFasilitasController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private NasabahDokumenService nasabahDokumenService;
	
	@Autowired
	private DokumenFasilitasService dokumenFasilitasService;
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private FasilitasKreditService fasilitasKreditService;
	
	// Parameter upload.folder di application.properties
	@Value("${upload.folder}")
	private String uploadFolder;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/index/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView Index(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("listDokumen", dokumenFasilitasService.FindByNoFasilitas(nofasilitas));
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("mode", "MODE_INDEX");
		modelAndView.addObject("menu", "MENU_PINJAMAN_FASILITAS_EDIT");
		modelAndView.setViewName("pinjaman/dokumenfasilitas");
		return modelAndView;
	}
	
	@RequestMapping(value="/create/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView Create(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		DokumenFasilitasViewModel dokumenFasilitasViewModel = new DokumenFasilitasViewModel();
		modelAndView.addObject("dokumenFasilitasViewModel", dokumenFasilitasViewModel);
		modelAndView.addObject("kodedokumens", parameterService.listAllKodeDokumen());
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("mode", "MODE_CREATE");
		modelAndView.addObject("menu", "MENU_PINJAMAN_FASILITAS_EDIT");
		modelAndView.setViewName("pinjaman/dokumenfasilitas");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/{nofasilitas}", method = RequestMethod.POST)
	public ModelAndView Insert(@PathVariable String nofasilitas, @Valid DokumenFasilitasViewModel dokumenFasilitasViewModel, BindingResult bindingResult, @RequestParam MultipartFile uploadFile) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back To Form
			modelAndView.addObject("dokumenFasilitasViewModel", dokumenFasilitasViewModel);
			modelAndView.addObject("kodedokumens", parameterService.listAllKodeDokumen());
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.addObject("mode", "MODE_CREATE");
			modelAndView.addObject("menu", "MENU_PINJAMAN_FASILITAS_EDIT");
			modelAndView.setViewName("pinjaman/dokumenfasilitas");
			return modelAndView;
		}
		
		Long nonasabah = fasilitasKreditService.findOne(nofasilitas).getNoNasabah();
		
		NasabahDokumen nasabahDokumen = new NasabahDokumen();
		nasabahDokumen.setNonasabah(nonasabah);
		nasabahDokumen.setDokcode(dokumenFasilitasViewModel.getDokCode());
		nasabahDokumen.setCaption(dokumenFasilitasViewModel.getCaption());
		
		DokumenFasilitas dokumenFasilitas = new DokumenFasilitas();
		dokumenFasilitas.setNoFasilitas(nofasilitas);
		
		// File Upload
		try {
			String filetosave = nasabahDokumenService.fileUpload(uploadFile);
			nasabahDokumen.setFilename(filetosave);
			
			// Save NasabahDokumen
			nasabahDokumenService.save(nasabahDokumen);
			
			// Save Dokumen Fasilitas
			dokumenFasilitas.setNasabahDokumen(nasabahDokumen);
			dokumenFasilitasService.save(dokumenFasilitas);
			
		} catch (Exception e) {
			modelAndView.addObject("errorMessage", e.getMessage());
			// Back To Form
			modelAndView.addObject("dokumenFasilitasViewModel", dokumenFasilitasViewModel);
			modelAndView.addObject("kodedokumens", parameterService.listAllKodeDokumen());
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.addObject("mode", "MODE_CREATE");
			modelAndView.addObject("menu", "MENU_PINJAMAN_FASILITAS_EDIT");
			modelAndView.setViewName("pinjaman/dokumenfasilitas");
			return modelAndView;
		}
		
		// Return View
		modelAndView.setViewName("redirect:/pinjaman/dokfasilitas/index/" + nofasilitas);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{nofasilitas}/{id}", method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable String nofasilitas, @PathVariable int id) {
		DokumenFasilitas dokumenFasilitas = dokumenFasilitasService.findOne(id);
		Integer nasabahDokumenId = dokumenFasilitas.getNasabahDokumen().getId();
		String fileName = dokumenFasilitas.getNasabahDokumen().getFilename();
		Path path = Paths.get(uploadFolder + fileName);
		
		// Delete DokumenFasilitas
		dokumenFasilitasService.delete(id);
		
		// Delete NasabahDokumen
		try {
			nasabahDokumenService.delete(nasabahDokumenId);
		} catch (Exception e) { }
		
		// Delete File
		try {
			Files.deleteIfExists(path);
		} catch(Exception e) { }
		
		// Return View
		ModelAndView modelAndView = new ModelAndView("redirect:/pinjaman/dokfasilitas/index/" + nofasilitas);
		return modelAndView;
		
	}
	
	@RequestMapping(value="/inquiry/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView Inquiry(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("listDokumen", dokumenFasilitasService.FindByNoFasilitas(nofasilitas));
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("mode", "MODE_INQUIRY");
		modelAndView.addObject("menu", "MENU_PINJAMAN_FASILITAS_APPROVAL");
		modelAndView.setViewName("pinjaman/dokumenfasilitas");
		return modelAndView;
	}
	
}
