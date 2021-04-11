package com.mert.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "subsubposlapkeuunit")
public class SubSubPosLapKeuUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "subsubpos_id", referencedColumnName = "subsubpos_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private SubSubPosLapKeu SubSubPosLapKeu;
	
	@Column(name = "unit_id")
	private String UnitId;
	
	@ManyToOne
	@JoinColumn(name = "bukubesar_id", referencedColumnName = "no_rekening")
	@NotFound(action = NotFoundAction.IGNORE)
	private RekeningBukuBesar RekeningBukuBesar;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public SubSubPosLapKeu getSubSubPosLapKeu() {
		return SubSubPosLapKeu;
	}

	public void setSubSubPosLapKeu(SubSubPosLapKeu subSubPosLapKeu) {
		SubSubPosLapKeu = subSubPosLapKeu;
	}

	public String getUnitId() {
		return UnitId;
	}

	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public RekeningBukuBesar getRekeningBukuBesar() {
		return RekeningBukuBesar;
	}

	public void setRekeningBukuBesar(RekeningBukuBesar rekeningBukuBesar) {
		RekeningBukuBesar = rekeningBukuBesar;
	}
	
}
