package com.mert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appuser")
public class AppUser {
	
	@Id
	@Column(name = "user_id")
	private String UserId;
	
	@Column(name = "full_name")
	private String FullName;
	
	@Column(name = "password")
	private String Password;
	
	@ManyToOne
	@JoinColumn(name = "group_id", referencedColumnName = "group_id")
	private AppGroup GroupId;
	
	@ManyToOne
	@JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
	private AppUnit UnitId;
	
	@Column(name = "email")
	private String Email;
	
	@Column(name = "phone")
	private String Phone;
	
	@Column(name = "approval_limit")
	private Double ApprovalLimit;
	
	@Column(name = "rek_buku_besar")
	private String RekBukuBesar;
	
	@Column(name = "is_active")
	private String IsActive;
	
	@Column(name = "is_login")
	private String IsLogin;
	
	@Column(name = "is_revoke")
	private String IsRevoke;
	
	@Column(name = "false_pwd_count")
	private Integer FalsePwdCount;
	
	@Column(name = "created_date")
	private Date CreatedDate;
	
	@Column(name = "created_by")
	private String CreatedBy;
	
	@Column(name = "updated_date")
	private Date UpdatedDate;
	
	@Column(name = "updated_by")
	private String UpdatedBy;
	
	@Column(name = "expired_date")
	private Date ExpiredDate;
	
	@Column(name = "last_login_date")
	private Date LastLoginDate;
	
	@Column(name = "last_login_host")
	private String LastLoginHost;
	
	
	
	// Getter and Setter

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public AppGroup getGroupId() {
		return GroupId;
	}

	public void setGroupId(AppGroup groupId) {
		GroupId = groupId;
	}

	public AppUnit getUnitId() {
		return UnitId;
	}

	public void setUnitId(AppUnit unitId) {
		UnitId = unitId;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public Double getApprovalLimit() {
		return ApprovalLimit;
	}

	public void setApprovalLimit(Double approvalLimit) {
		ApprovalLimit = approvalLimit;
	}

	public String getRekBukuBesar() {
		return RekBukuBesar;
	}

	public void setRekBukuBesar(String rekBukuBesar) {
		RekBukuBesar = rekBukuBesar;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String isActive) {
		IsActive = isActive;
	}

	public String getIsLogin() {
		return IsLogin;
	}

	public void setIsLogin(String isLogin) {
		IsLogin = isLogin;
	}

	public String getIsRevoke() {
		return IsRevoke;
	}

	public void setIsRevoke(String isRevoke) {
		IsRevoke = isRevoke;
	}

	public Integer getFalsePwdCount() {
		return FalsePwdCount;
	}

	public void setFalsePwdCount(Integer falsePwdCount) {
		FalsePwdCount = falsePwdCount;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public Date getUpdatedDate() {
		return UpdatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		UpdatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	public Date getExpiredDate() {
		return ExpiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		ExpiredDate = expiredDate;
	}

	public Date getLastLoginDate() {
		return LastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		LastLoginDate = lastLoginDate;
	}

	public String getLastLoginHost() {
		return LastLoginHost;
	}

	public void setLastLoginHost(String lastLoginHost) {
		LastLoginHost = lastLoginHost;
	}
	
	
	
	// Constructor
	
	public AppUser() {
		
	}

}
