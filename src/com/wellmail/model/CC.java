package com.wellmail.model;

public class CC {

	//������id
	private int ccid;
	
	//����������
	private String ccname;
	
	//email ���
	private Email email;

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public int getCcid() {
		return ccid;
	}

	public void setCcid(int ccid) {
		this.ccid = ccid;
	}

	public String getCcname() {
		return ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
	
}
