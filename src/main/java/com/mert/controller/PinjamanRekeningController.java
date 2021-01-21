package com.mert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.service.AppUserService;
import com.mert.model.RekeningKredit;
import com.mert.service.RekeningKreditService;

@Controller
@RequestMapping("/pinjaman/rekening")
public class PinjamanRekeningController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private RekeningKreditService rekeningKreditService;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/searchedit", method = RequestMethod.GET)
	public ModelAndView SearchEdit(String norek, Long nonasabah, String nama) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		List<RekeningKredit> listRekeningKredit = rekeningKreditService.searchByProp(norek, nonasabah, nama);
		modelAndView.addObject("listRekeningKredit", listRekeningKredit);
		
		modelAndView.addObject("norek", norek);
		modelAndView.addObject("nonasabah", nonasabah);
		modelAndView.addObject("nama", nama);
		
		modelAndView.setViewName("/pinjaman/rekeningsearchedit");
		return modelAndView;
				
	}

}
