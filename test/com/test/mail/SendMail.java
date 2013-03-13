package com.test.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * ��ͨ�ı��ʼ�����
 */

public class SendMail {

	public static void send() {

		//������
		String from = "severus@mymail.com";
		//�ռ���
		String to = "bauble@mymail.com";
		//�ʼ�����
		String subject = "��2������ʼ�";
		//�ʼ�����
		String content = "�����ǵ�2������ʼ���лл";
		
		//ȷ�������ʼ���������ַ   ��������.com��
		String mailServer = "localhost";
		
		//�����ʼ�����Э��
		//���ɱ�׼�������ļ�����
		Properties props = System.getProperties();
		//����Ҫ����һ����������������
		props.put("mail.smtp.host", mailServer);
		
		//���������ʼ�������
		//��prop�������������ӣ�ͬʱ������������null
		Session session = Session.getDefaultInstance(props, null);
		
		//��Session��������д���������Ϣ����
		Message msg = new MimeMessage(session);
		//������ص��ʼ�����
		try {
			//�����ʼ��ķ����˵�ַ
			msg.setFrom(new InternetAddress(from));
			//�����ʼ����ռ��˵�ַ     
			//  To:��ͨ���ͣ���Ե�     
			//  CC�����ͣ�ͬʱ�����    
			//  BCC�����ͣ���������
			
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			/*
				//Ⱥ��
				msg.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to),
						new InternetAddress(to_2),new InternetAddress(to_3)});
				//����дһ��ѭ������ÿһ��to�ĵ�ַ��setһ�飬Ȼ����
			*/
			
			//�����ʼ�����
			msg.setSubject(subject);
			//�����ʼ�����ʱ��
			msg.setSentDate(new Date());
			//�����ʼ�����   ���ı����ʼ�������������   ����д���ŷ����ˣ�
			msg.setText(content);
			
			//����
			Transport.send(msg);
			
			System.out.print("�ʼ����ͳɹ���");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		SendMail.send();
	}
	
}
