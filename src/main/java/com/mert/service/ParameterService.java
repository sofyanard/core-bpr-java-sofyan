package com.mert.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.mert.model.ParameterSandiBIOJK;
import com.mert.model.ParameterProvinsi;
import com.mert.model.ParameterKotaKab;
import com.mert.model.ParameterKodeDokumen;
import com.mert.model.ParameterKategoriCatatan;
import com.mert.repository.ParameterSandiBIOJKRepository;
import com.mert.repository.ParameterProvinsiRepository;
import com.mert.repository.ParameterKotaKabRepository;
import com.mert.repository.ParameterKodeDokumenRepository;
import com.mert.repository.ParameterKategoriCatatanRepository;

@Service
public class ParameterService {
	
	@Autowired
	private ParameterSandiBIOJKRepository parameterSandiBIOJKRepository;
	
	@Autowired
	private ParameterProvinsiRepository parameterProvinsiRepository;
	
	@Autowired
	private ParameterKotaKabRepository parameterKotaKabRepository;
	
	@Autowired
	private ParameterKodeDokumenRepository parameterKodeDokumenRepository;
	
	@Autowired
	private ParameterKategoriCatatanRepository parameterKategoriCatatanRepository;
	
	public List<ParameterProvinsi> listAllProvinsi() {
		return parameterProvinsiRepository.findAll();
	}
	
	public List<ParameterKotaKab> listKotaKabByProv(String provinsicode) {
		return parameterKotaKabRepository.findByProvinsicode(provinsicode);
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKByKategori(String kategori) {
		return parameterSandiBIOJKRepository.findByKategoricode(kategori);
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKAgunan() {
		return parameterSandiBIOJKRepository.findByKategoricode("AGUNAN");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKGolJamin() {
		return parameterSandiBIOJKRepository.findByKategoricode("GOLJAMIN");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKGolongan() {
		return parameterSandiBIOJKRepository.findByKategoricode("GOLONGAN");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKHubungan() {
		return parameterSandiBIOJKRepository.findByKategoricode("HUBUNGAN");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKJabatan() {
		return parameterSandiBIOJKRepository.findByKategoricode("JABATAN");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKJenisBU() {
		return parameterSandiBIOJKRepository.findByKategoricode("JENISBU");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKJenisFasilitas() {
		return parameterSandiBIOJKRepository.findByKategoricode("JENISFAS");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKJenisID() {
		return parameterSandiBIOJKRepository.findByKategoricode("JENISID");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKJenisKredit() {
		return parameterSandiBIOJKRepository.findByKategoricode("JENISKRED");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKJenisLapor() {
		return parameterSandiBIOJKRepository.findByKategoricode("JENISLAPOR");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKJenisSB() {
		return parameterSandiBIOJKRepository.findByKategoricode("JENISSB");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKJobCode() {
		return parameterSandiBIOJKRepository.findByKategoricode("JOBCODE");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKKetRestru() {
		return parameterSandiBIOJKRepository.findByKategoricode("KETRESTRU");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKKodeBank() {
		return parameterSandiBIOJKRepository.findByKategoricode("KODEBANK");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKKodeIkat() {
		return parameterSandiBIOJKRepository.findByKategoricode("KODEIKAT");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKKodeNegara() {
		return parameterSandiBIOJKRepository.findByKategoricode("KODENEGARA");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKKolektibilitas() {
		return parameterSandiBIOJKRepository.findByKategoricode("KOLEK");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKKondisi() {
		return parameterSandiBIOJKRepository.findByKategoricode("KONDISI");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKLokasi() {
		return parameterSandiBIOJKRepository.findByKategoricode("LOKASI");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKMarital() {
		return parameterSandiBIOJKRepository.findByKategoricode("MARITAL");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKOrientasi() {
		return parameterSandiBIOJKRepository.findByKategoricode("ORIENTASI");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKPelaporan() {
		return parameterSandiBIOJKRepository.findByKategoricode("PELAPORAN");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKPendapatan() {
		return parameterSandiBIOJKRepository.findByKategoricode("PENDAPATAN");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKPendidikan() {
		return parameterSandiBIOJKRepository.findByKategoricode("PENDIDIKAN");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKPeringkat() {
		return parameterSandiBIOJKRepository.findByKategoricode("PERINGKAT");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKPKAkad() {
		return parameterSandiBIOJKRepository.findByKategoricode("PKAKAD");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKProgram() {
		return parameterSandiBIOJKRepository.findByKategoricode("PROGRAM");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKPurpose() {
		return parameterSandiBIOJKRepository.findByKategoricode("PURPOSE");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKSebabMacet() {
		return parameterSandiBIOJKRepository.findByKategoricode("SEBABMACET");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKSektor() {
		return parameterSandiBIOJKRepository.findByKategoricode("SEKTOR");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKSifatKredit() {
		return parameterSandiBIOJKRepository.findByKategoricode("SIFATKRD");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKSukuBunga() {
		return parameterSandiBIOJKRepository.findByKategoricode("SUKUBUNGA");
	}
	
	public List<ParameterSandiBIOJK> listSandiBIOJKValuta() {
		return parameterSandiBIOJKRepository.findByKategoricode("VALUTA");
	}
	
	public List<ParameterKodeDokumen> listAllKodeDokumen() {
		return parameterKodeDokumenRepository.findAll();
	}
	
	public List<ParameterKategoriCatatan> listAllKategoriCatatan() {
		return parameterKategoriCatatanRepository.findAll();
	}

}
