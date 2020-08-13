package com.mert.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rekening_tabungan")
public class Tabungan implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="No_Nasabah")
	private int NoNasabah;
	
	@Column(name="Nama_Nasabah")
	private String NamaNasabah;
	
	@Column(name="No_Rekg")
	private int NoRekg;
	
	@Column(name="Kode_Kelompok")
	private int Kode_Kelompok;
	
	@Column(name="Unit")
	private int Unit;

	public int getNoNasabah() {
		return NoNasabah;
	}

	public void setNoNasabah(int noNasabah) {
		NoNasabah = noNasabah;
	}

	public String getNamaNasabah() {
		return NamaNasabah;
	}

	public void setNamaNasabah(String namaNasabah) {
		NamaNasabah = namaNasabah;
	}

	public int getNoRekg() {
		return NoRekg;
	}

	public void setNoRekg(int noRekg) {
		NoRekg = noRekg;
	}

	public int getKode_Kelompok() {
		return Kode_Kelompok;
	}

	public void setKode_Kelompok(int kode_Kelompok) {
		Kode_Kelompok = kode_Kelompok;
	}

	public int getUnit() {
		return Unit;
	}

	public void setUnit(int unit) {
		Unit = unit;
	}
	
	

	
	
}
