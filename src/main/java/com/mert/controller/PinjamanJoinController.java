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
import com.mert.model.NasabahBasic;
import com.mert.service.NasabahBasicService;
import com.mert.model.FasilitasJoin;
import com.mert.model.FasilitasKredit;
import com.mert.service.FasilitasJoinService;

@Controller
@RequestMapping("/pinjaman/join")
public class PinjamanJoinController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private NasabahBasicService nasabahBasicService;
	
	@Autowired
	private FasilitasJoinService fasilitasJoinService;
	
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
		modelAndView.addObject("listJoin", fasilitasJoinService.findByNofasilitas(nofasilitas));
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("mode", "MODE_INDEX");
		modelAndView.setViewName("pinjaman/joinrekening");
		return modelAndView;
	}
	
	@RequestMapping(value="/create/{nofasilitas}", method = RequestMethod.GET)
	public ModelAndView Create(@PathVariable String nofasilitas) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		FasilitasJoin fasilitasJoin = new FasilitasJoin();
		modelAndView.addObject("fasilitasJoin", fasilitasJoin);
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.addObject("mode", "MODE_CREATE");
		modelAndView.setViewName("pinjaman/joinrekening");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/{nofasilitas}", method = RequestMethod.POST)
	public ModelAndView Insert(@PathVariable String nofasilitas, @Valid FasilitasJoin fasilitasJoin, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("auth", getUser());
			modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
			modelAndView.addObject("fasilitasJoin", fasilitasJoin);
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.addObject("mode", "MODE_CREATE");
			modelAndView.setViewName("pinjaman/joinrekening");
			return modelAndView;
		}
		
		// Check if Joined Nasabah is Valid
		NasabahBasic nasabah = nasabahBasicService.findByNonasabah(fasilitasJoin.getJoinNonasabah());
		if (nasabah == null) {
			// Back to Form
			modelAndView.addObject("auth", getUser());
			modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
			modelAndView.addObject("fasilitasJoin", fasilitasJoin);
			modelAndView.addObject("nofasilitas", nofasilitas);
			modelAndView.addObject("mode", "MODE_CREATE");
			modelAndView.addObject("customError", "No Nasabah tidak valid!");
			modelAndView.setViewName("pinjaman/joinrekening");
			return modelAndView;
		}
		
		// Save FasilitasJoin
		fasilitasJoin.setJoinNamalengkap(nasabah.getNamalengkap());
		fasilitasJoinService.save(fasilitasJoin);
		
		// Generate View
		modelAndView.setViewName("redirect:/pinjaman/join/index/" + nofasilitas);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{nofasilitas}/{joinid}", method = RequestMethod.POST)
	public ModelAndView Delete(@PathVariable String nofasilitas, @PathVariable Integer joinid) {
		ModelAndView modelAndView = new ModelAndView();
		fasilitasJoinService.delete(joinid);
		modelAndView.setViewName("redirect:/pinjaman/join/index/" + nofasilitas);
		return modelAndView;
	}

}
