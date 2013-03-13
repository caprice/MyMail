package com.test.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

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
import javax.mail.internet.MimeUtility;

/*
 * �����������ʼ�����
 */

public class SendAttachMail {

	public static void send() {

		//������
		String from = "severus@mymail.com";
		//�ռ���
		String to = "bauble@mymail.com";
		//�ʼ�����
		String subject = "��1����Դ��������ʼ�";
		//�ʼ�����
		String content = "<html><body>�����ǵ�<font color='red'>1</font>����Դ��������ʼ���лл</body></html>";
		
		//�ʼ�����
		// text/plain --���ı�
		// text/html  --HTML
		String type = "text/html";
		
		//�����ļ���
		String fileName = "D:/aaa.txt";
		String fileName2 = "D:/java�����ʼ�.doc";
		
		
		//ȷ�������ʼ���������ַ   ��������.com��
		String mailServer = "localhost";
		
		//�����ʼ�����Э��
		//���ɱ�׼�������ļ�����
		Properties props = System.getProperties();
		//����Ҫ����һ����������������
		props.put("mail.smtp.host", mailServer);
	    /*  // �Ƿ�smtp��֤   
	        props.put("mail.smtp.auth", "true"); 
	        // ����smtp�˿�   
	        props.put("mail.smtp.port", "25"); 
	        // ���ʼ�Э��   
	        props.put("mail.transport.protocol", "smtp"); 
	        // ���ʼ�Э��   
	        props.put("mail.store.protocol", "pop3"); 
        */
		
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

			//������
			if((fileName == null || fileName == "")&&(fileName2 == null || fileName2 == "") ) {
			
				//�жϷ��͵�Mime����
				//Multipart���ڴ������������   mp����ɲ���һ��������ɣ������Կ�򲿷ֵ����
				//ʹ��setText���͵��ʼ��൱һ��������Ƭ������ֻ���ŷ⣬û���š���Multipart�൱��һ�����š����������ŵ�body
				//��һ�����š������ж��body����mbp.setContent�ǽ�content����Ϊtype����
				Multipart mp = new MimeMultipart();
				MimeBodyPart mbp = new MimeBodyPart();
				//�����ʼ������ƶ�������
				//setContent�൱�뽫content����Ϊtype����
				mbp.setContent(content,type + ";charset=GB18030");
				//�������塱��ӽ����š���
				mp.addBodyPart(mbp);
				//�����š��ӵ����ŷ⡱��msg����
				msg.setContent(mp);
				
			}else {
				//����ݸ�  ���Լ������ٱ���һ��
				//File file = new File(fileName);

				//���ʼ�����ͨ���ݽ��д���
				Multipart mp = new MimeMultipart();
				
				
				MimeBodyPart mbp1 = new MimeBodyPart();
				mbp1.setContent(content,type + ";charset=GB18030");
				
				
				//���ʼ��еĸ������д���
				MimeBodyPart mbp2 = new MimeBodyPart();
				//�����ļ���ȡ����Դ
				DataSource fds = new FileDataSource(fileName);
				//��ȡ��������������������Ϣ����ʱmbp2��洢���Ƕ����Ƶ��������������ֽ�
				DataHandler dh = new DataHandler(fds);
				mbp2.setDataHandler(dh);
				//��ʼ��
				mbp2.setFileName(MimeUtility.encodeText(fds.getName(),"GB18030","B"));
				
				
				MimeBodyPart mbp3 = new MimeBodyPart();
				DataSource fds2 = new FileDataSource(fileName2);
				DataHandler dh2 = new DataHandler(fds2);
				mbp3.setDataHandler(dh2);
				mbp3.setFileName(MimeUtility.encodeText(fds2.getName(),"GB18030","B"));
				
				//�������
				mp.addBodyPart(mbp1);
				mp.addBodyPart(mbp2);
				mp.addBodyPart(mbp3);
				
				//��ӵ����ŷ⡱��
				msg.setContent(mp);
			}
			
			//����
			Transport.send(msg);
			
			System.out.print("�ʼ����ͳɹ���");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		SendAttachMail.send();
	}
	
}
