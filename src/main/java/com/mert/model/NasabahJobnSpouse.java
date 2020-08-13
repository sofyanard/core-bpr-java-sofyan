package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "datanasabah")
public class NasabahJobnSpouse {
	
	// Table Columns
	
	@Id
	@Column(name = "nonasabah")
	private Long nonasabah;
	
	@Column(name = "jobtype")
	private String jobtype;
	
	@Column(name = "workplace")
	private String workplace;
	
	@Column(name = "bidusaha")
	private String bidusaha;
	
	@Column(name = "income")
	private Double income;
	
	@Column(name = "officeadd")
	private String officeadd;

	@Column(name = "officekodepos")
	private String officekodepos;

	@Column(name = "officeprov")
	private String officeprov;

	@Column(name = "officecity")
	private String officecity;

	@Column(name = "officekec")
	private String officekec;

	@Column(name = "officekel")
	private String officekel;

	@Column(name = "officephnno")
	private String officephnno;
	
	@Column(name = "spousename")
	private String spousename;
	
	@Column(name = "spousebirthplace")
	private String spousebirthplace;
	
	@Column(name = "spousebod")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date spousebod;
	
	@Column(name = "spouseidtype")
	private String spouseidtype;
	
	@Column(name = "spouseidno")
	private String spouseidno;
	
	@Column(name = "spousejob")
	private String spousejob;
	
	@Column(name = "spouseedu")
	private String spouseedu;
	
	
	
	// Getter and Setter

	public Long getNonasabah() {
		return nonasabah;
	}

	public void setNonasabah(Long nonasabah) {
		this.nonasabah = nonasabah;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getBidusaha() {
		return bidusaha;
	}

	public void setBidusaha(String bidusaha) {
		this.bidusaha = bidusaha;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public String getOfficeadd() {
		return officeadd;
	}

	public void setOfficeadd(String officeadd) {
		this.officeadd = officeadd;
	}

	public String getOfficekodepos() {
		return officekodepos;
	}

	public void setOfficekodepos(String officekodepos) {
		this.officekodepos = officekodepos;
	}

	public String getOfficeprov() {
		return officeprov;
	}

	public void setOfficeprov(String officeprov) {
		this.officeprov = officeprov;
	}

	public String getOfficecity() {
		return officecity;
	}

	public void setOfficecity(String officecity) {
		this.officecity = officecity;
	}

	public String getOfficekec() {
		return officekec;
	}

	public void setOfficekec(String officekec) {
		this.officekec = officekec;
	}

	public String getOfficekel() {
		return officekel;
	}

	public void setOfficekel(String officekel) {
		this.officekel = officekel;
	}

	public String getOfficephnno() {
		return officephnno;
	}

	public void setOfficephnno(String officephnno) {
		this.officephnno = officephnno;
	}

	public String getSpousename() {
		return spousename;
	}

	public void setSpousename(String spousename) {
		this.spousename = spousename;
	}

	public String getSpousebirthplace() {
		return spousebirthplace;
	}

	public void setSpousebirthplace(String spousebirthplace) {
		this.spousebirthplace = spousebirthplace;
	}

	public Date getSpousebod() {
		return spousebod;
	}

	public void setSpousebod(Date spousebod) {
		this.spousebod = spousebod;
	}

	public String getSpouseidtype() {
		return spouseidtype;
	}

	public void setSpouseidtype(String spouseidtype) {
		this.spouseidtype = spouseidtype;
	}

	public String getSpouseidno() {
		return spouseidno;
	}

	public void setSpouseidno(String spouseidno) {
		this.spouseidno = spouseidno;
	}

	public String getSpousejob() {
		return spousejob;
	}

	public void setSpousejob(String spousejob) {
		this.spousejob = spousejob;
	}

	public String getSpouseedu() {
		return spouseedu;
	}

	public void setSpouseedu(String spouseedu) {
		this.spouseedu = spouseedu;
	}
	
	
	
	// Constructor
	
	public NasabahJobnSpouse() { }

}
