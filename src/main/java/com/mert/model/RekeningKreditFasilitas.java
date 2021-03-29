package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RekeningKreditFasilitas {
	
	@Id
	@NotNull
	private String NoRekening;
	
	@Column(name = "tenor")
	@NotNull
	@Min(1)
	private Integer Tenor;
	
	@Column(name = "bunga_persen")
	@NotNull
	@DecimalMin(value = "0.1", inclusive = false)
	@DecimalMax(value = "100.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private Double BungaPersen;
	
	@Column(name = "pinalti_bunga_persen")
	@DecimalMin(value = "0.1", inclusive = false)
	@DecimalMax(value = "100.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private Double PinaltiBungaPersen;
	
	@Column(name = "pinalti_pokok_persen")
	@DecimalMin(value = "0.1", inclusive = false)
	@DecimalMax(value = "100.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private Double PinaltiPokokPersen;
	
	@Column(name = "pinalti_lunas_persen")
	@DecimalMin(value = "0.1", inclusive = false)
	@DecimalMax(value = "100.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private Double PinaltiLunasPersen;
	
	@ManyToOne
	@JoinColumn(name = "pinalti_flag", referencedColumnName = "pinalti_id")
	private ParameterPinalti PinaltiFlag;
	
	

	public String getNoRekening() {
		return NoRekening;
	}

	public void setNoRekening(String noRekening) {
		NoRekening = noRekening;
	}

	public Integer getTenor() {
		return Tenor;
	}

	public void setTenor(Integer tenor) {
		Tenor = tenor;
	}

	public Double getBungaPersen() {
		return BungaPersen;
	}

	public void setBungaPersen(Double bungaPersen) {
		BungaPersen = bungaPersen;
	}

	public Double getPinaltiBungaPersen() {
		return PinaltiBungaPersen;
	}

	public void setPinaltiBungaPersen(Double pinaltiBungaPersen) {
		PinaltiBungaPersen = pinaltiBungaPersen;
	}

	public Double getPinaltiPokokPersen() {
		return PinaltiPokokPersen;
	}

	public void setPinaltiPokokPersen(Double pinaltiPokokPersen) {
		PinaltiPokokPersen = pinaltiPokokPersen;
	}

	public Double getPinaltiLunasPersen() {
		return PinaltiLunasPersen;
	}

	public void setPinaltiLunasPersen(Double pinaltiLunasPersen) {
		PinaltiLunasPersen = pinaltiLunasPersen;
	}

	public ParameterPinalti getPinaltiFlag() {
		return PinaltiFlag;
	}

	public void setPinaltiFlag(ParameterPinalti pinaltiFlag) {
		PinaltiFlag = pinaltiFlag;
	}
	
}
