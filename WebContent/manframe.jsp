<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
		<link href="css/globle_v1.css" rel="stylesheet" type="text/css">
		<link href="css/welcome2.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="css/common.css" type="text/css" />
		<link href="css/skin_blue.css" rel="stylesheet" type="text/css"
			id="lnkSkin" />
		<title>��������</title>
	</head>

	<body>
		<div id="man_zone">
			<div class="ContentWp">

				<div class="ContentThemeWp">
					<div class="F2Img w2_HeadWp">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="w2_HeadTb">
							<tr>
								<td class="User_tdWp">
									<div class="w2_U_Wp">
										<h1>
											���ã���ӭ��½MyMail����ϵͳ��
										</h1>
										<b class="IcoImg IcoNwMail"></b>
										<h2>
											����&nbsp;
											<a href="box.jsp" title="�Ķ�δ���ʼ�" class="fnt_Red"><b>${unreadcount
													}</b>
											</a>&nbsp;��&nbsp;
											<a href="goreceive.do" title="�Ķ�δ���ʼ�">δ���ʼ�</a>
										</h2>
										<div class="w2_Rong">
											<div class="rng_DcrTxt">
												����������
												<logic:present name="emailspace">
													<c:out value="${emailspace.espace/1024}" />
												</logic:present>
												G����ʹ��
												<logic:present name="emailspace">
													<fmt:formatNumber
														value="${(emailspace.espace-emailspace.spaceleft)}"
														pattern="####.###" />
												</logic:present>
												M
											</div>
											<div class="WelImg rng_PcsWp">
												<div class="WelImg rng_Pcs"
													style="width: ${(( emailspace.espace-emailspace.spaceleft )/ emailspace.espace ) * 100}"></div>
												<div class="rng_PcsTxt">
													<fmt:formatNumber
														value="${((emailspace.espace-emailspace.spaceleft)/emailspace.espace)*100}"
														pattern="##.###" />
													%
												</div>
											</div>
										</div>
									</div>
								</td>
								<td class="F2Img Mid_LineWp"
									style="background-repeat: no-repeat;"></td>
								<td class="Weather_tdWp"></td>
							</tr>
						</table>
					</div>
					<div class="w2_MidWp">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="w2_MidTb">
							<tr>
								<td class="Mid_JJ"></td>
								<td class="Mid_lWp">
									<div class="w2cbx cbx_yxFW">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="F2Img w2cbx_Top">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													&nbsp;
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
										<div class="cbx_TabWp">
											<ul class="cbx_TabIn">
												<li class="WelImg On">
													<a href="javascript:void(0);" title="">�������</a>
												</li>
											</ul>
										</div>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Mid">
											<tr>
												<td class="WelImg L_Rp"></td>
												<td class="M_C">
													<div class="M_C_Inr">
														<div class="F2Img w2ft_Bnr bTj_sdy">
															<table border="0" cellspacing="0" cellpadding="0"
																width="99%" class="BnrTj_Tb">
																<tr>
																	<td class="BnrTj_ImgWp">
																		<div class="BnrTj_Img WelImg"></div>
																	</td>
																	<td class="BnrTj_TtWp" nowrap>
																		<h3>
																			��ӭ��½MyMail����ϵͳ��
																		</h3>
																		<div class="BnrTj_DcrWp" nowrap>
																			����ȫ����
																		</div>
																		<div class="BnrTj_ExLink" nowrap>
																			<a href="about.html" target="_blank" title="">�����˽�&gt;&gt;</a>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0" class="sTj_WpTb">
															<tr>
																<td class="Bx Bx_Huangfu">
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%" class="sTj_Tb">
																		<tr>
																			<td rowspan="2" class="sTj_ImgWp">
																				<div class="sTj_Img WelImg"></div>
																			</td>
																			<td class="sTj_TtWp" nowrap>
																				<h4>
																					<a href="gonotebook.do" title="���±�">���±�</a>
																				</h4>
																			</td>
																		</tr>
																		<tr>
																			<td class="sTj_DcrWp" nowrap>
																				��¼�뷨�����¡�����
																			</td>
																		</tr>
																	</table>
																</td>
																<td class="Bx Bx_Laixinfenlei">
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%" class="sTj_Tb">
																		<tr>
																			<td rowspan="2" class="sTj_ImgWp">
																				<div class="sTj_Img WelImg"></div>
																			</td>
																			<td class="sTj_TtWp" nowrap>
																				<h4>
																					<a href="gocontactbox.do" title="ͨѶ¼">ͨѶ¼</a>
																				</h4>
																			</td>
																		</tr>
																		<tr>
																			<td class="sTj_DcrWp" nowrap>
																				ͨѶ¼
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td class="Bx Bx_Qianxin">
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%" class="sTj_Tb">
																		<tr>
																			<td rowspan="2" class="sTj_ImgWp">
																				<div class="sTj_Img WelImg"></div>
																			</td>
																			<td class="sTj_TtWp" nowrap>
																				<h4>
																					<a href="goothermailbox.do" title="������">������</a>
																				</h4>
																			</td>
																		</tr>
																		<tr>
																			<td class="sTj_DcrWp" nowrap>
																				����������������
																			</td>
																		</tr>
																	</table>
																</td>
																<td class="Bx Bx_Daohang">
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%" class="sTj_Tb">
																		<tr>
																			<td rowspan="2" class="sTj_ImgWp">
																				<div class="sTj_Img WelImg"></div>
																			</td>
																			<td class="sTj_TtWp" nowrap>
																				<h4>
																					<a href="gowebdiskfolder.do" title="���±�">����Ӳ��</a>
																				</h4>
																			</td>
																		</tr>
																		<tr>
																			<td class="sTj_DcrWp" nowrap>
																				�����ĵ���ͼƬ�����
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</div>
												</td>
												<td class="WelImg R_Rp"></td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Btm">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													&nbsp;
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
									</div>
								</td>
								<td class="Mid_JJ"></td>
								<td class="Mid_rWp">
									<div class="w2cbx cbx_yxBJ F2Img" style="margin-bottom: 14px;">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="F2Img w2cbx_Top"
											style="height: 6px; font-size: 1px; line-height: 1px;">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													&nbsp;
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Mid">
											<tr>
												<td class="WelImg L_Rp"></td>
												<td class="M_C">
													<div class="M_C_Inr">
														<div class="C_Tt">
															<a href="#" title="������">������</a>
														</div>
														<div class="C_Dcr">
															������������ȡ�������������������ʼ�
														</div>
														<form method="post" action="addothermailbox.do">
															<div class="C_Ipt">
																<input type="text" name="uname" class="Ipt IptNml"
																	value="����user@sina.com"
																	onClick="if(this.value=='����user@sina.com')this.value=''" />
																<input type="submit" class="Btn BtnNml" value="��һ��" />
															</div>
														</form>
													</div>
												<td class="WelImg R_Rp"></td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Btm">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													&nbsp;
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
									</div>
									<div class="w2cbx cbx_yxTJ">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="F2Img w2cbx_Top">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													��Ϣ����
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Mid">
											<tr>
												<td class="WelImg L_Rp"></td>
												<td class="M_C">
													<div class="M_C_Inr">
														<div class=""></div>
													</div>
												</td>
												<td class="WelImg R_Rp"></td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Btm">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													&nbsp;
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
									</div>
								</td>
								<td class="Mid_JJ"></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
