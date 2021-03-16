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
@Table(name = "poslapkeuunit")
public class PosLapKeuUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "pos_id", referencedColumnName = "pos_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private PosLapKeu PosLapKeu;
	
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

	public PosLapKeu getPosLapKeu() {
		return PosLapKeu;
	}

	public void setPosLapKeu(PosLapKeu posLapKeu) {
		PosLapKeu = posLapKeu;
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
