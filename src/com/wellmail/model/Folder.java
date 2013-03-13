package com.wellmail.model;

import java.util.HashSet;
import java.util.Set;

public class Folder {
	
	//�ļ���id
	private int folderid;
	
	//�ļ������ƣ�1���ռ��� ��2���ݸ��� �� 3���ѷ��� ��4����ɾ�� ��5�������ʼ��� 6������ʼ���
	private String foldername;
	
	//�ļ�����ռ�ռ��С
	private double folderspace;
	
	//�ļ������ļ�����
	private int mailcount;
	
	//email set
	private Set<Email> emails = new HashSet<Email>();
	
	//user ���
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getFolderid() {
		return folderid;
	}

	public void setFolderid(int folderid) {
		this.folderid = folderid;
	}

	public String getFoldername() {
		return foldername;
	}

	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}

	public double getFolderspace() {
		return folderspace;
	}

	public void setFolderspace(double folderspace) {
		this.folderspace = folderspace;
	}

	public int getMailcount() {
		return mailcount;
	}

	public void setMailcount(int mailcount) {
		this.mailcount = mailcount;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}
	
}
