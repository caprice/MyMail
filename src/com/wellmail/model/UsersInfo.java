package com.wellmail.model;

public class UsersInfo {

	//�û���Ϣ���
	private int usersinfoid;
	
	//�û��Ա�
	private String sex;
	
	//�û�����
	private String birthday;
	
	//�û��绰
	private String tel;

	//users���
	private Users user;
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getUsersinfoid() {
		return usersinfoid;
	}

	public void setUsersinfoid(int usersinfoid) {
		this.usersinfoid = usersinfoid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
