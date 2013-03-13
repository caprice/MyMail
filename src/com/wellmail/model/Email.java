package com.wellmail.model;

import java.util.HashSet;
import java.util.Set;

public class Email {

	//�ʼ�id
	private int emailid;
	
	//��ǰ�û�
	private String username;
	
	//������
	private String sender;
	
	//�ռ���
	private String recipients;
	
	//�ʼ�����
	private String subject;
	
	//�ʼ�����
	private String content;
	
	//�ʼ�����ʱ��
	private String senttime;
	
	//�ʼ�����
	private String mailtype;
	
	//�ʼ���С
	private double msgsize;
	
	//�Ƿ��Ѷ���0���Ѷ� �� 1��δ����
	private int unread;
	
	//attachment��������
	private Set<Attachment> attachments = new HashSet<Attachment>(); 
	
	//���folderid
	private Folder folder;
	
	//cc�����˼���
	private Set<CC> ccs = new HashSet<CC>();
	
	//bcc�����˼���
	private Set<BCC> bccs = new HashSet<BCC>();
	
	//�⼮priorityid
	private Priority priority; 
	
	//mailtag����
	private MailTag mailtag;
	
	//���user
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Set<BCC> getBccs() {
		return bccs;
	}

	public void setBccs(Set<BCC> bccs) {
		this.bccs = bccs;
	}

	public Set<CC> getCcs() {
		return ccs;
	}

	public void setCcs(Set<CC> ccs) {
		this.ccs = ccs;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public int getEmailid() {
		return emailid;
	}

	public void setEmailid(int emailid) {
		this.emailid = emailid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getMailtype() {
		return mailtype;
	}

	public void setMailtype(String mailtype) {
		this.mailtype = mailtype;
	}

	public double getMsgsize() {
		return msgsize;
	}

	public void setMsgsize(double msgsize) {
		this.msgsize = msgsize;
	}

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	public MailTag getMailtag() {
		return mailtag;
	}

	public void setMailtag(MailTag mailtag) {
		this.mailtag = mailtag;
	}
	
}
