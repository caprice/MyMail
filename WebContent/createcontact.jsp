<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<TITLE>�������ļ���</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GB18030">
		<META http-equiv=Content-Language content=GB18030>
		<STYLE type=text/css>
A:link {
	COLOR: #555555;
	TEXT-DECORATION: none
}

A:visited {
	COLOR: #555555;
	TEXT-DECORATION: none
}

A:active {
	COLOR: #555555;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #6f9822;
	TEXT-DECORATION: none
}

.text {
	FONT-SIZE: 12px;
	COLOR: #555555;
	FONT-FAMILY: "";
	TEXT-DECORATION: none
}

.STYLE1 {
	font-size: 13px
}

.STYLE2 {
	font-size: 12px
}

.STYLE3 {
	font-size: 11px
}
</STYLE>
		<script type="text/javascript">
	function checkcontact() {
		var contactname = document.getElementById("contactname");
		var contactemail = document.getElementById("contactemail");
		
		if(contactname.value == "" ) {
			var qfcode=document.getElementById("contactresult1");
			qfcode.innerHTML="<img src='images/178.gif'> ��ϵ������������д��";
			return false;
		}
		
		var patrn=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(contactemail.value != "") {
			if(!patrn.exec(contactemail.value)) {
				var qfcode=document.getElementById("contactresult2");
				qfcode.innerHTML="<img src='images/178.gif'> ��ϵ��Email��ַ��ʽ����ȷ��";
				return false;
			}
		}else {
			var qfcode=document.getElementById("contactresult2");
			qfcode.innerHTML="<img src='images/178.gif'> ��ϵ��Email��ַ������д��";
			return false;
		}
	}
</script>
	</head>
	<BODY>
		<BR>
		<BR>
		<BR>
		<BR>
		<BR>
		<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"
			align=center border=0>

			<TR>
				<TD vAlign="center" align="middle">
					<TABLE cellSpacing=0 cellPadding=0 width=500 align=center border=0>

						<TR>
							<TD width=17 height=17>
								<IMG height=17 src="images/co_01.gif" width=17>
							</TD>
							<TD width=316 background="bg01.gif"></TD>
							<TD width=17 height=17>
								<IMG height=17 src="images/co_02.gif" width=17>
							</TD>
						</TR>
						<TR>
							<TD background=images/bg02.gif></TD>
							<TD>
								<TABLE class=text cellSpacing=0 cellPadding=10 width="100%"
									align=center border=0>
									<TR>
										<TD>
											<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
												<TR>
													<TD width=20>
													</TD>
													<TD>
														<IMG height=66 src="images/createcontact.gif" width=400>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD>
											<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
												<TR>
													<TD background="images/dot_01.gif" height=1></TD>
												</TR>
											</TABLE>
											<BR>

											<%
												int groupid = Integer.parseInt(request.getParameter("groupid"));
											%>
											<TABLE class=text cellSpacing=0 cellPadding=0 width="100%"
												border=0>
												<TR>
													<TD width=20></TD>
													<TD>
														<div>
															<form action="createcontact.do">
																<input type="hidden" name="groupid"
																	value="<%=groupid%>" />
																<table>
																	<tr>
																		<td align="right">
																			������
																		</td>
																		<td align="left">
																			<input type="text" id="contactname"
																				name="contactname" onblur="checkcontact()" />
																			<br />
																			<span id="contactresult1"></span>
																		</td>
																	</tr>
																	<tr>
																		<td align="right">
																			Email��
																		</td>
																		<td align="left">
																			<input type="text" id="contactemail"
																				name="contactemail" onblur="checkcontact()" />
																			<br />
																			<span id="contactresult2"></span>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" align="center">
																			<input type="submit" value="�� ��" />
																		</td>
																	</tr>
																</table>


															</form>
														</div>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
								</TABLE>
							</TD>
							<TD background="images/bg03.gif"></TD>
						</TR>
						<TR>
							<TD width=17 height=17>
								<IMG height=17 src="images/co_03.gif" width=17>
							</TD>
							<TD background="images/bg04.gif" height=17></TD>
							<TD width=17 height=17>
								<IMG height=17 src="images/co_04.gif" width=17>
							</TD>
						</TR>
					</TABLE>
					<TABLE class=text cellSpacing=0 cellPadding=0 width=500
						align=center border=0>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD align="middle"></TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
		</TABLE>
	</BODY>