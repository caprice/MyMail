<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>��������</TITLE>
		<META name="Keywords" content="" />
		<META http-equiv=content-type content=text/html;charset=gb2312>
		<STYLE>
BODY {
	FONT-SIZE: 14px;
	FONT-FAMILY: arial, sans-serif
}

DIV.nav {
	MARGIN-TOP: 1ex
}

DIV.nav A {
	FONT-SIZE: 10pt;
	FONT-FAMILY: arial, sans-serif
}

SPAN.nav {
	FONT-WEIGHT: bold;
	FONT-SIZE: 10pt;
	FONT-FAMILY: arial, sans-serif
}

DIV.nav A {
	FONT-SIZE: 12pt;
	COLOR: #0000cc
}

SPAN.big {
	FONT-SIZE: 12pt;
	COLOR: #0000cc
}

DIV.nav A {
	FONT-SIZE: 10pt;
	COLOR: black
}

A.l:link {
	COLOR: #6f6f6f
}

A.u:link {
	COLOR: green
}

H1 {
	FONT-SIZE: 22px
}

UL {
	MARGIN: 1em
}

LI {
	LINE-HEIGHT: 1.6em;
	FONT-FAMILY: ����
}

A {
	COLOR: #00f
}
</STYLE>

		<SCRIPT><!--
var rc=404;
//-->
</SCRIPT>
<script type="text/javascript">
	// 24.У�����룺ֻ������6-20����ĸ�����֡��»���

	function isPasswd(s)
	{
	
		if (s == "") {
	       	var qf=document.getElementById("p1result");
			qf.innerHTML="<img src='images/178.gif'> ���벻��Ϊ�գ�";
			return false;
    	}
		var patrn=/^(\w){6,20}$/;
	
		if (!patrn.exec(s)) {
			var qf=document.getElementById("p1result");
			qf.innerHTML="<img src='images/178.gif'> �����ʽ����ȷ��ֻ������6-20����ĸ�����֡��»��ߣ�";
			return false;
		}
		var qf=document.getElementById("p1result");
		qf.innerHTML="<img src='images/002.gif'> �����ʽ��ȷ��";
		return true;
	
	} 
	
	function passwordEqual(s){
		var password = document.getElementById("password");
		///alert(s);
		// alert(password.value);
		if(password.value != "") {
			if(s != password.value) {
				var qf=document.getElementById("p2result");
				qf.innerHTML="<img src='images/178.gif'> �������벻��ͬ����ȷ�ϣ�";
				return false;
			}else {
				var qf=document.getElementById("p2result");
				qf.innerHTML="<img src='images/002.gif'> ����������ȷ��";
				return true;
			}
		}else {
			var qf=document.getElementById("p2result");
			qf.innerHTML="<img src='images/178.gif'> ȷ�����벻��Ϊ�գ�";
			return false;
		}
	}
</script>
		<META content="MSHTML 6.00.2900.3199" name=GENERATOR>
	</HEAD>
	<BODY text=#000000 bgColor=#ffffff>
		<TABLE cellSpacing=0 cellPadding=2 width="100%" border=0>
			<TBODY>
				<TR>
					<TD noWrap width="1%" rowSpan=3>
						<B><FONT face=times color=#0039b6 size=10>F</FONT> <FONT
							face=times color=#c41200 size=10>o</FONT> <FONT face=times
							color=#f3c518 size=10>r</FONT> <FONT face=times color=#c41200
							size=10>g</FONT>  <FONT face=times color=green
							size=10>e</FONT> <FONT face=times color=#0039b6 size=10>t</FONT>
						</B>
					<TD>
						&nbsp;
					</TD>
				</TR>
				<TR>
					<TD bgColor=#3366cc>
						<FONT face=arial,sans-serif color=#ffffff><B>&nbsp;&nbsp;&nbsp;��������</B>
						</FONT>
					</TD>
				</TR>
				<TR>
					<TD>
						&nbsp;
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<BLOCKQUOTE>
			<H1>
				��ӭʹ�����ټ�����ҵ�ڲ��ʼ�ϵͳ
			</H1>
			�������û���:<br/><br/>
			<form action="queryuserques.do" method="post">
				<table>
					<tr>
						<td align="right">�û�����</td>
						<td align="left"><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="submit" value="ȷ���ύ"/></td>
					</tr>
				</table>
			</form>
			<P></P>
		</BLOCKQUOTE>
		<TABLE cellSpacing=0 cellPadding=0 width="100%">
			<TBODY>
				<TR>
					<TD bgColor=#3366cc>
						<IMG height=4 alt="Դ���Ż�" width=1>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</BODY>
</HTML>