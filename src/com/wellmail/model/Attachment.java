package com.wellmail.model;

public class Attachment {

	//����id
	private int attachmentid;
	
	//����·��
	private String attachmentpath;

	//��������
	private String attachmentname;
	
	//������С
	private double attachmentsize;
	
	//emailid���
	private Email email;
	
	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getAttachmentname() {
		return attachmentname;
	}

	public void setAttachmentname(String attachmentname) {
		this.attachmentname = attachmentname;
	}

	public double getAttachmentsize() {
		return attachmentsize;
	}

	public void setAttachmentsize(double attachmentsize) {
		this.attachmentsize = attachmentsize;
	}

	public int getAttachmentid() {
		return attachmentid;
	}

	public void setAttachmentid(int attachmentid) {
		this.attachmentid = attachmentid;
	}

	public String getAttachmentpath() {
		return attachmentpath;
	}

	public void setAttachmentpath(String attachmentpath) {
		this.attachmentpath = attachmentpath;
	}
}
