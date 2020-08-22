package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dataperusahaan")
public class NasabahPengurus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nonasabah")
	private Long nonasabah;
	
	@Column(name = "nama")
	private String nama;
	
	@ManyToOne
	@JoinColumn(name = "gender", referencedColumnName = "gendercode")
	private ParameterGender gender;
	
	@Column(name = "jabatan")
	private String jabatan;
	
	@Column(name = "saham")
	private Double saham;
	
	@ManyToOne
	@JoinColumn(name = "idcode", referencedColumnName = "jenisidcode")
	private ParameterJenisID idcode;
	
	@Column(name = "noid")
	private String noid;
	
	@Column(name = "alamat")
	private String alamat;
	
	@Column(name = "kodepos")
	private String kodepos;
	
	@Column(name = "provinsi")
	private String provinsi;
	
	@Column(name = "kota")
	private String kota;
	
	@Column(name = "kecamatan")
	private String kecamatan;
	
	@Column(name = "kelurahan")
	private String kelurahan;
	
	@Column(name = "status")
	private String status;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getNonasabah() {
		return nonasabah;
	}

	public void setNonasabah(Long nonasabah) {
		this.nonasabah = nonasabah;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public ParameterGender getGender() {
		return gender;
	}

	public void setGender(ParameterGender gender) {
		this.gender = gender;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public Double getSaham() {
		return saham;
	}

	public void setSaham(Double saham) {
		this.saham = saham;
	}

	public ParameterJenisID getIdcode() {
		return idcode;
	}

	public void setIdcode(ParameterJenisID idcode) {
		this.idcode = idcode;
	}

	public String getNoid() {
		return noid;
	}

	public void setNoid(String noid) {
		this.noid = noid;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getKodepos() {
		return kodepos;
	}

	public void setKodepos(String kodepos) {
		this.kodepos = kodepos;
	}

	public String getProvinsi() {
		return provinsi;
	}

	public void setProvinsi(String provinsi) {
		this.provinsi = provinsi;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	public String getKecamatan() {
		return kecamatan;
	}

	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
	}

	public String getKelurahan() {
		return kelurahan;
	}

	public void setKelurahan(String kelurahan) {
		this.kelurahan = kelurahan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	public NasabahPengurus() { }

}
