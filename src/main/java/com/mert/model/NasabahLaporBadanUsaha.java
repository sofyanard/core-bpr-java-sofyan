package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "datanasabah")
public class NasabahLaporBadanUsaha {
	
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
	
	@Column(name = "gopublic")
	private String gopublic;
	
	@Column(name = "peringkat")
	private String peringkat;
	
	@Column(name = "lembagarating")
	private String lembagarating;
	
	@Column(name = "daterating")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date daterating;
	
	@Column(name = "groupusaha")
	private String groupusaha;
	
	

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

	public String getGopublic() {
		return gopublic;
	}

	public void setGopublic(String gopublic) {
		this.gopublic = gopublic;
	}

	public String getPeringkat() {
		return peringkat;
	}

	public void setPeringkat(String peringkat) {
		this.peringkat = peringkat;
	}

	public String getLembagarating() {
		return lembagarating;
	}

	public void setLembagarating(String lembagarating) {
		this.lembagarating = lembagarating;
	}

	public Date getDaterating() {
		return daterating;
	}

	public void setDaterating(Date daterating) {
		this.daterating = daterating;
	}

	public String getGroupusaha() {
		return groupusaha;
	}

	public void setGroupusaha(String groupusaha) {
		this.groupusaha = groupusaha;
	}
	
	
	
	public NasabahLaporBadanUsaha() { }

}
