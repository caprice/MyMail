package com.wellmail.model;

public class WebDisk {

	//����id
	private int webspaceid;
	
	//�����ܿռ��С
	private double webspace;
	
	//����ʣ��ռ��С
	private double wsleft;
	
	//�����ļ�����
	private int filecount;
	
	//�û����
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getWebspaceid() {
		return webspaceid;
	}

	public void setWebspaceid(int webspaceid) {
		this.webspaceid = webspaceid;
	}

	public double getWebspace() {
		return webspace;
	}

	public void setWebspace(double webspace) {
		this.webspace = webspace;
	}

	public double getWsleft() {
		return wsleft;
	}

	public void setWsleft(double wsleft) {
		this.wsleft = wsleft;
	}

	public int getFilecount() {
		return filecount;
	}

	public void setFilecount(int filecount) {
		this.filecount = filecount;
	}
}
