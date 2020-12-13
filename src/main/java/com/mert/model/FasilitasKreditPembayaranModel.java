package com.mert.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "fasilitaskredit")
public class FasilitasKreditPembayaranModel {
	
	@Id
	@Column(name = "no_fasilitas")
	private String NoFasilitas;
	
	@Column(name = "pembayaran_biaya_date")
	private Date PembayaranBiayaDate;
	
	@Column(name = "pembayaran_biaya_amount")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double PembayaranBiayaAmount;
	
	@Column(name = "pembayaran_biaya_tranref")
	private UUID PembayaranBiayaTranRef;
	
	

	public String getNoFasilitas() {
		return NoFasilitas;
	}

	public void setNoFasilitas(String noFasilitas) {
		NoFasilitas = noFasilitas;
	}

	public Date getPembayaranBiayaDate() {
		return PembayaranBiayaDate;
	}

	public void setPembayaranBiayaDate(Date pembayaranBiayaDate) {
		PembayaranBiayaDate = pembayaranBiayaDate;
	}

	public Double getPembayaranBiayaAmount() {
		return PembayaranBiayaAmount;
	}

	public void setPembayaranBiayaAmount(Double pembayaranBiayaAmount) {
		PembayaranBiayaAmount = pembayaranBiayaAmount;
	}

	public UUID getPembayaranBiayaTranRef() {
		return PembayaranBiayaTranRef;
	}

	public void setPembayaranBiayaTranRef(UUID pembayaranBiayaTranRef) {
		PembayaranBiayaTranRef = pembayaranBiayaTranRef;
	}
	
	

}
