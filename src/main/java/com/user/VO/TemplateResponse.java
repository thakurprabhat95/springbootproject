package com.user.VO;

import com.user.entity.User;

public class TemplateResponse {
	
	 private long auditId;
	 private User user;
	 
	public long getAuditId() {
		return auditId;
	}
	public void setAuditId(long auditId) {
		this.auditId = auditId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TemplateResponse(long auditId, User user) {
		this.auditId = auditId;
		this.user = user;
	}
	public TemplateResponse() {
	}
	
	
	 
	 

}
