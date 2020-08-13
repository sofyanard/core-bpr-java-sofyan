package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "datanasabah")
public class NasabahBadanUsaha {

	// Table Columns
	
	@Id
	@Column(name = "nonasabah")
	private Long nonasabah;

	@Column(name = "prefix")
	private String prefix;

	@Column(name = "namalengkap")
	private String namalengkap;

	@Column(name = "bodestablish")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bodestablish;
	
	@Column(name = "nonpwp")
	private String nonpwp;

	@Column(name = "email")
	private String email;

	@Column(name = "bidusaha")
	private String bidusaha;

	@Column(name = "appno")
	private String appno;

	@Column(name = "appdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appdate;

	@Column(name = "appchgno")
	private String appchgno;

	@Column(name = "appchgdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appchgdate;

	@Column(name = "notaris")
	private String notaris;

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

	@Column(name = "officefaxno")
	private String officefaxno;

	@Column(name = "officestatus")
	private String officestatus;

	@Column(name = "contactperson")
	private String contactperson;
	
	
	
	// Getter and Setter

	public Long getNonasabah() {
		return nonasabah;
	}

	public void setNonasabah(Long nonasabah) {
		this.nonasabah = nonasabah;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getNamalengkap() {
		return namalengkap;
	}

	public void setNamalengkap(String namalengkap) {
		this.namalengkap = namalengkap;
	}

	public Date getBodestablish() {
		return bodestablish;
	}

	public void setBodestablish(Date bodestablish) {
		this.bodestablish = bodestablish;
	}

	public String getNonpwp() {
		return nonpwp;
	}

	public void setNonpwp(String nonpwp) {
		this.nonpwp = nonpwp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBidusaha() {
		return bidusaha;
	}

	public void setBidusaha(String bidusaha) {
		this.bidusaha = bidusaha;
	}

	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

	public Date getAppdate() {
		return appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

	public String getAppchgno() {
		return appchgno;
	}

	public void setAppchgno(String appchgno) {
		this.appchgno = appchgno;
	}

	public Date getAppchgdate() {
		return appchgdate;
	}

	public void setAppchgdate(Date appchgdate) {
		this.appchgdate = appchgdate;
	}

	public String getNotaris() {
		return notaris;
	}

	public void setNotaris(String notaris) {
		this.notaris = notaris;
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

	public String getOfficefaxno() {
		return officefaxno;
	}

	public void setOfficefaxno(String officefaxno) {
		this.officefaxno = officefaxno;
	}

	public String getOfficestatus() {
		return officestatus;
	}

	public void setOfficestatus(String officestatus) {
		this.officestatus = officestatus;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	
	
	
	// Constructor
	
	public NasabahBadanUsaha() { }
	
}
