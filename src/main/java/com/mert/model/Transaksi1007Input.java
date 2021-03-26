package com.mert.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Transaksi1007Input {
	
	@NotNull
	private String userIdPost;
	
	@NotNull
	private String noRekKredit;
	
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
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
