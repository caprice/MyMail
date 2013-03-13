package com.test.receive;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class ReceiveEmail2 {
	private final String username; // �û���
	private final String password; // ����
	private String host; // ��������
	private String dir = "C:/TEMP"; // Ĭ�ϸ�������Ŀ¼
	private boolean ssl = false; // Ĭ�ϲ�ʹ�ð�ȫ�׽Ӳ�Э��

	private Store store;
	private Folder folder;

	public ReceiveEmail2(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
	}

	/**
	 * ���Խ���126�����ʼ�
	 */
	public static void main(String[] args) {
		String host = "pop3.126.com";
		String username = "bauble";
		String password = "severus125874693";

		ReceiveEmail2 receiveEmail = new ReceiveEmail2(host, username, password);
		// receiveEmail.setSSL(true); //����gmail�ʼ�������
		// receiveEmail.setDir("D:/upload"); //���ø�������Ŀ¼
		List<Message> list = receiveEmail.receive();
		System.out.println("total email��" + list.size());

		for (Message msg : list) {
			System.out.println("-------------start--------------------------");
			System.out.println("from��" + msg.getFrom());
			System.out.println("sendDate��" + msg.getFormatSendDate());// msg.getSendDate();
			System.out.println("subject��" + msg.getSubject());
			System.out.println("content��" + msg.getContent());

			Map<String, String> map = msg.getAttachMap();
			for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
				String name = it.next();
				String path = map.get(name);
				System.out.println("attach��" + name + "===" + path);
			}
			System.out.println("-------------end----------------------------");
		}
	}

	/**
	 * ���ø�������Ŀ¼
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * �����Ƿ�ʹ�ð�ȫ�׽Ӳ�Э��
	 */
	public void setSSL(boolean ssl) {
		this.ssl = ssl;
	}

	/**
	 * �����ʼ����������е�(��)�ʼ���Ϣ
	 */
	public List<Message> receive() {
		List<Message> list = null;
		try {
			list = handleMessages();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				folder.close(true);
				store.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ���������������Message
	 */
	private List<Message> handleMessages() throws Exception {
		javax.mail.Message[] msgs = getMessages();

		List<Message> list = new ArrayList<Message>();
		for (int i = 0; i < msgs.length; i++) {
			if (isNew((MimeMessage) msgs[i])) {
				Message message = new Message();
				message.setFrom(getFrom(msgs[i]));
				message.setSubject(msgs[i].getSubject());
				message.setSendDate(msgs[i].getSentDate());
				message.setContent(getContent(msgs[i]));

				handleAttachments(message, msgs[i]);
				list.add(message);
			}
		}
		return list;
	}

	/**
	 * ��ȡ������������Message
	 */
	private javax.mail.Message[] getMessages() throws Exception {
		Properties props = System.getProperties();
		if (ssl) {
			handleSSL(props);
			host = host.replace("pop3", "pop");
		}

		Session session = Session.getDefaultInstance(props, null);
		store = session.getStore("pop3");
		store.connect(host, username, password);
		folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		javax.mail.Message[] msgs = folder.getMessages();

		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		fp.add(FetchProfile.Item.FLAGS);
		fp.add("X-Mailer");
		folder.fetch(msgs, fp);
		return msgs;
	}

	/**
	 * �����ʼ���Դ
	 */
	private String getFrom(javax.mail.Message msg) throws Exception {
		String personal = ((InternetAddress) msg.getFrom()[0]).getPersonal();
		String address = "<"
				+ ((InternetAddress) msg.getFrom()[0]).getAddress() + ">";
		if (null == personal) {
			personal = address.substring(1, address.indexOf("@"));
		}
		return personal + address;
	}

	/**
	 * �����ʼ�����
	 */
	private String getContent(Part part) throws Exception {
		if (part.isMimeType("text/plain")) {
			return (String) part.getContent();
		} else if (part.isMimeType("text/html")) {
			return (String) part.getContent();
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			return getContent(multipart.getBodyPart(0));
		} else if (part.isMimeType("message/rfc822")) {
			return getContent((Part) part.getContent());
		}
		return "";
	}

	/**
	 * �ж��Ƿ����ʼ�(����ʡ��)
	 */
	private boolean isNew(MimeMessage msg) throws Exception {
		// ����pop3Э�飬��msg.getMessageID()Ψһ��UID�ж�(�ɽ�����UID�����ж��Ƿ���ڼ��ɡ�)
		// javamail�ṩ�Ĵ������ʼ�������pop3Э�鲻������
		return true;
	}

	/**
	 * ������
	 */
	private void handleAttachments(Message message, Part part) throws Exception {
		if (part.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) part.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				BodyPart bp = mp.getBodyPart(i);
				String disposition = bp.getDisposition();
				if (disposition != null
						&& (disposition.equals(Part.ATTACHMENT) || disposition
								.equals(Part.INLINE))) {
					saveFile(message, bp);
				} else if (bp.isMimeType("multipart/*")) {
					handleAttachments(message, (Part) part.getContent());
				} else {
					saveFile(message, bp);
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			handleAttachments(message, (Part) part.getContent());
		}
	}

	/**
	 * �����Ĵ������ϴ�
	 */
	private void saveFile(Message message, BodyPart bp) throws Exception {
		String fileName = bp.getFileName();
		if ((fileName != null)) {
			new File(dir).mkdirs(); // �½�Ŀ¼
			fileName = MimeUtility.decodeText(fileName);
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			String filePath = dir + "/" + UUID.randomUUID() + suffix;
			message.attachMap.put(fileName, filePath);

			File file = new File(filePath);
			BufferedInputStream bis = new BufferedInputStream(bp
					.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(file));
			int i;
			while ((i = bis.read()) != -1) {
				bos.write(i);
			}
			bos.close();
			bis.close();
		}
	}

	/**
	 * ����ȫ�׽Ӳ�Э��
	 */
	private void handleSSL(Properties props) {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.pop3.socketFactory.fallback", "false");
		props.setProperty("mail.pop3.port", "995");
		props.setProperty("mail.pop3.socketFactory.port", "995");

		props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.imap.socketFactory.fallback", "false");
		props.setProperty("mail.imap.port", "993");
		props.setProperty("mail.imap.socketFactory.port", "993");
	}

	/**
	 * �����ȡ��ÿ���ʼ���Ϣ
	 */
	public static class Message {
		private String from; // ������
		private String subject; // ����
		private String content; // ����
		private Date sendDate; // ��������
		// ������Ϣ(�ļ���=�ļ�·��)
		private Map<String, String> attachMap = new HashMap<String, String>();

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getFormatSendDate() {
			return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(sendDate);
		}

		public Date getSendDate() {
			return sendDate;
		}

		public void setSendDate(Date sendDate) {
			this.sendDate = sendDate;
		}

		public Map<String, String> getAttachMap() {
			return attachMap;
		}

		public void setAttachMap(Map<String, String> attachMap) {
			this.attachMap = attachMap;
		}
	}
}
