package com.mert.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tranhistory")
public class TranHistory {
	
	@Id
	@Column(name = "tran_id")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID TranId;
	
	@NotNull
	@Column(name = "tran_ref")
	private UUID TranRef;
	
	@NotNull
	@Column(name = "tran_date")
	private Date TranDate;
	
	@NotNull
	@Column(name = "ko_tran")
	private String KoTran;
	
	@Column(name = "account_type")
	private String AccountType;
	
	@ManyToOne
	@JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
	private AppUnit UnitId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private AppUser UserId;
	
	@Column(name = "no_rek_debit")
	private String NoRekDebit;
	
	@Column(name = "value_debit")
	private Double ValueDebit;
	
	@Column(name = "no_rek_kredit")
	private String NoRekKredit;
	
	@Column(name = "value_kredit")
	private Double ValueKredit;
	
	@Column(name = "note")
	@Size(max = 50)
	private String Note;
	
	@Column(name = "other_note")
	@Size(max = 50)
	private String OtherNote;
	
	@Column(name = "is_correction")
	@Size(max = 1)
	private String IsCorrection;
	
	
	
	// Getter and Setter

	public UUID getTranId() {
		return TranId;
	}

	public void setTranId(UUID tranId) {
		TranId = tranId;
	}

	public UUID getTranRef() {
		return TranRef;
	}

	public void setTranRef(UUID tranRef) {
		TranRef = tranRef;
	}

	public Date getTranDate() {
		return TranDate;
	}

	public void setTranDate(Date tranDate) {
		TranDate = tranDate;
	}

	public String getKoTran() {
		return KoTran;
	}

	public void setKoTran(String koTran) {
		KoTran = koTran;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public AppUnit getUnitId() {
		return UnitId;
	}

	public void setUnitId(AppUnit unitId) {
		UnitId = unitId;
	}

	public AppUser getUserId() {
		return UserId;
	}

	public void setUserId(AppUser userId) {
		UserId = userId;
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

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}
	
	public String getOtherNote() {
		return OtherNote;
	}

	public void setOtherNote(String otherNote) {
		OtherNote = otherNote;
	}

	public String getIsCorrection() {
		return IsCorrection;
	}

	public void setIsCorrection(String isCorrection) {
		IsCorrection = isCorrection;
	}
	
	

}
