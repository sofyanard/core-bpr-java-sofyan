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
@Table(name = "eodpost")
public class EodPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "eod_tanggal")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date EodTanggal;
	
	@Column(name = "ko_tran")
	private String KoTran;
	
	@Column(name = "unit_id")
	private String UnitId;
	
	@Column(name = "post_object")
	private String PostObject;
	
	@Column(name = "sub_object")
	private String SubObject;
	
	@Column(name = "no_rek_debit")
	private String NoRekDebit;
	
	@Column(name = "value_debit")
	private Double ValueDebit;
	
	@Column(name = "no_rek_kredit")
	private String NoRekKredit;
	
	@Column(name = "value_kredit")
	private Double ValueKredit;
	
	@Column(name = "tran_date")
	private Date TranDate;
	
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

	public String getKoTran() {
		return KoTran;
	}

	public void setKoTran(String koTran) {
		KoTran = koTran;
	}

	public String getUnitId() {
		return UnitId;
	}

	public void setUnitId(String unitId) {
		UnitId = unitId;
	}
	
	public String getPostObject() {
		return PostObject;
	}

	public void setPostObject(String postObject) {
		PostObject = postObject;
	}
	
	public String getSubObject() {
		return SubObject;
	}

	public void setSubObject(String subObject) {
		SubObject = subObject;
	}

	public String getNoRekDebit() {
		return NoRekDebit;
	}

	public void setNoRekDebit(String noRekDebit) {
		NoRekDebit = noRekDebit;
	}

	public Double getValueDebit() {
		return ValueDebit;
	}

	public void setValueDebit(Double valueDebit) {
		ValueDebit = valueDebit;
	}

	public String getNoRekKredit() {
		return NoRekKredit;
	}

	public void setNoRekKredit(String noRekKredit) {
		NoRekKredit = noRekKredit;
	}

	public Double getValueKredit() {
		return ValueKredit;
	}

	public void setValueKredit(Double valueKredit) {
		ValueKredit = valueKredit;
	}

	public Date getTranDate() {
		return TranDate;
	}

	public void setTranDate(Date tranDate) {
		TranDate = tranDate;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}
	
	// Constructor
	
	public EodPost() {
		
	}
	
}
