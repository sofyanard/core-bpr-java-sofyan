package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

}
