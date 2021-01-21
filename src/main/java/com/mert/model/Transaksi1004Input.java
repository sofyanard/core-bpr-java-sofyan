package com.mert.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Transaksi1004Input {
	
	@NotNull
	private String userIdPost;
	
	@NotNull
	private String noRekKredit;
	
	@NotNull
	private String noRekPenerima;
	
	@NotNull
	private String jenisRekPenerima;
	
	@Size(max = 50)
	private String note;
	
	

	public String getUserIdPost() {
		return userIdPost;
	}

	public void setUserIdPost(String userIdPost) {
		this.userIdPost = userIdPost;
	}

	public String getNoRekKredit() {
		return noRekKredit;
	}

	public void setNoRekKredit(String noRekKredit) {
		this.noRekKredit = noRekKredit;
	}

	public String getNoRekPenerima() {
		return noRekPenerima;
	}

	public void setNoRekPenerima(String noRekPenerima) {
		this.noRekPenerima = noRekPenerima;
	}

	public String getJenisRekPenerima() {
		return jenisRekPenerima;
	}

	public void setJenisRekPenerima(String jenisRekPenerima) {
		this.jenisRekPenerima = jenisRekPenerima;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
