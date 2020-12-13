package com.mert.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.EodUnitStatus;
import com.mert.repository.EodUnitStatusRepository;
import com.mert.model.AppUser;
import com.mert.model.AppUnit;

@Service
public class EodUnitStatusService {
	
	@Autowired
	private EodUnitStatusRepository eodUnitStatusRepository;
	
	@Autowired
	private EodUnitStatusTypeService eodUnitStatusTypeService;
	
	@Autowired
	private AppUnitService appUnitService;
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private ParameterOpenCloseService parameterOpenCloseService;
	
	@Autowired
	private ParameterYesNoService parameterYesNoService;
	
	private String strToday;
	
	public EodUnitStatusService() {
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = formatter.format(today);
		this.strToday = strToday;
	}
	
	// List Unit and All Users in a Specific Unit
	public List<EodUnitStatus> findAllByUnit(String unitId) {
		return eodUnitStatusRepository.findAllByUnitAndOpenDate(unitId, this.strToday);
	}
	
	// Find One Specific Unit
	public EodUnitStatus findUnit(String unitId) {
		return eodUnitStatusRepository.findUnitByUnitAndOpenDate(unitId, this.strToday);
	}
	
	// List All Only Users in a Specific Unit
	public List<EodUnitStatus> findUsersByUnit(String unitId) {
		return eodUnitStatusRepository.findUsersByUnitAndOpenDate(unitId, this.strToday);
	}
	
	// Find One Specific User
	public EodUnitStatus findUser(String userId) {
		return eodUnitStatusRepository.findUserByUserAndOpenDate(userId, this.strToday);
	}
	
	// List All Only Units in a Whole Bank
	public List<EodUnitStatus> findUnits() {
		return eodUnitStatusRepository.findUnitsByOpenDate(this.strToday);
	}
	
	// List All Units and Users in a Whole Bank
	public List<EodUnitStatus> findUnitsAndUsers() {
		return eodUnitStatusRepository.findAllByOpenDate(this.strToday);
	}
	
	// Check if Specific Unit is Open
	public boolean checkIfUnitIsOpen(String unitId) throws Exception {
		EodUnitStatus eodUnitStatus = this.findUnit(unitId);
		if (eodUnitStatus == null) {
			return false;
		}
		if (eodUnitStatus.getStatusUnit().getOpenCloseId().trim().equals("1")) {
			return true;
		} else {
			return false;
		}
	}
	
	// Check if Specific Unit is Close
	public boolean checkIfUnitIsClose(String unitId) throws Exception {
		EodUnitStatus eodUnitStatus = this.findUnit(unitId);
		if (eodUnitStatus == null) {
			throw new Exception("Unit belum dibuka!");
		}
		if (eodUnitStatus.getStatusUnit().getOpenCloseId().trim().equals("0")) {
			return true;
		} else {
			return false;
		}
	}
	
	// Check if Specific User is Open
	public boolean checkIfUserIsOpen(String userId) throws Exception {
		EodUnitStatus eodUnitStatus = this.findUser(userId);
		if (eodUnitStatus == null) {
			return false;
		}
		if (eodUnitStatus.getStatusUser().getOpenCloseId().trim().equals("1")) {
			return true;
		} else {
			return false;
		}
	}
	
	// Check if Specific User is Close
	public boolean checkIfUserIsClose(String userId) throws Exception {
		EodUnitStatus eodUnitStatus = this.findUser(userId);
		if (eodUnitStatus == null) {
			throw new Exception("User belum dibuka!");
		}
		if (eodUnitStatus.getStatusUser().getOpenCloseId().trim().equals("0")) {
			return true;
		} else {
			return false;
		}
	}
	
	// Check if All Only Units in a Whole Bank is Close
	public boolean checkIfAllUnitsIsClose() throws Exception {
		List<EodUnitStatus> listEodUnitStatus = this.findUnits();
		for (EodUnitStatus eodUnitStatus : listEodUnitStatus) {
			if (eodUnitStatus.getStatusUnit().getOpenCloseId().trim().equals("1")) {
				throw new Exception("Unit " + eodUnitStatus.getUnitId().getUnitName() + " masih terbuka !");
			}
		}
		return true;
	}
	
	// Check if All Only Users in a Specific Unit is Close
	public boolean checkIfAllUsersByUnitIsClose(String unitId) throws Exception {
		List<EodUnitStatus> listEodUnitStatus = this.findUsersByUnit(unitId);
		for (EodUnitStatus eodUnitStatus : listEodUnitStatus) {
			if (eodUnitStatus.getStatusUser().getOpenCloseId().trim().equals("1")) {
				AppUser appUser = appUserService.findOne(eodUnitStatus.getUserId().getUserId());
				throw new Exception("User " + appUser.getFullName() + " masih terbuka !");
			}
		}
		return true;
	}
	
