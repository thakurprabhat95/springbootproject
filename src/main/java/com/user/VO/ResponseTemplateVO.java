package com.user.VO;

import com.user.entity.User;

public class ResponseTemplateVO {
	
	private User user;
	private Audit audit;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Audit getAudit() {
		return audit;
	}
	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	public ResponseTemplateVO(User user, Audit audit) {
		this.user = user;
		this.audit = audit;
	}
	public ResponseTemplateVO() {
	}
	
	

}
