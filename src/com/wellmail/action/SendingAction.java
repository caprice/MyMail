package com.wellmail.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wellmail.dao.AttachmentDao;
import com.wellmail.dao.BCCDao;
import com.wellmail.dao.CCDao;
import com.wellmail.dao.EmailDao;
import com.wellmail.dao.FolderDao;
import com.wellmail.dao.MailTagDao;
import com.wellmail.dao.PriorityDao;
import com.wellmail.dao.UserScoreDao;
import com.wellmail.dao.UsersDao;
import com.wellmail.form.EmailForm;
import com.wellmail.model.Attachment;
import com.wellmail.model.BCC;
import com.wellmail.model.CC;
import com.wellmail.model.Email;
import com.wellmail.model.Folder;
import com.wellmail.model.MailTag;
import com.wellmail.model.Priority;
import com.wellmail.model.UserScore;
import com.wellmail.model.Users;

public class SendingAction extends Action {

	private UsersDao usersDao;
	private Users users;

	private UserScoreDao userScoreDao;
	private UserScore userscore;

	private FolderDao folderDao;
	private Folder folder;

	private EmailDao emailDao;
	private Email email;

	private MailTagDao mailTagDao;
	private MailTag mailtag;

	private PriorityDao priorityDao;
	private Priority priority;

	private AttachmentDao attachmentDao;
	private Attachment attachment;

	private CCDao ccDao;
	private CC ccs;

	private BCCDao bccDao;
	private BCC bccs;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ��ȡ��ǰ�û���ͬʱҲ�Ƿ�����
		users = (Users) request.getSession().getAttribute("user");
		// System.out.println(users.getUsername());
		users = usersDao.queryUserByName(users);

		// �����û�����
		userscore = userScoreDao.queryScoreByUser(users);
		userscore.setScore(userscore.getScore() + 5);
		if (0 < userscore.getScore() && userscore.getScore() <= 300) {
			userscore.setLevel(1);
		} else if (301 <= userscore.getScore() && userscore.getScore() <= 1000) {
			userscore.setLevel(2);
		} else if (1001 <= userscore.getScore() && userscore.getScore() <= 5000) {
			userscore.setLevel(3);
		} else if (5001 <= userscore.getScore()
				&& userscore.getScore() <= 10000) {
			userscore.setLevel(4);
		} else if (10001 <= userscore.getScore()
				&& userscore.getScore() <= 50000) {
			userscore.setLevel(5);
		} else if (50001 <= userscore.getScore()
				&& userscore.getScore() <= 100000) {
			userscore.setLevel(6);
		} else if (100001 <= userscore.getScore()
				&& userscore.getScore() <= 500000) {
			userscore.setLevel(7);
		}
		userScoreDao.modifyScore(userscore);

		// ������
		String sender = users.getUsername();

		// mailtag id��Ϊ9������ͨ�ʼ���Ĭ��
		mailtag.setTagid(9);
		mailtag = mailTagDao.queryMailTagById(mailtag);

		// priority idΪ2������ͨ�ʼ���Ĭ��
		priority.setPriorityid(2);
		priority = priorityDao.queryPriorityById(priority);

		// folder id��Ϊ3���ѷ��ͣ�Ĭ�� ,���ڵ�ǰ��¼�û���folderΪ"�ѷ���",���Ƕ�����������˵��ǰfolderΪ���ռ��䡱
		// idΪ1
		// folder.setFolderid(3);
		// folder = folderDao.queryFolderByUserAndId(users, folder);

		// EmailForm
		EmailForm ef = (EmailForm) form;

		// �ʼ���Ϣ
		// �ռ���
		String recipients = ef.getRecipients();
		// ����
		String subject = "";
		if (ef.getSubject() != null && ef.getSubject() != "") {
			subject = new String(ef.getSubject().getBytes("ISO8859-1"),
					"Gb18030");
		} else {
			subject = "δ�����ʼ�";
		}
		// ����
		String content = "";
		if (ef.getContent() != null && ef.getContent() != "") {
			content = new String(ef.getContent().getBytes("ISO8859-1"),
					"Gb18030");
		} else {
			content = "ϵͳ��Ϣ�����ʼ�Ϊ�������ʼ���";
		}
		// �ʼ�����
		String mailtype = "text/html";

