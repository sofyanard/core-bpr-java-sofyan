package com.mert.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.model.NasabahDokumen;
import com.mert.service.AppUserService;
import com.mert.service.NasabahDokumenService;
import com.mert.service.NasabahBasicService;
import com.mert.service.ParameterService;

@Controller
@RequestMapping("/nasabah/dokumen")
public class NasabahDokumenController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private NasabahDokumenService nasabahDokumenService;
	
	@Autowired
	private NasabahBasicService nasabahBasicService;
	
	@Autowired
	private ParameterService parameterService;
	
	// Parameter upload.folder di application.properties
	@Value("${upload.folder}")
	private String uploadFolder;
	
	
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/index/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("nonasabah", nonasabah);
		List<NasabahDokumen> listDokumen = nasabahDokumenService.listByNonasabah(nonasabah);
		modelAndView.addObject("listDokumen", listDokumen);
		String tipenasabah = nasabahBasicService.findByNonasabah(nonasabah).getTipenasabah();
		modelAndView.addObject("tipenasabah", tipenasabah);
		modelAndView.setViewName("nasabah/dokumenindex");
		return modelAndView;
	}
	
	@RequestMapping(value="/create/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView create(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("nonasabah", nonasabah);
		NasabahDokumen nasabahDokumen = new NasabahDokumen();
		modelAndView.addObject("nasabahDokumen", nasabahDokumen);
		modelAndView.addObject("kodedokumens", parameterService.listAllKodeDokumen());
		String tipenasabah = nasabahBasicService.findByNonasabah(nonasabah).getTipenasabah();
		modelAndView.addObject("tipenasabah", tipenasabah);
		modelAndView.setViewName("nasabah/dokumencreate");
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{nonasabah}/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long nonasabah, @PathVariable int id) {
		NasabahDokumen nasabahDokumen = nasabahDokumenService.findById(id);
		String filename = nasabahDokumen.getFilename();
		Path path = Paths.get(uploadFolder + filename);
		
		// File Delete
		try {
			Files.deleteIfExists(path);
		} catch(Exception e) {
			
		}
		
		// Delete From Database
		nasabahDokumenService.delete(id);
		
		// Return View
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/dokumen/index/" + nonasabah);
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/{nonasabah}", method = RequestMethod.POST)
	public ModelAndView insert(@PathVariable Long nonasabah, NasabahDokumen nasabahDokumen, @RequestParam MultipartFile uploadFile) {
		
		// File Upload
		try {
			String filetosave = nasabahDokumenService.fileUpload(uploadFile);
			nasabahDokumen.setFilename(filetosave);
		} catch (Exception e) {
			
		}
		
		// Save to Database
		nasabahDokumen.setNonasabah(nonasabah);
		nasabahDokumenService.save(nasabahDokumen);
		
		// Return View
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/dokumen/index/" + nonasabah);
		return modelAndView;
	}
	
	@RequestMapping(value="/download/{fileName:.+}", method = RequestMethod.GET)
	public ResponseEntity download(@PathVariable String fileName) {
		Path path = Paths.get(uploadFolder + fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch(Exception e) {
			
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@RequestMapping(value="/inquiry/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView inquiry(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		modelAndView.addObject("nonasabah", nonasabah);
		List<NasabahDokumen> listDokumen = nasabahDokumenService.listByNonasabah(nonasabah);
		modelAndView.addObject("listDokumen", listDokumen);
		String tipenasabah = nasabahBasicService.findByNonasabah(nonasabah).getTipenasabah();
		modelAndView.addObject("tipenasabah", tipenasabah);
		modelAndView.setViewName("nasabah/dokumeninquiry");
		return modelAndView;
	}

}
