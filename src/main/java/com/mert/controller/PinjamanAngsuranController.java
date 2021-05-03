package com.mert.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.Finance;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	
	@RequestMapping(value="/indexexcel/{nofasilitas}", method = RequestMethod.GET)
	public void IndexExcel(HttpServletResponse response, @PathVariable String nofasilitas) throws IOException {
		
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
        
        // CREATE EXCEL
        response.setContentType("application/octet-stream");
		String heardeKey = "Content-Disposition";
		String headerValue = "attachment; filename=excelbook1.xlsx";
		response.setHeader(heardeKey, headerValue);
		
		XSSFWorkbook xSSFWorkbook = new XSSFWorkbook();
		XSSFSheet xSSFSheet = xSSFWorkbook.createSheet("Sheet1");
		
		// Header Row
		Row row = xSSFSheet.createRow(0);
		
		List<String> listHeaderColumn = Arrays.asList("Bulan Ke", "Angsuran Pokok", "Angsuran Bunga", "Total Angsuran", "Sisa Pinjaman");
		
		Integer column = 0;
		for (String headerColumn : listHeaderColumn) {
			Cell cell = row.createCell(column);
			cell.setCellValue(headerColumn);
			column++;
		}
		
		// Data Rows
		Integer dataRow = 1;
		
		for (DtInstallment tInstallment : listDtInstallment) {
			
			row = xSSFSheet.createRow(dataRow);
			
			Cell cell = row.createCell(0); // Bulan Ke
			if (tInstallment.BulanKe != null) {
				cell.setCellValue(tInstallment.BulanKe);
			}
			
			cell = row.createCell(1); // Angsuran Pokok
			if (tInstallment.AngsuranPokok != null) {
				cell.setCellValue(tInstallment.AngsuranPokok);
			}
			
			cell = row.createCell(2); // Angsuran Bunga
			if (tInstallment.AngsuranBunga != null) {
				cell.setCellValue(tInstallment.AngsuranBunga);
			}
			
			cell = row.createCell(3); // Total Angsuran
			if (tInstallment.TotalAngsuran != null) {
				cell.setCellValue(tInstallment.TotalAngsuran);
			}
			
			cell = row.createCell(4); // Sisa Pinjaman
			if (tInstallment.SisaPinjaman != null) {
				cell.setCellValue(tInstallment.SisaPinjaman);
			}
			
			dataRow++;
			
		}
		
		// Summary Row
		row = xSSFSheet.createRow(dataRow);
		
		Cell cell = row.createCell(0); // Bulan Ke
		cell.setCellValue("JUMLAH");
		
		cell = row.createCell(1); // Jumlah Angsuran Pokok
		if (jumlangspokok != null) {
			cell.setCellValue(jumlangspokok);
		}
		
		cell = row.createCell(2); // Jumlah Angsuran Bunga
		if (jumlangsbunga != null) {
			cell.setCellValue(jumlangsbunga);
		}
		
		cell = row.createCell(3); // Jumlah Total Angsuran
		if (jumltotangsuran != null) {
			cell.setCellValue(jumltotangsuran);
		}
		
		ServletOutputStream servletOutputStream = response.getOutputStream();
		xSSFWorkbook.write(servletOutputStream);
		xSSFWorkbook.close();
		servletOutputStream.close();
	}
	
}
