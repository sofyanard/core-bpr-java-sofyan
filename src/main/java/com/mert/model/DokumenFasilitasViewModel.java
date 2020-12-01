package com.mert.model;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class DokumenFasilitasViewModel {
	
	@Id
	private Integer Id;
	
	// @NotNull
	private String NoFasilitas;
	
	private Integer DokNasabahId;
	
	// @NotNull
	private Long NoNasabah;
	
	private ParameterKodeDokumen DokCode;
	
	// @NotNull
	private String Caption;
	
	private String FileName;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNoFasilitas() {
		return NoFasilitas;
	}

	public void setNoFasilitas(String noFasilitas) {
		NoFasilitas = noFasilitas;
	}

	public Integer getDokNasabahId() {
		return DokNasabahId;
	}

	public void setDokNasabahId(Integer dokNasabahId) {
		DokNasabahId = dokNasabahId;
	}

	public Long getNoNasabah() {
		return NoNasabah;
	}

	public void setNoNasabah(Long noNasabah) {
		NoNasabah = noNasabah;
	}

	public ParameterKodeDokumen getDokCode() {
		return DokCode;
	}

	public void setDokCode(ParameterKodeDokumen dokCode) {
		DokCode = dokCode;
	}

	public String getCaption() {
		return Caption;
	}

	public void setCaption(String caption) {
		Caption = caption;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}
	
	

}
