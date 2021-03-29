package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class RekeningKreditPelaporan {
	
	@Id
	@NotNull
	private String NoRekening;
	
	@Column(name = "sifat_kredit")
	private String SifatKredit;
	
	@Column(name = "jenis_penggunaan")
	private String JenisPenggunaan;
	
	@Column(name = "orientasi")
	private String Orientasi;
	
	@Column(name = "jenis_kredit")
	private String JenisKredit;
	
	@Column(name = "kode_fas_khusus")
	private String KodeFasKhusus;
	
	@Column(name = "note")
	private String Note;
	
	@Column(name = "lokasi_proyek")
	private String LokasiProyek;
	
	@Column(name = "nilai_proyek")
	private Double NilaiProyek;
	
	@Column(name = "golongan_penjamin")
	private String GolonganPenjamin;
	
	@Column(name = "kode_sektor")
	private String KodeSektor;
	
	@Column(name = "kode_pk")
	private String KodePk;
	
	@Column(name = "tanggal_pk_pertama")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalPkPertama;
	
	@Column(name = "no_pk_pertama")
	private String NoPkPertama;
	
	@Column(name = "tanggal_pk_akhir")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalPkAkhir;
	
	@Column(name = "no_pk_akhir")
	private String NoPkAkhir;
	
	@Column(name = "tanggal_awal_kredit")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalAwalKredit;
	
	@Column(name = "tanggal_mulai")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalMulai;
	
	

	public String getNoRekening() {
		return NoRekening;
	}

	public void setNoRekening(String noRekening) {
		NoRekening = noRekening;
	}

	public String getSifatKredit() {
		return SifatKredit;
	}

	public void setSifatKredit(String sifatKredit) {
		SifatKredit = sifatKredit;
	}

	public String getJenisPenggunaan() {
		return JenisPenggunaan;
	}

	public void setJenisPenggunaan(String jenisPenggunaan) {
		JenisPenggunaan = jenisPenggunaan;
	}

	public String getOrientasi() {
		return Orientasi;
	}

	public void setOrientasi(String orientasi) {
		Orientasi = orientasi;
	}

	public String getJenisKredit() {
		return JenisKredit;
	}

	public void setJenisKredit(String jenisKredit) {
		JenisKredit = jenisKredit;
	}

	public String getKodeFasKhusus() {
		return KodeFasKhusus;
	}

	public void setKodeFasKhusus(String kodeFasKhusus) {
		KodeFasKhusus = kodeFasKhusus;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public String getLokasiProyek() {
		return LokasiProyek;
	}

	public void setLokasiProyek(String lokasiProyek) {
		LokasiProyek = lokasiProyek;
	}

	public Double getNilaiProyek() {
		return NilaiProyek;
	}

	public void setNilaiProyek(Double nilaiProyek) {
		NilaiProyek = nilaiProyek;
	}

	public String getGolonganPenjamin() {
		return GolonganPenjamin;
	}

	public void setGolonganPenjamin(String golonganPenjamin) {
		GolonganPenjamin = golonganPenjamin;
	}

	public String getKodeSektor() {
		return KodeSektor;
	}

	public void setKodeSektor(String kodeSektor) {
		KodeSektor = kodeSektor;
	}

	public String getKodePk() {
		return KodePk;
	}

	public void setKodePk(String kodePk) {
		KodePk = kodePk;
	}

	public Date getTanggalPkPertama() {
		return TanggalPkPertama;
	}

	public void setTanggalPkPertama(Date tanggalPkPertama) {
		TanggalPkPertama = tanggalPkPertama;
	}

	public String getNoPkPertama() {
		return NoPkPertama;
	}

	public void setNoPkPertama(String noPkPertama) {
		NoPkPertama = noPkPertama;
	}

	public Date getTanggalPkAkhir() {
		return TanggalPkAkhir;
	}

	public void setTanggalPkAkhir(Date tanggalPkAkhir) {
		TanggalPkAkhir = tanggalPkAkhir;
	}

	public String getNoPkAkhir() {
		return NoPkAkhir;
	}

	public void setNoPkAkhir(String noPkAkhir) {
		NoPkAkhir = noPkAkhir;
	}

	public Date getTanggalAwalKredit() {
		return TanggalAwalKredit;
	}

	public void setTanggalAwalKredit(Date tanggalAwalKredit) {
		TanggalAwalKredit = tanggalAwalKredit;
	}

	public Date getTanggalMulai() {
		return TanggalMulai;
	}

	public void setTanggalMulai(Date tanggalMulai) {
		TanggalMulai = tanggalMulai;
	}
	
}
