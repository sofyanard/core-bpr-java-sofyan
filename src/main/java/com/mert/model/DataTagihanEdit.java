package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class DataTagihanEdit {
	
	@Id
	@NotNull
	private Integer Id;
	
	@NotNull
	private String NoRekening;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date DueDate;
	
	private Double Pokok;
	
	private Double Bunga;
	
	private Double DendaPokok;
	
	private Double DendaBunga;
	
	private Double Lainnya;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNoRekening() {
		return NoRekening;
	}

	public void setNoRekening(String noRekening) {
		NoRekening = noRekening;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public Double getPokok() {
		return Pokok;
	}

	public void setPokok(Double pokok) {
		Pokok = pokok;
	}

	public Double getBunga() {
		return Bunga;
	}

	public void setBunga(Double bunga) {
		Bunga = bunga;
	}

	public Double getDendaPokok() {
		return DendaPokok;
	}

	public void setDendaPokok(Double dendaPokok) {
		DendaPokok = dendaPokok;
	}

	public Double getDendaBunga() {
		return DendaBunga;
	}

	public void setDendaBunga(Double dendaBunga) {
		DendaBunga = dendaBunga;
	}

	public Double getLainnya() {
		return Lainnya;
	}

	public void setLainnya(Double lainnya) {
		Lainnya = lainnya;
	}
	
}
