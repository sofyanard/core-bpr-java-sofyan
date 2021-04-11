package com.mert.model;

public class LaporanKeuanganView {
	
	private String LapKeuId;
	
	private String LapKeuName;
	
	private String BukuBesar;
	
	private Double Saldo;
	
	

	public String getLapKeuId() {
		return LapKeuId;
	}

	public void setLapKeuId(String lapKeuId) {
		LapKeuId = lapKeuId;
	}

	public String getLapKeuName() {
		return LapKeuName;
	}

	public void setLapKeuName(String lapKeuName) {
		LapKeuName = lapKeuName;
	}

	public String getBukuBesar() {
		return BukuBesar;
	}

	public void setBukuBesar(String bukuBesar) {
		BukuBesar = bukuBesar;
	}

	public Double getSaldo() {
		return Saldo;
	}

	public void setSaldo(Double saldo) {
		Saldo = saldo;
	}
	
}
