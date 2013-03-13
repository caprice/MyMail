package com.test.mail.db;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.james.security.DigestUtil;
  
/**  
 * ͨ���������ݿ������James�û�  
 *   
 * @author Michael.Zhang  
 *         Mail:bauble@126.com 
 *         qq:675980656
 */  
public class AddUserByDB {   
    public static void main(String[] args) {   
        
    	// �������ݿ�   
        String driverName = "com.mysql.jdbc.Driver";   
        String dbURL = "jdbc:mysql://localhost/testmail?autoReconnect=true";   
        String userName = "root";   
        String userPwd = "root";   
        
        Connection conn = null;   
        Statement stmt = null;
        
        try {   
            Class.forName(driverName);   
            conn = DriverManager.getConnection(dbURL, userName, userPwd);  
            
            // ���첢ִ��SQL��䣬�ؼ�����DigestUtil.digestString("881213", "SHA")��ʵ�ֶ������SHA����   
            // ע���û���Ϣ�ĺ��ĸ�������Ҫʹ�ã�'SHA',0,NULL,0,null�����ĸ�Ĭ��ֵ�����ô����½��û����ܲ���ʹ��   
            String sql = "insert into users values('severus','"  
                + DigestUtil.digestString("861213", "SHA")   
                + "','SHA',0,NULL,0,null)";   
	        stmt = conn.createStatement();
	        stmt.executeUpdate(sql);   
	        conn.close();   
	        System.out.println("�û���ӳɹ�"); 
        } catch (ClassNotFoundException e) {   
            e.printStackTrace();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally{
			if(stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}  
    }   
}  
