<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030" import="java.util.*,java.text.DecimalFormat"%>
<%@page import="com.wellmail.bean.WebFileBean"%>
<%@page import="com.wellmail.model.*"%>
<%@page import="java.io.*"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>Insert title here</title>
		<script type="text/javascript" src="js/theme_output.js"></script>
		<link href="css/style2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/1004271438/tools.js"></script>
		<link href="css/inbox2.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/wp_v7.js"></script>

		<link href="css/global[2].css" rel="stylesheet" type="text/css" />
		<link href="css/skin_126green[1].css" rel="stylesheet" type="text/css" />
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
	   document.messageForm.action="deletefile.do"; 
	   document.messageForm.submit(); 
	} 
	
	
</script>
	</head>
	<body>
		<form name="messageForm">
			<div class="gNetfolder">
				<!-- ���� Start -->
				<div class="g-title-1">
					<h2>
						����
					</h2>
					<%
						WebDisk webdisk = (WebDisk) request.getSession().getAttribute(
								"webdisk");

						double webspace = webdisk.getWebspace();
						double wsleft = webdisk.getWsleft();

						int filecount = webdisk.getFilecount();

						double probar = ((webspace - wsleft) / webspace) * 100;

						DecimalFormat df = new DecimalFormat("##.###");
						String st = df.format(probar);
						probar = Double.parseDouble(st);
					%>

					<span class="txt-info"> ��${fn:length(webfilebeanlist)}���ļ��� <%=filecount%>���ļ�
						| ������ <span class="g-probar" title="��ʹ��<%=probar%>%"><span
							style="width: <%=probar%>%"></span> </span> <span class="maxsize"><script
								language="JavaScript"></script> </span>&nbsp; </span>
					<div class="ext">

						<span class="srch"> <span class="g-srch"><!-- <span
								class="ipt-t ipt-t-dft"><input id="minikeyword"
										type="text" class="txt-info" value="��������"
										onfocus="if(this.value =='��������'){this.value='';}"
										onblur="if(this.value ==''){this.value='��������';}"
										onkeyup="if(event.keyCode == 13){fWpMiniSearch();}" /><a
									href="javascript:fGoto()" onclick="fWpMiniSearch();"
									class="btn btn-srch"></a><span class="btn btn-advsrch"
									onclick="fWpSetSearch(true)"></span> --> </span> </span>
					</div>

				</div>
				<!-- ���� End -->
				<%
					int filetypeid = Integer.parseInt(request
							.getParameter("filetypeid"));
					//System.out.println("filetypeid(webdisk):"+filetypeid);
				%>
				<script type="text/javascript">
				function changepage(s){
		
				   document.messageForm.action="webdiskfolder.jsp?pageNo="+s+"&filetypeid=<%=filetypeid%>"; 
				   document.messageForm.submit();
				}
			</script>
				<input type="hidden" name="filetypeid" value="<%=filetypeid%>" />

				<!-- ������1 -->
				<div class="g-toolbar">
					<div class="btngrp">
						<a href="#" class="btn btn-dft txt-b"
							onclick="javascript:changeframe('', '', 'fileUploadtemp.jsp?filetypeid=<%=filetypeid%>'); return false;"><span>��
								��</span> </a>
					</div>
					<div class="btngrp">
						<a href="#" onclick="action1()" class="btn btn-dft btn-dft-gc"><span>ɾ
								��</span>
					</div>
					<div class="btngrp">
						<a href="#" class="btn btn-dft-pd btn-dft"><span></span><b
							class="arr"></b> </a>
					</div>
					<%
						int flag = 0;

						List<WebFileBean> webFileBeanList = (List<WebFileBean>) request
								.getSession().getAttribute("webfilebeanlist");
						List<WebFile> webFileList = null;
						for (Iterator<WebFileBean> i = webFileBeanList.iterator(); i
								.hasNext();) {
							WebFileBean wfb = i.next();
							FileType ft = wfb.getFiletype();
							if (ft.getFiletypeid() == filetypeid) {
								flag = 1;
								webFileList = wfb.getWebFileList();
							}
						}

						if (webFileList == null) {
							//System.out.println("asdfasdfadsfa");
							webFileList = new ArrayList<WebFile>();
						}

						int pageSize = 7;
						String temp = request.getParameter("pageNo");
						int pageNo = 0;
						if (temp != null) {
							pageNo = Integer.parseInt(temp);
						}
						if (pageNo <= 0) {
							pageNo = 1;
						}

						int totalRecords = webFileList.size();
						//System.out.println(totalRecords+"____totalRecords");

						int totalPages = totalRecords % pageSize == 0 ? totalRecords
								/ pageSize : totalRecords / pageSize + 1;

						if (pageNo > totalPages) {
							pageNo = totalPages;
						}

						int startPos = (pageNo - 1) * pageSize;
						//System.out.println(startPos+"____startPos");

						List<WebFile> webFileListTemp = new ArrayList<WebFile>();

						//System.out.println(pageNo+"***pageno**");
						for (int i = 0; i < pageNo - 1; i++) {
							pageSize += 7;
						}
						//System.out.println(pageSize+"***pagesize*");

						if (startPos >= totalRecords - totalRecords % pageSize) {
							pageSize = totalRecords % pageSize;
						}
						if (webFileList.size() > 0) {
							for (int i = startPos; i < pageSize; i++) {
								webFileListTemp.add(webFileList.get(i));
								//System.out.println(articles.get(i)+"--------");
							}
						}
						List<WebFile> webFileListTemp2 = new ArrayList<WebFile>();
						for (Iterator<WebFile> i = webFileListTemp.iterator(); i.hasNext();) {
							WebFile wf = i.next();

							File f = new File(wf.getFilename());
							String filename = f.getName();
							wf.setFilename(filename);

							webFileListTemp2.add(wf);
						}

						request.getSession().setAttribute("webFilePageNo", pageNo);
						request.getSession().setAttribute("webFileTotalPages", totalPages);
						request.getSession().setAttribute("webFileListTemp",
								webFileListTemp2);
					%>
					<div class="btngrp btngrp-ext">
						<a href="webdisk.jsp?pageNo=1&filetypeid=<%=filetypeid%>"
							class="txt-disabd">��ҳ</a><a
							href="webdisk.jsp?pageNo=${webFilePageNo - 1 }&filetypeid=<%=filetypeid%>"
							class="txt-disabd">��ҳ</a><a
							href="webdisk.jsp?pageNo=${webFilePageNo + 1 }&filetypeid=<%=filetypeid%>"
							class="txt-disabd">��ҳ</a><a
							href="webdisk.jsp?pageNo=${webFileTotalPages }&filetypeid=<%=filetypeid%>"
							class="txt-disabd">ĩҳ</a>
						<select name="pageNo" onchange="changepage(this.value)">
							<c:forEach var="t" begin="1" end="${webFileTotalPages}">
								<option value="${t }">
									${t }/${webFileTotalPages }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<table width="96%" align="center">
					<tr>
						<td>
							<div class="ContentWp">
								<div class="ContentThemeWp">

									<div class="Ibx_Main_Wp">
										<table id="oTableHead" class="Ibx_gTable F2Img Ibx_gTable_TT">
											<tbody>
												<tr>

													<th class="Ibx_Th_Chkbx">
														<input type="checkbox" id="oFormCheckAll"
															onclick="checkall()" title="ȫѡ/��ѡ����ҳ�����ʼ�" />
													</th>
													<th class="Ibx_Th_From">
														<a href="#" title="����ɰ�������">�ļ���</a>
													</th>
													<th class="Ibx_Th_Size">
														<a href="#" title="����ɰ�������">��С<b
															class="icoIbx icoDown"></b>
														</a>
													</th>
													<th class="Ibx_Th_Date">
														<a href="#" title="����ɰ�������">����</a>
													</th>
													<th class="Ibx_Th_Date">
														<a href="#" title="����ɰ�������">����</a>
													</th>
												</tr>
											<tbody>
										</table>
										<div class="Ibx_Lst_dWp dWpOpen">

											<table class="Ibx_gTable Ibx_gTable_Con" id="oTableCOUNT0">
												<tbody>
													<logic:notPresent name="webFileListTemp">
												�Բ��𣬲�ѯ�������Ժ����ԣ�
											</logic:notPresent>
													<logic:present name="webFileListTemp">
														<logic:empty name="webFileListTemp">
															<br />
															<br />
															<font size="2">��ǰ�ļ���û���ļ�����</font>
															<br />
															<br />
														</logic:empty>
														<logic:notEmpty name="webFileListTemp">
															<c:forEach items="${webFileListTemp}" var="t">
																<tr class="I_Mark0">
																	<td class="Ibx_Td_Chkbx">
																		<input name="mid" type="checkbox" title="ѡ��/��ѡ"
																			value="${t.fileid }" />
																	</td>

																	<td class="Ibx_Td_From">
																		<a href="#"
																			onclick="javascript:changeframe('', '', 'webdisk.jsp'); return false;">${t.filename
																			}</a>
																	</td>

																	<td class="Ibx_Td_Size" title="" width="20%">
																		${t.filesize }K
																	</td>

																	<td class="Ibx_Td_Date" width="20%">
																		<span title="5637 �ֽ�">${t.uploadtime }</span>
																	</td>
																	<td class="Ibx_Td_Date" width="20%">
																		<span title="5637 �ֽ�"><a
																			href="fileDownload.jsp?fileid=${t.fileid }">�����ļ�</a>
																		</span>
																	</td>
																</tr>
															</c:forEach>
														</logic:notEmpty>
													</logic:present>
													<c:set var="flag" value="<%=flag%>"></c:set>
													<c:if test="${flag eq 0}">
														<br />
														<br />
														<font size="2">��ǰ�ļ���û���ļ�����</font>
														<br />
														<br />

													</c:if>
												<tbody>
											</table>
										</div>



									</div>

								</div>

							</div>
						</td>
					</tr>
				</table>

				<!-- ������2 -->
				<div class="g-toolbar">
					<div class="btngrp">
						<a href="#" class="btn btn-dft txt-b"><span>�� ��</span> </a>
					</div>
					<div class="btngrp">
						<a href="#" class="btn btn-dft btn-dft-gc"><span>ɾ ��</span> </a>
					</div>
					<div class="btngrp">
						<a href="#" class="btn btn-dft-pd btn-dft"><span>�� ��</span><b
							class="arr"></b> </a>
					</div>
					<div class="btngrp btngrp-ext">
						<a href="webdisk.jsp?pageNo=1&filetypeid=<%=filetypeid%>"
							class="txt-disabd">��ҳ</a><a
							href="webdisk.jsp?pageNo=${webFilePageNo - 1 }&filetypeid=<%=filetypeid%>"
							class="txt-disabd">��ҳ</a><a
							href="webdisk.jsp?pageNo=${webFilePageNo + 1 }&filetypeid=<%=filetypeid%>"
							class="txt-disabd">��ҳ</a><a
							href="webdisk.jsp?pageNo=${webFileTotalPages }&filetypeid=<%=filetypeid%>"
							class="txt-disabd">ĩҳ</a>
						<select name="pageNo" onchange="changepage(this.value)">
							<c:forEach var="t" begin="1" end="${webFileTotalPages}">
								<option value="${t }">
									${t }/${webFileTotalPages }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="ln-thin ln-c-mid"></div>
			</div>

			<div id="divSort" class="g-menu g-menu-hasico"
				style="width: 120px; display: none">
				<div class="g-menu-inner">
					<ul>
						<li>
							<a href="#"><b class='ico ico-slct ico-slct-radio'></b>ʱ����µ���</a>
						</li>
						<li>
							<a href="#">ʱ��Ӿɵ���</a>
						</li>
						<li>
							<a href="#">�ļ��Ӵ�С</a>
						</li>
						<li>
							<a href="#">�ļ���С����</a>
						</li>
						<li>
							<a href="#">���ļ�����</a>
						</li>
						<li>
							<a href="#">������</a>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</body>
</html>