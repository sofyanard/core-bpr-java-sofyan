package com.mert.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.service.AppUserService;
import com.mert.model.AppUnit;
import com.mert.service.AppUnitService;
import com.mert.model.BukuBesar;
import com.mert.model.RekeningBukuBesar;
import com.mert.model.ParameterProduk;
import com.mert.model.RekeningKredit;
import com.mert.model.JenisLapKeu;
import com.mert.model.PosisiLapKeu;
import com.mert.model.PosisiLapKeuUnit;
import com.mert.model.PosLapKeu;
import com.mert.model.PosLapKeuUnit;
import com.mert.model.SubPosLapKeu;
import com.mert.model.SubPosLapKeuUnit;
import com.mert.model.SubSubPosLapKeu;
import com.mert.model.SubSubPosLapKeuUnit;
import com.mert.model.LaporanKeuanganView;
import com.mert.model.LapKeuDaily;
import com.mert.model.LapKeuMonthly;
import com.mert.service.BukuBesarService;
import com.mert.service.RekeningBukuBesarService;
import com.mert.service.ParameterProdukService;
import com.mert.service.RekeningKreditService;
import com.mert.service.JenisLapKeuService;
import com.mert.service.PosisiLapKeuService;
import com.mert.service.PosisiLapKeuUnitService;
import com.mert.service.PosLapKeuService;
import com.mert.service.PosLapKeuUnitService;
import com.mert.service.SubPosLapKeuService;
import com.mert.service.SubPosLapKeuUnitService;
import com.mert.service.SubSubPosLapKeuService;
import com.mert.service.SubSubPosLapKeuUnitService;
import com.mert.service.LapKeuDailyService;
import com.mert.service.LapKeuMonthlyService;

