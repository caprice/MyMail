package com.test.mail;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*
 *�����Ѵ����ʼ�
 */

public class SendMailWithTemp {

	public static void send() {
		
		//���ʼ�ģ��
		ResourceBundle rb = ResourceBundle.getBundle("mail");
		

		//������
		String from = "michael.zhang";
		//�ռ���
		String to = "bauble";
		//�ʼ�����
		String subject = "��������ģ���ʼ�������س�����";
		//�ʼ�����
		//String content = "�����ǵ�һ������ʼ���лл";
		
		//ȷ�������ʼ���������ַ   ��������.com��
		String mailServer = "localhost";
		
		
		Object[] arg = {to};
		MessageFormat mf = new MessageFormat("");
		mf.applyPattern(rb.getString("message"));
		//�ò������ģ��
		String message = mf.format(arg);
		
		
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
			
			msg.setSentDate(new Date());
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
			//msg.setText(content);
			
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(message,"text/html;charset=GB18030");
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp);
			
			msg.setContent(mp);
			
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
		SendMailWithTemp.send();
	}
	
}