	// Check if All Unis and All Users in a Whole Bank is Close
	public boolean checkIfAllUnitsAndUsersIsClose(String unitId) throws Exception {
		List<EodUnitStatus> listEodUnitStatus = this.findUnitsAndUsers();
		for (EodUnitStatus eodUnitStatus : listEodUnitStatus) {
			if (eodUnitStatus.getTypeId().getTypeId().trim().equals("2")) {
				if (eodUnitStatus.getStatusUser().getOpenCloseId().trim().equals("1")) {
					AppUser appUser = appUserService.findOne(eodUnitStatus.getUserId().getUserId());
					throw new Exception("User " + appUser.getFullName() + " masih terbuka !");
				}
				
			} else {
				if (eodUnitStatus.getStatusUnit().getOpenCloseId().trim().equals("1")) {
					throw new Exception("Unit " + eodUnitStatus.getUnitId().getUnitName() + " masih terbuka !");
				}
			}
		}
		return true;
	}
	
	// Open a Unit
	public void setUnitOpen(String unitId) throws Exception {
		EodUnitStatus eodUnitStatus = this.findUnit(unitId);
		
		if ((eodUnitStatus != null) && (this.checkIfUnitIsOpen(unitId))) {
			throw new Exception("Unit " + unitId + " sudah terbuka pada " + eodUnitStatus.getDateBuka().toString() + " !");
		}
		
		try {
			eodUnitStatus = new EodUnitStatus();
			eodUnitStatus.setTypeId(eodUnitStatusTypeService.findOne("1")); // Type Unit
			eodUnitStatus.setUnitId(appUnitService.findOne(unitId));
			eodUnitStatus.setStatusUnit(parameterOpenCloseService.findOne("1")); // Status Open
			eodUnitStatus.setDateBuka(new Date());
			eodUnitStatusRepository.save(eodUnitStatus);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	// Close a Unit (Normal Process)
	public void setUnitClose(String unitId) throws Exception {
		EodUnitStatus eodUnitStatus = this.findUnit(unitId);
		
		if (eodUnitStatus == null) {
			throw new Exception("Unit " + unitId + " hari ini belum dibuka !");
		}
		
		if ((eodUnitStatus != null) && (this.checkIfUnitIsClose(unitId))) {
			throw new Exception("Unit " + unitId + " sudah ditutup pada " + eodUnitStatus.getDateTutup().toString() + " !");
		}
		
		try {
			this.checkIfAllUsersByUnitIsClose(unitId);
		}
		catch (Exception e) {
			throw e;
		}
		
		try {
			eodUnitStatus.setStatusUnit(parameterOpenCloseService.findOne("0")); // Status Close
			eodUnitStatus.setDateTutup(new Date());
			eodUnitStatusRepository.save(eodUnitStatus);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	// Force Close a Unit (Forced Process)
	public void forceUnitClose(String unitId) throws Exception {
		EodUnitStatus eodUnitStatus = this.findUnit(unitId);
		
		if (eodUnitStatus == null) {
			throw new Exception("Unit " + unitId + " hari ini belum dibuka !");
		}
		
		try {
			this.checkIfAllUsersByUnitIsClose(unitId);
		}
		catch (Exception e) {
			throw e;
		}
		
		try {
			eodUnitStatus.setStatusUnit(parameterOpenCloseService.findOne("0")); // Status Close
			eodUnitStatus.setPaksaFlag(parameterYesNoService.findOne("1"));
			eodUnitStatus.setDateTutup(new Date());
			eodUnitStatusRepository.save(eodUnitStatus);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	// Open a User
	public void setUserOpen(String userId) throws Exception {
		EodUnitStatus eodUnitStatus = this.findUser(userId);
		AppUser appUser = appUserService.findOne(userId);
		String unitId = appUser.getUnitId().getUnitId();
		EodUnitStatus eodUnitStatusUnit = this.findUnit(unitId);
		
		if (eodUnitStatusUnit == null) {
			throw new Exception("Unit " + unitId + " hari ini belum dibuka !");
		}
		
		if ((eodUnitStatusUnit != null) && (this.checkIfUnitIsClose(unitId))) {
			throw new Exception("Unit " + unitId + " sudah ditutup pada " + eodUnitStatusUnit.getDateTutup().toString() + " !");
		}
		
		if ((eodUnitStatus != null) && (this.checkIfUserIsOpen(userId))) {
			throw new Exception("User " + appUser.getFullName() + " sudah terbuka pada " + eodUnitStatus.getDateBuka().toString() + " !");
		}
		
		try {
			eodUnitStatus = new EodUnitStatus();
			eodUnitStatus.setTypeId(eodUnitStatusTypeService.findOne("2")); // Type User
			eodUnitStatus.setUnitId(appUnitService.findOne(unitId));
			eodUnitStatus.setUserId(appUserService.findOne(userId));
			eodUnitStatus.setStatusUser(parameterOpenCloseService.findOne("1")); // Status Open
			eodUnitStatus.setDateBuka(new Date());
			eodUnitStatusRepository.save(eodUnitStatus);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	// Close a User (Normal Process)
	public void setUserClose(String userId) throws Exception {
		EodUnitStatus eodUnitStatus = this.findUser(userId);
		AppUser appUser = appUserService.findOne(userId);
		
		if (eodUnitStatus == null) {
			throw new Exception("User " + appUser.getFullName() + " hari ini belum dibuka !");
		}
		
		if ((eodUnitStatus != null) && (this.checkIfUserIsClose(userId))) {
			throw new Exception("User " + appUser.getFullName() + " sudah ditutup pada " + eodUnitStatus.getDateTutup().toString() + " !");
		}
		
		try {
			eodUnitStatus.setStatusUser(parameterOpenCloseService.findOne("0")); // Status Close
			eodUnitStatus.setDateTutup(new Date());
			eodUnitStatusRepository.save(eodUnitStatus);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	// Force Close a User (Forced Process)
	public void forceUserClose(String userId) throws Exception {
		EodUnitStatus eodUnitStatus = this.findUser(userId);
		AppUser appUser = appUserService.findOne(userId);
		
		if (eodUnitStatus == null) {
			throw new Exception("User " + appUser.getFullName() + " hari ini belum dibuka !");
		}
		
		try {
			eodUnitStatus.setStatusUser(parameterOpenCloseService.findOne("0")); // Status Close
			eodUnitStatus.setPaksaFlag(parameterYesNoService.findOne("1"));
			eodUnitStatus.setDateTutup(new Date());
			eodUnitStatusRepository.save(eodUnitStatus);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	// Get Status Detail of a Unit
	public EodUnitStatus getUnitStatusDetail(String unitId) {
		EodUnitStatus eodUnitStatus = this.findUnit(unitId);
		if ((eodUnitStatus != null) && (eodUnitStatus.getUnitId().getUnitId().trim().equals(unitId))) {
			return eodUnitStatus;
		} else {
			eodUnitStatus = new EodUnitStatus();
			eodUnitStatus.setTypeId(eodUnitStatusTypeService.findOne("1"));
			eodUnitStatus.setUnitId(appUnitService.findOne(unitId));
			eodUnitStatus.setStatusUnit(parameterOpenCloseService.findOne("0"));
			return eodUnitStatus;
		}
	}
	
	// Get Status Detail of a User
	public EodUnitStatus getUserStatusDetail(String userId) {
		EodUnitStatus eodUnitStatus = this.findUser(userId);
		if ((eodUnitStatus.getUserId() != null) && (eodUnitStatus.getUserId().getUserId().trim().equals(userId))) {
			return eodUnitStatus;
		} else {
			AppUser appUser = appUserService.findOne(userId);
			AppUnit appUnit = appUnitService.findOne(appUser.getUnitId().getUnitId());
			eodUnitStatus = new EodUnitStatus();
			eodUnitStatus.setTypeId(eodUnitStatusTypeService.findOne("2"));
			eodUnitStatus.setUnitId(appUnit);
			eodUnitStatus.setUserId(appUser);
			eodUnitStatus.setStatusUser(parameterOpenCloseService.findOne("0"));
			return eodUnitStatus;
		}
	}
	
	// List User-User yang sudah terdaftar di Buku Besar di Unit tertentu
	public List<AppUser> listAvailableUsersByUnit(String unitId) {
		return appUserService.listBukuBesarUsersByUnit(unitId);
	}
	
	// List User-User yang terdaftar di Buku Besar di Unit tertentu yang sudah Buka Kasir pada Hari Ini
	public List<AppUser> listOpenBukuBesarUsersByUnitToday(String unitId) {
		return appUserService.listOpenBukuBesarUsersByUnit(unitId, this.strToday);
	}
	
	// List User-User yang terdaftar di Buku Besar di Unit tertentu yang sudah Buka Kasir dan sudah dilakukan Transaksi Kas Keluar pada Hari Ini
	public List<AppUser> listOpenBukuBesarKasUsersByUnitToday(String unitId) {
		return appUserService.listOpenBukuBesarKasUsersByUnit(unitId, this.strToday);
	}

}
