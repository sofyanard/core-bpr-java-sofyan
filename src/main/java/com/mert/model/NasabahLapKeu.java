package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "lapkeunasabah")
public class NasabahLapKeu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nonasabah")
	private Long nonasabah;
	
	@Column(name = "periode")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date periode;
	
	@Column(name = "kas")
	private Double kas;
	
	@Column(name = "piutang")
	private Double piutang;
	
	@Column(name = "investasi")
	private Double investasi;
	
	@Column(name = "asetlancarlain")
	private Double asetlancarlain;
	
	@Column(name = "asettidaklancar")
	private Double asettidaklancar;
	
	@Column(name = "pinjamanpendek")
	private Double pinjamanpendek;
	
	@Column(name = "utangusahapendek")
	private Double utangusahapendek;
	
	@Column(name = "liabpendeklain")
	private Double liabpendeklain;
	
	@Column(name = "pinjamanpanjang")
	private Double pinjamanpanjang;
	
	@Column(name = "utangusahapanjang")
	private Double utangusahapanjang;
	
	@Column(name = "liabpanjanglain")
	private Double liabpanjanglain;
	
	@Column(name = "modal")
	private Double modal;
	
	@Column(name = "pendapatanopr")
	private Double pendapatanopr;
	
	@Column(name = "bebanopr")
	private Double bebanopr;
	
	@Column(name = "pendapatannonopr")
	private Double pendapatannonopr;
	
	@Column(name = "bebannonopr")
	private Double bebannonopr;
	
	@Column(name = "lababruto")
	private Double lababruto;
	
	@Column(name = "labasebelumpajak")
	private Double labasebelumpajak;
	
	@Column(name = "labatahunberjalan")
	private Double labatahunberjalan;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getNonasabah() {
		return nonasabah;
	}

	public void setNonasabah(Long nonasabah) {
		this.nonasabah = nonasabah;
	}

	public Date getPeriode() {
		return periode;
	}

	public void setPeriode(Date periode) {
		this.periode = periode;
	}

	public Double getKas() {
		return kas;
	}

	public void setKas(Double kas) {
		this.kas = kas;
	}

	public Double getPiutang() {
		return piutang;
	}

	public void setPiutang(Double piutang) {
		this.piutang = piutang;
	}

	public Double getInvestasi() {
		return investasi;
	}

	public void setInvestasi(Double investasi) {
		this.investasi = investasi;
	}

	public Double getAsetlancarlain() {
		return asetlancarlain;
	}

	public void setAsetlancarlain(Double asetlancarlain) {
		this.asetlancarlain = asetlancarlain;
	}

	public Double getAsettidaklancar() {
		return asettidaklancar;
	}

	public void setAsettidaklancar(Double asettidaklancar) {
		this.asettidaklancar = asettidaklancar;
	}

	public Double getPinjamanpendek() {
		return pinjamanpendek;
	}

	public void setPinjamanpendek(Double pinjamanpendek) {
		this.pinjamanpendek = pinjamanpendek;
	}

	public Double getUtangusahapendek() {
		return utangusahapendek;
	}

	public void setUtangusahapendek(Double utangusahapendek) {
		this.utangusahapendek = utangusahapendek;
	}

	public Double getLiabpendeklain() {
		return liabpendeklain;
	}

	public void setLiabpendeklain(Double liabpendeklain) {
		this.liabpendeklain = liabpendeklain;
	}

	public Double getPinjamanpanjang() {
		return pinjamanpanjang;
	}

	public void setPinjamanpanjang(Double pinjamanpanjang) {
		this.pinjamanpanjang = pinjamanpanjang;
	}

	public Double getUtangusahapanjang() {
		return utangusahapanjang;
	}

	public void setUtangusahapanjang(Double utangusahapanjang) {
		this.utangusahapanjang = utangusahapanjang;
	}

	public Double getLiabpanjanglain() {
		return liabpanjanglain;
	}

	public void setLiabpanjanglain(Double liabpanjanglain) {
		this.liabpanjanglain = liabpanjanglain;
	}

	public Double getModal() {
		return modal;
	}

	public void setModal(Double modal) {
		this.modal = modal;
	}

	public Double getPendapatanopr() {
		return pendapatanopr;
	}

	public void setPendapatanopr(Double pendapatanopr) {
		this.pendapatanopr = pendapatanopr;
	}

	public Double getBebanopr() {
		return bebanopr;
	}

	public void setBebanopr(Double bebanopr) {
		this.bebanopr = bebanopr;
	}

	public Double getPendapatannonopr() {
		return pendapatannonopr;
	}

	public void setPendapatannonopr(Double pendapatannonopr) {
		this.pendapatannonopr = pendapatannonopr;
	}

	public Double getBebannonopr() {
		return bebannonopr;
	}

	public void setBebannonopr(Double bebannonopr) {
		this.bebannonopr = bebannonopr;
	}

	public Double getLababruto() {
		return lababruto;
	}

	public void setLababruto(Double lababruto) {
		this.lababruto = lababruto;
	}

	public Double getLabasebelumpajak() {
		return labasebelumpajak;
	}

	public void setLabasebelumpajak(Double labasebelumpajak) {
		this.labasebelumpajak = labasebelumpajak;
	}

	public Double getLabatahunberjalan() {
		return labatahunberjalan;
	}

	public void setLabatahunberjalan(Double labatahunberjalan) {
		this.labatahunberjalan = labatahunberjalan;
	}
	
	
	
	public NasabahLapKeu() { }

}
