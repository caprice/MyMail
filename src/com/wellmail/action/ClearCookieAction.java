package com.wellmail.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ClearCookieAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		Cookie cookie1 = new Cookie("username", "");//����û���Cookie
        //�½�һ��Cook,�Ǽ�ֵ(key-value)��ϵ   
        int month1 = 60 * 60 * 24 * 30;// ����һ����(��*��*ʱ*��)   
        cookie1.setMaxAge(month1);// ����Cook��������Ϊһ����   
        cookie1.setPath("/");//���ô���·��   
        //��cook��ӵ�response�����С���response���󷵻ظ�����   

        response.addCookie(cookie1);
        
        Cookie cookie2 = new Cookie("pwdHash", "");        //�������Cookie
        //�½�һ��Cook,�Ǽ�ֵ(key-value)��ϵ   
        int month2 = 60 * 60 * 24 * 30;// ����һ����(��*��*ʱ*��)   
        cookie2.setMaxAge(month2);// ����Cook��������Ϊһ����   
        cookie2.setPath("/");//���ô���·��   
        //��cook��ӵ�response�����С���response���󷵻ظ�����   

        response.addCookie(cookie2);
		
		return mapping.findForward("success");
	}
}
