package com.wellmail.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
         doGet(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

            httpServletResponse.setContentType("text/html;charset=GB18030");
            PrintWriter out = httpServletResponse.getWriter();

            //1.ȡ����  

            String path = httpServletRequest.getParameter("path");
            path = new String(path.getBytes("ISO8859-1"),"GB18030");
            //System.out.println("path:"+path);
            //2.��������Ƿ�������  

            if(path == null || path.length() == 0) {
                out.println("�ļ�������Ϊ��");
            } else {

                //3.У�����

                
                
                try{
                	/* FileInputStream in = new FileInputStream(path);
              	  FileOutputStream ot = new FileOutputStream("D:/upload/"+name);

              	  int i = 0;
              	  while((i= in.read()) != -1){
              	    ot.write(i);
              	  }
              	  
              	  ot.close();
              	  in.close();*/
                	System.out.println("path:"+path);
              	  
              	 File srcFile  =  new File(path);   
                 if(!srcFile.exists() || !srcFile.isFile()){   
                     throw new RuntimeException("Դ�ļ�:'" + path + "'û�б����֣�");   
                 }   
                 //path:C:\Documents and Settings\Administrator\����\��163��������Ч css.html
                // D:\ProgramFiles\apache-tomcat-6.0.14\bin\��163��������Ч css.html
                 File desFile = new File("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"+srcFile.getName());   
                 System.out.println(desFile.getAbsolutePath());
                 if(desFile.isDirectory()){   
                     desFile = new File(desFile,srcFile.getName());   
                 }   
                 BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcFile));   
                 BufferedOutputStream ot=new BufferedOutputStream(new FileOutputStream(desFile));   
                 byte[] buff = new byte[4084];   
                 int length=-1;   
                 while((length=in.read(buff))!=-1){   
                     ot.write(buff,0,length);   
                 }   
                 out.flush();
                 in.close();   
                 ot.close();   
                 out.println("�ϴ��ɹ�");
              	 }catch(Exception ex){
              		  ex.printStackTrace();
              	 }
                
        		//4.�봫ͳӦ�ò�֮ͬ������һ����Ҫ���û���Ҫ�����ݷ��ظ�ҳ��ˣ������ǽ�һ���µ�ҳ�淵�ظ��ͻ���

              //  if(path.equals(username)) {
                	//out.println("name:"+name);
                	//out.println("username:"+username);
                 //   out.println("<img src='images/178.gif'> �û�����<font color='red'>"+ path +"</font>���Ѿ����ڣ�");
                  //  break;
               // }
                            
    			//if(!path.equals(username)) {
    				//out.println("==================");
                	//out.println("name:"+name);
                	//out.println("username:"+username);
    			// out.println("<img src='images/002.gif'>�û�����<font color='green'>"+ path +"</font>����δ����,����ʹ�ã�");
    			//}

           
    }
}
}