package com.mert.model;

import javax.persistence.Id;

public class DokumenAgunanViewModel {
	
	@Id
	private Integer Id;
	
	// @NotNull
	private String NoAgunan;
	
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

	public String getNoAgunan() {
		return NoAgunan;
	}

	public void setNoAgunan(String noAgunan) {
		NoAgunan = noAgunan;
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
