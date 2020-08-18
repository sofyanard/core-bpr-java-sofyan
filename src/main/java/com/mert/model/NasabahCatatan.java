package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "catatannasabah")
public class NasabahCatatan {
	
	@Id
	@Column(name = "nonasabah")
	private Long nonasabah;
	
	@ManyToOne
	@JoinColumn(name = "kategori", referencedColumnName = "catatancode")
	private ParameterKategoriCatatan kategori;
	
	@Column(name = "source")
	private String source;
	
	@Column(name = "expdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expdate;
	
	@Column(name = "catatan")
	private String catatan;
	
	

	public Long getNonasabah() {
		return nonasabah;
	}

	public void setNonasabah(Long nonasabah) {
		this.nonasabah = nonasabah;
	}

	public ParameterKategoriCatatan getKategori() {
		return kategori;
	}

	public void setKategori(ParameterKategoriCatatan kategori) {
		this.kategori = kategori;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getExpdate() {
		return expdate;
	}

	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	
	
	
	public NasabahCatatan() { }

}
