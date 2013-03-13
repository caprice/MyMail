package com.wellmail.model;

import java.util.HashSet;
import java.util.Set;

public class FileType {

	//文件类型id
	private int filetypeid;
	
	//文件类型名称（1.我的文档 2.我的图片 3.我的音乐 4.我的多媒体 5.我的软件）（可添加）
	private String filetypename;
	
	//该文件类型下，文件数量
	private int containfilecount;
	
	private double foldersize;
	
	//webfile set
	private Set<WebFile> webfiles = new HashSet<WebFile>();
	
	//user外键
	private Users user;

	public double getFoldersize() {
		return foldersize;
	}

	public void setFoldersize(double foldersize) {
		this.foldersize = foldersize;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Set<WebFile> getWebfiles() {
		return webfiles;
	}

	public void setWebfiles(Set<WebFile> webfiles) {
		this.webfiles = webfiles;
	}

	public int getFiletypeid() {
		return filetypeid;
	}

	public void setFiletypeid(int filetypeid) {
		this.filetypeid = filetypeid;
	}

	public String getFiletypename() {
		return filetypename;
	}

	public void setFiletypename(String filetypename) {
		this.filetypename = filetypename;
	}

	public int getContainfilecount() {
		return containfilecount;
	}

	public void setContainfilecount(int containfilecount) {
		this.containfilecount = containfilecount;
	}
	
}
