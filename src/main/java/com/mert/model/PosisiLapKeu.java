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
@Table(name = "posisilapkeu")
public class PosisiLapKeu {
	
	@Id
	@Column(name = "posisi_id")
	private String PosisiId;
	
	@ManyToOne
	@JoinColumn(name = "jenis_id", referencedColumnName = "jenis_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private JenisLapKeu JenisLapKeu;
	
	@Column(name = "deskripsi")
	private String Deskripsi;
	
	@Column(name = "status")
	private String Status;
	
	

	public String getPosisiId() {
		return PosisiId;
	}

	public void setPosisiId(String posisiId) {
		PosisiId = posisiId;
	}

	public JenisLapKeu getJenisLapKeu() {
		return JenisLapKeu;
	}

	public void setJenisLapKeu(JenisLapKeu jenisLapKeu) {
		JenisLapKeu = jenisLapKeu;
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
