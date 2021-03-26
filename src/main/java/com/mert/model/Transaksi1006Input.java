package com.mert.model;

import javax.validation.constraints.NotNull;

public class Transaksi1006Input {
	
	@NotNull
	private String userIdPost;
	
	@NotNull
	private String noRekKredit;
	
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
	
}
