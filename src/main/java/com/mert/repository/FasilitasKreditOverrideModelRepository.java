package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.FasilitasKreditOverrideModel;

public interface FasilitasKreditOverrideModelRepository extends JpaRepository<FasilitasKreditOverrideModel, String> {
	
	@Query(value = "select * from fasilitaskredit n where n.no_ref = :noref ", nativeQuery=true)
	FasilitasKreditOverrideModel findByNoRef(@Param("noref") String noref);

}
