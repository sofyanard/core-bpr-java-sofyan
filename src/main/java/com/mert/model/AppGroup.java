package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appgroup")
public class AppGroup {
	
	@Id
	@Column(name = "group_id")
	private String GroupId;
	
	@Column(name = "group_name")
	private String GroupName;
	
	@Column(name = "is_active")
	private String IsActive;
	
	
	
	// Getter and Setter

	public String getGroupId() {
		return GroupId;
	}

	public void setGroupId(String groupId) {
		GroupId = groupId;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String isActive) {
		IsActive = isActive;
	}
	
	
	
	// Constructor
	
	public AppGroup() {
		
	}

}
