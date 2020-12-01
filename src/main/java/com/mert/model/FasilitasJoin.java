package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fasilitasjoin")
public class FasilitasJoin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "join_id")
	private Integer JoinId;
	
	@Column(name = "no_fasilitas")
	@NotNull
	private String NoFasilitas;
	
	@Column(name = "join_nonasabah")
	@NotNull
	private Long JoinNonasabah;
	
	@Column(name = "join_namalengkap")
	private String JoinNamalengkap;
	
	

	public Integer getJoinId() {
		return JoinId;
	}

	public void setJoinId(Integer joinId) {
		JoinId = joinId;
	}

	public String getNoFasilitas() {
		return NoFasilitas;
	}

	public void setNoFasilitas(String noFasilitas) {
		NoFasilitas = noFasilitas;
	}

	public Long getJoinNonasabah() {
		return JoinNonasabah;
	}

	public void setJoinNonasabah(Long joinNonasabah) {
		JoinNonasabah = joinNonasabah;
	}

	public String getJoinNamalengkap() {
		return JoinNamalengkap;
	}

	public void setJoinNamalengkap(String joinNamalengkap) {
		JoinNamalengkap = joinNamalengkap;
	}
	
	

}
