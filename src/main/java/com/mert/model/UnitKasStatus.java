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

@Entity
@Table(name = "unitkasstatus")
public class UnitKasStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
	private AppUnit UnitId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private AppUser UserId;
	
	@Column(name = "kaskeluar_tran_ref")
	private UUID KasKeluarTranRef;
	
	@Column(name = "kaskeluar_date")
	private Date KasKeluarDate;
	
	@Column(name = "kaskeluar_amount")
	private Double KasKeluarAmount;
	
	@Column(name = "kaspooling_trans_ref")
	private UUID KasPoolingTranRef;
	
	@Column(name = "kaspooling_date")
	private Date KasPoolingDate;
	
	@Column(name = "kaspooling_amount")
	private Double KasPoolingAmount;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public AppUnit getUnitId() {
		return UnitId;
	}

	public void setUnitId(AppUnit unitId) {
		UnitId = unitId;
	}

	public AppUser getUserId() {
		return UserId;
	}

	public void setUserId(AppUser userId) {
		UserId = userId;
	}

	public UUID getKasKeluarTranRef() {
		return KasKeluarTranRef;
	}

	public void setKasKeluarTranRef(UUID kasKeluarTranRef) {
		KasKeluarTranRef = kasKeluarTranRef;
	}

	public Date getKasKeluarDate() {
		return KasKeluarDate;
	}

	public void setKasKeluarDate(Date kasKeluarDate) {
		KasKeluarDate = kasKeluarDate;
	}

	public Double getKasKeluarAmount() {
		return KasKeluarAmount;
	}

	public void setKasKeluarAmount(Double kasKeluarAmount) {
		KasKeluarAmount = kasKeluarAmount;
	}

	public UUID getKasPoolingTranRef() {
		return KasPoolingTranRef;
	}

	public void setKasPoolingTranRef(UUID kasPoolingTranRef) {
		KasPoolingTranRef = kasPoolingTranRef;
	}

	public Date getKasPoolingDate() {
		return KasPoolingDate;
	}

	public void setKasPoolingDate(Date kasPoolingDate) {
		KasPoolingDate = kasPoolingDate;
	}

	public Double getKasPoolingAmount() {
		return KasPoolingAmount;
	}

	public void setKasPoolingAmount(Double kasPoolingAmount) {
		KasPoolingAmount = kasPoolingAmount;
	}
	
}
