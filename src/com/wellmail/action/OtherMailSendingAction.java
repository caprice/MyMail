package com.wellmail.action;

import java.io.UnsupportedEncodingException;
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

import com.wellmail.bean.MyAutherticator;
import com.wellmail.form.EmailForm;
import com.wellmail.model.OtherMailBoxType;

public class OtherMailSendingAction extends Action {


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		EmailForm ef = (EmailForm) form;
		// ������
		String sender = (String)request.getSession().getAttribute("othermailwriteuname");
		if(sender == null) {
			System.out.println("�Բ���unameΪ��");
		}else{
			System.out.println(sender);
		}
		// �ʼ���Ϣ
		// �ռ���
		String recipients = ef.getRecipients();
		// ����
		String subject = "";
		if(ef.getSubject()!= null && ef.getSubject() != "") {
			subject = new String(ef.getSubject().getBytes("ISO8859-1"),"Gb18030");
		}else {
			subject = "δ�����ʼ�";
		}
		// ����
		String content = "";
		if(ef.getContent() != null && ef.getContent() != "") {
			content = new String(ef.getContent().getBytes("ISO8859-1"),"Gb18030");
		}else {
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
		if (qftemp != null && qftemp != "") {
			qf = new String[qftemp.toCharArray().length];
			qf = qftemp.split(";");
		}

		// ������ַ����
		String attach[] = request.getParameterValues("attach");
		
		OtherMailBoxType omt = (OtherMailBoxType) request.getSession().getAttribute("othermailwritetype");
		
		String smtp = omt.getTsmtp();
		smtp = smtp.substring(5);
		
		sender = sender +"@"+smtp;
		System.out.println(sender);
		
		/**
		 * 
		 * ------------------------------------------ ��ʽ�ʼ�����-----------------------------------------
		 * 
		 */

		// ȷ�������ʼ���������ַ ��������.com��

		// �����ʼ�����Э��
		// ���ɱ�׼�������ļ�����
		Properties props = System.getProperties();
		// ����Ҫ����һ����������������
		props.put("mail.smtp.host", omt.getTsmtp());
		// ����smtp�˿�
		props.put("mail.smtp.port", "25");
		// ���ʼ�Э��
		props.put("mail.transport.protocol", "smtp");
		// ���ʼ�Э��
		props.put("mail.store.protocol", "pop3");

		// �Ƿ�smtp��֤
		props.put("mail.smtp.auth", "true");

		// ���������ʼ�������
		MyAutherticator myauth = new MyAutherticator(sender, "severus125874693");
		// ��prop�������������ӣ�ͬʱ������������null
		Session session = Session.getDefaultInstance(props, myauth);

		// ��Session��������д���������Ϣ����
		Message msg = new MimeMessage(session);
		
		
		if(qf != null ) {
			
			//Ⱥ�������鲻Ϊ��
			
			for(int n = 0; n<qf.length; n++) {
		
				// ������ص��ʼ�����
				try {
					// �����ʼ��ķ����˵�ַ
					msg.setFrom(new InternetAddress(sender));
					
					// �����ʼ����ռ��˵�ַ
					// To:��ͨ���ͣ���Ե�
					// CC�����ͣ�ͬʱ�����
					// BCC�����ͣ���������
					msg.setRecipient(Message.RecipientType.TO, new InternetAddress(qf[n]));		
		
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
						mbpContent.setContent(content, mailtype + ";charset=GB18030");
		
						for (int i = 0; i < attach.length; i++) {
		
							// ���ʼ��еĸ������д���
							MimeBodyPart mbpAttach = new MimeBodyPart();
							// �����ļ���ȡ����Դ
							DataSource fds = new FileDataSource("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"+new String(attach[i].getBytes("ISO8859-1"),"GB18030"));
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
		
		}else {
			
			//��Ⱥ����
			
			// ������ص��ʼ�����
			try {
				// �����ʼ��ķ����˵�ַ
				msg.setFrom(new InternetAddress(sender));
				
				// �����ʼ����ռ��˵�ַ
				// To:��ͨ���ͣ���Ե�
				// CC�����ͣ�ͬʱ�����
				// BCC�����ͣ���������
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
				
				//���ڳ�����
				if(cc != null && cc.length > 0) {
					
					InternetAddress[] ia = new InternetAddress[cc.length]; 
					for(int c = 0; c < cc.length ; c++) {
						ia[c] = new InternetAddress(cc[c]);
					}
				
					//�����ʹ�ó��͵Ļ����Ͳ�����TO�ˣ�ֱ�ӽ�TO��ӵ�Recipients�����
					//TNND��To���ǵ��еģ�û�з�����ȥ����Recipients�оͲ�Ҫ���ˣ�Ҫ�����ظ��ˣ��������飩
					msg.setRecipients(Message.RecipientType.CC, ia);
				}
	
				//����������
				if(bcc != null && bcc.length > 0) {
					
					InternetAddress[] ia = new InternetAddress[bcc.length]; 
					for(int b = 0; b < bcc.length ; b++) {
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
					mbpContent.setContent(content, mailtype + ";charset=GB18030");
	
					for (int i = 0; i < attach.length; i++) {
	
						// ���ʼ��еĸ������д���
						MimeBodyPart mbpAttach = new MimeBodyPart();
						// �����ļ���ȡ����Դ
						DataSource fds = new FileDataSource("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"+new String(attach[i].getBytes("ISO8859-1"),"GB18030"));
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
	
				// ����
				//Transport.send(msg);

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


}
