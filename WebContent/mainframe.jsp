<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" href="css/common.css" type="text/css" />
		<title>����������</title>
	</head>
	<script type="text/javascript">
var preClassName = "man_nav_1";
function list_sub_nav(Id,sortname){
   if(preClassName != ""){
      getObject(preClassName).className="bg_image";
   }
   if(getObject(Id).className == "bg_image"){
      getObject(Id).className="bg_image_onclick";
      preClassName = Id;
	  showInnerText(Id);
	  window.top.frames['leftFrame'].outlookbar.getbytitle(sortname);
	  window.top.frames['leftFrame'].outlookbar.getdefaultnav(sortname);
   }
}

function showInnerText(Id){
    var switchId = parseInt(Id.substring(8));
	var showText = "�Բ���û����Ϣ��";
	switch(switchId){
	    case 1:
		   showText =  "��ӭʹ�������׻�����www.51ehuo.cn����̨����ϵͳ!";
		   break;
	    case 2:
		   showText =  "system setting!";
		   break;
	    case 3:
		   showText =  "User Manage";
		   break;		   
	    case 4:
		   showText =  "Chanage Manage";
		   break;	
	    case 5:
		   showText =  "Ad AND news!";
		   break;		   		   
	}
	getObject('show_text').innerHTML = showText;
}
 //��ȡ�������Լ��ݷ���
 function getObject(objectId) {
    if(document.getElementById && document.getElementById(objectId)) {
	// W3C DOM
	return document.getElementById(objectId);
    } else if (document.all && document.all(objectId)) {
	// MSIE 4 DOM
	return document.all(objectId);
    } else if (document.layers && document.layers[objectId]) {
	// NN 4 DOM.. note: this won't find nested layers
	return document.layers[objectId];
    } else {
	return false;
    }
}
</script>
	<body>
		<div id="nav">
			<ul>
				<li id="man_nav_1" class="bg_image_onclick">
					������ҳ
				</li>
			</ul>
		</div>
		<div id="sub_info">
			&nbsp;&nbsp;
			<img src="images/hi.gif" />
			&nbsp;
			<span id="show_text">��ӭʹ�� <font color="blue"><b>M</b>
			</font> <font color="red"><b>y</b>
			</font> <font color="green"><b>M</b>
			</font> <font color="blue"><b>a</b>
			</font> <font color="green"><b>i</b>
			</font> <font color="red"><b>l</b>
			</font> <font color="black">.</font> <font color="blue"><b>C</b>
			</font> <font color="red"><b>o</b>
			</font> <font color="green"><b>m</b>
			</font> ϵͳ!</span>
		</div>
	</body>
</html>
