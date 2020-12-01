package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fasilitaskredit")
public class FasilitasKredit {
	
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
	
	@ManyToOne
	@JoinColumn(name = "segment", referencedColumnName = "segment_id")
	private ParameterSegment Segment;
	
	@ManyToOne
	@JoinColumn(name = "take_over", referencedColumnName = "yesno_id")
	private ParameterYesNo TakeOver;
	
	@ManyToOne
	@JoinColumn(name = "bank", referencedColumnName = "bank_id")
	private ParameterBank Bank;
	
	@ManyToOne
	@JoinColumn(name = "fas_khusus", referencedColumnName = "yesno_id")
	private ParameterYesNo FasKhusus;
	
	@ManyToOne
	@JoinColumn(name = "joint_flag", referencedColumnName = "yesno_id")
	private ParameterYesNo JointFlag;
	
	@Column(name = "cif_joint")
	private Long CifJoint;
	
	@Column(name = "nama_joint")
	private Long NamaJoint;
	
	@Column(name = "seq_joint")
	private Integer SeqJoint;
	
	@Column(name = "pinalti_bunga_persen")
	@DecimalMin(value = "0.1", inclusive = false)
	@DecimalMax(value = "100.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private Double PinaltiBungaPersen;
	
	@Column(name = "pinalti_pokok_persen")
	@DecimalMin(value = "0.1", inclusive = false)
	@DecimalMax(value = "100.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private Double PinaltiPokokPersen;
	
	@Column(name = "pinalti_lunas_persen")
	@DecimalMin(value = "0.1", inclusive = false)
	@DecimalMax(value = "100.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private Double PinaltiLunasPersen;
	
	@ManyToOne
	@JoinColumn(name = "pinalti_flag", referencedColumnName = "pinalti_id")
	private ParameterPinalti PinaltiFlag;
	
	@ManyToOne
	@JoinColumn(name = "agunan", referencedColumnName = "yesno_id")
	@NotNull
	private ParameterYesNo Agunan;
	
	@Column(name = "nilai_bank")
	@DecimalMin(value = "1.0", inclusive = false)
    @Digits(integer=14, fraction=2)
	private Double NilaiBank;
	
	@Column(name = "ltv_persen")
	@DecimalMin(value = "0.1", inclusive = false)
	@Digits(integer=7, fraction=2)
	private Double LtvPersen;
	
	@Column(name = "pledging_persen")
	@DecimalMin(value = "0.1", inclusive = false)
	@Digits(integer=7, fraction=2)
	private Double PledgingPersen;
	
	@ManyToOne
	@JoinColumn(name = "tujuan_kredit", referencedColumnName = "purpose_id")
	private ParameterPurpose TujuanKredit;
	
	@Column(name = "provisi_persen")
	@DecimalMin(value = "0.1", inclusive = false)
	@DecimalMax(value = "100.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private Double ProvisiPersen;
	
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
	

	
	// Getter & Setter

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

	public ParameterSegment getSegment() {
		return Segment;
	}

	public void setSegment(ParameterSegment segment) {
		Segment = segment;
	}

	public ParameterYesNo getTakeOver() {
		return TakeOver;
	}

	public void setTakeOver(ParameterYesNo takeOver) {
		TakeOver = takeOver;
	}

	public ParameterBank getBank() {
		return Bank;
	}

	public void setBank(ParameterBank bank) {
		Bank = bank;
	}

	public ParameterYesNo getFasKhusus() {
		return FasKhusus;
	}

	public void setFasKhusus(ParameterYesNo fasKhusus) {
		FasKhusus = fasKhusus;
	}

	public ParameterYesNo getJointFlag() {
		return JointFlag;
	}

	public void setJointFlag(ParameterYesNo jointFlag) {
		JointFlag = jointFlag;
	}

	public Long getCifJoint() {
		return CifJoint;
	}

	public void setCifJoint(Long cifJoint) {
		CifJoint = cifJoint;
	}

	public Long getNamaJoint() {
		return NamaJoint;
	}

	public void setNamaJoint(Long namaJoint) {
		NamaJoint = namaJoint;
	}

	public Integer getSeqJoint() {
		return SeqJoint;
	}

	public void setSeqJoint(Integer seqJoint) {
		SeqJoint = seqJoint;
	}

	public Double getPinaltiBungaPersen() {
		return PinaltiBungaPersen;
	}

	public void setPinaltiBungaPersen(Double pinaltiBungaPersen) {
		PinaltiBungaPersen = pinaltiBungaPersen;
	}

	public Double getPinaltiPokokPersen() {
		return PinaltiPokokPersen;
	}

	public void setPinaltiPokokPersen(Double pinaltiPokokPersen) {
		PinaltiPokokPersen = pinaltiPokokPersen;
	}

	public Double getPinaltiLunasPersen() {
		return PinaltiLunasPersen;
	}

	public void setPinaltiLunasPersen(Double pinaltiLunasPersen) {
		PinaltiLunasPersen = pinaltiLunasPersen;
	}

	public ParameterPinalti getPinaltiFlag() {
		return PinaltiFlag;
	}

	public void setPinaltiFlag(ParameterPinalti pinaltiFlag) {
		PinaltiFlag = pinaltiFlag;
	}

	public ParameterYesNo getAgunan() {
		return Agunan;
	}

	public void setAgunan(ParameterYesNo agunan) {
		Agunan = agunan;
	}

	public Double getNilaiBank() {
		return NilaiBank;
	}

	public void setNilaiBank(Double nilaiBank) {
		NilaiBank = nilaiBank;
	}

	public Double getLtvPersen() {
		return LtvPersen;
	}

	public void setLtvPersen(Double ltvPersen) {
		LtvPersen = ltvPersen;
	}

	public Double getPledgingPersen() {
		return PledgingPersen;
	}

	public void setPledgingPersen(Double pledgingPersen) {
		PledgingPersen = pledgingPersen;
	}

	public ParameterPurpose getTujuanKredit() {
		return TujuanKredit;
	}

	public void setTujuanKredit(ParameterPurpose tujuanKredit) {
		TujuanKredit = tujuanKredit;
	}

	public Double getProvisiPersen() {
		return ProvisiPersen;
	}

	public void setProvisiPersen(Double provisiPersen) {
		ProvisiPersen = provisiPersen;
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

	

}
