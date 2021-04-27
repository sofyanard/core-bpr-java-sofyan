package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.DcxTemplateMaster;

public interface DcxTemplateMasterRepository extends JpaRepository<DcxTemplateMaster, Integer> {
	
	@Query(value = "select * from dcx_templatemaster n where n.template_id = :templateId ", nativeQuery=true)
	DcxTemplateMaster findByTemplateId(@Param("templateId") String templateId);
	
	@Query(value = "select * from dcx_templatemaster n where n.template_group = :templateGroup order by n.id ", nativeQuery=true)
	List<DcxTemplateMaster> findByTemplateGroup(@Param("templateGroup") String templateGroup);

}
