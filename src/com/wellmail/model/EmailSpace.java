package com.wellmail.model;

public class EmailSpace {

	//��������id
	private int esid;
	
	//����������
	private double espace;
	
	//����ʣ��ռ�����
	private double spaceleft;
	
	//users���
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getEsid() {
		return esid;
	}

	public void setEsid(int esid) {
		this.esid = esid;
	}

	public double getEspace() {
		return espace;
	}

	public void setEspace(double espace) {
		this.espace = espace;
	}

	public double getSpaceleft() {
		return spaceleft;
	}

	public void setSpaceleft(double spaceleft) {
		this.spaceleft = spaceleft;
	}

}
