package com.mert.model;

import javax.validation.constraints.NotNull;

public class Transaksi1005Input {
	
	@NotNull
	private String userIdPost;
	
	@NotNull
	private String noRekKredit;
	
	

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
	
}
