package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "asuransipenjaminan")
public class AsuransiPenjaminan {
	
	@Id
	@Column(name = "no_fasilitas")
	@NotNull
	private String NoFasilitas;
	
	@Column(name = "no_nasabah")
	@NotNull
	private Long NoNasabah;
	
	@Column(name = "nama_nasabah")
	private String NamaNasabah;
	
	@ManyToOne
	@JoinColumn(name = "gol_penjamin", referencedColumnName = "golpenjamin_id")
	@NotNull
	private ParameterGolonganPenjamin GolPenjamin;
	
	@ManyToOne
	@JoinColumn(name = "jenis_id", referencedColumnName = "jenisidcode")
	private ParameterJenisID JenisId;
	
	@Column(name = "no_id")
	private String NoId;
	
	@Column(name = "nama_penjamin")
	@NotNull
	private String NamaPenjamin;
	
	@Column(name = "alamat_penjamin")
	@NotNull
	private String AlamatPenjamin;
	
	@Column(name = "kode_pos")
	private String KodePos;
	
	@ManyToOne
	@JoinColumn(name = "provinsi", referencedColumnName = "provinsicode")
	private ParameterProvinsi Provinsi;
	
	@ManyToOne
	@JoinColumn(name = "kota_kabupaten", referencedColumnName = "kotacode")
	private ParameterKotaKab KotaKabupaten;
	
	@Column(name = "kecamatan")
	private String Kecamatan;
	
	@Column(name = "kelurahan")
	private String Kelurahan;
	
	@Column(name = "jenis_asuransi")
	private String JenisAsuransi;
	
	@Column(name = "amt_premi")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double AmtPremi;
	
	@Column(name = "persen_jamin")
	@DecimalMin(value = "0.1", inclusive = false)
    @Digits(integer=5, fraction=2)
	private Double PersenJamin;
	
	@Column(name = "penutupan")
	private String Penutupan;
	
	@Column(name = "note")
	private String Note;
	
	

	public String getNoFasilitas() {
		return NoFasilitas;
	}

	public void setNoFasilitas(String noFasilitas) {
		NoFasilitas = noFasilitas;
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

	public ParameterGolonganPenjamin getGolPenjamin() {
		return GolPenjamin;
	}

	public void setGolPenjamin(ParameterGolonganPenjamin golPenjamin) {
		GolPenjamin = golPenjamin;
	}

	public ParameterJenisID getJenisId() {
		return JenisId;
	}

	public void setJenisId(ParameterJenisID jenisId) {
		JenisId = jenisId;
	}

	public String getNoId() {
		return NoId;
	}

	public void setNoId(String noId) {
		NoId = noId;
	}

	public String getNamaPenjamin() {
		return NamaPenjamin;
	}

	public void setNamaPenjamin(String namaPenjamin) {
		NamaPenjamin = namaPenjamin;
	}

	public String getAlamatPenjamin() {
		return AlamatPenjamin;
	}

	public void setAlamatPenjamin(String alamatPenjamin) {
		AlamatPenjamin = alamatPenjamin;
	}

	public String getKodePos() {
		return KodePos;
	}

	public void setKodePos(String kodePos) {
		KodePos = kodePos;
	}

	public ParameterProvinsi getProvinsi() {
		return Provinsi;
	}

	public void setProvinsi(ParameterProvinsi provinsi) {
		Provinsi = provinsi;
	}

	public ParameterKotaKab getKotaKabupaten() {
		return KotaKabupaten;
	}

	public void setKotaKabupaten(ParameterKotaKab kotaKabupaten) {
		KotaKabupaten = kotaKabupaten;
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

	public String getJenisAsuransi() {
		return JenisAsuransi;
	}

	public void setJenisAsuransi(String jenisAsuransi) {
		JenisAsuransi = jenisAsuransi;
	}

	public Double getAmtPremi() {
		return AmtPremi;
	}

	public void setAmtPremi(Double amtPremi) {
		AmtPremi = amtPremi;
	}

	public Double getPersenJamin() {
		return PersenJamin;
	}

	public void setPersenJamin(Double persenJamin) {
		PersenJamin = persenJamin;
	}

	public String getPenutupan() {
		return Penutupan;
	}

	public void setPenutupan(String penutupan) {
		Penutupan = penutupan;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}
	
	

}
