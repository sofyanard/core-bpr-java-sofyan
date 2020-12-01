package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dataagunan")
public class DataAgunan {
	
	@Id
	@Column(name = "no_agunan")
	private String NoAgunan;
	
	@Column(name = "no_nasabah")
	@NotNull
	private Long NoNasabah;
	
	@Column(name = "nama_nasabah")
	private String NamaNasabah;
	
	@Column(name = "no_fasilitas")
	@NotNull
	private String NoFasilitas;
	
	@ManyToOne
	@JoinColumn(name = "jenis_agunan", referencedColumnName = "jenisagunan_id")
	@NotNull
	private ParameterJenisAgunan JenisAgunan;
	
	@ManyToOne
	@JoinColumn(name = "status_agunan", referencedColumnName = "statusagunan_id")
	private ParameterStatusAgunan StatusAgunan;
	
	@Column(name = "peringkat_sb")
	private String PeringkatSb;
	
	@ManyToOne
	@JoinColumn(name = "lembaga_rating", referencedColumnName = "lembagarating_id")
	private ParameterLembagaRating LembagaRating;
	
	@Column(name = "rating_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date RatingDate;
	
	@Column(name = "nama_pemilik")
	@NotNull
	@Size(min=1, max=50)
	private String NamaPemilik;
	
	@Column(name = "nilai_pasar")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double NilaiPasar;
	
	@Column(name = "nilai_appraisal")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double NilaiAppraisal;
	
	@Column(name = "nilai_njop")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double NilaiNjop;
	
	@ManyToOne
	@JoinColumn(name = "dok_pemilikan", referencedColumnName = "dokcode")
	@NotNull
	private ParameterKodeDokumen DokPemilikan;
	
	@Column(name = "no_dok")
	@NotNull
	@Size(min=1, max=50)
	private String NoDok;
	
	@Column(name = "appraisal_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date AppraisalDate;
	
	@Column(name = "penilai")
	private String Penilai;
	
	@Column(name = "alamat")
	@NotNull
	@Size(min=1, max=100)
	private String Alamat;
	
	@Column(name = "kode_pos")
	private String KodePos;
	
	@ManyToOne
	@JoinColumn(name = "kota_kabupaten", referencedColumnName = "kotacode")
	@NotNull
	private ParameterKotaKab KotaKabupaten;
	
	@ManyToOne
	@JoinColumn(name = "provinsi", referencedColumnName = "provinsicode")
	@NotNull
	private ParameterProvinsi Provinsi;
	
	@Column(name = "kecamatan")
	private String Kecamatan;
	
	@Column(name = "kelurahan")
	private String Kelurahan;
	
	@ManyToOne
	@JoinColumn(name = "asuransi", referencedColumnName = "yesno_id")
	private ParameterYesNo Asuransi;
	
	@ManyToOne
	@JoinColumn(name = "pengikatan", referencedColumnName = "pengikatan_id")
	private ParameterPengikatan Pengikatan;
	
	@Column(name = "date_pengikatan")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date DatePengikatan;
	
	@Column(name = "notaris")
	private String Notaris;
	
	@Column(name = "note")
	private String Note;
	
	

	public String getNoAgunan() {
		return NoAgunan;
	}

	public void setNoAgunan(String noAgunan) {
		NoAgunan = noAgunan;
	}

	public Long getNoNasabah() {
		return NoNasabah;
	}

	public void setNoNasabah(Long noNasabah) {
		NoNasabah = noNasabah;
	}

	public String getNamaNasabah() {
		return NamaNasabah;
	}

	public void setNamaNasabah(String namaNasabah) {
		NamaNasabah = namaNasabah;
	}

	public String getNoFasilitas() {
		return NoFasilitas;
	}

	public void setNoFasilitas(String noFasilitas) {
		NoFasilitas = noFasilitas;
	}

	public ParameterJenisAgunan getJenisAgunan() {
		return JenisAgunan;
	}

	public void setJenisAgunan(ParameterJenisAgunan jenisAgunan) {
		JenisAgunan = jenisAgunan;
	}

	public ParameterStatusAgunan getStatusAgunan() {
		return StatusAgunan;
	}

	public void setStatusAgunan(ParameterStatusAgunan statusAgunan) {
		StatusAgunan = statusAgunan;
	}

	public String getPeringkatSb() {
		return PeringkatSb;
	}

	public void setPeringkatSb(String peringkatSb) {
		PeringkatSb = peringkatSb;
	}

	public ParameterLembagaRating getLembagaRating() {
		return LembagaRating;
	}

	public void setLembagaRating(ParameterLembagaRating lembagaRating) {
		LembagaRating = lembagaRating;
	}

	public Date getRatingDate() {
		return RatingDate;
	}

	public void setRatingDate(Date ratingDate) {
		RatingDate = ratingDate;
	}

	public String getNamaPemilik() {
		return NamaPemilik;
	}

	public void setNamaPemilik(String namaPemilik) {
		NamaPemilik = namaPemilik;
	}

	public Double getNilaiPasar() {
		return NilaiPasar;
	}

	public void setNilaiPasar(Double nilaiPasar) {
		NilaiPasar = nilaiPasar;
	}

	public Double getNilaiAppraisal() {
		return NilaiAppraisal;
	}

	public void setNilaiAppraisal(Double nilaiAppraisal) {
		NilaiAppraisal = nilaiAppraisal;
	}

	public Double getNilaiNjop() {
		return NilaiNjop;
	}

	public void setNilaiNjop(Double nilaiNjop) {
		NilaiNjop = nilaiNjop;
	}

	public ParameterKodeDokumen getDokPemilikan() {
		return DokPemilikan;
	}

	public void setDokPemilikan(ParameterKodeDokumen dokPemilikan) {
		DokPemilikan = dokPemilikan;
	}

	public String getNoDok() {
		return NoDok;
	}

	public void setNoDok(String noDok) {
		NoDok = noDok;
	}

	public Date getAppraisalDate() {
		return AppraisalDate;
	}

	public void setAppraisalDate(Date appraisalDate) {
		AppraisalDate = appraisalDate;
	}

	public String getPenilai() {
		return Penilai;
	}

	public void setPenilai(String penilai) {
		Penilai = penilai;
	}

	public String getAlamat() {
		return Alamat;
	}

	public void setAlamat(String alamat) {
		Alamat = alamat;
	}

	public String getKodePos() {
		return KodePos;
	}

	public void setKodePos(String kodePos) {
		KodePos = kodePos;
	}

	public ParameterKotaKab getKotaKabupaten() {
		return KotaKabupaten;
	}

	public void setKotaKabupaten(ParameterKotaKab kotaKabupaten) {
		KotaKabupaten = kotaKabupaten;
	}

	public ParameterProvinsi getProvinsi() {
		return Provinsi;
	}

	public void setProvinsi(ParameterProvinsi provinsi) {
		Provinsi = provinsi;
	}

	public String getKecamatan() {
		return Kecamatan;
	}

	public void setKecamatan(String kecamatan) {
		Kecamatan = kecamatan;
	}

	public String getKelurahan() {
		return Kelurahan;
	}

	public void setKelurahan(String kelurahan) {
		Kelurahan = kelurahan;
	}

	public ParameterYesNo getAsuransi() {
		return Asuransi;
	}

	public void setAsuransi(ParameterYesNo asuransi) {
		Asuransi = asuransi;
	}

	public ParameterPengikatan getPengikatan() {
		return Pengikatan;
	}

	public void setPengikatan(ParameterPengikatan pengikatan) {
		Pengikatan = pengikatan;
	}

	public Date getDatePengikatan() {
		return DatePengikatan;
	}

	public void setDatePengikatan(Date datePengikatan) {
		DatePengikatan = datePengikatan;
	}

	public String getNotaris() {
		return Notaris;
	}

	public void setNotaris(String notaris) {
		Notaris = notaris;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}
	
	
}
