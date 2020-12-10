package com.mert.model;

public class UserBukuBesarKasStatusViewModel {
	
	private AppUser AppUser;
	private BukuBesar BukuBesar;
	private RekeningBukuBesar RekeningBukuBesar;
	private EodUnitStatus EodUnitStatus;
	private UnitKasStatus UnitKasStatus;
	
	public AppUser getAppUser() {
		return AppUser;
	}
	public void setAppUser(AppUser appUser) {
		AppUser = appUser;
	}
	public BukuBesar getBukuBesar() {
		return BukuBesar;
	}
	public void setBukuBesar(BukuBesar bukuBesar) {
		BukuBesar = bukuBesar;
	}
	public RekeningBukuBesar getRekeningBukuBesar() {
		return RekeningBukuBesar;
	}
	public void setRekeningBukuBesar(RekeningBukuBesar rekeningBukuBesar) {
		RekeningBukuBesar = rekeningBukuBesar;
	}
	public EodUnitStatus getEodUnitStatus() {
		return EodUnitStatus;
	}
	public void setEodUnitStatus(EodUnitStatus eodUnitStatus) {
		EodUnitStatus = eodUnitStatus;
	}
	public UnitKasStatus getUnitKasStatus() {
		return UnitKasStatus;
	}
	public void setUnitKasStatus(UnitKasStatus unitKasStatus) {
		UnitKasStatus = unitKasStatus;
	}
	
}
