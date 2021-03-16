package com.mert.model;

import java.util.Date;

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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "lapkeumonthly")
public class LapKeuMonthly {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@Column(name = "tanggal")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date Tanggal;
	
	@Column(name = "unit_id")
	private String UnitId;
	
	@ManyToOne
	@JoinColumn(name = "jenis_id", referencedColumnName = "jenis_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private JenisLapKeu JenisLapKeu;
	
	@ManyToOne
	@JoinColumn(name = "posisi_id", referencedColumnName = "posisi_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private PosisiLapKeu PosisiLapKeu;
	
	@ManyToOne
	@JoinColumn(name = "pos_id", referencedColumnName = "pos_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private PosLapKeu PosLapKeu;
	
	@ManyToOne
	@JoinColumn(name = "subpos_id", referencedColumnName = "subpos_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private SubPosLapKeu SubPosLapKeu;
	
	@ManyToOne
	@JoinColumn(name = "subsubpos_id", referencedColumnName = "subsubpos_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private SubSubPosLapKeu SubSubPosLapKeu;
	
	@ManyToOne
	@JoinColumn(name = "bukubesar_id", referencedColumnName = "no_rekening")
	@NotFound(action = NotFoundAction.IGNORE)
	private RekeningBukuBesar RekeningBukuBesar;
	
	@Column(name = "value")
	private Double Value;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Date getTanggal() {
		return Tanggal;
	}

	public void setTanggal(Date tanggal) {
		Tanggal = tanggal;
	}

	public String getUnitId() {
		return UnitId;
	}

	public void setUnitId(String unitId) {
		UnitId = unitId;
	}

	public JenisLapKeu getJenisLapKeu() {
		return JenisLapKeu;
	}

	public void setJenisLapKeu(JenisLapKeu jenisLapKeu) {
		JenisLapKeu = jenisLapKeu;
	}

	public PosisiLapKeu getPosisiLapKeu() {
		return PosisiLapKeu;
	}

	public void setPosisiLapKeu(PosisiLapKeu posisiLapKeu) {
		PosisiLapKeu = posisiLapKeu;
	}

	public PosLapKeu getPosLapKeu() {
		return PosLapKeu;
	}

	public void setPosLapKeu(PosLapKeu posLapKeu) {
		PosLapKeu = posLapKeu;
	}

	public SubPosLapKeu getSubPosLapKeu() {
		return SubPosLapKeu;
	}

	public void setSubPosLapKeu(SubPosLapKeu subPosLapKeu) {
		SubPosLapKeu = subPosLapKeu;
	}

	public SubSubPosLapKeu getSubSubPosLapKeu() {
		return SubSubPosLapKeu;
	}

	public void setSubSubPosLapKeu(SubSubPosLapKeu subSubPosLapKeu) {
		SubSubPosLapKeu = subSubPosLapKeu;
	}

	public RekeningBukuBesar getRekeningBukuBesar() {
		return RekeningBukuBesar;
	}

	public void setRekeningBukuBesar(RekeningBukuBesar rekeningBukuBesar) {
		RekeningBukuBesar = rekeningBukuBesar;
	}

	public Double getValue() {
		return Value;
	}

	public void setValue(Double value) {
		Value = value;
	}
	
}
