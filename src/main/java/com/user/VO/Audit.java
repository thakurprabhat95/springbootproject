package com.user.VO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Audit {
	

	private Long auditId;
	
	private String name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private String startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private String updateDate;
	
	private String audit_process;

	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getAudit_process() {
		return audit_process;
	}

	public void setAudit_process(String audit_process) {
		this.audit_process = audit_process;
	}

	public Audit(Long auditId, String name, String startDate, String updateDate, String audit_process) {
		this.auditId = auditId;
		this.name = name;
		this.startDate = startDate;
		this.updateDate = updateDate;
		this.audit_process = audit_process;
	}

	public Audit() {
	}

	
	

}
