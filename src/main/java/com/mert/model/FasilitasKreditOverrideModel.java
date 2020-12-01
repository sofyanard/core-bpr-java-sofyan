package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fasilitaskredit")
public class FasilitasKreditOverrideModel {
	
	@Id
	@Column(name = "no_fasilitas")
	private String NoFasilitas;
	
	@Column(name = "no_nasabah")
	@NotNull
	private Long NoNasabah;
	
	@Column(name = "nama_nasabah")
	private String NamaNasabah;
	
	@ManyToOne
	@JoinColumn(name = "produk", referencedColumnName = "code")
	@NotNull
	private ParameterProduk Produk;
	
	@ManyToOne
	@JoinColumn(name = "valuta", referencedColumnName = "code")
	@NotNull
	private ParameterKodeValuta Valuta;
	
	@Column(name = "plafond")
	@NotNull
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double Plafond;
	
	@Column(name = "kurs")
	@NotNull
	@DecimalMin(value = "0.1", inclusive = false)
	@Digits(integer=7, fraction=2)
	private Double Kurs;
	
	@Column(name = "eqv_plafond")
	@NotNull
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double EqvPlafond;
	
	@Column(name = "tenor")
	@NotNull
	@Min(1)
	private Integer Tenor;
	
	@Column(name = "bunga_persen")
	@NotNull
	@DecimalMin(value = "0.1", inclusive = false)
	@DecimalMax(value = "100.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private Double BungaPersen;
	
	@ManyToOne
	@JoinColumn(name = "hitung_bunga", referencedColumnName = "code")
	@NotNull
	private ParameterHitungBunga HitungBunga;
	
	@Column(name = "provisi_amount")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double ProvisiAmount;
	
	@Column(name = "amt_admin")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double AmtAdmin;
	
	@Column(name = "amt_notaris")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double AmtNotaris;
	
	@Column(name = "amt_asuransi")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double AmtAsuransi;
	
	@Column(name = "amt_appraisal")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double AmtAppraisal;
	
	@Column(name = "amt_materai")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double AmtMaterai;
	
	@Column(name = "note_putusan")
	@Size(min=1, max=50)
	private String NotePutusan;
	
	@ManyToOne
	@JoinColumn(name = "putusan", referencedColumnName = "putusan_id")
	private ParameterPutusan Putusan;
	
	@Column(name = "reason")
	@Size(min=1, max=50)
	private String Reason;
	
	@Column(name = "no_ref")
	private String NoRef;
	
	

	public String getNoFasilitas() {
		return NoFasilitas;
	}

	public void setNoFasilitas(String noFasilitas) {
		NoFasilitas = noFasilitas;
	}

	public Long getNoNasabah() {
		return NoNasabah;
	}

	public void setNoNasabah(Long noNasabah) {
		NoNasabah = noNasabah;
	}

	public String getNamaNasabah() {
		return NamaNasabah;
	}

	public void setNamaNasabah(String namaNasabah) {
		NamaNasabah = namaNasabah;
	}

	public ParameterProduk getProduk() {
		return Produk;
	}

	public void setProduk(ParameterProduk produk) {
		Produk = produk;
	}

	public ParameterKodeValuta getValuta() {
		return Valuta;
	}

	public void setValuta(ParameterKodeValuta valuta) {
		Valuta = valuta;
	}

	public Double getPlafond() {
		return Plafond;
	}

	public void setPlafond(Double plafond) {
		Plafond = plafond;
	}

	public Double getKurs() {
		return Kurs;
	}

	public void setKurs(Double kurs) {
		Kurs = kurs;
	}

	public Double getEqvPlafond() {
		return EqvPlafond;
	}

	public void setEqvPlafond(Double eqvPlafond) {
		EqvPlafond = eqvPlafond;
	}

	public Integer getTenor() {
		return Tenor;
	}

	public void setTenor(Integer tenor) {
		Tenor = tenor;
	}

	public Double getBungaPersen() {
		return BungaPersen;
	}

	public void setBungaPersen(Double bungaPersen) {
		BungaPersen = bungaPersen;
	}

	public ParameterHitungBunga getHitungBunga() {
		return HitungBunga;
	}

	public void setHitungBunga(ParameterHitungBunga hitungBunga) {
		HitungBunga = hitungBunga;
	}

	public Double getProvisiAmount() {
		return ProvisiAmount;
	}
	
	public void setProvisiAmount(Double provisiAmount) {
		ProvisiAmount = provisiAmount;
	}

	public Double getAmtAdmin() {
		return AmtAdmin;
	}

	public void setAmtAdmin(Double amtAdmin) {
		AmtAdmin = amtAdmin;
	}

	public Double getAmtNotaris() {
		return AmtNotaris;
	}

	public void setAmtNotaris(Double amtNotaris) {
		AmtNotaris = amtNotaris;
	}

	public Double getAmtAsuransi() {
		return AmtAsuransi;
	}

	public void setAmtAsuransi(Double amtAsuransi) {
		AmtAsuransi = amtAsuransi;
	}

	public Double getAmtAppraisal() {
		return AmtAppraisal;
	}

	public void setAmtAppraisal(Double amtAppraisal) {
		AmtAppraisal = amtAppraisal;
	}

	public Double getAmtMaterai() {
		return AmtMaterai;
	}

	public void setAmtMaterai(Double amtMaterai) {
		AmtMaterai = amtMaterai;
	}

	public String getNotePutusan() {
		return NotePutusan;
	}

	public void setNotePutusan(String notePutusan) {
		NotePutusan = notePutusan;
	}

	public ParameterPutusan getPutusan() {
		return Putusan;
	}

	public void setPutusan(ParameterPutusan putusan) {
		Putusan = putusan;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getNoRef() {
		return NoRef;
	}

	public void setNoRef(String noRef) {
		NoRef = noRef;
	}
	
	

}
