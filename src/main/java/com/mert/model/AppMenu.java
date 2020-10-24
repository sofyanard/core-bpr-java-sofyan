package com.mert.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "appmenu")
public class AppMenu {
	
	@Id
	@Column(name = "menu_id")
	private String MenuId;
	
	@Column(name = "menu_name")
	private String MenuName;
	
	@Column(name = "level")
	private Integer Level;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent", referencedColumnName = "menu_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private AppMenu Parent;
	
	@Column(name = "route")
	private String Route;
	
	@Column(name = "icon")
	private String Icon;
	
	@Column(name = "param")
	private String Param;
	
	
	
	// Getter and Setter

	public String getMenuId() {
		return MenuId;
	}

	public void setMenuId(String menuId) {
		MenuId = menuId;
	}

	public String getMenuName() {
		return MenuName;
	}

	public void setMenuName(String menuName) {
		MenuName = menuName;
	}

	public Integer getLevel() {
		return Level;
	}

	public void setLevel(Integer level) {
		Level = level;
	}

	public AppMenu getParent() {
		return Parent;
	}

	public void setParent(AppMenu parent) {
		Parent = parent;
	}

	public String getRoute() {
		return Route;
	}

	public void setRoute(String route) {
		Route = route;
	}

	public String getIcon() {
		return Icon;
	}

	public void setIcon(String icon) {
		Icon = icon;
	}

	public String getParam() {
		return Param;
	}

	public void setParam(String param) {
		Param = param;
	}
	
	
	
	// Constructor
	
	public AppMenu() {
		
	}

}
