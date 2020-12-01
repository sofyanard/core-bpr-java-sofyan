package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producttype")
public class ParameterProductType {
	
	@Id
	@Column(name = "producttype_id")
	private String ProductTypeId;
	
	@Column(name = "producttype_name")
	private String ProductTypeName;
	
	

	public String getProductTypeId() {
		return ProductTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		ProductTypeId = productTypeId;
	}

	public String getProductTypeName() {
		return ProductTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		ProductTypeName = productTypeName;
	}
	
	
	
	public ParameterProductType() {
		
	}

}
