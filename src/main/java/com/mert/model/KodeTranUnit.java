package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kodetranunit")
public class KodeTranUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kodetranunit_id")
	private Integer Id;
	
	@Column(name = "ko_tran")
	private String KoTran;
	
	@Column(name = "unit_id")
	private String UnitId;
	
	@Column(name = "bukubesar_debit")
	private String BukuBesarDebit;
	
	@Column(name = "bukubesar_kredit")
	private String BukuBesarKredit;
	
	@Column(name = "status")
	private String Status;
	
	
	
	// Getter and Setter

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	public String getBukuBesarDebit() {
		return BukuBesarDebit;
	}

	public void setBukuBesarDebit(String bukuBesarDebit) {
		BukuBesarDebit = bukuBesarDebit;
	}

	public String getBukuBesarKredit() {
		return BukuBesarKredit;
	}

	public void setBukuBesarKredit(String bukuBesarKredit) {
		BukuBesarKredit = bukuBesarKredit;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	
	
	// Constructor
	
	public KodeTranUnit() {
		
	}

}
