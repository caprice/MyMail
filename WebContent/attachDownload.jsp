<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030" import="java.io.*,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			int attachmentid = Integer.parseInt(request
					.getParameter("attachmentid"));

			String driverName = "com.mysql.jdbc.Driver";
			String dbURL = "jdbc:mysql://localhost/wellmail?autoReconnect=true";
			String userName = "root";
			String userPwd = "root";

			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			String attachmentname = "";

			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL, userName, userPwd);
				String sql = "select * from attachment where attachmentid="
						+ attachmentid;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					attachmentname = rs.getString("attachmentname");
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
						rs = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (stmt != null) {
					try {
						stmt.close();
						stmt = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (conn != null) {
					try {
						conn.close();
						conn = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			try {
				File file = new File(
						"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
								+ attachmentname);
				String fileName = file.getName();
				InputStream is = new FileInputStream(file);
				OutputStream os = response.getOutputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				BufferedOutputStream bos = new BufferedOutputStream(os);

				fileName = java.net.URLEncoder.encode(fileName, "UTF-8");// ���������ļ���������
				fileName = new String(fileName.getBytes("UTF-8"), "GBK");// ���������ļ���������
				response.reset();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/x-msdownload");// ��ͬ���͵��ļ���Ӧ��ͬ��MIME����
				response.setHeader("Content-Disposition",
						"attachment; filename=" + fileName);
				int bytesRead = 0;
				byte[] buffer = new byte[1024];
				while ((bytesRead = bis.read(buffer)) != -1) {
					bos.write(buffer, 0, bytesRead);// ���ļ����͵��ͻ���
				}

				out.clear();
				out = pageContext.pushBody();

				bos.flush();
				bis.close();
				bos.close();
				is.close();
				os.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		%>
	</body>
</html>