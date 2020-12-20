package com.mert.model;

import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "rekeningkredit")
public class RekeningKredit {
	
	@Id
	@Column(name = "no_rekening")
	private String NoRekening;
	
	@Column(name = "no_fasilitas")
	@NotNull
	private String NoFasilitas;
	
	@Column(name = "no_nasabah")
	@NotNull
	private Long NoNasabah;
	
	@Column(name = "nama_nasabah")
	private String NamaNasabah;
	
	@ManyToOne
	@JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
	private AppUnit UnitId;
	
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
	
	@Column(name = "amount_angsuran")
	private Double AmountAngsuran;
	
	@Column(name = "due_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date DueDate;
	
	@Column(name = "next_due_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date NextDueDate;
	
	@Column(name = "hitung_lunas")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date HitungLunas;
	
	@Column(name = "baki_debet")
	private Double BakiDebet;
	
	@Column(name = "disburse")
	private Double Disburse;
	
	@Column(name = "disburse_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date DisburseDate;
	
	@Column(name = "available_amount")
	private Double AvailableAmount;
	
	@Column(name = "awal_maturity")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date AwalMaturity;
	
	@Column(name = "next_maturity")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date NextMaturity;
	
	@Column(name = "renew_count")
	private Integer RenewCount;
	
	@Column(name = "totd_pokok")
	private Double TotdPokok;
	
	@Column(name = "totd_bunga")
	private Double TotdBunga;
	
	@Column(name = "nilai_pinalti")
	private Double NilaiPinalti;
	
	@Column(name = "sifat_kredit")
	private String SifatKredit;
	
	@Column(name = "jenis_penggunaan")
	private String JenisPenggunaan;
	
	@Column(name = "orientasi")
	private String Orientasi;
	
	@Column(name = "jenis_kredit")
	private String JenisKredit;
	
	@Column(name = "kode_fas_khusus")
	private String KodeFasKhusus;
	
	@Column(name = "note")
	private String Note;
	
	@Column(name = "lokasi_proyek")
	private String LokasiProyek;
	
	@Column(name = "nilai_proyek")
	private Double NilaiProyek;
	
	@Column(name = "golongan_penjamin")
	private String GolonganPenjamin;
	
	@Column(name = "kode_sektor")
	private String KodeSektor;
	
	@Column(name = "kode_pk")
	private String KodePk;
	
	@Column(name = "tanggal_pk_pertama")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalPkPertama;
	
	@Column(name = "no_pk_pertama")
	private String NoPkPertama;
	
	@Column(name = "tanggal_pk_akhir")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalPkAkhir;
	
	@Column(name = "no_pk_akhir")
	private String NoPkAkhir;
	
	@Column(name = "tanggal_awal_kredit")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalAwalKredit;
	
	@Column(name = "tanggal_mulai")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalMulai;
	
	@ManyToOne
	@JoinColumn(name = "status_rekening", referencedColumnName = "status_rekening")
	private StatusRekg StatusRekening;
	
	@ManyToOne
	@JoinColumn(name = "status_kolektibilitas", referencedColumnName = "kolektibilitas_id")
	private ParameterKolektibilitas StatusKolektibilitas;
	
	@ManyToOne
	@JoinColumn(name = "restru_flag", referencedColumnName = "yesno_id")
	private ParameterYesNo RestruFlag;
	
	@Column(name = "tanggal_restru_awal")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalRestruAwal;
	
	@Column(name = "tanggal_restru_akhir")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalRestruAkhir;
	
	@Column(name = "tanggal_review")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalReview;
	
	@Column(name = "restru_ke")
	private Integer RestruKe;
	
	@Column(name = "ket_restru")
	private String KetRestru;
	
	@Column(name = "kondisi")
	private String Kondisi;
	
	@Column(name = "tanggal_kondisi")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalKondisi;
	
	@Column(name = "sebab_macet")
	private String SebabMacet;
	
	@Column(name = "tanggal_macet")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date TanggalMacet;
	
	
	
	// Getter and Setter

	public String getNoRekening() {
		return NoRekening;
	}

	public void setNoRekening(String noRekening) {
		NoRekening = noRekening;
	}

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

	public AppUnit getUnitId() {
		return UnitId;
	}

	public void setUnitId(AppUnit unitId) {
		UnitId = unitId;
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

	public Double getAmountAngsuran() {
		return AmountAngsuran;
	}

	public void setAmountAngsuran(Double amountAngsuran) {
		AmountAngsuran = amountAngsuran;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public Date getNextDueDate() {
		return NextDueDate;
	}

	public void setNextDueDate(Date nextDueDate) {
		NextDueDate = nextDueDate;
	}

	public Date getHitungLunas() {
		return HitungLunas;
	}

	public void setHitungLunas(Date hitungLunas) {
		HitungLunas = hitungLunas;
	}

	public Double getBakiDebet() {
		return BakiDebet;
	}

	public void setBakiDebet(Double bakiDebet) {
		BakiDebet = bakiDebet;
	}

	public Double getDisburse() {
		return Disburse;
	}

	public void setDisburse(Double disburse) {
		Disburse = disburse;
	}

	public Date getDisburseDate() {
		return DisburseDate;
	}

	public void setDisburseDate(Date disburseDate) {
		DisburseDate = disburseDate;
	}

	public Double getAvailableAmount() {
		return AvailableAmount;
	}

	public void setAvailableAmount(Double availableAmount) {
		AvailableAmount = availableAmount;
	}

	public Date getAwalMaturity() {
		return AwalMaturity;
	}

	public void setAwalMaturity(Date awalMaturity) {
		AwalMaturity = awalMaturity;
	}

	public Date getNextMaturity() {
		return NextMaturity;
	}

	public void setNextMaturity(Date nextMaturity) {
		NextMaturity = nextMaturity;
	}

	public Integer getRenewCount() {
		return RenewCount;
	}

	public void setRenewCount(Integer renewCount) {
		RenewCount = renewCount;
	}

	public Double getTotdPokok() {
		return TotdPokok;
	}

	public void setTotdPokok(Double totdPokok) {
		TotdPokok = totdPokok;
	}

	public Double getTotdBunga() {
		return TotdBunga;
	}

	public void setTotdBunga(Double totdBunga) {
		TotdBunga = totdBunga;
	}

	public Double getNilaiPinalti() {
		return NilaiPinalti;
	}

	public void setNilaiPinalti(Double nilaiPinalti) {
		NilaiPinalti = nilaiPinalti;
	}

	public String getSifatKredit() {
		return SifatKredit;
	}

	public void setSifatKredit(String sifatKredit) {
		SifatKredit = sifatKredit;
	}

	public String getJenisPenggunaan() {
		return JenisPenggunaan;
	}

	public void setJenisPenggunaan(String jenisPenggunaan) {
		JenisPenggunaan = jenisPenggunaan;
	}

	public String getOrientasi() {
		return Orientasi;
	}

	public void setOrientasi(String orientasi) {
		Orientasi = orientasi;
	}

	public String getJenisKredit() {
		return JenisKredit;
	}

	public void setJenisKredit(String jenisKredit) {
		JenisKredit = jenisKredit;
	}

	public String getKodeFasKhusus() {
		return KodeFasKhusus;
	}

	public void setKodeFasKhusus(String kodeFasKhusus) {
		KodeFasKhusus = kodeFasKhusus;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public String getLokasiProyek() {
		return LokasiProyek;
	}

	public void setLokasiProyek(String lokasiProyek) {
		LokasiProyek = lokasiProyek;
	}

	public Double getNilaiProyek() {
		return NilaiProyek;
	}

	public void setNilaiProyek(Double nilaiProyek) {
		NilaiProyek = nilaiProyek;
	}

	public String getGolonganPenjamin() {
		return GolonganPenjamin;
	}

	public void setGolonganPenjamin(String golonganPenjamin) {
		GolonganPenjamin = golonganPenjamin;
	}

	public String getKodeSektor() {
		return KodeSektor;
	}

	public void setKodeSektor(String kodeSektor) {
		KodeSektor = kodeSektor;
	}

	public String getKodePk() {
		return KodePk;
	}

	public void setKodePk(String kodePk) {
		KodePk = kodePk;
	}

	public Date getTanggalPkPertama() {
		return TanggalPkPertama;
	}

	public void setTanggalPkPertama(Date tanggalPkPertama) {
		TanggalPkPertama = tanggalPkPertama;
	}

	public String getNoPkPertama() {
		return NoPkPertama;
	}

	public void setNoPkPertama(String noPkPertama) {
		NoPkPertama = noPkPertama;
	}

	public Date getTanggalPkAkhir() {
		return TanggalPkAkhir;
	}

	public void setTanggalPkAkhir(Date tanggalPkAkhir) {
		TanggalPkAkhir = tanggalPkAkhir;
	}

	public String getNoPkAkhir() {
		return NoPkAkhir;
	}

	public void setNoPkAkhir(String noPkAkhir) {
		NoPkAkhir = noPkAkhir;
	}

	public Date getTanggalAwalKredit() {
		return TanggalAwalKredit;
	}

	public void setTanggalAwalKredit(Date tanggalAwalKredit) {
		TanggalAwalKredit = tanggalAwalKredit;
	}

	public Date getTanggalMulai() {
		return TanggalMulai;
	}

	public void setTanggalMulai(Date tanggalMulai) {
		TanggalMulai = tanggalMulai;
	}

	public StatusRekg getStatusRekening() {
		return StatusRekening;
	}

	public void setStatusRekening(StatusRekg statusRekening) {
		StatusRekening = statusRekening;
	}

	public ParameterKolektibilitas getStatusKolektibilitas() {
		return StatusKolektibilitas;
	}

	public void setStatusKolektibilitas(ParameterKolektibilitas statusKolektibilitas) {
		StatusKolektibilitas = statusKolektibilitas;
	}

	public ParameterYesNo getRestruFlag() {
		return RestruFlag;
	}

	public void setRestruFlag(ParameterYesNo restruFlag) {
		RestruFlag = restruFlag;
	}

	public Date getTanggalRestruAwal() {
		return TanggalRestruAwal;
	}

	public void setTanggalRestruAwal(Date tanggalRestruAwal) {
		TanggalRestruAwal = tanggalRestruAwal;
	}

	public Date getTanggalRestruAkhir() {
		return TanggalRestruAkhir;
	}

	public void setTanggalRestruAkhir(Date tanggalRestruAkhir) {
		TanggalRestruAkhir = tanggalRestruAkhir;
	}

	public Date getTanggalReview() {
		return TanggalReview;
	}

	public void setTanggalReview(Date tanggalReview) {
		TanggalReview = tanggalReview;
	}

	public Integer getRestruKe() {
		return RestruKe;
	}

	public void setRestruKe(Integer restruKe) {
		RestruKe = restruKe;
	}

	public String getKetRestru() {
		return KetRestru;
	}

	public void setKetRestru(String ketRestru) {
		KetRestru = ketRestru;
	}

	public String getKondisi() {
		return Kondisi;
	}

	public void setKondisi(String kondisi) {
		Kondisi = kondisi;
	}

	public Date getTanggalKondisi() {
		return TanggalKondisi;
	}

	public void setTanggalKondisi(Date tanggalKondisi) {
		TanggalKondisi = tanggalKondisi;
	}

	public String getSebabMacet() {
		return SebabMacet;
	}

	public void setSebabMacet(String sebabMacet) {
		SebabMacet = sebabMacet;
	}

	public Date getTanggalMacet() {
		return TanggalMacet;
	}

	public void setTanggalMacet(Date tanggalMacet) {
		TanggalMacet = tanggalMacet;
	}
	
	

}
