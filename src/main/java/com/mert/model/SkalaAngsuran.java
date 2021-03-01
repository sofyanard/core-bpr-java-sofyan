package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "skalaangsuran")
public class SkalaAngsuran {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@NotNull
	@Column(name = "no_rekening")
	private String NoRekening;
	
	@NotNull
	@Column(name = "bulan_ke")
	private Integer BulanKe;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "due_date")
	private Date DueDate;
	
	@NotNull
	// @DecimalMin(value = "0.0", inclusive = false)
    // @Digits(integer=14, fraction=2)
	@Column(name = "angsuran_pokok")
	private Double AngsuranPokok;
	
	@NotNull
	// @DecimalMin(value = "0.0", inclusive = false)
    // @Digits(integer=14, fraction=2)
	@Column(name = "angsuran_bunga")
	private Double AngsuranBunga;
	
	@NotNull
	// @DecimalMin(value = "0.0", inclusive = false)
    // @Digits(integer=14, fraction=2)
	@Column(name = "total_angsuran")
	private Double TotalAngsuran;
	
	@NotNull
	// @DecimalMin(value = "0.0", inclusive = false)
    // @Digits(integer=14, fraction=2)
	@Column(name = "sisa_pinjaman")
	private Double SisaPinjaman;
	
	// Getter and Setter

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNoRekening() {
		return NoRekening;
	}

	public void setNoRekening(String noRekening) {
		NoRekening = noRekening;
	}

	public Integer getBulanKe() {
		return BulanKe;
	}

	public void setBulanKe(Integer bulanKe) {
		BulanKe = bulanKe;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public Double getAngsuranPokok() {
		return AngsuranPokok;
	}

	public void setAngsuranPokok(Double angsuranPokok) {
		AngsuranPokok = angsuranPokok;
	}

	public Double getAngsuranBunga() {
		return AngsuranBunga;
	}

	public void setAngsuranBunga(Double angsuranBunga) {
		AngsuranBunga = angsuranBunga;
	}

	public Double getTotalAngsuran() {
		return TotalAngsuran;
	}

	public void setTotalAngsuran(Double totalAngsuran) {
		TotalAngsuran = totalAngsuran;
	}

	public Double getSisaPinjaman() {
		return SisaPinjaman;
	}

	public void setSisaPinjaman(Double sisaPinjaman) {
		SisaPinjaman = sisaPinjaman;
	}
	
	// Constructor
	
	public SkalaAngsuran() {
		
	}
	
	public SkalaAngsuran(
			String noRekening,
			Integer bulanKe,
			Date dueDate,
			Double angsuranPokok,
			Double angsuranBunga,
			Double totalAngsuran,
			Double sisaPinjaman) {
		
		this.setNoRekening(noRekening);
		this.setBulanKe(bulanKe);
		this.setDueDate(dueDate);
		this.setAngsuranPokok(angsuranPokok);
		this.setAngsuranBunga(angsuranBunga);
		this.setTotalAngsuran(totalAngsuran);
		this.setSisaPinjaman(sisaPinjaman);
	}

}
