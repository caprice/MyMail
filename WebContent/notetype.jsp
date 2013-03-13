<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>���±�</title>
		<link href="images/zh-cn/globle_v1.css" rel="stylesheet"
			type="text/css">
		<link href="images/zh-cn/inbox.css" rel="stylesheet" type="text/css">
		<link href="images/zh-cn/skin_blue.css" rel="stylesheet"
			type="text/css" id="lnkSkin" />
	</head>

	<script type="text/javascript">
	function changeframe(item, sortname, src) { 
		if(item != "" && sortname != "") { 
			window.top.frames['mainFrame'].getObject('show_text').innerHTML = sortname + "  <img src=images/slide.gif broder=0 />  " + item 
		} 
		if(src != "") { 
			window.top.frames['manFrame'].location = src 
		} 
	} 
</script>

	<script type="text/javascript">   
        function checkall(){   
            var all = document.getElementById("oFormCheckAll");   
            var sub = document.getElementsByName("mid");   
            if(all.checked==true){   
                for(var i=0;i<sub.length;i++){   
                    sub[i].checked=true;   
                }   
            }else{   
                for(var i=0;i<sub.length;i++){   
                    sub[i].checked=false;   
                }   
            }   
        }   
    </script>
	<script language="javascript"> 
	function action1() 
	{ 
	   document.messageForm.action="deletenotetype.do"; 
	   document.messageForm.submit(); 
	} 
	function action2() 
	{ 
	   document.messageForm.action="createnotetype.jsp"; 
	   document.messageForm.submit(); 
	} 
</script>
	<body class="All_C_Page Inbox">
		<form name="messageForm" id="oFormMessage" method="post">
			<div class="ContentWp">
				<div class="ContentThemeWp">
					<div class="gTitle">
						���±���ҳ
					</div>

					<div class="gToolbar gTbrTop">
						<input type="button" onclick="action1()" class="Btn BtnNml ImpBtn"
							value="ɾ ��" />
						<input type="button" onclick="action2()" class="Btn BtnNml"
							value="�½�" />
						<div class="Extra">
							<span class="Txt"><span class="Unable">��ҳ</span>&nbsp;&nbsp;
								<span class="Unable">��ҳ</span>&nbsp;&nbsp; <span class="Unable">��ҳ</span>&nbsp;&nbsp;
								<span class="Unable">ĩҳ</span> </span>
							<select class="SelA" style="margin-right: 0px">
								<option value="1" selected>
									1/1
								</option>
							</select>
						</div>
					</div>

					<div class="Ibx_Main_Wp">
						<table id="oTableHead" class="Ibx_gTable F2Img Ibx_gTable_TT">
							<tbody>
								<tr>
									<th class="Ibx_Th_Chkbx">
										<input id="oFormCheckAll" onclick="checkall()" type="checkbox"
											title="ȫѡ/��ѡ����ҳ�����ʼ�" />
									</th>
									<th class="Ibx_Th_Subject" width="50%">
										<a href="#" title="����ɰ�������">����</a>
									</th>
									<th class="Ibx_Th_Size">
										<a href="#" title="����ɰ�������">����</a>
									</th>
								</tr>
							<tbody>
						</table>
						<div class="Ibx_Lst_dWp dWpOpen">
							<table class="Ibx_gTable Ibx_gTable_Con" id="oTableCOUNT0">
								<tbody>
									<logic:notPresent name="noteTypeList" scope="session">
										<tr class="I_Mark0">
											<td align="center">
												�Բ��𣬲�ѯ�������Ժ����ԣ�
											</td>
										</tr>
									</logic:notPresent>
									<logic:present name="noteTypeList" scope="session">

										<logic:empty name="noteTypeList">
											<tr class="I_Mark0">
												<td align="center">
													�Բ��𣬵�ǰû�м��±����ͣ�
												</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="noteTypeList">
											<c:forEach items="${noteTypeList}" var="t">
												<tr class="I_Mark0" align="left">
													<td class="Ibx_Td_Chkbx">
														<input id="checked" name="mid" type="checkbox"
															title="ѡ��/��ѡ" value="${t.notetypeid }" />
													</td>
													<td class="Ibx_Td_Subject" align="left" width="50%">
														${t.notetypename }
													</td>
													<td class="Ibx_Td_Size">
														<span title="5637 �ֽ�"> <a
															href="deleteonenotetype.do?notetypeid=${t.notetypeid}">ɾ��</a>
														</span>
													</td>
												</tr>
											</c:forEach>
										</logic:notEmpty>
									</logic:present>
								<tbody>
							</table>
						</div>
					</div>

					<div class="gToolbar gTbrBtm">
						<input type="button" onclick="action2()" class="Btn BtnNml ImpBtn"
							value="ɾ ��" />
						<input type="button" onclick="createnotetype.jsp"
							class="Btn BtnNml" value="�½�" />
						<div class="Extra">
							<span class="Txt"><span class="Unable">��ҳ</span>&nbsp;&nbsp;
								<span class="Unable">��ҳ</span>&nbsp;&nbsp; <span class="Unable">��ҳ</span>&nbsp;&nbsp;
								<span class="Unable">ĩҳ</span> </span>
							<select action="page" class="SelA" style="margin-right: 0px">
								<option value="1" selected>
									1/1
								</option>
							</select>
						</div>
					</div>

				</div>
			</div>
		</form>
	</body>
</html>
