package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fasilitaskredit")
public class FasilitasKreditApprovalModel {
	
	@Id
	@Column(name = "no_fasilitas")
	private String NoFasilitas;
	
	@Column(name = "note_putusan")
	private String NotePutusan;
	
	@Column(name = "putusan")
	private String Putusan;
	
	@Column(name = "no_ref")
	private String NoRef;
	
	@Column(name = "reason")
	private String Reason;
	
	

	public String getNoFasilitas() {
		return NoFasilitas;
	}

	public void setNoFasilitas(String noFasilitas) {
		NoFasilitas = noFasilitas;
	}

	public String getNotePutusan() {
		return NotePutusan;
	}

	public void setNotePutusan(String notePutusan) {
		NotePutusan = notePutusan;
	}

	public String getPutusan() {
		return Putusan;
	}

	public void setPutusan(String putusan) {
		Putusan = putusan;
	}

	public String getNoRef() {
		return NoRef;
	}

	public void setNoRef(String noRef) {
		NoRef = noRef;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}
	
	

}
