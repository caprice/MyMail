package com.wellmail.model;

public class UserPwd {

	//�û���������id
	private int qid;
	
	//�û���������
	private String question;
	
	//�û����������
	private String answer;
	
	//user���
	private Users user; 

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
