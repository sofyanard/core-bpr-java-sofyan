package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fasilitaskredit")
public class FasilitasKreditAktifasiModel {
	
	@Id
	@Column(name = "no_fasilitas")
	private String NoFasilitas;
	
	@Column(name = "aktifasi_date")
	private Date AktifasiDate;
	
	@Column(name = "no_rekening")
	private String NoRekening;
	
	

	public String getNoFasilitas() {
		return NoFasilitas;
	}

	public void setNoFasilitas(String noFasilitas) {
		NoFasilitas = noFasilitas;
	}

	public Date getAktifasiDate() {
		return AktifasiDate;
	}

	public void setAktifasiDate(Date aktifasiDate) {
		AktifasiDate = aktifasiDate;
	}

	public String getNoRekening() {
		return NoRekening;
	}

	public void setNoRekening(String noRekening) {
		NoRekening = noRekening;
	}
	
	

}
