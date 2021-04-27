package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.DcxTemplateMaster;
import com.mert.repository.DcxTemplateMasterRepository;

@Service
public class DcxTemplateMasterService {
	
	@Autowired
	private DcxTemplateMasterRepository dcxTemplateMasterRepository;
	
	public DcxTemplateMaster findByTemplateId(String templateId) {
		return dcxTemplateMasterRepository.findByTemplateId(templateId);
	}
	
	public List<DcxTemplateMaster> findByTemplateGroup(String templateGroup) {
		return dcxTemplateMasterRepository.findByTemplateGroup(templateGroup);
	}

}
