package com.mert.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "datatagihan")
public class DataTagihan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "no_rekening", referencedColumnName = "no_rekening")
	@NotNull
	private RekeningKredit rekeningKredit;
	
	@Column(name = "due_date")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date DueDate;
	
	@Column(name = "pokok")
	private Double Pokok;
	
	@Column(name = "bunga")
	private Double Bunga;
	
	@Column(name = "denda_pokok")
	private Double DendaPokok;
	
	@Column(name = "denda_bunga")
	private Double DendaBunga;
	
	@Column(name = "lainnya")
	private Double Lainnya;
	
	@Column(name = "paid_status")
	private String PaidStatus;
	
	@Column(name = "paid_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date PaidDate;
	
	@Column(name = "paid_amount")
	private Double PaidAmount;
	
	@Column(name = "paid_tranref")
	private UUID PaidTranRef;
	
	



	@Column(name = "hapus_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date HapusDate;
	
	@Column(name = "dpd")
	private Integer Dpd;
	
	
	
	// Getter and Setter

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public RekeningKredit getRekeningKredit() {
		return rekeningKredit;
	}

	public void setRekeningKredit(RekeningKredit rekeningKredit) {
		this.rekeningKredit = rekeningKredit;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public Double getPokok() {
		return Pokok;
	}

	public void setPokok(Double pokok) {
		Pokok = pokok;
	}

	public Double getBunga() {
		return Bunga;
	}

	public void setBunga(Double bunga) {
		Bunga = bunga;
	}

	public Double getDendaPokok() {
		return DendaPokok;
	}

	public void setDendaPokok(Double dendaPokok) {
		DendaPokok = dendaPokok;
	}

	public Double getDendaBunga() {
		return DendaBunga;
	}

	public void setDendaBunga(Double dendaBunga) {
		DendaBunga = dendaBunga;
	}

	public Double getLainnya() {
		return Lainnya;
	}

	public void setLainnya(Double lainnya) {
		Lainnya = lainnya;
	}

	public String getPaidStatus() {
		return PaidStatus;
	}

	public void setPaidStatus(String paidStatus) {
		PaidStatus = paidStatus;
	}

	public Date getPaidDate() {
		return PaidDate;
	}

	public void setPaidDate(Date paidDate) {
		PaidDate = paidDate;
	}

	public Date getHapusDate() {
		return HapusDate;
	}

	public void setHapusDate(Date hapusDate) {
		HapusDate = hapusDate;
	}

	public Integer getDpd() {
		return Dpd;
	}

	public void setDpd(Integer dpd) {
		Dpd = dpd;
	}
	
	public Double getPaidAmount() {
		return PaidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		PaidAmount = paidAmount;
	}

	public UUID getPaidTranRef() {
		return PaidTranRef;
	}

	public void setPaidTranRef(UUID paidTranRef) {
		PaidTranRef = paidTranRef;
	}



	// Constructor
	
	public DataTagihan() {
		
	}

}
