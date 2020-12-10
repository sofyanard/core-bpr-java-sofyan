package com.mert.model;

import javax.validation.constraints.NotNull;

public class Transaksi4002Input {
	
	@NotNull
	private String userIdPost;
	
	@NotNull
	private String unitId;
	
	

	public String getUserIdPost() {
		return userIdPost;
	}

	public void setUserIdPost(String userIdPost) {
		this.userIdPost = userIdPost;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	

}
