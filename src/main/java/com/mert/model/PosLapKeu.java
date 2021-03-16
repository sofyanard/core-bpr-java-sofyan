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
@Table(name = "poslapkeu")
public class PosLapKeu {
	
	@Id
	@Column(name = "pos_id")
	private String PosId;
	
	@ManyToOne
	@JoinColumn(name = "posisi_id", referencedColumnName = "posisi_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private PosisiLapKeu PosisiLapKeu;
	
	@Column(name = "deskripsi")
	private String Deskripsi;
	
	@Column(name = "status")
	private String Status;
	
	

	public String getPosId() {
		return PosId;
	}

	public void setPosId(String posId) {
		PosId = posId;
	}

	public PosisiLapKeu getPosisiLapKeu() {
		return PosisiLapKeu;
	}

	public void setPosisiLapKeu(PosisiLapKeu posisiLapKeu) {
		PosisiLapKeu = posisiLapKeu;
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
