package com.wellmail.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AJAXServlet extends HttpServlet {
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

            String old = httpServletRequest.getParameter("name");
           

            //2.��������Ƿ�������  

            if(old == null || old.length() == 0) {
                out.println("�û�������Ϊ��");
            } else {

                //3.У�����

                String name = new String(old.getBytes("ISO8859-1"));
                
                //���ݿ�����
                Connection conn = null;
        		Statement stmt = null;
        		ResultSet rs = null;
        		try {
        			Class.forName("com.mysql.jdbc.Driver");
        			conn = DriverManager
        			    .getConnection("jdbc:mysql://localhost/wellmail", "root", "root");
        			String sql = "select username from users";
        			stmt = conn.createStatement();
        			rs = stmt.executeQuery(sql);
        			//System.out.println("deptno	dname");
        			String username = "";
        			while(rs.next()) {
        				username = rs.getString("username");
        				
        				 //4.�봫ͳӦ�ò�֮ͬ������һ����Ҫ���û���Ҫ�����ݷ��ظ�ҳ��ˣ������ǽ�һ���µ�ҳ�淵�ظ��ͻ���

                        if(name.equals(username)) {
                        	//out.println("name:"+name);
                        	//out.println("username:"+username);
                            out.println("<img src='images/178.gif'> �û�����<font color='red'>"+ name +"</font>���Ѿ����ڣ�");
                            break;
                        }
                            
        			}
        			if(!name.equals(username)) {
        				//out.println("==================");
                    	//out.println("name:"+name);
                    	//out.println("username:"+username);
        			 out.println("<img src='images/002.gif'>�û�����<font color='green'>"+ name +"</font>����δ����,����ʹ�ã�");
        			}
        		} catch (ClassNotFoundException e) {
        			e.printStackTrace();
        		} catch (SQLException e) {
        			e.printStackTrace();
        		} finally {
        			if(rs != null) {
        				try {
        					rs.close();
        					rs = null;
        				} catch (SQLException e) {
        					e.printStackTrace();
        				}
        			}
        			if(stmt != null) {
        				try {
        					stmt.close();
        					stmt = null;
        				}catch (SQLException e) {
        					e.printStackTrace();
        				}
        			}
        			if(conn != null) {
        				try {
        					conn.close();
        					conn = null;
        				}catch(SQLException e) {
        					e.printStackTrace();
        				}
        			}
        		}

            }
           // out.println("<br/> <a href=\"index.html\">����У�����</a>");
              
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
