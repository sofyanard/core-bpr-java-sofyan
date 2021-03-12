package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "eodkalkulasi")
public class EodKalkulasi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "eod_tanggal")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date EodTanggal;
	
	@Column(name = "kode_eod")
	private String KodeEod;
	
	@Column(name = "unit_id")
	private String UnitId;
	
	@Column(name = "calc_object")
	private String CalcObject;
	
	@Column(name = "sub_object")
	private String SubObject;
	
	@Column(name = "calc_value")
	private Double CalcValue;
	
	@Column(name = "rek_buku_besar")
	private String RekBukuBesar;
	
	@Column(name = "calc_date")
	private Date CalcDate;
	
	@Column(name = "note")
	private String Note;
	
	// Getter and Setter

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getEodTanggal() {
		return EodTanggal;
	}

	public void setEodTanggal(Date eodTanggal) {
		EodTanggal = eodTanggal;
	}

	public String getKodeEod() {
		return KodeEod;
	}

	public void setKodeEod(String kodeEod) {
		KodeEod = kodeEod;
	}
	
	public String getUnitId() {
		return UnitId;
	}

	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public String getCalcObject() {
		return CalcObject;
	}

	public void setCalcObject(String calcObject) {
		CalcObject = calcObject;
	}
	
	public String getSubObject() {
		return SubObject;
	}

	public void setSubObject(String subObject) {
		SubObject = subObject;
	}

	public Double getCalcValue() {
		return CalcValue;
	}

	public void setCalcValue(Double calcValue) {
		CalcValue = calcValue;
	}

	public String getRekBukuBesar() {
		return RekBukuBesar;
	}

	public void setRekBukuBesar(String rekBukuBesar) {
		RekBukuBesar = rekBukuBesar;
	}

	public Date getCalcDate() {
		return CalcDate;
	}

	public void setCalcDate(Date calcDate) {
		CalcDate = calcDate;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}
	
	
	
	// Constructor
	
	public EodKalkulasi() {
		
	}

}
