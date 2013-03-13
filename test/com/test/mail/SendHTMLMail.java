package com.test.mail;

import java.util.Date;
import java.util.Properties;

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
 * ����HTML���ʼ�����
 */

public class SendHTMLMail {

	public static void send() {

		//������
		String from = "severus@mymail.com";
		//�ռ���
		String to = "bauble@mymail.com";
		//�ʼ�����
		String subject = "��3�����HTML�ʼ�";
		//�ʼ�����
		String content = "<html><body>������<h3>��3��</h3><font color='red'>HTML</font>�ʼ���лл</body></html>";
		
		//�ʼ�����
		// text/plain --���ı�
		// text/html  --HTML
		String type = "text/html";
		
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
			
			//System.out.println(msg.getm());
			
			
			
			
			/*
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager
				    .getConnection("jdbc:mysql://localhost/mymail?autoReconnect=true", "root", "root");
				String sql = "insert into email values(1,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, from);
				pstmt.setString(2, to);
				pstmt.setString(3, subject);
				pstmt.setString(4, content);
				pstmt.setString(5, new Date().toString());
				pstmt.setString(6, type + ";charset=GB18030");
				pstmt.setString(7, 0.01+"");
				pstmt.setInt(8, 1);
				pstmt.setInt(9, 1);
				pstmt.setInt(10, 1);
				pstmt.setInt(11, 1);
				pstmt.setString(12, "severus");
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(conn != null) {
					try {
						conn.close();
						conn = null;
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			
			
			*/
			
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
		SendHTMLMail.send();
	}
	
}
