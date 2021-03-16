package com.mert.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "subposlapkeu")
public class SubPosLapKeu {
	
	@Id
	@Column(name = "subpos_id")
	private String SubPosId;
	
	@ManyToOne
	@JoinColumn(name = "pos_id", referencedColumnName = "pos_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private PosLapKeu PosLapKeu;
	
	@Column(name = "deskripsi")
	private String Deskripsi;
	
	@Column(name = "status")
	private String Status;
	
	

	public String getSubPosId() {
		return SubPosId;
	}

	public void setSubPosId(String subPosId) {
		SubPosId = subPosId;
	}

	public PosLapKeu getPosLapKeu() {
		return PosLapKeu;
	}

	public void setPosLapKeu(PosLapKeu posLapKeu) {
		PosLapKeu = posLapKeu;
	}

	public String getDeskripsi() {
		return Deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		Deskripsi = deskripsi;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
}
