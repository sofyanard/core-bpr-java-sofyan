package com.mert.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="rekeningtabungan")
public class TabunganPemeliharaan{
	
	@Id
	@Column(name="no_rekg")
	private String no_rekg;	

    @Column(name="no_nasabah")
	private Long no_nasabah;
	
	@Column(name="nama_nasabah")
	private String nama_nasabah;
	
	
	@ManyToOne
	@JoinColumn(name = "status_rekening", referencedColumnName = "status_rekening")
	private StatusRekg StatusRekening;
	
	@Column(name="unit")
	private String unit;
	
	@ManyToOne
	@JoinColumn(name = "code", referencedColumnName = "code")
	private ParameterProduk Code;
	
	@Column(name="bunga")
	private String bunga;
	
	@Column(name="sandi_kel")
	private String sandi_kel;
	
	@Column(name = "blokir_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date blokir_date;
	
	@Column(name="amt_blokir")
	private String amt_blokir;
	
	//GetterandSetter

	public Long getNo_nasabah() {
		return no_nasabah;
	}

	public void setNo_nasabah(Long no_nasabah) {
		this.no_nasabah = no_nasabah;
	}

	public String getNama_nasabah() {
		return nama_nasabah;
	}

	public void setNama_nasabah(String nama_nasabah) {
		this.nama_nasabah = nama_nasabah;
	}

	public String getNo_rekg() {
		return no_rekg;
	}

	public void setNo_rekg(String no_rekg) {
		this.no_rekg = no_rekg;
	}

	

	public StatusRekg getStatusRekening() {
		return StatusRekening;
	}

	public void setStatusRekening(StatusRekg statusRekening) {
		StatusRekening = statusRekening;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public ParameterProduk getCode() {
		return Code;
	}

	public void setCode(ParameterProduk code) {
		Code = code;
	}

	public String getBunga() {
		return bunga;
	}

	public void setBunga(String bunga) {
		this.bunga = bunga;
	}

	public String getSandi_kel() {
		return sandi_kel;
	}

	public void setSandi_kel(String sandi_kel) {
		this.sandi_kel = sandi_kel;
	}

	public Date getBlokir_date() {
		return blokir_date;
	}

	public void setBlokir_date(Date blokir_date) {
		this.blokir_date = blokir_date;
	}

	public String getAmt_blokir() {
		return amt_blokir;
	}

	public void setAmt_blokir(String amt_blokir) {
		this.amt_blokir = amt_blokir;
	}
	
	//constructor
		public TabunganPemeliharaan() { };
}
