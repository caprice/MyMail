package com.test.receive;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.*;

public class JavaMailSSLReceiver {
	
	
	public static void main(String[] args) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props);
		// ��1������Ϊ����Э�飬��2������Ϊ�ʼ����շ������ĵ�ַ������������Ϊpop3Э��Ķ˿ڣ��������Ϊ�û���������
		URLName url = new URLName("pop3", "pop.126.com", 110, null,
				"bauble", "severus125874693");
		// ʹ��SSL���ܴ���Э��,gmail����ʹ��sslЭ������֤�ʼ�����İ�ȫ��ʹ��SSL��POP3S��Ĭ�϶˿�Ϊ995��
		// URLName url=new
		// URLName("pop3s","pop.gmail.com",995,null,"masterspring","spring");

		Store store = null;
		Folder inbox = null;
		try {
			store = session.getStore(url);
			// ʹ��URLNameָ��Session�ʼ�������������֤��Ϣ
			store.connect(); // �����ʼ�������

			// ���ʼ��������з��������ڵ���Ϣ
			inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY); // ��Folder
			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);

			Message[] msgs = inbox.getMessages();
			inbox.fetch(msgs, fp);

			// ��ʾ�ʼ���Ϣ
			for (int i = 0; i < msgs.length; i++) {
				String from = msgs[i].getFrom()[0].toString();
				InternetAddress id = new InternetAddress(from);
				System.out.println();
				System.out.println("������:" + id.getPersonal() + "/"
						+ id.getAddress());

				System.out.println("����:" + msgs[i].getSubject());
				System.out.println("��С:" + msgs[i].getSize());
				System.out.println("ʱ��:" + msgs[i].getSentDate());
				System.out.println("����:" + msgs[i].getContentType());
				System.out.println("����:" + msgs[i].getContent().toString());
				
			}
		} catch (Exception er) {
			System.out.println("�����쳣��" + er.toString());
		} finally {
			try {
				inbox.close(false);
				store.close();
			} catch (Exception e) {
			}
		}
	}
	
}
