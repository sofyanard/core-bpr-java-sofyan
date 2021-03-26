package com.mert.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Transaksi1008Input {
	
	@NotNull
	private String userIdPost;
	
	@NotNull
	private String noRekKredit;
	
	@NotNull
	private String noRekBeban;
	
	@NotNull
	private String jenisRekBeban;
	
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
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
