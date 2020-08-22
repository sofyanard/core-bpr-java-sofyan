package com.mert.model;

import java.io.Serializable;

public class ParameterSandiBIOJKID implements Serializable {
	
	private String kategoricode;
	
	private String sandicode;
	
	

	public String getKategoricode() {
		return kategoricode;
	}

	public void setKategoricode(String kategoricode) {
		this.kategoricode = kategoricode;
	}

	public String getSandicode() {
		return sandicode;
	}

	public void setSandicode(String sandicode) {
		this.sandicode = sandicode;
	}
	
	

	public ParameterSandiBIOJKID() { }
	
	public ParameterSandiBIOJKID(String kategoricode, String sandicode) {
		super();
		this.kategoricode = kategoricode;
		this.sandicode = sandicode;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kategoricode == null) ? 0 : kategoricode.hashCode());
		result = prime * result + ((sandicode == null) ? 0 : sandicode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParameterSandiBIOJKID other = (ParameterSandiBIOJKID) obj;
		if (kategoricode == null) {
			if (other.kategoricode != null)
				return false;
		} else if (!kategoricode.equals(other.kategoricode))
			return false;
		if (sandicode == null) {
			if (other.sandicode != null)
				return false;
		} else if (!sandicode.equals(other.sandicode))
			return false;
		return true;
	}
	
}
