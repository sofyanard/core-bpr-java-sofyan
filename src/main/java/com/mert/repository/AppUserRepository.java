package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
	
	// ATTENTION! all string date format must be: YYYY-MM-DD (ex: 2020-01-01, 2020-12-31)
	
	// List Semua User di Unit Tertentu yang Sudah Terdaftar di Buku Besar
	@Query(value = "select u.* from appuser u join bukubesar b on u.rek_buku_besar = b.bukubesar_id where u.unit_id = :unitId order by u.full_name ", nativeQuery=true)
	List<AppUser> listBukuBesarUsersByUnit(@Param("unitId") String unitId);
	
	// List Semua User di Unit Tertentu yang Terdaftar di Buku Besar dan Sudah Buka Kasir di Tanggal Tertentu
	@Query(value = "select u.* from appuser u " + 
			"join bukubesar b on u.rek_buku_besar = b.bukubesar_id " + 
			"join eodunitstatus s on u.user_id = s.user_id and s.status_user = '1' " + 
			"where u.unit_id = :unitId and to_char(s.date_buka, 'YYYY-MM-DD') = :strDate " + 
			"order by u.full_name ", nativeQuery=true)
	List<AppUser> listOpenBukuBesarUsersByUnit(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	// List Semua User di Unit Tertentu yang Terdaftar di Buku Besar dan Sudah Buka Kasir dan Sudah Dilakukan Transaksi Kas Keluar di Tanggal Tertentu
		@Query(value = "select u.* from appuser u " + 
				"join bukubesar b on u.rek_buku_besar = b.bukubesar_id " + 
				"join eodunitstatus s on u.user_id = s.user_id and s.status_user = '1' " + 
				"join unitkasstatus k on s.user_id = k.user_id and to_char(s.date_buka, 'YYYY-MM-DD') = to_char(k.kaskeluar_date, 'YYYY-MM-DD') " + 
				"where u.unit_id = :unitId and to_char(s.date_buka, 'YYYY-MM-DD') = :strDate " + 
				"order by u.full_name ", nativeQuery=true)
		List<AppUser> listOpenBukuBesarKasUsersByUnit(@Param("unitId") String unitId, @Param("strDate") String strDate);

}
