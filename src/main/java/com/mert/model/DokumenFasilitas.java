package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dokfasilitaskredit")
public class DokumenFasilitas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@Column(name = "no_fasilitas")
	@NotNull
	private String NoFasilitas;
	
	@ManyToOne
	@JoinColumn(name = "doknasabah_id", referencedColumnName = "id")
	@NotNull
	private NasabahDokumen NasabahDokumen;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNoFasilitas() {
		return NoFasilitas;
	}

	public void setNoFasilitas(String noFasilitas) {
		NoFasilitas = noFasilitas;
	}

	public NasabahDokumen getNasabahDokumen() {
		return NasabahDokumen;
	}

	public void setNasabahDokumen(NasabahDokumen nasabahDokumen) {
		NasabahDokumen = nasabahDokumen;
	}
	
	

}
