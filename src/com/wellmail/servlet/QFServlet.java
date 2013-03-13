package com.wellmail.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class QFServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
         doGet(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

          try {
            httpServletResponse.setContentType("text/html;charset=GB18030");
            PrintWriter out = httpServletResponse.getWriter();

            //1.ȡ����  

            String name = httpServletRequest.getParameter("name");

        	//System.out.println("name1:"+name);

            //2.��������Ƿ�������  

            if(name == null || name.length() == 0) {
                out.println("ϵͳ��������Ϊ�գ�");
            } else {

                //3.У�����

                name = new String(name.getBytes("ISO8859-1"),"GB18030");
                
                
                if(name.equals("ʹ��Ⱥ������")){
                	//System.out.println("name2:"+name);
                	out.println("<table class='rTable'  style='padding-top:5px'><tr valign='middle'><td align='right' style='padding-top:5px'>"
                			+"<input type='button' value='Ⱥ����'></td><td style='padding-top:5px'>"
                			+"<input type='text' name='qf' class='login_table_text1_input' style='font-weight:normal;font-family:Verdana, Arial, Helvetica, sans-serif' value='' size='90'/>"
                			+"</td></tr></table>");
                }else if(name.equals("ɾ��Ⱥ������")) {
                	//System.out.println("name3:"+name);
                	out.println("");
                }
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