@Controller
public class AkuntingPemantauanBukuBesarController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private AppUnitService appUnitService;
	
	@Autowired
	private BukuBesarService bukuBesarService;
	
	@Autowired
	private RekeningBukuBesarService rekeningBukuBesarService;
	
	@Autowired
	private ParameterProdukService parameterProdukService;
	
	@Autowired
	private RekeningKreditService rekeningKreditService;
	
	@Autowired
	private JenisLapKeuService jenisLapKeuService;
	
	@Autowired
	private PosisiLapKeuService posisiLapKeuService;
	
	@Autowired
	private PosisiLapKeuUnitService posisiLapKeuUnitService;
	
	@Autowired
	private PosLapKeuService posLapKeuService;
	
	@Autowired
	private PosLapKeuUnitService posLapKeuUnitService;
	
	@Autowired
	private SubPosLapKeuService subPosLapKeuService;
	
	@Autowired
	private SubPosLapKeuUnitService subPosLapKeuUnitService;
	
	@Autowired
	private SubSubPosLapKeuService subSubPosLapKeuService;
	
	@Autowired
	private SubSubPosLapKeuUnitService subSubPosLapKeuUnitService;
	
	@Autowired
	private LapKeuDailyService lapKeuDailyService;
	
	@Autowired
	private LapKeuMonthlyService lapKeuMonthlyService;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/akunting/pemantauanbukubesar", method = RequestMethod.GET)
	public ModelAndView PemantauanBukuBesar(String bbid) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		List<BukuBesar> listBukuBesar = bukuBesarService.findAll();
		modelAndView.addObject("listBukuBesar", listBukuBesar);
		
		if ((bbid != null) && (!bbid.isEmpty())) {
			
			BukuBesar bukuBesar = bukuBesarService.findOne(bbid);
			modelAndView.addObject("bukuBesar", bukuBesar);
			
			RekeningBukuBesar rekeningBukuBesar = rekeningBukuBesarService.findOne(bbid);
			modelAndView.addObject("rekeningBukuBesar", rekeningBukuBesar);
			
			ParameterProduk parameterProduk = parameterProdukService.findByBukuBesar(bbid);
			
			if (parameterProduk != null) {
				
				// Produk Pinjaman
				if (parameterProduk.getType().getProductTypeId().equals("1")) {
					
					// Cari semua rekening pinjaman berdasarkan produk
					List<RekeningKredit> listRekeningKredit = rekeningKreditService.findByProduk(parameterProduk.getCode());
					modelAndView.addObject("listRekeningKredit", listRekeningKredit);
					
					System.out.println("listRekeningKredit");
					System.out.println(listRekeningKredit.toString());
					
				}
				
				// Produk Tabungan
				else if (parameterProduk.getType().getProductTypeId().equals("2")) {
					
					// Cari semua rekening tabungan berdasarkan produk
					// .....
					
				}
				
			}
			
		}
		
		modelAndView.addObject("bbid", bbid);
		modelAndView.setViewName("akunting/pemantauanbukubesar");
		return modelAndView;
	}
	
	@RequestMapping(value="/akunting/laporankeuangan", method = RequestMethod.GET)
	public ModelAndView LaporanKeuangan(String unit, String jenis) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		List<JenisLapKeu> listJenisLapKeu = jenisLapKeuService.findAll();
		modelAndView.addObject("listJenisLapKeu", listJenisLapKeu);
		
		List<AppUnit> listAppUnit = appUnitService.findAll();
		modelAndView.addObject("listAppUnit", listAppUnit);
		
		if ((unit != null) && (!unit.isEmpty()) && (jenis != null) && (!jenis.isEmpty())) {
			
			List<LaporanKeuanganView> listLaporanKeuanganView = new ArrayList<LaporanKeuanganView>();
			
			// POSISI
			List<PosisiLapKeu> listPosisiLapKeu = posisiLapKeuService.findByJenis(jenis);
			
			for (PosisiLapKeu posisiLapKeu : listPosisiLapKeu) {
				
				LaporanKeuanganView laporanKeuanganView = new LaporanKeuanganView();
				laporanKeuanganView.setLapKeuId(posisiLapKeu.getPosisiId());
				laporanKeuanganView.setLapKeuName(posisiLapKeu.getDeskripsi());
				
				PosisiLapKeuUnit posisiLapKeuUnit = posisiLapKeuUnitService.findByPosisiAndUnit(posisiLapKeu.getPosisiId(), unit);
				
				if (posisiLapKeuUnit != null) {
					
					laporanKeuanganView.setBukuBesar(posisiLapKeuUnit.getRekeningBukuBesar().getNoRekening());
					
					RekeningBukuBesar rekeningBukuBesar = rekeningBukuBesarService.findOne(posisiLapKeuUnit.getRekeningBukuBesar().getNoRekening());
					
					if (rekeningBukuBesar != null) {
						
						laporanKeuanganView.setSaldo(rekeningBukuBesar.getSaldo());
						
					}
					
				}
				
				listLaporanKeuanganView.add(laporanKeuanganView);
				
				// POS
				List<PosLapKeu> listPosLapKeu = posLapKeuService.findByPosisi(posisiLapKeu.getPosisiId());
				
				for (PosLapKeu posLapKeu : listPosLapKeu) {
					
					laporanKeuanganView = new LaporanKeuanganView();
					laporanKeuanganView.setLapKeuId(posLapKeu.getPosId());
					laporanKeuanganView.setLapKeuName("   --->   " + posLapKeu.getDeskripsi());
					
					PosLapKeuUnit posLapKeuUnit = posLapKeuUnitService.findByPosAndUnit(posLapKeu.getPosId(), unit);
					
					if (posLapKeuUnit != null) {
						
						laporanKeuanganView.setBukuBesar(posLapKeuUnit.getRekeningBukuBesar().getNoRekening());
						
						RekeningBukuBesar rekeningBukuBesar = rekeningBukuBesarService.findOne(posLapKeuUnit.getRekeningBukuBesar().getNoRekening());
						
						if (rekeningBukuBesar != null) {
							
							laporanKeuanganView.setSaldo(rekeningBukuBesar.getSaldo());
							
						}
						
					}
					
					listLaporanKeuanganView.add(laporanKeuanganView);
					
					// SUB POS
					List<SubPosLapKeu> listSubPosLapKeu = subPosLapKeuService.findByPos(posLapKeu.getPosId());
					
					for (SubPosLapKeu subPosLapKeu : listSubPosLapKeu) {
						
						laporanKeuanganView = new LaporanKeuanganView();
						laporanKeuanganView.setLapKeuId(subPosLapKeu.getSubPosId());
						laporanKeuanganView.setLapKeuName("   --->      --->   " + subPosLapKeu.getDeskripsi());
						
						SubPosLapKeuUnit subPosLapKeuUnit = subPosLapKeuUnitService.findBySubPosAndUnit(subPosLapKeu.getSubPosId(), unit);
						
						if (subPosLapKeuUnit != null) {
							
							laporanKeuanganView.setBukuBesar(subPosLapKeuUnit.getRekeningBukuBesar().getNoRekening());
							
							RekeningBukuBesar rekeningBukuBesar = rekeningBukuBesarService.findOne(subPosLapKeuUnit.getRekeningBukuBesar().getNoRekening());
							
							if (rekeningBukuBesar != null) {
								
								laporanKeuanganView.setSaldo(rekeningBukuBesar.getSaldo());
								
							}
							
						}
						
						listLaporanKeuanganView.add(laporanKeuanganView);
						
						// SUB SUB POS
						List<SubSubPosLapKeu> listSubSubPosLapKeu = subSubPosLapKeuService.findBySubPos(subPosLapKeu.getSubPosId());
						
						for (SubSubPosLapKeu subSubPosLapKeu : listSubSubPosLapKeu) {
							
							laporanKeuanganView = new LaporanKeuanganView();
							laporanKeuanganView.setLapKeuId(subSubPosLapKeu.getSubSubPosId());
							laporanKeuanganView.setLapKeuName("   --->      --->      --->   " + subSubPosLapKeu.getDeskripsi());
							
							SubSubPosLapKeuUnit subSubPosLapKeuUnit = subSubPosLapKeuUnitService.findBySubSubPosAndUnit(subSubPosLapKeu.getSubSubPosId(), unit);
							
							if (subSubPosLapKeuUnit != null) {
								
								laporanKeuanganView.setBukuBesar(subSubPosLapKeuUnit.getRekeningBukuBesar().getNoRekening());
								
								RekeningBukuBesar rekeningBukuBesar = rekeningBukuBesarService.findOne(subSubPosLapKeuUnit.getRekeningBukuBesar().getNoRekening());
								
								if (rekeningBukuBesar != null) {
									
									laporanKeuanganView.setSaldo(rekeningBukuBesar.getSaldo());
									
								}
								
							}
							
							listLaporanKeuanganView.add(laporanKeuanganView);
							
						}
						
					}
					
				}
				
			}
			
			modelAndView.addObject("listLaporanKeuanganView", listLaporanKeuanganView);
			
		}
		
		modelAndView.addObject("unit", unit);
		modelAndView.addObject("jenis", jenis);
		modelAndView.setViewName("akunting/laporankeuangan");
		return modelAndView;
	}
	
	@RequestMapping(value="/akunting/laporankeuanganharian", method = RequestMethod.GET)
	public ModelAndView LaporanKeuanganHarian(String unit, String jenis) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		List<JenisLapKeu> listJenisLapKeu = jenisLapKeuService.findAll();
		modelAndView.addObject("listJenisLapKeu", listJenisLapKeu);
		
		List<AppUnit> listAppUnit = appUnitService.findAll();
		modelAndView.addObject("listAppUnit", listAppUnit);
		
		if ((unit != null) && (!unit.isEmpty()) && (jenis != null) && (!jenis.isEmpty())) {
			
			List<LapKeuDaily> listLapKeuDaily = lapKeuDailyService.findByUnitAndJenis(unit, jenis);
			modelAndView.addObject("listLapKeuDaily", listLapKeuDaily);
			
		}
		
		modelAndView.addObject("unit", unit);
		modelAndView.addObject("jenis", jenis);
		modelAndView.setViewName("akunting/laporankeuanganharian");
		return modelAndView;
	}
	
	@RequestMapping(value="/akunting/laporankeuanganbulanan", method = RequestMethod.GET)
	public ModelAndView LaporanKeuanganBulanan(String unit, String jenis, String tanggal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		List<JenisLapKeu> listJenisLapKeu = jenisLapKeuService.findAll();
		modelAndView.addObject("listJenisLapKeu", listJenisLapKeu);
		
		List<AppUnit> listAppUnit = appUnitService.findAll();
		modelAndView.addObject("listAppUnit", listAppUnit);
		
		List<String> listTanggal = lapKeuMonthlyService.listTanggal();
		modelAndView.addObject("listTanggal", listTanggal);
		
		if ((unit != null) && (!unit.isEmpty()) && (jenis != null) && (!jenis.isEmpty()) && (tanggal != null) && (!tanggal.isEmpty())) {
			
			List<LapKeuMonthly> listLapKeuMonthly = lapKeuMonthlyService.findByTanggalAndUnitAndJenis(tanggal, unit, jenis);
			modelAndView.addObject("listLapKeuMonthly", listLapKeuMonthly);
			
		}
		
		modelAndView.addObject("unit", unit);
		modelAndView.addObject("jenis", jenis);
		modelAndView.addObject("tanggal", tanggal);
		modelAndView.setViewName("akunting/laporankeuanganbulanan");
		return modelAndView;
	}

}
