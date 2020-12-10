package com.mert.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class Transaksi4001Input {
	
	@NotNull
	private String userIdPost;
	
	@NotNull
	private String userIdKasir;
	
	@NotNull
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double nominal;
	
	

	public String getUserIdPost() {
		return userIdPost;
	}

	public void setUserIdPost(String userIdPost) {
		this.userIdPost = userIdPost;
	}

	public String getUserIdKasir() {
		return userIdKasir;
	}

	public void setUserIdKasir(String userIdKasir) {
		this.userIdKasir = userIdKasir;
	}

	public Double getNominal() {
		return nominal;
	}

	public void setNominal(Double nominal) {
		this.nominal = nominal;
	}
	
	

	public Transaksi4001Input() {
		
	}
	
	public Transaksi4001Input(String userIdPost, String userIdKasir, Double nominal) {
		super();
		this.userIdPost = userIdPost;
		this.userIdKasir = userIdKasir;
		this.nominal = nominal;
	}
	
}
