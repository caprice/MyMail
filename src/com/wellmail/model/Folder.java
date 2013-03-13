package com.wellmail.model;

import java.util.HashSet;
import java.util.Set;

public class Folder {
	
	//文件夹id
	private int folderid;
	
	//文件夹名称（1：收件箱 ，2：草稿箱 ， 3：已发送 ，4：已删除 ，5：垃圾邮件， 6：广告邮件）
	private String foldername;
	
	//文件夹所占空间大小
	private double folderspace;
	
	//文件夹中文件数量
	private int mailcount;
	
	//email set
	private Set<Email> emails = new HashSet<Email>();
	
	//user 外键
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
