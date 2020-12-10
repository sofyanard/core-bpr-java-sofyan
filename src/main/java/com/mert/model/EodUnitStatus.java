package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "eodunitstatus")
public class EodUnitStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "type_id")
	private EodUnitStatusType TypeId;
	
	@ManyToOne
	@JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
	private AppUnit UnitId;
	
	@ManyToOne
	@JoinColumn(name = "status_unit", referencedColumnName = "openclose_id")
	private ParameterOpenClose StatusUnit;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private AppUser UserId;
	
	@ManyToOne
	@JoinColumn(name = "status_user", referencedColumnName = "openclose_id")
	private ParameterOpenClose StatusUser;
	
	@Column(name = "date_buka")
	private Date DateBuka;
	
	@Column(name = "date_tutup")
	private Date DateTutup;
	
	@ManyToOne
	@JoinColumn(name = "paksa_flag", referencedColumnName = "yesno_id")
	private ParameterYesNo PaksaFlag;
	
	
	
	// Getter and Setter

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
	
	public EodUnitStatusType getTypeId() {
		return TypeId;
	}

	public void setTypeId(EodUnitStatusType typeId) {
		TypeId = typeId;
	}

	public AppUnit getUnitId() {
		return UnitId;
	}

	public void setUnitId(AppUnit unitId) {
		UnitId = unitId;
	}

	public ParameterOpenClose getStatusUnit() {
		return StatusUnit;
	}

	public void setStatusUnit(ParameterOpenClose statusUnit) {
		StatusUnit = statusUnit;
	}

	public AppUser getUserId() {
		return UserId;
	}

	public void setUserId(AppUser userId) {
		UserId = userId;
	}

	public ParameterOpenClose getStatusUser() {
		return StatusUser;
	}

	public void setStatusUser(ParameterOpenClose statusUser) {
		StatusUser = statusUser;
	}

	public Date getDateBuka() {
		return DateBuka;
	}

	public void setDateBuka(Date dateBuka) {
		DateBuka = dateBuka;
	}

	public Date getDateTutup() {
		return DateTutup;
	}

	public void setDateTutup(Date dateTutup) {
		DateTutup = dateTutup;
	}

	public ParameterYesNo getPaksaFlag() {
		return PaksaFlag;
	}

	public void setPaksaFlag(ParameterYesNo paksaFlag) {
		PaksaFlag = paksaFlag;
	}
	
	

}
