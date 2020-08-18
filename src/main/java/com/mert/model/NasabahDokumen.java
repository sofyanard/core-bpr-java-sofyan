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
@Table(name = "dokdatanasabah")
public class NasabahDokumen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nonasabah")
	private Long nonasabah;
	
	@ManyToOne
	@JoinColumn(name = "dokcode", referencedColumnName = "dokcode")
	private ParameterKodeDokumen dokcode;
	
	@Column(name = "caption")
	private String caption;
	
	@Column(name = "filename")
	private String filename;
	
	

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

	public ParameterKodeDokumen getDokcode() {
		return dokcode;
	}

	public void setDokcode(ParameterKodeDokumen dokcode) {
		this.dokcode = dokcode;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	
	public NasabahDokumen() { }

}
