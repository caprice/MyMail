<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" href="css/common.css" type="text/css" />
		<title>��ʾ/������ർ����</title>
	</head>
	<script language="JavaScript">
function Submit_onclick(){
	if(parent.myFrame.cols == "199,7,*") {
		parent.myFrame.cols="0,7,*";
		document.getElementById("ImgArrow").src="images/switch_right.gif";
		document.getElementById("ImgArrow").alt="����ർ����";
	} else {
		parent.myFrame.cols="199,7,*";
		document.getElementById("ImgArrow").src="images/switch_left.gif";
		document.getElementById("ImgArrow").alt="������ർ����";
	}
}

function MyLoad() {
	if(window.parent.location.href.indexOf("MainUrl")>0) {
		window.top.midFrame.document.getElementById("ImgArrow").src="images/switch_right.gif";
	}
}
</script>
	<body onload="MyLoad()">
		<div id="switchpic">
			<a href="javascript:Submit_onclick()"><img
					src="images/switch_left.gif" alt="������ർ����" id="ImgArrow" />
			</a>
		</div>
	</body>
</html>
