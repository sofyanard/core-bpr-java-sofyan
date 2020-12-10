package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rekeningbukubesar")
public class RekeningBukuBesar {
	
	@Id
	@Column(name = "no_rekening")
	private String NoRekening;
	
	@Column(name = "nama_rekening")
	private String NamaRekening;
	
	@Column(name = "account_type")
	private String AccountType;
	
	@Column(name = "status_rekening")
	private String StatusRekening;
	
	@Column(name = "bpr_code")
	private String BprCode;
	
	@Column(name = "unit_id")
	private String UnitId;
	
	@Column(name = "posisi_cd")
	private String PosisiCd;
	
	@Column(name = "pos_cd")
	private String PosCd;
	
	@Column(name = "sub_pos_cd")
	private String SubPosCd;
	
	@Column(name = "sub_sub_pos_cd")
	private String SubSubPosCd;
	
	@Column(name = "saldo")
	private Double Saldo;
	
	@Column(name = "ko_tran")
	private String KoTran;
	
	@Column(name = "date_tran")
	private Date DateTran;
	
	@Column(name = "amt_tran")
	private Double AmtTran;
	
	@Column(name = "rekg_lawan")
	private String RekgLawan;
	
	

	public String getNoRekening() {
		return NoRekening;
	}

	public void setNoRekening(String noRekening) {
		NoRekening = noRekening;
	}

	public String getNamaRekening() {
		return NamaRekening;
	}

	public void setNamaRekening(String namaRekening) {
		NamaRekening = namaRekening;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getStatusRekening() {
		return StatusRekening;
	}

	public void setStatusRekening(String statusRekening) {
		StatusRekening = statusRekening;
	}

	public String getBprCode() {
		return BprCode;
	}

	public void setBprCode(String bprCode) {
		BprCode = bprCode;
	}

	public String getUnitId() {
		return UnitId;
	}

	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public String getPosisiCd() {
		return PosisiCd;
	}

	public void setPosisiCd(String posisiCd) {
		PosisiCd = posisiCd;
	}

	public String getPosCd() {
		return PosCd;
	}

	public void setPosCd(String posCd) {
		PosCd = posCd;
	}

	public String getSubPosCd() {
		return SubPosCd;
	}

	public void setSubPosCd(String subPosCd) {
		SubPosCd = subPosCd;
	}

	public String getSubSubPosCd() {
		return SubSubPosCd;
	}

	public void setSubSubPosCd(String subSubPosCd) {
		SubSubPosCd = subSubPosCd;
	}

	public Double getSaldo() {
		return Saldo;
	}

	public void setSaldo(Double saldo) {
		Saldo = saldo;
	}

	public String getKoTran() {
		return KoTran;
	}

	public void setKoTran(String koTran) {
		KoTran = koTran;
	}

	public Date getDateTran() {
		return DateTran;
	}

	public void setDateTran(Date dateTran) {
		DateTran = dateTran;
	}

	public Double getAmtTran() {
		return AmtTran;
	}

	public void setAmtTran(Double amtTran) {
		AmtTran = amtTran;
	}

	public String getRekgLawan() {
		return RekgLawan;
	}

	public void setRekgLawan(String rekgLawan) {
		RekgLawan = rekgLawan;
	}
	
	

}
