package com.mert.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Transaksi1002Input {
	
	@NotNull
	private String userIdPost;
	
	@NotNull
	private String noReferensi;
	
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double biayaLain;
	
	@Size(max = 10)
	private String kodeBiaya;
	
	@Size(max = 50)
	private String note;
	
	@NotNull
	private String noRekBeban;
	
	@NotNull
	private String jenisRekBeban;
	
	

	public String getUserIdPost() {
		return userIdPost;
	}

	public void setUserIdPost(String userIdPost) {
		this.userIdPost = userIdPost;
	}

	public String getNoReferensi() {
		return noReferensi;
	}

	public void setNoReferensi(String noReferensi) {
		this.noReferensi = noReferensi;
	}

	public Double getBiayaLain() {
		return biayaLain;
	}

	public void setBiayaLain(Double biayaLain) {
		this.biayaLain = biayaLain;
	}

	public String getKodeBiaya() {
		return kodeBiaya;
	}

	public void setKodeBiaya(String kodeBiaya) {
		this.kodeBiaya = kodeBiaya;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNoRekBeban() {
		return noRekBeban;
	}

	public void setNoRekBeban(String noRekBeban) {
		this.noRekBeban = noRekBeban;
	}
	
	public String getJenisRekBeban() {
		return jenisRekBeban;
	}

	public void setJenisRekBeban(String jenisRekBeban) {
		this.jenisRekBeban = jenisRekBeban;
	}
	
	

}
