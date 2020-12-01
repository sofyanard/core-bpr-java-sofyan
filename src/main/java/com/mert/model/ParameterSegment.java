package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametersegment")
public class ParameterSegment {
	
	@Id
	@Column(name = "segment_id")
	private String SegmentId;
	
	@Column(name = "segment_name")
	private String SegmentName;
	
	@Column(name = "biojk_category")
	private String BiojkCategory;
	
	@Column(name = "biojk_code")
	private String BiojkCode;
	
	

	public String getSegmentId() {
		return SegmentId;
	}

	public void setSegmentId(String segmentId) {
		SegmentId = segmentId;
	}

	public String getSegmentName() {
		return SegmentName;
	}

	public void setSegmentName(String segmentName) {
		SegmentName = segmentName;
	}

	public String getBiojkCategory() {
		return BiojkCategory;
	}

	public void setBiojkCategory(String biojkCategory) {
		BiojkCategory = biojkCategory;
	}

	public String getBiojkCode() {
		return BiojkCode;
	}

	public void setBiojkCode(String biojkCode) {
		BiojkCode = biojkCode;
	}
	
	
	
	public ParameterSegment() {
		
	}

}
