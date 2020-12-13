package com.mert.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Transaksi1001Input {
	
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
	
	

}
