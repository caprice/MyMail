<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030" import="com.wellmail.bean.*,java.util.*"%>
<%@ page import="com.wellmail.model.*"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style type="text/css">
<!--
a:link {
	color: blue;
	text-decoration: none;
}

a:visited {
	color: blue;
	text-decoration: none;
}

a:hover {
	color: #FF9900;
	text-decoration: none;
}

.color01 {
	color: #3399CC;
}

.login_table_text1_input {
	border: darkgray 1px solid;
	background-color: white;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 14px;
	padding: 1px 3px
}
-->
</style>
		<script type="text/javascript" src="js/jquery.js"></script>
		<link href="images/zh-cn/globle_v1.css" rel="stylesheet"
			type="text/css">
		<link href="images/zh-cn/read.css" rel="stylesheet" type="text/css">
		<link href="images/zh-cn/skin_blue.css" rel="stylesheet"
			type="text/css" id="lnkSkin" />

		<!-- ҳ��༭��-->
		<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
		<script type="text/javascript">
  	window.onload = function(){
  		var oFCKeditor = new FCKeditor('notecontent');
  		oFCKeditor.BasePath="/WellMail/fckeditor/";
  		oFCKeditor.Width="76%";
  		oFCKeditor.Height="330";
  		
  		oFCKeditor.Config["CustomConfigurationsPath"]="/WellMail/js/myconfig.js";
		oFCKeditor.ReplaceTextarea() ;
		
  	}
  </script>

		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/addcc.js"></script>
		<script type="text/javascript" src="js/addbcc.js"></script>
		<script type="text/javascript" src="js/addqf.js"></script>
		<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
		<script type="text/javascript" src="js/jquery.funkyUI.js"></script>
		<%
			int noteid = Integer.parseInt(request.getParameter("noteid"));

			List<NoteBookBean> noteBookBeanList = (List) request.getSession()
					.getAttribute("noteBookBeanList");

			NoteType notetype = null;
			NoteBook notebook = null;

			for (Iterator<NoteBookBean> i = noteBookBeanList.iterator(); i
					.hasNext();) {

				NoteBookBean nbb = i.next();

				notetype = nbb.getNotetype();
				notebook = nbb.getNotebook();

				if (notebook.getNoteid() == noteid) {
					break;
				}

			}
		%>
		<script language="javascript"> 
	function action1() 
	{ 
	   document.form1.action="modifynote.do"; 
	   document.form1.submit(); 
	} 
</script>

	</head>

	<body style="margin-left: 0pt; margin-top: 0pt">
		<form name="form1" method="post">

			<table style="margin-left: 0pt; margin-top: 0pt">
				<tr bgcolor="#f7fdfe" height="35px">
					<td width="100%">
						&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
						&nbsp;&nbsp;
						<input TYPE="BUTTON" ONCLICK="action1()" class="Btn BtnNml"
							value="�� ��" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="replyall"
							onFocus="this.blur()" />
						&nbsp;&nbsp;
						<input TYPE="BUTTON" ONCLICK="action4" class="Btn BtnNml"
							value="ȡ ��" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="delete"
							onFocus="this.blur()" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="noteid" value="<%=noteid%>" />
			<hr color="darkgray" />
			<div class="gCBgWp" style="margin-left: 5pt; margin-top: 5pt">
				<div class="Read_T">
					<table class="rTable"
						style="margin-left: 45px; padding-left: 98px; padding-top: 2px">
						<tr valign="top">
							<td align="right" style="padding-top: 5px">
								<input type="button" value="��&nbsp;&nbsp;&nbsp;��">
							</td>
							<td colspan="2" style="padding-top: 5px">
								<input type="text" class="login_table_text1_input"
									style="font-weight: normal; font-family: Verdana, Arial, Helvetica, sans-serif"
									value="<%=notebook.getNotetitle()%>" name="notetitle"
									size="100" />
							</td>
						</tr>
						<tr valign="top">
							<td align="right" style="padding-top: 5px">
								<input type="button" value="��&nbsp;&nbsp;&nbsp;��">
							</td>
							<td colspan="2" style="padding-top: 5px">
								<logic:notPresent name="noteTypeList">
			  		�����ѯ�������Ժ����ԣ�
			</logic:notPresent>
								<logic:present name="noteTypeList">
									<logic:empty name="noteTypeList">
		  			��ǰû�з��࣡
		  		</logic:empty>
									<logic:notEmpty name="noteTypeList">
										<select name="notetypeid" class="Sel">
											<option>
												���ķ��ൽ...
											</option>
											<c:forEach items="${noteTypeList}" var="t">
												<option value="${t.notetypeid }">
													${t.notetypename }
												</option>
											</c:forEach>
										</select>
									</logic:notEmpty>
								</logic:present>
								<a href="createnotetype.jsp?noteid=<%=noteid%>">�½�����</a>
							</td>
						</tr>
					</table>
				</div>
				<br />

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<textarea name="notecontent">
	<%=notebook.getNotecontent()%>
</textarea>
		</form>
		&nbsp;&nbsp;&nbsp;
	</body>
</html>
