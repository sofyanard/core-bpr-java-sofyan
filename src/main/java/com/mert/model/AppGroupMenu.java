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

@Entity
@Table(name = "appgroupmenu")
public class AppGroupMenu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "group_id", referencedColumnName = "group_id")
	private AppGroup GroupId;
	
	@ManyToOne
	@JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
	private AppMenu MenuId;
	
	
	
	// Getter and Setter

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public AppGroup getGroupId() {
		return GroupId;
	}

	public void setGroupId(AppGroup groupId) {
		GroupId = groupId;
	}

	public AppMenu getMenuId() {
		return MenuId;
	}

	public void setMenuId(AppMenu menuId) {
		MenuId = menuId;
	}
	
	
	
	// Constructor
	
	public AppGroupMenu() {
		
	}

}
