package com.mert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dcx_templatemaster")
public class DcxTemplateMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@Column(name = "template_group")
	private String TemplateGroup;
	
	@Column(name = "template_id")
	private String TemplateId;
	
	@Column(name = "sheet_id")
	private String SheetId;
	
	@Column(name = "sequence_id")
	private Integer SequenceId;
	
	@Column(name = "template_desc")
	private String TemplateDesc;
	
	@Column(name = "sheet_desc")
	private String SheetDesc;
	
	@Column(name = "sequence_desc")
	private String SequenceDesc;
	
	@Column(name = "document_type")
	private String DocumentType;
	
	@Column(name = "action_type")
	private String ActionType;
	
	@Column(name = "template_filename")
	private String TemplateFilename;
	
	@Column(name = "sql_command")
	private String SqlCommand;
	
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getTemplateGroup() {
		return TemplateGroup;
	}

	public void setTemplateGroup(String templateGroup) {
		TemplateGroup = templateGroup;
	}

	public String getTemplateId() {
		return TemplateId;
	}

	public void setTemplateId(String templateId) {
		TemplateId = templateId;
	}

	public String getSheetId() {
		return SheetId;
	}

	public void setSheetId(String sheetId) {
		SheetId = sheetId;
	}

	public Integer getSequenceId() {
		return SequenceId;
	}

	public void setSequenceId(Integer sequenceId) {
		SequenceId = sequenceId;
	}

	public String getTemplateDesc() {
		return TemplateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		TemplateDesc = templateDesc;
	}

	public String getSheetDesc() {
		return SheetDesc;
	}

	public void setSheetDesc(String sheetDesc) {
		SheetDesc = sheetDesc;
	}

	public String getSequenceDesc() {
		return SequenceDesc;
	}

	public void setSequenceDesc(String sequenceDesc) {
		SequenceDesc = sequenceDesc;
	}

	public String getDocumentType() {
		return DocumentType;
	}

	public void setDocumentType(String documentType) {
		DocumentType = documentType;
	}

	public String getActionType() {
		return ActionType;
	}

	public void setActionType(String actionType) {
		ActionType = actionType;
	}

	public String getTemplateFilename() {
		return TemplateFilename;
	}

	public void setTemplateFilename(String templateFilename) {
		TemplateFilename = templateFilename;
	}

	public String getSqlCommand() {
		return SqlCommand;
	}

	public void setSqlCommand(String sqlCommand) {
		SqlCommand = sqlCommand;
	}
	
}
