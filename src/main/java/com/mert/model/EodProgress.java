package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "eodprogress")
public class EodProgress {
	
	@Id
	@Column(name = "kode_eod")
	private String KodeEod;
	
	@Column(name = "status")
	private String Status;
	
	@Column(name = "date_start")
	private Date DateStart;
	
	@Column(name = "date_finish")
	private Date DateFinish;
	
	@Column(name = "count_start")
	private Integer CountStart;
	
	@Column(name = "count_finish")
	private Integer CountFinish;
	
	@Column(name = "count_now")
	private Integer CountNow;
	
	@Column(name = "percent_progress")
	private Integer PercentProgress;
	
	@Column(name = "note")
	private String Note;
	
	// Getter and Setter

	public String getKodeEod() {
		return KodeEod;
	}

	public void setKodeEod(String kodeEod) {
		KodeEod = kodeEod;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getDateStart() {
		return DateStart;
	}

	public void setDateStart(Date dateStart) {
		DateStart = dateStart;
	}

	public Date getDateFinish() {
		return DateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		DateFinish = dateFinish;
	}

	public Integer getCountStart() {
		return CountStart;
	}

	public void setCountStart(Integer countStart) {
		CountStart = countStart;
	}

	public Integer getCountFinish() {
		return CountFinish;
	}

	public void setCountFinish(Integer countFinish) {
		CountFinish = countFinish;
	}

	public Integer getCountNow() {
		return CountNow;
	}

	public void setCountNow(Integer countNow) {
		CountNow = countNow;
	}

	public Integer getPercentProgress() {
		return PercentProgress;
	}

	public void setPercentProgress(Integer percentProgress) {
		PercentProgress = percentProgress;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}
	
	// Constructor
	
	public EodProgress() {
		
	}
	
}
