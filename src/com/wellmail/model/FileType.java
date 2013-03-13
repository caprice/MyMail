package com.wellmail.model;

import java.util.HashSet;
import java.util.Set;

public class FileType {

	//�ļ�����id
	private int filetypeid;
	
	//�ļ��������ƣ�1.�ҵ��ĵ� 2.�ҵ�ͼƬ 3.�ҵ����� 4.�ҵĶ�ý�� 5.�ҵ��������������ӣ�
	private String filetypename;
	
	//���ļ������£��ļ�����
	private int containfilecount;
	
	private double foldersize;
	
	//webfile set
	private Set<WebFile> webfiles = new HashSet<WebFile>();
	
	//user���
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