		// bcc�������б�
		String bcctemp = ef.getBcc();
		String bcc[] = null;
		if (null != bcctemp && "" != bcctemp) {
			bcc = bcctemp.split(";");
		}
		// cc�������б�
		String cctemp = ef.getCc();
		String cc[] = null;
		if (null != cctemp && "" != cctemp) {
			cc = cctemp.split(";");
		}
		// Ⱥ�������б�
		String qftemp = ef.getQf();

		String qf[] = null;
		String qf2[] = null;
		if (qftemp != null && qftemp != "") {
			qf = new String[qftemp.toCharArray().length];
			qf = qftemp.split(";");
			// for(int i = 0; i<qf.length; i++) {
			// System.out.println("qf"+i+":"+qf[i]);
			// }
			qf2 = new String[qf.length + 1];
			for (int i = 0; i < qf.length; i++) {
				qf2[i] = qf[i];
			}
			qf2[qf.length] = recipients;

		}
		// for(int i = 0 ; i< qf2.length; i++) {
		// System.out.println("qf2"+i+":"+qf2[i]);
		// }

		// ������ַ����
		String attach[] = request.getParameterValues("attach");

		/*
		 * for(int i = 0; i<attach.length; i++) {
		 * 
		 * System.out.println("attach :"+new
		 * String(attach[i].getBytes("ISO8859-1"),"GB18030")); }
		 */
		if (qf2 != null) {

			for (int q = 0; q < qf2.length; q++) {

				/**
				 * --------------------------------------�÷��ʼ�û�������ˣ����ñ���------------------------------------------
				 */

				System.out.println("�÷��ʼ�û�������ˣ����ñ���");

				/*
				 * File file2 = new File("d:/upload/temp.doc"); FileOutputStream
				 * fos2; try { fos2 = new FileOutputStream(file2);
				 * OutputStreamWriter osw2 = new OutputStreamWriter(fos2);
				 * BufferedWriter bw2 = new BufferedWriter(osw2);
				 * bw2.write(content); bw2.close(); } catch
				 * (FileNotFoundException e) { e.printStackTrace(); } catch
				 * (IOException e) { e.printStackTrace(); } // ��ȡ�ļ���С ��λKB
				 * filesize2 = file2.length(); filesize2 = filesize2 / 1024;
				 * 
				 * if (attach != null) { for (int j = 0; j < attach.length; j++) {
				 * File f = new File("d:/upload/" + new
				 * String(attach[j].getBytes("ISO8859-1"), "GB18030"));
				 * 
				 * float asize2 = f.length() / 1024;
				 * 
				 * filesize2 += asize2; } }
				 * 
				 * System.out.println("�ʼ���С��" + filesize2 + "KB"); if (filesize2 <
				 * 1) { filesize2 = 1; }
				 * 
				 * filesize2 = filesize2 / 1024; System.out.println("�ʼ���С��" +
				 * filesize2 + "MB");
				 * 
				 */

				// ���淢������Ϣ
				users.setUsername(sender);
				users = usersDao.queryUserByName(users);

				email.setUser(users);
				email.setSender(sender);
				email.setRecipients(qf2[q]);
				email.setSubject(subject);
				email.setContent(content);

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String senttime = sdf.format(new Date());
				email.setSenttime(senttime);

				email.setMailtype(mailtype);
				email.setMailtag(mailtag);
				email.setMsgsize(0);
				email.setUnread(0);
				email.setMailtag(mailtag);
				email.setPriority(priority);

				folder.setFoldername("�ѷ���");
				folder = folderDao.queryFolderByUserAndFname(users, folder);
				folder.setMailcount(folder.getMailcount() + 1);
				folderDao.modifyFolder(folder);
				email.setFolder(folder);

				emailDao.addEmail(email);

				// ������
				if (attach != null && attach.length > 0) {
					for (int j = 0; j < attach.length; j++) {
						File f = new File(
								"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						System.out.println("attach3"
								+ new String(attach[j].getBytes("ISO8859-1"),
										"GB18030"));
						attachment
								.setAttachmentpath("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						attachment.setAttachmentname(new String(attach[j]
								.getBytes("ISO8859-1"), "GB18030"));

						double attachmentsize = f.length() / 1024;

						attachmentsize = attachmentsize / 1024;

						DecimalFormat df = new DecimalFormat(".###");
						String st = df.format(attachmentsize);
						attachmentsize = Double.parseDouble(st);

						attachment.setAttachmentsize(attachmentsize);

						attachment.setEmail(email);

						attachmentDao.addAttachment(attachment);
					}
				}

				/*// ��������cc
				if (cc != null) {
					for (int k = 0; k < cc.length; k++) {
						ccs.setCcname(cc[k]);
						ccs.setEmail(email);
						ccDao.addCC(ccs);
					}
				}

				// ����������bcc
				if (bcc != null) {
					for (int k = 0; k < bcc.length; k++) {
						bccs.setBccname(bcc[k]);
						bccs.setEmail(email);
						bccDao.addBCC(bccs);
					}
				}*/
			}

		} else {
			// ��Ⱥ�� ��Ϊ�������˺�û��������������

			if (null != ef.getBcc() && "" != ef.getBcc()) {

				// �÷��ʼ��������ˣ��������棩

				for (int i = 0; i < bcc.length; i++) {
					// ��������Ϣ
					users.setUsername(bcc[i]);
					users = usersDao.queryUserByName(users);

					email.setUser(users);
					email.setSender(sender);
					email.setRecipients(recipients);
					email.setSubject(subject);
					email.setContent(content);

					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String senttime = sdf.format(new Date());
					email.setSenttime(senttime);

					/*
					 * File file = new File("d:/upload/temp.doc");
					 * FileOutputStream fos; try { fos = new
					 * FileOutputStream(file); OutputStreamWriter osw = new
					 * OutputStreamWriter(fos); BufferedWriter bw = new
					 * BufferedWriter(osw); bw.write(content); bw.close(); }
					 * catch (FileNotFoundException e) { e.printStackTrace(); }
					 * catch (IOException e) { e.printStackTrace(); } // ��ȡ�ļ���С
					 * ��λKB filesize = file.length(); filesize = filesize /
					 * 1024;
					 * 
					 * if (attach != null) { for (int j = 0; j < attach.length;
					 * j++) { File f = new File("d:/upload/" + new
					 * String(attach[j].getBytes("ISO8859-1"), "GB18030"));
					 * 
					 * float asize = f.length() / 1024;
					 * 
					 * filesize += asize; } }
					 * 
					 * System.out.println("�ʼ���С��" + filesize + "KB"); if
					 * (filesize < 1) { filesize = 1; }
					 * 
					 * filesize = filesize / 1024; System.out.println("�ʼ���С��" +
					 * filesize + "MB");
					 */

					email.setMsgsize(0);
					email.setMailtype(mailtype);
					email.setUnread(1);
					email.setMailtag(mailtag);
					email.setPriority(priority);

					// ����ǰemail���ļ���
					folder.setFoldername("�ռ���");
					folder = folderDao.queryFolderByUserAndFname(users, folder);
					folder.setMailcount(folder.getMailcount() + 1);
					folderDao.modifyFolder(folder);
					email.setFolder(folder);

					emailDao.addEmail(email);

					// �����ʼ��ռ�
					// emailspace = emailSpaceDao.querySpaceByUser(users);
					// emailSpaceDao.modifySpace(emailspace);

					// ��������cc
					if (cc != null) {
						for (int k = 0; k < cc.length; k++) {
							ccs.setCcname(cc[k]);
							ccs.setEmail(email);
							ccDao.addCC(ccs);
						}
					}

					// ������
					if (attach != null && attach.length > 0) {
						for (int j = 0; j < attach.length; j++) {
							File f = new File(
									"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
											+ new String(attach[j]
													.getBytes("ISO8859-1"),
													"GB18030"));
							System.out.println("attach 2"
									+ new String(attach[j]
											.getBytes("ISO8859-1"), "GB18030"));
							attachment
									.setAttachmentpath("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
											+ new String(attach[j]
													.getBytes("ISO8859-1"),
													"GB18030"));
							attachment.setAttachmentname(new String(attach[j]
									.getBytes("ISO8859-1"), "GB18030"));

							double attachmentsize = f.length() / 1024;

							attachmentsize = attachmentsize / 1024;

							DecimalFormat df = new DecimalFormat(".###");
							String st = df.format(attachmentsize);
							attachmentsize = Double.parseDouble(st);

							attachment.setAttachmentsize(attachmentsize);

							attachment.setEmail(email);

							attachmentDao.addAttachment(attachment);
						}
					}
				}

				/**
				 * ---------------------------------���淢������Ϣ(��������)--------------------------------
				 */
				users.setUsername(sender);
				users = usersDao.queryUserByName(users);

				email.setUser(users);
				email.setSender(sender);
				email.setRecipients(recipients);
				email.setSubject(subject);
				email.setContent(content);

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String senttime = sdf.format(new Date());
				email.setSenttime(senttime);

				email.setMailtype(mailtype);
				email.setMailtag(mailtag);
				email.setMsgsize(0);
				email.setUnread(0);
				email.setMailtag(mailtag);
				email.setPriority(priority);

				folder.setFoldername("�ѷ���");
				folder = folderDao.queryFolderByUserAndFname(users, folder);
				folder.setMailcount(folder.getMailcount() + 1);
				folderDao.modifyFolder(folder);
				email.setFolder(folder);

				emailDao.addEmail(email);

				// �����ʼ��ռ�
				/*
				 * emailspace = emailSpaceDao.querySpaceByUser(users);
				 * emailspace.setSpaceleft(emailspace.getSpaceleft() -
				 * filesize); emailSpaceDao.modifySpace(emailspace);
				 */

				// ������
				if (attach != null && attach.length > 0) {
					for (int j = 0; j < attach.length; j++) {
						File f = new File(
								"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						System.out.println("attach3"
								+ new String(attach[j].getBytes("ISO8859-1"),
										"GB18030"));
						attachment
								.setAttachmentpath("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						attachment.setAttachmentname(new String(attach[j]
								.getBytes("ISO8859-1"), "GB18030"));

						double attachmentsize = f.length() / 1024;

						attachmentsize = attachmentsize / 1024;

						DecimalFormat df = new DecimalFormat(".###");
						String st = df.format(attachmentsize);
						attachmentsize = Double.parseDouble(st);

						attachment.setAttachmentsize(attachmentsize);

						attachment.setEmail(email);

						attachmentDao.addAttachment(attachment);
					}
				}

				// ��������cc
				if (cc != null) {
					for (int k = 0; k < cc.length; k++) {
						ccs.setCcname(cc[k]);
						ccs.setEmail(email);
						ccDao.addCC(ccs);
					}
				}

				// ����������bcc
				if (bcc != null) {
					for (int k = 0; k < bcc.length; k++) {
						bccs.setBccname(bcc[k]);
						bccs.setEmail(email);
						bccDao.addBCC(bccs);
					}
				}

			} else {

				/**
				 * --------------------------------------�÷��ʼ�û�������ˣ����ñ���------------------------------------------
				 */

				System.out.println("�÷��ʼ�û�������ˣ����ñ���");

				/*
				 * File file2 = new File("d:/upload/temp.doc"); FileOutputStream
				 * fos2; try { fos2 = new FileOutputStream(file2);
				 * OutputStreamWriter osw2 = new OutputStreamWriter(fos2);
				 * BufferedWriter bw2 = new BufferedWriter(osw2);
				 * bw2.write(content); bw2.close(); } catch
				 * (FileNotFoundException e) { e.printStackTrace(); } catch
				 * (IOException e) { e.printStackTrace(); } // ��ȡ�ļ���С ��λKB
				 * filesize2 = file2.length(); filesize2 = filesize2 / 1024;
				 * 
				 * if (attach != null) { for (int j = 0; j < attach.length; j++) {
				 * File f = new File("d:/upload/" + new
				 * String(attach[j].getBytes("ISO8859-1"), "GB18030"));
				 * 
				 * float asize2 = f.length() / 1024;
				 * 
				 * filesize2 += asize2; } }
				 * 
				 * System.out.println("�ʼ���С��" + filesize2 + "KB"); if (filesize2 <
				 * 1) { filesize2 = 1; }
				 * 
				 * filesize2 = filesize2 / 1024; System.out.println("�ʼ���С��" +
				 * filesize2 + "MB");
				 * 
				 */

				// ���淢������Ϣ
				users.setUsername(sender);
				users = usersDao.queryUserByName(users);

				email.setUser(users);
				email.setSender(sender);
				email.setRecipients(recipients);
				email.setSubject(subject);
				email.setContent(content);

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String senttime = sdf.format(new Date());
				email.setSenttime(senttime);

				email.setMailtype(mailtype);
				email.setMailtag(mailtag);
				email.setMsgsize(0);
				email.setUnread(0);
				email.setMailtag(mailtag);
				email.setPriority(priority);

				folder.setFoldername("�ѷ���");
				folder = folderDao.queryFolderByUserAndFname(users, folder);
				folder.setMailcount(folder.getMailcount() + 1);
				folderDao.modifyFolder(folder);
				email.setFolder(folder);

				emailDao.addEmail(email);

				// ������
				if (attach != null && attach.length > 0) {
					for (int j = 0; j < attach.length; j++) {
						File f = new File(
								"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						System.out.println("attach3"
								+ new String(attach[j].getBytes("ISO8859-1"),
										"GB18030"));
						attachment
								.setAttachmentpath("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[j]
												.getBytes("ISO8859-1"),
												"GB18030"));
						attachment.setAttachmentname(new String(attach[j]
								.getBytes("ISO8859-1"), "GB18030"));

						double attachmentsize = f.length() / 1024;

						attachmentsize = attachmentsize / 1024;

						DecimalFormat df = new DecimalFormat(".###");
						String st = df.format(attachmentsize);
						attachmentsize = Double.parseDouble(st);

						attachment.setAttachmentsize(attachmentsize);

						attachment.setEmail(email);

						attachmentDao.addAttachment(attachment);
					}
				}

				// ��������cc
				if (cc != null) {
					for (int k = 0; k < cc.length; k++) {
						ccs.setCcname(cc[k]);
						ccs.setEmail(email);
						ccDao.addCC(ccs);
					}
				}

				// ����������bcc
				if (bcc != null) {
					for (int k = 0; k < bcc.length; k++) {
						bccs.setBccname(bcc[k]);
						bccs.setEmail(email);
						bccDao.addBCC(bccs);
					}
				}
			}

		}

		/**
		 * 
		 * ------------------------------------------
		 * ��ʽ�ʼ�����-----------------------------------------
		 * 
		 */

		if (qf2 != null) {

			// Ⱥ�������鲻Ϊ��
			InternetAddress[] address=new InternetAddress[qf2.length];//�����ռ��˵�ַ���� 

			for (int n = 0; n < qf2.length; n++) {
				address[n] = new InternetAddress(qf2[n]);
				
			}

				// ȷ�������ʼ���������ַ ��������.com��
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
					msg.setRecipients(Message.RecipientType.TO,
							address);

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

						// ����Ϊ�� �����߸����ĳ���С��1

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
						mbpContent.setContent(content, mailtype
								+ ";charset=GB18030");

						for (int i = 0; i < attach.length; i++) {

							// ���ʼ��еĸ������д���
							MimeBodyPart mbpAttach = new MimeBodyPart();
							// �����ļ���ȡ����Դ
							DataSource fds = new FileDataSource(
									"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
											+ new String(attach[i]
													.getBytes("ISO8859-1"),
													"GB18030"));
							// ��ȡ��������������������Ϣ����ʱmbp2��洢���Ƕ����Ƶ��������������ֽ�
							DataHandler dh = new DataHandler(fds);
							mbpAttach.setDataHandler(dh);
							// ��ʼ��
							mbpAttach.setFileName(MimeUtility.encodeText(fds
									.getName(), "GB18030", "B"));

							// ��Ӹ�������
							mp.addBodyPart(mbpAttach);

						}

						// ����ʼ���������
						mp.addBodyPart(mbpContent);

						// ��ӵ����ŷ⡱��
						msg.setContent(mp);
					}

					// ����
					Transport.send(msg);

					System.out.print("�ʼ����ͳɹ���");
					msg = null;

				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

		} else {
			// ��Ⱥ����

			// ȷ�������ʼ���������ַ ��������.com��
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
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
						recipients));

				// ���ڳ�����
				if (cc != null && cc.length > 0) {

					InternetAddress[] ia = new InternetAddress[cc.length];
					for (int c = 0; c < cc.length; c++) {
						ia[c] = new InternetAddress(cc[c]);
					}

					// �����ʹ�ó��͵Ļ����Ͳ�����TO�ˣ�ֱ�ӽ�TO��ӵ�Recipients�����
					// TNND��To���ǵ��еģ�û�з�����ȥ����Recipients�оͲ�Ҫ���ˣ�Ҫ�����ظ��ˣ��������飩
					msg.setRecipients(Message.RecipientType.CC, ia);
				}

				// ����������
				if (bcc != null && bcc.length > 0) {

					InternetAddress[] ia = new InternetAddress[bcc.length];
					for (int b = 0; b < bcc.length; b++) {
						ia[b] = new InternetAddress(bcc[b]);
					}

					msg.setRecipients(Message.RecipientType.BCC, ia);
				}

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
					mbpContent.setContent(content, mailtype
							+ ";charset=GB18030");

					for (int i = 0; i < attach.length; i++) {

						// ���ʼ��еĸ������д���
						MimeBodyPart mbpAttach = new MimeBodyPart();
						// �����ļ���ȡ����Դ
						DataSource fds = new FileDataSource(
								"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
										+ new String(attach[i]
												.getBytes("ISO8859-1"),
												"GB18030"));
						// ��ȡ��������������������Ϣ����ʱmbp2��洢���Ƕ����Ƶ��������������ֽ�
						DataHandler dh = new DataHandler(fds);
						mbpAttach.setDataHandler(dh);
						// ��ʼ��
						mbpAttach.setFileName(MimeUtility.encodeText(fds
								.getName(), "GB18030", "B"));

						// ��Ӹ�������
						mp.addBodyPart(mbpAttach);

					}

					// ����ʼ���������
					mp.addBodyPart(mbpContent);

					// ��ӵ����ŷ⡱��
					msg.setContent(mp);
				}

				// ����
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

		request.setAttribute("flag", "sentsuccess");
		return mapping.findForward("success");
	}

	public void setCcDao(CCDao ccDao) {
		this.ccDao = ccDao;
	}

	public void setCcs(CC ccs) {
		this.ccs = ccs;
	}

	public void setBccDao(BCCDao bccDao) {
		this.bccDao = bccDao;
	}

	public void setBccs(BCC bccs) {
		this.bccs = bccs;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void setPriorityDao(PriorityDao priorityDao) {
		this.priorityDao = priorityDao;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setMailTagDao(MailTagDao mailTagDao) {
		this.mailTagDao = mailTagDao;
	}

	public void setMailtag(MailTag mailtag) {
		this.mailtag = mailtag;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setUserScoreDao(UserScoreDao userScoreDao) {
		this.userScoreDao = userScoreDao;
	}

	public void setUserscore(UserScore userscore) {
		this.userscore = userscore;
	}

	public void setFolderDao(FolderDao folderDao) {
		this.folderDao = folderDao;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public void setEmailDao(EmailDao emailDao) {
		this.emailDao = emailDao;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

}
