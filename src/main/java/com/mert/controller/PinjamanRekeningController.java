package com.mert.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.ss.formula.functions.Finance;
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
import com.mert.model.DtInstallment;
import com.mert.model.RekeningKreditFasilitas;
import com.mert.model.RekeningKreditPelaporan;
import com.mert.model.RekeningKreditRestru;
import com.mert.model.SkalaAngsuran;
import com.mert.model.DataTagihan;
import com.mert.model.DataTagihanEdit;
import com.mert.model.FasilitasKredit;
import com.mert.service.AppUserService;
import com.mert.service.ParameterPinaltiService;
import com.mert.model.RekeningKredit;
import com.mert.service.RekeningKreditService;
import com.mert.service.SkalaAngsuranService;
import com.mert.service.DataTagihanService;
import com.mert.service.FasilitasKreditService;
import com.mert.service.ParameterService;
import com.mert.service.StatusRekgService;
import com.mert.service.ParameterKolektibilitasService;
import com.mert.service.ParameterYesNoService;

@Controller
@RequestMapping("/pinjaman/rekening")
public class PinjamanRekeningController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private RekeningKreditService rekeningKreditService;
	
	@Autowired
	private ParameterPinaltiService parameterPinaltiService;
	
	@Autowired
	private SkalaAngsuranService skalaAngsuranService;
	
	@Autowired
	private FasilitasKreditService fasilitasKreditService;
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private StatusRekgService statusRekgService;
	
	@Autowired
	private ParameterKolektibilitasService parameterKolektibilitasService;
	
	@Autowired
	private ParameterYesNoService parameterYesNoService;
	
	@Autowired
	private DataTagihanService dataTagihanService;
	
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
	
	@RequestMapping(value="/editfasilitas/{noRekening}", method = RequestMethod.GET)
	public ModelAndView EditFasilitas(@PathVariable String noRekening) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
		modelAndView.addObject("rekeningKredit", rekeningKredit);
		
		RekeningKreditFasilitas rekeningKreditFasilitas = new RekeningKreditFasilitas();
		rekeningKreditFasilitas.setNoRekening(noRekening);
		rekeningKreditFasilitas.setTenor(rekeningKredit.getTenor());
		rekeningKreditFasilitas.setBungaPersen(rekeningKredit.getBungaPersen());
		rekeningKreditFasilitas.setPinaltiBungaPersen(rekeningKredit.getPinaltiBungaPersen());
		rekeningKreditFasilitas.setPinaltiPokokPersen(rekeningKredit.getPinaltiPokokPersen());
		rekeningKreditFasilitas.setPinaltiLunasPersen(rekeningKredit.getPinaltiLunasPersen());
		rekeningKreditFasilitas.setPinaltiFlag(rekeningKredit.getPinaltiFlag());
		modelAndView.addObject("rekeningKreditFasilitas", rekeningKreditFasilitas);
		
		modelAndView.addObject("listPinalti", parameterPinaltiService.findAll());
		
		modelAndView.setViewName("pinjaman/rekeningeditfasilitas");
		return modelAndView;
	}
	
	@RequestMapping(value="/updatefasilitas/{noRekening}", method = RequestMethod.POST)
	public ModelAndView UpdateFasilitas(@PathVariable String noRekening, @Valid RekeningKreditFasilitas rekeningKreditFasilitas, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
			modelAndView.addObject("rekeningKredit", rekeningKredit);
			
			modelAndView.addObject("listPinalti", parameterPinaltiService.findAll());
			
			modelAndView.setViewName("pinjaman/rekeningeditfasilitas");
			return modelAndView;
		}
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
		
		boolean reCalculate = false;
		
		if (rekeningKreditFasilitas.getTenor() != rekeningKredit.getTenor()) {
			rekeningKredit.setTenor(rekeningKreditFasilitas.getTenor());
			reCalculate = true;
		}
		
		if (rekeningKreditFasilitas.getBungaPersen() != rekeningKredit.getBungaPersen()) {
			rekeningKredit.setBungaPersen(rekeningKreditFasilitas.getBungaPersen());
			reCalculate = true;
		}
		
		if (rekeningKreditFasilitas.getPinaltiBungaPersen() != rekeningKredit.getPinaltiBungaPersen()) {
			rekeningKredit.setPinaltiBungaPersen(rekeningKreditFasilitas.getPinaltiBungaPersen());
		}
		
		if (rekeningKreditFasilitas.getPinaltiPokokPersen() != rekeningKredit.getPinaltiPokokPersen()) {
			rekeningKredit.setPinaltiPokokPersen(rekeningKreditFasilitas.getPinaltiPokokPersen());
		}
		
		if (rekeningKreditFasilitas.getPinaltiLunasPersen() != rekeningKredit.getPinaltiLunasPersen()) {
			rekeningKredit.setPinaltiLunasPersen(rekeningKreditFasilitas.getPinaltiLunasPersen());
		}
		
		if (rekeningKreditFasilitas.getPinaltiFlag() != rekeningKredit.getPinaltiFlag()) {
			rekeningKredit.setPinaltiFlag(rekeningKreditFasilitas.getPinaltiFlag());
		}
		
		rekeningKreditService.save(rekeningKredit);
		
		// Kalau ada perubahan Bunga atau Tenor, hitung ulang Skala Angsuran
		if (reCalculate) {
			try {
				this.ReCreateSkalaAngsuran(rekeningKredit);
			}
			catch (Exception e) {
				System.out.println("ERROR RECALCULATE : " + e.getMessage());
			}
		}
		
		modelAndView.setViewName("redirect:/pinjaman/rekening/editfasilitas/" + noRekening);
		return modelAndView;
	}
	
	@RequestMapping(value="/editpelaporan/{noRekening}", method = RequestMethod.GET)
	public ModelAndView EditPelaporan(@PathVariable String noRekening) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
		modelAndView.addObject("rekeningKredit", rekeningKredit);
		
		modelAndView.addObject("listSifatKredit", parameterService.listSandiBIOJKSifatKredit());
		modelAndView.addObject("listPurpose", parameterService.listSandiBIOJKPurpose());
		modelAndView.addObject("listOrientasi", parameterService.listSandiBIOJKOrientasi());
		modelAndView.addObject("listJenisKredit", parameterService.listSandiBIOJKJenisKredit());
		modelAndView.addObject("listJenisFasilitas", parameterService.listSandiBIOJKJenisFasilitas());
		modelAndView.addObject("listLokasi", parameterService.listSandiBIOJKLokasi());
		modelAndView.addObject("listGolJamin", parameterService.listSandiBIOJKGolJamin());
		modelAndView.addObject("listSektor", parameterService.listSandiBIOJKSektor());
		modelAndView.addObject("listPKAkad", parameterService.listSandiBIOJKPKAkad());
		
		RekeningKreditPelaporan rekeningKreditPelaporan = new RekeningKreditPelaporan();
		rekeningKreditPelaporan.setNoRekening(rekeningKredit.getNoRekening());
		rekeningKreditPelaporan.setSifatKredit(rekeningKredit.getSifatKredit());
		rekeningKreditPelaporan.setJenisPenggunaan(rekeningKredit.getJenisPenggunaan());
		rekeningKreditPelaporan.setOrientasi(rekeningKredit.getOrientasi());
		rekeningKreditPelaporan.setJenisKredit(rekeningKredit.getJenisKredit());
		rekeningKreditPelaporan.setKodeFasKhusus(rekeningKredit.getKodeFasKhusus());
		rekeningKreditPelaporan.setNote(rekeningKredit.getNote());
		rekeningKreditPelaporan.setLokasiProyek(rekeningKredit.getLokasiProyek());
		rekeningKreditPelaporan.setNilaiProyek(rekeningKredit.getNilaiProyek());
		rekeningKreditPelaporan.setGolonganPenjamin(rekeningKredit.getGolonganPenjamin());
		rekeningKreditPelaporan.setKodeSektor(rekeningKredit.getKodeSektor());
		rekeningKreditPelaporan.setKodePk(rekeningKredit.getKodePk());
		rekeningKreditPelaporan.setTanggalPkPertama(rekeningKredit.getTanggalPkPertama());
		rekeningKreditPelaporan.setNoPkPertama(rekeningKredit.getNoPkPertama());
		rekeningKreditPelaporan.setTanggalPkAkhir(rekeningKredit.getTanggalPkAkhir());
		rekeningKreditPelaporan.setNoPkAkhir(rekeningKredit.getNoPkAkhir());
		rekeningKreditPelaporan.setTanggalAwalKredit(rekeningKredit.getTanggalAwalKredit());
		rekeningKreditPelaporan.setTanggalMulai(rekeningKredit.getTanggalMulai());
		modelAndView.addObject("rekeningKreditPelaporan", rekeningKreditPelaporan);
		
		modelAndView.setViewName("pinjaman/rekeningeditpelaporan");
		return modelAndView;
	}
	
	@RequestMapping(value="/updatepelaporan/{noRekening}", method = RequestMethod.POST)
	public ModelAndView UpdatePelaporan(@PathVariable String noRekening, @Valid RekeningKreditPelaporan rekeningKreditPelaporan, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("listSifatKredit", parameterService.listSandiBIOJKSifatKredit());
			modelAndView.addObject("listPurpose", parameterService.listSandiBIOJKPurpose());
			modelAndView.addObject("listOrientasi", parameterService.listSandiBIOJKOrientasi());
			modelAndView.addObject("listJenisKredit", parameterService.listSandiBIOJKJenisKredit());
			modelAndView.addObject("listJenisFasilitas", parameterService.listSandiBIOJKJenisFasilitas());
			modelAndView.addObject("listLokasi", parameterService.listSandiBIOJKLokasi());
			modelAndView.addObject("listGolJamin", parameterService.listSandiBIOJKGolJamin());
			modelAndView.addObject("listSektor", parameterService.listSandiBIOJKSektor());
			modelAndView.addObject("listPKAkad", parameterService.listSandiBIOJKPKAkad());
			
			modelAndView.setViewName("pinjaman/rekeningeditpelaporan");
			return modelAndView;
		}
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
		
		rekeningKredit.setSifatKredit(rekeningKreditPelaporan.getSifatKredit());
		rekeningKredit.setJenisPenggunaan(rekeningKreditPelaporan.getJenisPenggunaan());
		rekeningKredit.setOrientasi(rekeningKreditPelaporan.getOrientasi());
		rekeningKredit.setJenisKredit(rekeningKreditPelaporan.getJenisKredit());
		rekeningKredit.setKodeFasKhusus(rekeningKreditPelaporan.getKodeFasKhusus());
		rekeningKredit.setNote(rekeningKreditPelaporan.getNote());
		rekeningKredit.setLokasiProyek(rekeningKreditPelaporan.getLokasiProyek());
		rekeningKredit.setNilaiProyek(rekeningKreditPelaporan.getNilaiProyek());
		rekeningKredit.setGolonganPenjamin(rekeningKreditPelaporan.getGolonganPenjamin());
		rekeningKredit.setKodeSektor(rekeningKreditPelaporan.getKodeSektor());
		rekeningKredit.setKodePk(rekeningKreditPelaporan.getKodePk());
		rekeningKredit.setTanggalPkPertama(rekeningKreditPelaporan.getTanggalPkPertama());
		rekeningKredit.setNoPkPertama(rekeningKreditPelaporan.getNoPkPertama());
		rekeningKredit.setTanggalPkAkhir(rekeningKreditPelaporan.getTanggalPkAkhir());
		rekeningKredit.setNoPkAkhir(rekeningKreditPelaporan.getNoPkAkhir());
		rekeningKredit.setTanggalAwalKredit(rekeningKreditPelaporan.getTanggalAwalKredit());
		rekeningKredit.setTanggalMulai(rekeningKreditPelaporan.getTanggalMulai());
		
		rekeningKreditService.save(rekeningKredit);
		
		modelAndView.setViewName("redirect:/pinjaman/rekening/editpelaporan/" + noRekening);
		return modelAndView;
	}
	
	@RequestMapping(value="/editrestru/{noRekening}", method = RequestMethod.GET)
	public ModelAndView EditRestru(@PathVariable String noRekening) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
		modelAndView.addObject("rekeningKredit", rekeningKredit);
		
		modelAndView.addObject("listKolektibilitas", parameterKolektibilitasService.findAll());
		modelAndView.addObject("listYesNo", parameterYesNoService.findAll());
		modelAndView.addObject("listKetRestru", parameterService.listSandiBIOJKKetRestru());
		modelAndView.addObject("listKondisi", parameterService.listSandiBIOJKKondisi());
		modelAndView.addObject("listSebabMacet", parameterService.listSandiBIOJKSebabMacet());
		
		RekeningKreditRestru rekeningKreditRestru = new RekeningKreditRestru();
		rekeningKreditRestru.setNoRekening(rekeningKredit.getNoRekening());
		rekeningKreditRestru.setStatusKolektibilitas(rekeningKredit.getStatusKolektibilitas());
		rekeningKreditRestru.setRestruFlag(rekeningKredit.getRestruFlag());
		rekeningKreditRestru.setTanggalRestruAwal(rekeningKredit.getTanggalRestruAwal());
		rekeningKreditRestru.setTanggalRestruAkhir(rekeningKredit.getTanggalRestruAkhir());
		rekeningKreditRestru.setTanggalReview(rekeningKredit.getTanggalReview());
		rekeningKreditRestru.setRestruKe(rekeningKredit.getRestruKe());
		rekeningKreditRestru.setKetRestru(rekeningKredit.getKetRestru());
		rekeningKreditRestru.setKondisi(rekeningKredit.getKondisi());
		rekeningKreditRestru.setTanggalKondisi(rekeningKredit.getTanggalKondisi());
		rekeningKreditRestru.setSebabMacet(rekeningKredit.getSebabMacet());
		rekeningKreditRestru.setTanggalMacet(rekeningKredit.getTanggalMacet());
		modelAndView.addObject("rekeningKreditRestru", rekeningKreditRestru);
		
		modelAndView.setViewName("pinjaman/rekeningeditrestru");
		return modelAndView;
	}
	
	@RequestMapping(value="/updaterestru/{noRekening}", method = RequestMethod.POST)
	public ModelAndView UpdateRestru(@PathVariable String noRekening, @Valid RekeningKreditRestru rekeningKreditRestru, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			modelAndView.addObject("listKolektibilitas", parameterKolektibilitasService.findAll());
			modelAndView.addObject("listYesNo", parameterYesNoService.findAll());
			modelAndView.addObject("listKetRestru", parameterService.listSandiBIOJKKetRestru());
			modelAndView.addObject("listKondisi", parameterService.listSandiBIOJKKondisi());
			modelAndView.addObject("listSebabMacet", parameterService.listSandiBIOJKSebabMacet());
			
			modelAndView.setViewName("pinjaman/rekeningeditrestru");
			return modelAndView;
		}
		
		RekeningKredit rekeningKredit = rekeningKreditService.findOne(noRekening);
		
		rekeningKredit.setStatusKolektibilitas(rekeningKreditRestru.getStatusKolektibilitas());
		rekeningKredit.setRestruFlag(rekeningKreditRestru.getRestruFlag());
		rekeningKredit.setTanggalRestruAwal(rekeningKreditRestru.getTanggalRestruAwal());
		rekeningKredit.setTanggalRestruAkhir(rekeningKreditRestru.getTanggalRestruAkhir());
		rekeningKredit.setTanggalReview(rekeningKreditRestru.getTanggalReview());
		rekeningKredit.setRestruKe(rekeningKreditRestru.getRestruKe());
		rekeningKredit.setKetRestru(rekeningKreditRestru.getKetRestru());
		rekeningKredit.setKondisi(rekeningKreditRestru.getKondisi());
		rekeningKredit.setTanggalKondisi(rekeningKreditRestru.getTanggalKondisi());
		rekeningKredit.setSebabMacet(rekeningKreditRestru.getSebabMacet());
		rekeningKredit.setTanggalMacet(rekeningKreditRestru.getTanggalMacet());
		
		rekeningKreditService.save(rekeningKredit);
		
		modelAndView.setViewName("redirect:/pinjaman/rekening/editrestru/" + noRekening);
		return modelAndView;
	}
	
	@RequestMapping(value="/listangsuran/{noRekening}", method = RequestMethod.GET)
	public ModelAndView ListAngsuran(@PathVariable String noRekening) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		List<SkalaAngsuran> listSkalaAngsuran = skalaAngsuranService.findByNoRekening(noRekening);
		modelAndView.addObject("listSkalaAngsuran", listSkalaAngsuran);
		
		modelAndView.setViewName("pinjaman/rekeninglistangsuran");
		return modelAndView;
	}
	
	@RequestMapping(value="/tagihanindex/{noRekening}", method = RequestMethod.GET)
	public ModelAndView TagihanIndex(@PathVariable String noRekening) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		List<DataTagihan> listDataTagihan = dataTagihanService.findByNoRekening(noRekening);
		modelAndView.addObject("listDataTagihan", listDataTagihan);
		
		Double sumPokok = dataTagihanService.sumPokokByNoRekening(noRekening);
		Double sumBunga = dataTagihanService.sumBungaByNoRekening(noRekening);
		Double sumDendaPokok = dataTagihanService.sumDendaPokokByNoRekening(noRekening);
		Double sumDendaBunga = dataTagihanService.sumDendaBungaByNoRekening(noRekening);
		Double sumLainnya = dataTagihanService.sumLainnyaByNoRekening(noRekening);
		
		if (sumPokok == null)
			sumPokok = 0.0;
		if (sumBunga == null)
			sumBunga = 0.0;
		if (sumDendaPokok == null)
			sumDendaPokok = 0.0;
		if (sumDendaBunga == null)
			sumDendaBunga = 0.0;
		if (sumLainnya == null)
			sumLainnya = 0.0;
		
		Double totalKewajiban = sumPokok + sumBunga + sumDendaPokok + sumDendaBunga + sumLainnya;
		
		modelAndView.addObject("sumPokok", sumPokok);
		modelAndView.addObject("sumBunga", sumBunga);
		modelAndView.addObject("sumDendaPokok", sumDendaPokok);
		modelAndView.addObject("sumDendaBunga", sumDendaBunga);
		modelAndView.addObject("sumLainnya", sumLainnya);
		modelAndView.addObject("totalKewajiban", totalKewajiban);
		
		modelAndView.addObject("mode", "MODE_INDEX");
		modelAndView.setViewName("pinjaman/rekeningedittagihan");
		return modelAndView;
	}
	
	@RequestMapping(value="/tagihanedit/{noRekening}/{tagihanId}", method = RequestMethod.GET)
	public ModelAndView TagihanEdit(@PathVariable String noRekening, @PathVariable Integer tagihanId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		DataTagihan dataTagihan = dataTagihanService.findOne(tagihanId);
		modelAndView.addObject("dataTagihan", dataTagihan);
		
		DataTagihanEdit dataTagihanEdit = new DataTagihanEdit();
		dataTagihanEdit.setId(dataTagihan.getId());
		dataTagihanEdit.setNoRekening(dataTagihan.getRekeningKredit().getNoRekening());
		dataTagihanEdit.setDueDate(dataTagihan.getDueDate());
		dataTagihanEdit.setPokok(dataTagihan.getPokok());
		dataTagihanEdit.setBunga(dataTagihan.getBunga());
		dataTagihanEdit.setDendaPokok(dataTagihan.getDendaPokok());
		dataTagihanEdit.setDendaBunga(dataTagihan.getDendaBunga());
		dataTagihanEdit.setLainnya(dataTagihan.getLainnya());
		modelAndView.addObject("dataTagihanEdit", dataTagihanEdit);
		
		modelAndView.addObject("mode", "MODE_EDIT");
		modelAndView.setViewName("pinjaman/rekeningedittagihan");
		return modelAndView;
	}
	
	@RequestMapping(value="/tagihanupdate/{noRekening}/{tagihanId}", method = RequestMethod.POST)
	public ModelAndView TagihanUpdate(@PathVariable String noRekening, @PathVariable Integer tagihanId, @Valid DataTagihanEdit dataTagihanEdit, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		if (bindingResult.hasErrors()) {
			// Back to Form
			DataTagihan dataTagihan = dataTagihanService.findOne(tagihanId);
			modelAndView.addObject("dataTagihan", dataTagihan);
		}
		
		DataTagihan dataTagihan = dataTagihanService.findOne(tagihanId);
		boolean ischanged = false;
		
		if (dataTagihanEdit.getPokok() != dataTagihan.getPokok()) {
			dataTagihan.setPokok(dataTagihanEdit.getPokok());
			ischanged = true;
		}
		
		if (dataTagihanEdit.getBunga() != dataTagihan.getBunga()) {
			dataTagihan.setBunga(dataTagihanEdit.getBunga());
			ischanged = true;
		}
		
		if (dataTagihanEdit.getDendaPokok() != dataTagihan.getDendaPokok()) {
			dataTagihan.setDendaPokok(dataTagihanEdit.getDendaPokok());
			ischanged = true;
		}
		
		if (dataTagihanEdit.getDendaBunga() != dataTagihan.getDendaBunga()) {
			dataTagihan.setDendaBunga(dataTagihanEdit.getDendaBunga());
			ischanged = true;
		}
		
		if (dataTagihanEdit.getLainnya() != dataTagihan.getLainnya()) {
			dataTagihan.setLainnya(dataTagihanEdit.getLainnya());
			ischanged = true;
		}

		if (ischanged) {
			// dataTagihan.setPaidStatus("Hapus"); ?????
			dataTagihan.setHapusDate(new Date());
			dataTagihanService.save(dataTagihan);
			
			// Update Total to RekeningKredit
			rekeningKreditService.updateTotalKewajiban(noRekening);
		}
		
		modelAndView.setViewName("redirect:/pinjaman/rekening/tagihanindex/" + noRekening);
		return modelAndView;
	}
	
	
	
	// from ~/controller/PinjamanFasilitasController
	// delete all first
	// change start date --> fasilitas kredit activation date
	private void ReCreateSkalaAngsuran(RekeningKredit rekeningKredit) throws Exception {
		
		try {
			String noRekening = rekeningKredit.getNoRekening();
			String noFasilitas = rekeningKredit.getNoFasilitas();
			Double limit = rekeningKredit.getEqvPlafond();
			Double rate = rekeningKredit.getBungaPersen() / 100.0;
			String interesttype = rekeningKredit.getHitungBunga().getCode();
			Integer tenor = rekeningKredit.getTenor();
			
			// delete Skala Angsuran
			skalaAngsuranService.deleteByNoRekening(noRekening);
			
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
	        		throw new Exception("Invalid Interest Type");
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
	        
	        // Insert Skala Angsuran
	        FasilitasKredit fasilitasKredit = fasilitasKreditService.findOne(noFasilitas);
	        Date startDate = fasilitasKredit.getAktifasiDate();
	        startDate = this.removeTime(startDate);
	        Date dueDate = startDate;
	        
	        for (DtInstallment item : listDtInstallment) {
	        	
	        	SkalaAngsuran angs = new SkalaAngsuran();
	        	
	        	angs.setNoRekening(rekeningKredit.getNoRekening());
	        	angs.setBulanKe(item.BulanKe);
	        	dueDate = this.addMonth(startDate, (Integer)item.BulanKe);
	        	angs.setDueDate(dueDate);
	        	angs.setAngsuranPokok(item.AngsuranPokok);
	        	angs.setAngsuranBunga(item.AngsuranBunga);
	        	angs.setTotalAngsuran(item.TotalAngsuran);
	        	angs.setSisaPinjaman(item.SisaPinjaman);
	        	
	        	skalaAngsuranService.save(angs);
	        }
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	// from ~/controller/PinjamanFasilitasController
	private static Date removeTime(Date date) {    
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
	
	// from ~/controller/PinjamanFasilitasController
	private static Date addMonth(Date date, Integer numMonth) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, numMonth);
		return cal.getTime();
	}

}
