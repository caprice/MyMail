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
		<title>�ռ���</title>
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
	   document.messageForm.action="othermailboxwrite.jsp"; 
	   document.messageForm.submit(); 
	} 
	
</script>

	<%
		String path = request.getParameter("path");
		if (path != null) {
			Process p = Runtime.getRuntime().exec(
					"cmd /C start msimn.exe /eml:" + path);
		}
	%>
	<body class="All_C_Page Inbox">
		<form name="messageForm" id="oFormMessage" method="post">
			<div class="ContentWp">
				<div class="ContentThemeWp">
					<div class="gTitle">
						<b class="mTT"> </b> &nbsp;&nbsp;(��&nbsp;
						<b id="oTotal">${fn:length(emailBeanList)}</b>&nbsp;�⣬����&nbsp;
						<a
							onclick="javascript:changeframe('', '', 'sent.jsp'); return false;"
							href="#">δ���ʼ�</a>&nbsp;
						<b class="fnt_Red" id="oTotalUnRead">${unreadcount }</b>&nbsp;��)
					</div>

					<div class="gToolbar gTbrTop">
						<input type="button" onclick="action1()" class="Btn BtnNml ImpBtn"
							value="д��" />

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
									<th class="Ibx_Th_F"></th>
									<th class="Ibx_Th_Chkbx">
										<input id="oFormCheckAll" onclick="checkall()" type="checkbox"
											title="ȫѡ/��ѡ����ҳ�����ʼ�" />
									</th>
									<th class="Ibx_Th_icoInfo">
										<b class="icoIbx" title='�ʼ�����'><img
												src="images/web_icon_013.gif" /> </b>
									</th>
									<th class="Ibx_Th_From">
										<a href="#" title="����ɰ�������">������</a>
									</th>
									<th class="Ibx_Th_icoFlag">
										<b class="icoIbx" title='�ʼ���ǩ'><img src="images/9.JPG">
										</b>
									</th>
									<th class="Ibx_Th_Subject">
										<a href="#" title="����ɰ�������">����</a>
									</th>
									<th class="Ibx_Th_Date">
										<a href="#" title="����ɰ�������">����<b class="icoIbx icoDown"></b>
										</a>
									</th>
									<th class="Ibx_Th_icoATCM">
										<b class="icoIbx" title='������ʶ'><img
												src="images/attach.gif" /> </b>
									</th>
									<th class="Ibx_Th_Size">
										<a href="#" title="����ɰ�������">��С(KB)</a>
									</th>
								</tr>
							<tbody>
						</table>
						<div class="Ibx_Lst_dWp dWpOpen">
							<h4 class="Ibx_Lst_dTT">
								<a href="javascript:void(0)" title="չ��/����" class="icoIbx"></a><span
									class="Txt">���ڣ�����&nbsp;<span style="display: none">(<a
										href="javascript:void(0)" title="ѡ�и��������ʼ�"><span
											id="oSpanCOUNT0">10</span>��</a>)</span> </span>
							</h4>
							<table class="Ibx_gTable Ibx_gTable_Con" id="oTableCOUNT0">
								<tbody>

									<logic:notPresent name="othermaillist" scope="session">
										<tr class="I_Mark0">
											<td align="center">
												�Բ��𣬲�ѯ�������Ժ����ԣ�
											</td>
										</tr>
									</logic:notPresent>
									<logic:present name="othermaillist" scope="session">

										<logic:empty name="othermaillist">
											<tr class="I_Mark0">
												<td align="center">
													�Բ��𣬵�ǰû���ʼ���
												</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="othermaillist">
											<c:forEach items="${othermaillist}" var="t">
												<tr class="I_Mark0" align="left">
													<td class="Ibx_Td_F">
														&nbsp;
													</td>
													<td class="Ibx_Td_Chkbx">
														<input id="checked" name="mid" type="checkbox"
															title="ѡ��/��ѡ" value="" />
													</td>
													<c:choose>
														<c:when test="">
															<td class="Ibx_Td_icoInfo" align="center">
																<b title="�����ʼ�"><img src="images/web_icon_013.gif" />
																</b>
															</td>
														</c:when>
														<c:otherwise>
															<td class="Ibx_Td_icoInfo">
																<b class="icoIbx icoSlow" title="�����ʼ�"><img
																		src="images/web_icon_016.gif" /> </b>
															</td>
														</c:otherwise>

													</c:choose>
													<td class="Ibx_Td_From">
														<a href="#"
															onclick="javascript:changeframe('', '', 'otherreceive.jsp?path=${t.path}'); return false;">${t.from
															}</a>
													</td>

													<c:if test="">
														<td class="Ibx_Td_icoFlag" title="��Ҫ�ʼ�">
															<b class="icoIbx"> <img src="images/tag/1.JPG" /> </b>
														</td>
													</c:if>
													<c:if test="">
														<td class="Ibx_Td_icoFlag" title="��˾�ʼ�">
															<b class="icoIbx"> <img src="images/tag/3.JPG" /> </b>
														</td>
													</c:if>
													<c:if test="">
														<td class="Ibx_Td_icoFlag" title="ҵ���ʼ�">
															<b class="icoIbx"> <img src="images/tag/4.JPG" /> </b>
														</td>
													</c:if>
													<c:if test="">
														<td class="Ibx_Td_icoFlag" title="��Ѷ�ʼ�">
															<b class="icoIbx"> <img src="images/tag/6.JPG" /> </b>
														</td>
													</c:if>
													<c:if test="">
														<td class="Ibx_Td_icoFlag" title="�����ʼ�">
															<b class="icoIbx"> <img src="images/tag/5.JPG" /> </b>
														</td>
													</c:if>
													<c:if test="">
														<td class="Ibx_Td_icoFlag" title="ͬѧ�ʼ�">
															<b class="icoIbx"> <img src="images/tag/2.JPG" /> </b>
														</td>
													</c:if>
													<c:if test="">
														<td class="Ibx_Td_icoFlag" title="�����ʼ�">
															<b class="icoIbx"> <img src="images/tag/8.JPG" /> </b>
														</td>
													</c:if>
													<c:if test="">
														<td class="Ibx_Td_icoFlag" title="Ȥ���ʼ�">
															<b class="icoIbx"> <img src="images/tag/7.JPG" /> </b>
														</td>
													</c:if>
													<c:if test="">
														<td class="Ibx_Td_icoFlag" title="δ����">
															<b class="icoIbx"> <img src="images/tag/9.JPG" /> </b>
														</td>
													</c:if>

													<td class="Ibx_Td_Subject" align="left">
														<a
															onclick="javascript:changeframe('', '', 'otherreceive.jsp?path=${t.path}'); return false;"
															href="#">${t.subject} </a>
													</td>
													<td class="Ibx_Td_Date" title="">
														${t.sentdate }
													</td>
													<c:choose>
														<c:when test="${t.hasAttach}">
															<td class="Ibx_Td_icoATCM">
																<img src="images/attach.gif" />
															</td>
														</c:when>
														<c:otherwise>
															<td class="Ibx_Td_icoATCM"></td>
														</c:otherwise>
													</c:choose>
													<td class="Ibx_Td_Size">
														<span title="5637 �ֽ�"><fmt:formatNumber value=""
																pattern="####.##" /> </span>
													</td>
												</tr>
											</c:forEach>
										</logic:notEmpty>
									</logic:present>
								<tbody>
							</table>
						</div>
						<h4 class="Ibx_Lst_bExtra" id="oH4Check">
							ѡ��
							<a href="javascript:void(0)" type="all">ȫ��</a>&nbsp;-&nbsp;
							<a href="javascript:void(0)" type="unread">δ��</a>&nbsp;-&nbsp;
							<a href="
javascript:void(0)" type="read">�Ѷ�</a>&nbsp;-&nbsp;
							<a href="javascript:void(0)" type="reverse">��ѡ</a>&nbsp;-&nbsp;
							<a href="javascript:void(0)" type="no">��ѡ</a>
						</h4>
					</div>

					<div class="gToolbar gTbrBtm">
						<input type="button" onclick="action1()" class="Btn BtnNml ImpBtn"
							value="д��" />
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
