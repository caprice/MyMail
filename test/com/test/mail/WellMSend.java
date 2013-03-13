package com.test.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class WellMSend {
	public static void main(String [] args) {
		
		//������
		String sender = "a@mymail.com";
		//�ռ���
		String recipients = "admin@mymail.com";
		//�ʼ�����
		String subject = "aggggg222222222222";
		//�ʼ�����
		String content = "<a>xxxxxxxxxxxx222222222xxxxxxxtrt</a>";
		
		//����
		String attach[] = new String[]{"����config.xml.txt","JavaMail API���.txt"};
		//String attach[] = null;
		
		String mailtype = "text/html";
		
		String mailServer = "localhost";

		// �����ʼ�����Э��
		// ���ɱ�׼�������ļ�����
		Properties props = System.getProperties();
		// ����Ҫ����һ����������������
		props.put("mail.smtp.host", mailServer);
		// ����smtp�˿�
		props.put("mail.smtp.port", "25");
		// ���ʼ�Э��
		props.put("mail.transport.protocol", "smtp");
		// ���ʼ�Э��
		props.put("mail.store.protocol", "pop3");

		// �Ƿ�smtp��֤
		// props.put("mail.smtp.auth", "true");

		// ���������ʼ�������
		// ��prop�������������ӣ�ͬʱ������������null
		Session session = Session.getDefaultInstance(props, null);

		// ��Session��������д���������Ϣ����
		Message msg = new MimeMessage(session);
		
		// ������ص��ʼ�����
		try {
			// �����ʼ��ķ����˵�ַ
			msg.setFrom(new InternetAddress(sender));
			
			// �����ʼ����ռ��˵�ַ
			// To:��ͨ���ͣ���Ե�
			// CC�����ͣ�ͬʱ�����
			// BCC�����ͣ���������
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
			
			String cc1 = "b@mymail.com";
			String cc2 = "c@mymail.com";
			
			String bcc1 = "d@mymail.com";
			
			msg.setRecipients(Message.RecipientType.CC, new InternetAddress[]{new InternetAddress(cc1),new InternetAddress(cc2)});
			msg.setRecipients(Message.RecipientType.BCC, new InternetAddress[]{new InternetAddress(bcc1)});
			
			// �����ʼ�����
			msg.setSubject(subject);
			// �����ʼ�����ʱ��
			Date date = new Date();
			SimpleDateFormat simpledf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String source = simpledf.format(date);
			date = simpledf.parse(source);
			msg.setSentDate(date);

			// ������
			if (attach == null || attach.length < 1) {

				// ����Ϊ�� �����߸�������ĳ���С��1

				// �жϷ��͵�Mime����
				// Multipart���ڴ������������ mp����ɲ���һ��������ɣ������Կ�򲿷ֵ����
				// ʹ��setText���͵��ʼ��൱һ��������Ƭ������ֻ���ŷ⣬û���š���Multipart�൱��һ�����š����������ŵ�body
				// ��һ�����š������ж��body����mbp.setContent�ǽ�content����Ϊtype����
				Multipart mp = new MimeMultipart();
				MimeBodyPart mbp = new MimeBodyPart();
				// �����ʼ������ƶ�������
				// setContent�൱�뽫content����Ϊtype����
				mbp.setContent(content, mailtype + ";charset=GB18030");
				// �������塱��ӽ����š���
				mp.addBodyPart(mbp);
				// �����š��ӵ����ŷ⡱��msg����
				msg.setContent(mp);

			} else {

				// ��������

				// ���ʼ�����ͨ���ݽ��д���
				Multipart mp = new MimeMultipart();

				MimeBodyPart mbpContent = new MimeBodyPart();
				mbpContent.setContent(content, mailtype + ";charset=GB18030");

				for (int i = 0; i < attach.length; i++) {

					// ���ʼ��еĸ������д���
					MimeBodyPart mbpAttach = new MimeBodyPart();
					// �����ļ���ȡ����Դ
					DataSource fds = new FileDataSource("d:/upload/"+ attach[i] );
					// ��ȡ��������������������Ϣ����ʱmbp2��洢���Ƕ����Ƶ��������������ֽ�
					DataHandler dh = new DataHandler(fds);
					mbpAttach.setDataHandler(dh);
					// ��ʼ��
					mbpAttach.setFileName(MimeUtility.encodeText(fds.getName(),
							"GB18030", "B"));

					// ��Ӹ�������
					mp.addBodyPart(mbpAttach);

				}

				// ����ʼ���������
				mp.addBodyPart(mbpContent);
				

				// ��ӵ����ŷ⡱��
				msg.setContent(mp);
			}
			
			File f = new File("d:/test1.eml");
			msg.writeTo(new FileOutputStream(f));
			

			// ����
			Transport.send(msg);

			System.out.print("�ʼ����ͳɹ���");
			System.out.println(f.length()+144);
			

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
