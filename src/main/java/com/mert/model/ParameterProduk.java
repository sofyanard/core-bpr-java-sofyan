package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produk")
public class ParameterProduk {
	
	@Id
	@Column(name = "code")
	private String Code;
	
	@Column(name = "desc")
	private String Desc;
	
	@ManyToOne
	@JoinColumn(name = "type", referencedColumnName = "producttype_id")
	private ParameterProductType Type;
	
	@ManyToOne
	@JoinColumn(name = "valuta", referencedColumnName = "code")
	private ParameterKodeValuta Valuta;
	
	@ManyToOne
	@JoinColumn(name = "hitung_bunga", referencedColumnName = "code")
	private ParameterHitungBunga HitungBunga;
	
	@ManyToOne
	@JoinColumn(name = "bunga_cd", referencedColumnName = "code")
	private ParameterSukuBunga BungaCd;
	
	@ManyToOne
	@JoinColumn(name = "var_rate", referencedColumnName = "varrate_id")
	private ParameterVarRate VarRate;
	
	@Column(name = "buku_besar")
	private String BukuBesar;
	
	@Column(name = "status")
	private String Status;
	
	

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public ParameterProductType getType() {
		return Type;
	}

	public void setType(ParameterProductType type) {
		Type = type;
	}

	public ParameterKodeValuta getValuta() {
		return Valuta;
	}

	public void setValuta(ParameterKodeValuta valuta) {
		Valuta = valuta;
	}

	public ParameterHitungBunga getHitungBunga() {
		return HitungBunga;
	}

	public void setHitungBunga(ParameterHitungBunga hitungBunga) {
		HitungBunga = hitungBunga;
	}

	public ParameterSukuBunga getBungaCd() {
		return BungaCd;
	}

	public void setBungaCd(ParameterSukuBunga bungaCd) {
		BungaCd = bungaCd;
	}

	public ParameterVarRate getVarRate() {
		return VarRate;
	}

	public void setVarRate(ParameterVarRate varRate) {
		VarRate = varRate;
	}

	public String getBukuBesar() {
		return BukuBesar;
	}

	public void setBukuBesar(String bukuBesar) {
		BukuBesar = bukuBesar;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	
	
	public ParameterProduk() {
		
	}

}
