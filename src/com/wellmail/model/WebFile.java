package com.wellmail.model;

public class WebFile {

	//�����ļ�id
	private int fileid;
	
	//�����ļ�����
	private String filename;
	
	//�ļ���С
	private double filesize;
	
	//�ļ��ϴ�ʱ��
	private String uploadtime;

	//user���
	private Users user;
	
	//filetype ���
	private FileType filetype;
	
	public FileType getFiletype() {
		return filetype;
	}

	public void setFiletype(FileType filetype) {
		this.filetype = filetype;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getFileid() {
		return fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public double getFilesize() {
		return filesize;
	}

	public void setFilesize(double filesize) {
		this.filesize = filesize;
	}

	public String getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	
}
