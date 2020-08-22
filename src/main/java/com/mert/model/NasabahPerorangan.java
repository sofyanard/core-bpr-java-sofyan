package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "datanasabah")
public class NasabahPerorangan {

	// Table Columns
	
	@Id
	@Column(name = "nonasabah")
	private Long nonasabah;
	
	@Column(name = "namaid")
	private String namaid;
	
	@Column(name = "namalengkap")
	private String namalengkap;
	
	@ManyToOne
	@JoinColumn(name = "gender", referencedColumnName = "gendercode")
	private ParameterGender gender;
	
	@Column(name = "placebirth")
	private String placebirth;
	
	@Column(name = "bodestablish")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bodestablish;
	
	@Column(name = "homeadd")
	private String homeadd;
	
	@Column(name = "homeposcode")
	private String homeposcode;
	
	@Column(name = "homeprovinsi")
	private String homeprovinsi;
	
	@Column(name = "homekota")
	private String homekota;
	
	@Column(name = "homekec")
	private String homekec;
	
	@Column(name = "homekel")
	private String homekel;
	
	@ManyToOne
	@JoinColumn(name = "homestatus", referencedColumnName = "homestatuscode")
	private ParameterHomeStatus homestatus;
	
	@Column(name = "homephone")
	private String homephone;
	
	@Column(name = "nohp")
	private String nohp;
	
	@Column(name = "namaibu")
	private String namaibu;
	
	@Column(name = "citizen")
	private String citizen;
	
	@ManyToOne
	@JoinColumn(name = "marital", referencedColumnName = "maritalcode")
	private ParameterMarital marital;
	
	@Column(name = "pendidikan")
	private String pendidikan;
	
	@ManyToOne
	@JoinColumn(name = "jenisid", referencedColumnName = "jenisidcode")
	private ParameterJenisID jenisid;
	
	@Column(name = "noid")
	private String noid;
	
	@Column(name = "nonpwp")
	private String nonpwp;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "tanggungan")
	private Integer tanggungan;
	
	@Column(name = "kodeharta")
	private String kodeharta;
	
	
	
	// Getter and Setter

	public Long getNonasabah() {
		return nonasabah;
	}

	public void setNonasabah(Long nonasabah) {
		this.nonasabah = nonasabah;
	}

	public String getNamaid() {
		return namaid;
	}

	public void setNamaid(String namaid) {
		this.namaid = namaid;
	}

	public String getNamalengkap() {
		return namalengkap;
	}

	public void setNamalengkap(String namalengkap) {
		this.namalengkap = namalengkap;
	}

	public ParameterGender getGender() {
		return gender;
	}

	public void setGender(ParameterGender gender) {
		this.gender = gender;
	}

	public String getPlacebirth() {
		return placebirth;
	}

	public void setPlacebirth(String placebirth) {
		this.placebirth = placebirth;
	}

	public Date getBodestablish() {
		return bodestablish;
	}

	public void setBodestablish(Date bodestablish) {
		this.bodestablish = bodestablish;
	}

	public String getHomeadd() {
		return homeadd;
	}

	public void setHomeadd(String homeadd) {
		this.homeadd = homeadd;
	}

	public String getHomeposcode() {
		return homeposcode;
	}

	public void setHomeposcode(String homeposcode) {
		this.homeposcode = homeposcode;
	}

	public String getHomeprovinsi() {
		return homeprovinsi;
	}

	public void setHomeprovinsi(String homeprovinsi) {
		this.homeprovinsi = homeprovinsi;
	}

	public String getHomekota() {
		return homekota;
	}

	public void setHomekota(String homekota) {
		this.homekota = homekota;
	}

	public String getHomekec() {
		return homekec;
	}

	public void setHomekec(String homekec) {
		this.homekec = homekec;
	}

	public String getHomekel() {
		return homekel;
	}

	public void setHomekel(String homekel) {
		this.homekel = homekel;
	}

	public ParameterHomeStatus getHomestatus() {
		return homestatus;
	}

	public void setHomestatus(ParameterHomeStatus homestatus) {
		this.homestatus = homestatus;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getNohp() {
		return nohp;
	}

	public void setNohp(String nohp) {
		this.nohp = nohp;
	}

	public String getNamaibu() {
		return namaibu;
	}

	public void setNamaibu(String namaibu) {
		this.namaibu = namaibu;
	}

	public String getCitizen() {
		return citizen;
	}

	public void setCitizen(String citizen) {
		this.citizen = citizen;
	}

	public ParameterMarital getMarital() {
		return marital;
	}

	public void setMarital(ParameterMarital marital) {
		this.marital = marital;
	}

	public String getPendidikan() {
		return pendidikan;
	}

	public void setPendidikan(String pendidikan) {
		this.pendidikan = pendidikan;
	}

	public ParameterJenisID getJenisid() {
		return jenisid;
	}

	public void setJenisid(ParameterJenisID jenisid) {
		this.jenisid = jenisid;
	}

	public String getNoid() {
		return noid;
	}

	public void setNoid(String noid) {
		this.noid = noid;
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

	public Integer getTanggungan() {
		return tanggungan;
	}

	public void setTanggungan(Integer tanggungan) {
		this.tanggungan = tanggungan;
	}

	public String getKodeharta() {
		return kodeharta;
	}

	public void setKodeharta(String kodeharta) {
		this.kodeharta = kodeharta;
	}
	
	
	
	// Constructor
	
	public NasabahPerorangan() { };

}
