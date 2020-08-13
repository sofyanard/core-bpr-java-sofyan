package com.mert.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rekening_tabungan")
public class PemeliharaanTabungan implements Serializable{
	
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
	
	@Column(name="Status_rekening")
	private int Status_rekening;
	
	@Column(name="Tanggal_Blokir")
	private Date Tanggal_Blokir;
	
	@Column(name="Nominal_Blokir")
	private int Nominal_Blokir;

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

	public int getStatus_rekening() {
		return Status_rekening;
	}

	public void setStatus_rekening(int status_rekening) {
		Status_rekening = status_rekening;
	}

	public Date getTanggal_Blokir() {
		return Tanggal_Blokir;
	}

	public void setTanggal_Blokir(Date tanggal_Blokir) {
		Tanggal_Blokir = tanggal_Blokir;
	}

	public int getNominal_Blokir() {
		return Nominal_Blokir;
	}

	public void setNominal_Blokir(int nominal_Blokir) {
		Nominal_Blokir = nominal_Blokir;
	}
	
	

}
