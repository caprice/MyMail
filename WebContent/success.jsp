<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.a {
	width: 600px;
}

.spiffy {
	display: block;
}

.spiffy * {
	display: block;
	height: 1px;
	overflow: hidden;
	background: #82c0e9;
}

.spiffy1 {
	border-right: 1px solid #82c0e9;
	padding-right: 1px;
	margin-right: 3px;
	border-left: 1px solid #95bdf4;
	padding-left: 1px;
	margin-left: 3px;
	background: #82c0e9;
}

.spiffy2 {
	border-right: 1px solid #e6effc;
	border-left: 1px solid #e6effc;
	padding: 0px 1px;
	background: #82c0e9;
	margin: 0px 1px;
}

.spiffy3 {
	border-right: 1px solid #82c0e9;
	border-left: 1px solid #82c0e9;
	margin: 0px 1px;
}

.spiffy4 {
	border-right: 1px solid #82c0e9;
	border-left: 1px solid #82c0e9;
}

.spiffy5 {
	border-right: 1px solid #82c0e9;
	border-left: 1px solid #82c0e9;
}

.spiffy_content {
	padding: 0px 5px;
	background: #82c0e9;
}
</style>
<script language="JavaScript1.2" type="text/javascript">

function delayURL(url) {
    var delay = document.getElementById("time").innerHTML;
	if(delay > 0) {
		delay--;
		document.getElementById("time").innerHTML = delay;
	} else {
		window.top.location.href = url;
    }
    setTimeout("delayURL('" + url + "')", 1000); 
}

</script>

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

<br />
<br />
<br />
<br />
<br />
<br />
<br />
<center>
	<div class="a">
		<b class="spiffy"> <b class="spiffy1"><b></b>
		</b> <b class="spiffy2"><b></b>
		</b> <b class="spiffy3"></b> <b class="spiffy4"></b> <b class="spiffy5"></b>
		</b>
		<div class="spiffy_content">

			<!-- �����������ı����� -->

			<logic:present name="flag">

				<c:set value="addusersuccess" var="addusersuccess" />
				<c:set value="sentsuccess" var="sentsuccess" />
				<c:set value="gotologin" var="gotologin" />

				<c:if test="${addusersuccess eq flag}">
					<center>
						��ϲ����ע��ɹ���
						<br>
						<br>
						<span id="time">3 </span>���Ӻ��Զ���ת����������Զ���ת��������������
						<br>
						<br>
						<a href="register.jsp"><font color="dimgray">����ע��ҳ��</font>
						</a>
					</center>

					<script type="text/javascript">
			delayURL("login.jsp", 3000);
		</script>
				</c:if>
				
				<c:if test="${gotologin eq flag}">
					<center>
						��ϲ���������޸ĳɹ��������µ�¼��
						<br>
						<br>
						<span id="time">3 </span>���Ӻ��Զ���ת����������Զ���ת��������������
						<br>
						<br>
						<a href="modifyuser.jsp"><font color="dimgray">���������޸�ҳ��</font>
						</a>
					</center>

					<script type="text/javascript">
			delayURL("login.jsp", 3000);
		</script>
				</c:if>

				<c:if test="${sentsuccess eq flag}">
					<center>
						��ϲ�����ʼ����ͳɹ���
						<br>
						<br>
						<span id="time">3 </span>���Ӻ��Զ���ת����������Զ���ת��������������
						<br>
						<br>
						<a
							onclick="javascript:changeframe('', '', 'write.jsp'); return false;"
							href="#"><font color="dimgray">����д��ҳ��</font>
						</a>
					</center>

					<script type="text/javascript">
			delayURL("index.jsp", 3000);
		</script>
				</c:if>

			</logic:present>

		</div>
		<b class="spiffy"> <b class="spiffy5"></b> <b class="spiffy4"></b>
			<b class="spiffy3"></b> <b class="spiffy2"><b></b>
		</b> <b class="spiffy1"><b></b>
		</b> </b>
	</div>
</center>