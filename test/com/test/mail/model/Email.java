package com.test.mail.model;

public class Email {

	//�ʼ�id
	private int emailid;	
	
	//������
	private String sender;		
	
	//�ռ���
	private String recipients;
	
	//�ʼ�����
	private String subject;	
	
	//�ʼ�����
	private String content;
	
	//����ʱ��
	private String senttime;
	
	//����
	private String attachement;	
	
	//�ʼ�����
	private int mailtype;	
	
	//�ʼ�messageid
	private String messageid ;
	
	//�ʼ���С
	private int msgsize;
	
	//�ʼ�״̬(0.�Ѷ� 1.δ��)
	private int unread;
	
	//�����ļ��У�1�����䣬2�ռ��䣬3�ݸ��䣬4�ѷ��ͣ�5��ɾ����6�����ʼ��䣬6����ʼ���
	private int folderid;

	public int getEmailid() {
		return emailid;
	}

	public void setEmailid(int emailid) {
		this.emailid = emailid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSenttime() {
		return senttime;
	}

	public void setSenttime(String senttime) {
		this.senttime = senttime;
	}

	public String getAttachement() {
		return attachement;
	}

	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

	public int getMailtype() {
		return mailtype;
	}

	public void setMailtype(int mailtype) {
		this.mailtype = mailtype;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public int getMsgsize() {
		return msgsize;
	}

	public void setMsgsize(int msgsize) {
		this.msgsize = msgsize;
	}

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}

	public int getFolderid() {
		return folderid;
	}

	public void setFolderid(int folderid) {
		this.folderid = folderid;
	}
	
	
}
