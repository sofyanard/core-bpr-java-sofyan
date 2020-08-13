package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "datanasabah")
public class NasabahBasic {
	
	@Id
	@Column(name = "nonasabah")
	private Long nonasabah;
	
	@Column(name = "tipenasabah")
	private String tipenasabah;
	
	@Column(name = "namalengkap")
	private String namalengkap;
	
	@Column(name = "bodestablish")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bodestablish;
	
	@Column(name = "homeadd")
	private String homeadd;
	
	@Column(name = "homekota")
	private String homekota;
	
	@Column(name = "officeadd")
	private String officeadd;
	
	@Column(name = "officecity")
	private String officecity;
	
	@Column(name = "noid")
	private String noid;
	
	@Column(name = "appno")
	private String appno;
	
	@Column(name = "nonpwp")
	private String nonpwp;
	
	

	public Long getNonasabah() {
		return nonasabah;
	}

	public String getTipenasabah() {
		return tipenasabah;
	}

	public String getNamalengkap() {
		return namalengkap;
	}
	
	public Date getBodestablish() {
		return bodestablish;
	}

	public String getHomeadd() {
		return homeadd;
	}

	public String getHomekota() {
		return homekota;
	}

	public String getOfficeadd() {
		return officeadd;
	}

	public String getOfficecity() {
		return officecity;
	}

	public String getNoid() {
		return noid;
	}

	public String getAppno() {
		return appno;
	}

	public String getNonpwp() {
		return nonpwp;
	}
	
	

	public NasabahBasic() { }

}
