package com.mert.model;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

public class RekeningKreditRestru {
	
	@Id
	private String NoRekening;
	
	private ParameterKolektibilitas StatusKolektibilitas;
	
	private ParameterYesNo RestruFlag;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalRestruAwal;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalRestruAkhir;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalReview;
	
	private Integer RestruKe;
	
	private String KetRestru;
	
	private String Kondisi;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalKondisi;
	
	private String SebabMacet;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalMacet;
	
	

	public String getNoRekening() {
		return NoRekening;
	}

	public void setNoRekening(String noRekening) {
		NoRekening = noRekening;
	}

	public ParameterKolektibilitas getStatusKolektibilitas() {
		return StatusKolektibilitas;
	}

	public void setStatusKolektibilitas(ParameterKolektibilitas statusKolektibilitas) {
		StatusKolektibilitas = statusKolektibilitas;
	}

	public ParameterYesNo getRestruFlag() {
		return RestruFlag;
	}

	public void setRestruFlag(ParameterYesNo restruFlag) {
		RestruFlag = restruFlag;
	}

	public Date getTanggalRestruAwal() {
		return TanggalRestruAwal;
	}

	public void setTanggalRestruAwal(Date tanggalRestruAwal) {
		TanggalRestruAwal = tanggalRestruAwal;
	}

	public Date getTanggalRestruAkhir() {
		return TanggalRestruAkhir;
	}

	public void setTanggalRestruAkhir(Date tanggalRestruAkhir) {
		TanggalRestruAkhir = tanggalRestruAkhir;
	}

	public Date getTanggalReview() {
		return TanggalReview;
	}

	public void setTanggalReview(Date tanggalReview) {
		TanggalReview = tanggalReview;
	}

	public Integer getRestruKe() {
		return RestruKe;
	}

	public void setRestruKe(Integer restruKe) {
		RestruKe = restruKe;
	}

	public String getKetRestru() {
		return KetRestru;
	}

	public void setKetRestru(String ketRestru) {
		KetRestru = ketRestru;
	}

	public String getKondisi() {
		return Kondisi;
	}

	public void setKondisi(String kondisi) {
		Kondisi = kondisi;
	}

	public Date getTanggalKondisi() {
		return TanggalKondisi;
	}

	public void setTanggalKondisi(Date tanggalKondisi) {
		TanggalKondisi = tanggalKondisi;
	}

	public String getSebabMacet() {
		return SebabMacet;
	}

	public void setSebabMacet(String sebabMacet) {
		SebabMacet = sebabMacet;
	}

	public Date getTanggalMacet() {
		return TanggalMacet;
	}

	public void setTanggalMacet(Date tanggalMacet) {
		TanggalMacet = tanggalMacet;
	}
	
}
