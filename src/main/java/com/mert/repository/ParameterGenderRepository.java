package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mert.model.ParameterGender;

public interface ParameterGenderRepository extends JpaRepository<ParameterGender, String> {
	
	@Query(value = "select * from parametergender where gendercode in ('L', 'P')", nativeQuery=true)
	List<ParameterGender> findGenders();

}
