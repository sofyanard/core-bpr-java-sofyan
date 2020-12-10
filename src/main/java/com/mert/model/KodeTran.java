package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kodetran")
public class KodeTran {
	
	@Id
	@Column(name = "ko_tran")
	private String KoTran;
	
	@Column(name = "deskripsi")
	private String Deskripsi;
	
	@Column(name = "post_type")
	private String PostType;
	
	@Column(name = "balance_check")
	private String BalanceCheck;
	
	@Column(name = "post_debit")
	private String PostDebit;
	
	@Column(name = "bukubesar_debit")
	private String BukuBesarDebit;
	
	@Column(name = "post_kredit")
	private String PostKredit;
	
	@Column(name = "bukubesar_kredit")
	private String BukuBesarKredit;
	
	@Column(name = "screen_route")
	private String ScreenRoute;
	
	@Column(name = "status")
	private String Status;
	
	
	
	// Getter and Setter

	public String getKoTran() {
		return KoTran;
	}

	public void setKoTran(String koTran) {
		KoTran = koTran;
	}

	public String getDeskripsi() {
		return Deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		Deskripsi = deskripsi;
	}

	public String getPostType() {
		return PostType;
	}

	public void setPostType(String postType) {
		PostType = postType;
	}

	public String getBalanceCheck() {
		return BalanceCheck;
	}

	public void setBalanceCheck(String balanceCheck) {
		BalanceCheck = balanceCheck;
	}

	public String getPostDebit() {
		return PostDebit;
	}

	public void setPostDebit(String postDebit) {
		PostDebit = postDebit;
	}

	public String getBukuBesarDebit() {
		return BukuBesarDebit;
	}

	public void setBukuBesarDebit(String bukuBesarDebit) {
		BukuBesarDebit = bukuBesarDebit;
	}

	public String getPostKredit() {
		return PostKredit;
	}

	public void setPostKredit(String postKredit) {
		PostKredit = postKredit;
	}

	public String getBukuBesarKredit() {
		return BukuBesarKredit;
	}

	public void setBukuBesarKredit(String bukuBesarKredit) {
		BukuBesarKredit = bukuBesarKredit;
	}

	public String getScreenRoute() {
		return ScreenRoute;
	}

	public void setScreenRoute(String screenRoute) {
		ScreenRoute = screenRoute;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	

}
