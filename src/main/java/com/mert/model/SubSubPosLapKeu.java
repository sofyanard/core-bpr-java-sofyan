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
@Table(name = "subsubposlapkeu")
public class SubSubPosLapKeu {
	
	@Id
	@Column(name = "subsubpos_id")
	private String SubSubPosId;
	
	@ManyToOne
	@JoinColumn(name = "subpos_id", referencedColumnName = "subpos_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private SubPosLapKeu SubPosLapKeu;
	
	@Column(name = "deskripsi")
	private String Deskripsi;
	
	@Column(name = "status")
	private String Status;
	
	

	public String getSubSubPosId() {
		return SubSubPosId;
	}

	public void setSubSubPosId(String subSubPosId) {
		SubSubPosId = subSubPosId;
	}

	public SubPosLapKeu getSubPosLapKeu() {
		return SubPosLapKeu;
	}

	public void setSubPosLapKeu(SubPosLapKeu subPosLapKeu) {
		SubPosLapKeu = subPosLapKeu;
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
