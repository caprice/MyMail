package com.wellmail.servlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DeleteServlet extends HttpServlet {
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
            
            File srcFile  = null;

            //2.��������Ƿ�������  

            if(path == null || path.length() == 0) {
                out.println("�û�������Ϊ��");
            } else {

                //3.У�����

                path = new String(path.getBytes("ISO8859-1"),"GB18030");
                System.out.println("delete path:"+path);
                
                try{
                	/* FileInputStream in = new FileInputStream(path);
              	  FileOutputStream ot = new FileOutputStream("D:/upload/"+name);

              	  int i = 0;
              	  while((i= in.read()) != -1){
              	    ot.write(i);
              	  }
              	  
              	  ot.close();
              	  in.close();*/
                	srcFile  =  new File(path);   
                	File delFile = new File("D:/upload/"+srcFile.getName());
              	  
                   boolean   flag   =   (delFile).delete();   
                   System.out.println(flag);   
              	 
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

            out.println("�ļ�"+ srcFile.getName()+"ɾ���ɹ�");
    }
}
}