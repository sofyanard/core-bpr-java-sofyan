package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "datanasabah")
public class NasabahLaporPerorangan {
	
	@Id
	@Column(name = "nonasabah")
	private Long nonasabah;
	
	@Column(name = "namapelaporan")
	private String namapelaporan;
	
	@Column(name = "golnasabah")
	private String golnasabah;
	
	@Column(name = "hubdgnbank")
	private String hubdgnbank;
	
	@Column(name = "sourceincome")
	private String sourceincome;
	
	@Column(name = "bmpklebih")
	private String bmpklebih;
	
	@Column(name = "bmpklampaui")
	private String bmpklampaui;
	
	

	public Long getNonasabah() {
		return nonasabah;
	}

	public void setNonasabah(Long nonasabah) {
		this.nonasabah = nonasabah;
	}

	public String getNamapelaporan() {
		return namapelaporan;
	}

	public void setNamapelaporan(String namapelaporan) {
		this.namapelaporan = namapelaporan;
	}

	public String getGolnasabah() {
		return golnasabah;
	}

	public void setGolnasabah(String golnasabah) {
		this.golnasabah = golnasabah;
	}

	public String getHubdgnbank() {
		return hubdgnbank;
	}

	public void setHubdgnbank(String hubdgnbank) {
		this.hubdgnbank = hubdgnbank;
	}

	public String getSourceincome() {
		return sourceincome;
	}

	public void setSourceincome(String sourceincome) {
		this.sourceincome = sourceincome;
	}

	public String getBmpklebih() {
		return bmpklebih;
	}

	public void setBmpklebih(String bmpklebih) {
		this.bmpklebih = bmpklebih;
	}

	public String getBmpklampaui() {
		return bmpklampaui;
	}

	public void setBmpklampaui(String bmpklampaui) {
		this.bmpklampaui = bmpklampaui;
	}
	
	
	
	public NasabahLaporPerorangan() { }

}
