package com.mert.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.Finance;
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
import com.mert.model.FasilitasKredit;
import com.mert.service.FasilitasKreditService;
import com.mert.model.DtInstallment;

@Controller
@RequestMapping("/pinjaman/angsuran")
public class PinjamanAngsuranController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private FasilitasKreditService fasilitasKreditService;
	
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
		
		FasilitasKredit fasilitasKredit = fasilitasKreditService.findOne(nofasilitas);
		Double limit = fasilitasKredit.getEqvPlafond();
		Double rate = fasilitasKredit.getBungaPersen() / 100.0;
		String interesttype = fasilitasKredit.getHitungBunga().getCode();
		Integer tenor = fasilitasKredit.getTenor();
		
		List<DtInstallment> listDtInstallment = new ArrayList<DtInstallment>();
		DtInstallment dtInstallment;
		
		Double jumlangspokok = 0.0;
		Double jumlangsbunga = 0.0;
		Double jumltotangsuran = 0.0;
		
		Double iapokok;
		Double iabunga;
		Double iatotal;
		Double ipsisa;
		
		iapokok = 0.0;
        iabunga = 0.0;
        iatotal = iapokok + iabunga;
        ipsisa = limit;
        
        jumlangspokok = jumlangspokok + iapokok;
        jumlangsbunga = jumlangsbunga + iabunga;
        jumltotangsuran = jumltotangsuran + iatotal;
        
        dtInstallment = new DtInstallment();
        
        dtInstallment.BulanKe = 0;
        dtInstallment.AngsuranPokok = iapokok;
        dtInstallment.AngsuranBunga = iabunga;
        dtInstallment.TotalAngsuran = iatotal;
        dtInstallment.SisaPinjaman = ipsisa;
        listDtInstallment.add(dtInstallment);
        
        for (int i = 1; i <= tenor; i++) {
        	
        	//Flat
        	if (interesttype.equals("F")) {
        		iapokok = limit / tenor;
        		if (iapokok > ipsisa)
                    iapokok = ipsisa;
        		iabunga = limit * (rate / 12);
        		iatotal = iapokok + iabunga;
        		ipsisa = ipsisa - iapokok;
        		if (ipsisa < 0)
                    ipsisa = 0.0;
        	}
        	
        	//Anuitas
        	else if (interesttype.equals("A")) {
        		iapokok = Finance.ppmt(rate / 12, i, tenor, -1*limit);
                if (iapokok > ipsisa)
                    iapokok = ipsisa;
                iabunga = Finance.ipmt(rate / 12, i, tenor, -1*limit);
                iatotal = iapokok + iabunga;
                ipsisa = ipsisa - iapokok;
                if (ipsisa < 0)
                    ipsisa = 0.0;
        	}
        	
        	else {
        		// Error Invalid Interest Type
        		modelAndView.addObject("errorMsg", "Invalid Interest Type");
        	}
        	
        	jumlangspokok = jumlangspokok + iapokok;
            jumlangsbunga = jumlangsbunga + iabunga;
            jumltotangsuran = jumltotangsuran + iatotal;
            
            dtInstallment = new DtInstallment();
            
            dtInstallment.BulanKe = i;
            dtInstallment.AngsuranPokok = iapokok;
            dtInstallment.AngsuranBunga = iabunga;
            dtInstallment.TotalAngsuran = iatotal;
            dtInstallment.SisaPinjaman = ipsisa;
            listDtInstallment.add(dtInstallment);
        	
        }
        
        modelAndView.addObject("listDtInstallment", listDtInstallment);
        modelAndView.addObject("jumlangspokok", jumlangspokok);
        modelAndView.addObject("jumlangsbunga", jumlangsbunga);
        modelAndView.addObject("jumltotangsuran", jumltotangsuran);
		modelAndView.addObject("nofasilitas", nofasilitas);
		modelAndView.setViewName("pinjaman/angsuranindex");
		return modelAndView;
	}
	
}
