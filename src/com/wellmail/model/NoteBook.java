package com.wellmail.model;

public class NoteBook {

	//���±�id
	private int noteid;
	
	//���±�����
	private String notetitle;
	
	//���±�����
	private String notecontent;
	
	//user ���
	private Users user;
	
	//notetype ���
	private NoteType notetype;

	public NoteType getNotetype() {
		return notetype;
	}

	public void setNotetype(NoteType notetype) {
		this.notetype = notetype;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getNoteid() {
		return noteid;
	}

	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}

	public String getNotetitle() {
		return notetitle;
	}

	public void setNotetitle(String notetitle) {
		this.notetitle = notetitle;
	}

	public String getNotecontent() {
		return notecontent;
	}

	public void setNotecontent(String notecontent) {
		this.notecontent = notecontent;
	}
	
}
