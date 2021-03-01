package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jenisagunanbase")
public class ParameterJenisAgunanBase {
	
	@Id
	@Column(name = "base_id")
	private String BaseId;
	
	@Column(name = "base_name")
	private String BaseName;
	
	@Column(name = "table_name")
	private String TableName;
	
	@Column(name = "column_name")
	private String ColumnName;
	
	@Column(name = "table_link")
	private String TableLink;

	// Getter and Setter
	
	public String getBaseId() {
		return BaseId;
	}

	public void setBaseId(String baseId) {
		BaseId = baseId;
	}

	public String getBaseName() {
		return BaseName;
	}

	public void setBaseName(String baseName) {
		BaseName = baseName;
	}

	public String getTableName() {
		return TableName;
	}

	public void setTableName(String tableName) {
		TableName = tableName;
	}

	public String getColumnName() {
		return ColumnName;
	}

	public void setColumnName(String columnName) {
		ColumnName = columnName;
	}

	public String getTableLink() {
		return TableLink;
	}

	public void setTableLink(String tableLink) {
		TableLink = tableLink;
	}
	
	// Constructor
	
	public ParameterJenisAgunanBase() {
		
	}

}
