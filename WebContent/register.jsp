<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
		<title>MyMail���� - ע�����û�</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="javascript">
  function loadimage(){
    document.getElementById("vcode_img").src = "image.jsp?"+Math.random();
  }
</script>
		<script language="javascript">
	function fsubmit(obj){
	
		var n = 0; 
		var objs = document.getElementById("servItems"); 
		 
		if(objs.checked) {
			n=n+1; 
		}
		// alert(n);
		if( n == 0 ) {
			// alert("����ѡ��һ��");
			var qf=document.getElementById("serviceitem");
			qf.innerHTML="<img src='images/178.gif'> ����ܷ������";
			return false;
		} 
		
		var objs2=document.getElementsByName("sex");
		var temp= 0;
		for (var i=0;i<objs2.length;i++){
		  if(objs2[i].checked){
		  	temp++;
		  }
		 }
		 
		 if(temp == 0) {
		 	var qfa=document.getElementById("sexresult");
			qfa.innerHTML="<img src='images/178.gif'> �Ա����ѡ��";
			return false;
		 }
	
		obj.submit();
		// alert("ok");
	}
	
	function registeCheckUser(s) {
    if (s == "") {
        //alert("�ʼ��ʺŲ���Ϊ�գ�");
        
        alert("�ʼ��ʺŲ���Ϊ�գ�");
        
        // $('user').focus();
        return false;
    }
    var patrn=/^[a-zA-Z]{6,15}$/;

	if (!patrn.exec(s)) {
		alert("�ʺŸ�ʽ����ȷ������Ϊ6��15����ĸ��");
		return false;
	}
    return true;
}


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
	
	function changeQuestion(s){
		if(s == "��ѡ��������ʾ����") {
			var qf=document.getElementById("quesresult");
			qf.innerHTML="<img src='images/178.gif'> ��ѡ��������ʾ���⣡";
			return false;
		}else {
			var qf=document.getElementById("quesresult");
			qf.innerHTML="";
		}
	}
	
	function answerCheck(s){
		if(s == "") {
			var qfa=document.getElementById("ansresult");
			qfa.innerHTML="<img src='images/178.gif'> ������ʾ���������д��";
			return false;
		}else {
			var qfa=document.getElementById("ansresult");
			qfa.innerHTML="<img src='images/002.gif'> ������ʾ������ɣ�";
			return true;
		}
	}
	
	function checkRadioValue(){
	 var obj=document.getElementsByName("sex");
	 var t = 0;
	 for (var i=0;i<obj.length;i++)
	 {
	  if(obj[i].checked){
	  	t++;
	  }
	 }
	 if(t > 0) {
	 	var qfa=document.getElementById("sexresult");
	 	qfa.innerHTML="<img src='images/002.gif'> �Ա�ѡ����ɣ�";
	  	return false;
	 }
	 var qfa=document.getElementById("sexresult");
	 qfa.innerHTML="<img src='images/178.gif'> �Ա����ѡ��";
	 return true;
	}
	
	 function CheckChinaMobileID(mobile){
        if(mobile != "") {
	        if(isNaN(mobile)||(mobile.length!=11)){
	            var qfa=document.getElementById("telresult");
		 		qfa.innerHTML="<img src='images/178.gif'> �ֻ�����Ϊ11λ���֣�����ȷ��д��";
	            return false;
	        }
	        var reg =/^0{0,1}(13[4-9]|15[7-9]|15[0-2]|18[7-8])[0-9]{8}$/;
	        if(!reg.test(mobile))
	        {
	            var qfa=document.getElementById("telresult");
		 		qfa.innerHTML="<img src='images/178.gif'> �����ֻ����벻���й����к��룬���������룡";
	            return false;
	        }
	        
	        var qfa=document.getElementById("telresult");
		 	qfa.innerHTML="<img src='images/002.gif'> �����ֻ�������ȷ��";
	        return true;
        }
    }
    
    function checkCode(a) {
    	if(a == "") {
    		var qfa=document.getElementById("ccoderesult");
		 	qfa.innerHTML="<img src='images/178.gif'> �Բ�����֤�벻��Ϊ�գ�";
		 	 return false;
    	}
    	
    	if(isNaN(a)||(a.length!=4)) {
    		var qfa=document.getElementById("ccoderesult");
		 	qfa.innerHTML="<img src='images/178.gif'> �Բ�����֤��Ϊ�����ҳ���Ϊ4��";
		 	return false;
    	}
    	
    	var qfa=document.getElementById("ccoderesult");
	 	qfa.innerHTML="<img src='images/002.gif'> ��֤���ʽ��ȷ��";
        return true;
    }
    
    function serviceitem() 
	{ 
		var n = 0; 
		var objs = document.all.servItems; 
		for(var i=0;i <objs.length;i++)  
		{ 
		if(objs[i].checked) 
		n=n+1; 
		} 
		if( n == 0 ) 
		alert("����ѡ��һ��"); 
	} 
</script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/verify.js"></script>
	</head>


	<body>
		<div class="header">
			<a href="#"><img src="images/man_logo.jpg" alt="MyMail����"
					class="logo" />
			</a>
			<div class="head_links">
				<a href="http://help.163.com/" target="_blank" title="����">����</a>&nbsp;&nbsp;&nbsp;
			</div>
		</div>

		<div class="content">
			<div class="mhd">
				<div class="tit">
					��ӭע��MyMail������䣡
				</div>
			</div>
			<div class="mcont">
				<!-- �ֲ𲹳����ϲ��� Start -->
				<div id="sysmessage" class="err-info" style="display: none"></div>
				<!-- registing.do -->
				<form id="regform" name="regform" action="registing.do"
					method="post">
					<div class="main-cont">
						<div class="main-cont-tit">
							<div class="arr"></div>
							<h2>
								���������ʺ�
							</h2>
						</div>

						<table class="cont-tab" cellspacing="0" cellpadding="0"
							style="table-layout: fixed">
							<!--�û����Ĵ��� Start-->
							<tr id="tr_input_username">
								<td class="td1">
									�û�����
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<div class="fle">
										<input onblur="registeCheckUser(this.value)" type="text"
											name="username" id="inp_uname"
											onFocus="this.className='inp ipt-focus'"
											class="inp ipt-normal"
											style="width: 205px; font-weight: bold" maxlength="18" />
									</div>

									<input id="btn_chk" type="button" value="���" class="btn-jc fri"
										align="left" onclick="verify()" />
									<br />
									<span id="result"></span>
								</td>
							</tr>

							<tr id="tr_chk_username_result" style="display: none">
								<td class="td1">
									&nbsp;
								</td>
								<td colspan="2" class="td3">
									<div id="div_chose_uname" class="chose-list">

									</div>
								</td>
							</tr>

							<tr>
								<td class="td1">
									�� �룺
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<input onblur="isPasswd(this.value)"
										onFocus="this.className='inp ipt-focus'" type="password"
										name="pwdHash" id="password" class="inp ipt-normal"
										style="width: 235px" maxlength="16" value="" />
									<br />
									<span id="p1result"></span>
								</td>
							</tr>
							<tr>
								<td class="td1">
									�ٴ��������룺
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<input onblur="passwordEqual(this.value)" style="width: 235px"
										type="password" name="pwdHashConfirm" id="passwordconfirm"
										class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'" maxlength="16"
										value="" />
									<br />
									<span id="p2result"></span>
								</td>
							</tr>
						</table>
						<div class="main-cont-tit">
							<div class="arr"></div>
							<h2>
								��ȫ��Ϣ����
							</h2>
							<span class="fle Cgray">��������Ϣ�ǳ���Ҫ����������д��</span>
						</div>

						<table class="cont-tab" cellspacing="0" cellpadding="0"
							style="table-layout: fixed">
							<tr>
								<td class="td1">
									���뱣�����⣺
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<select name="question" id="secproblem" class="sel"
										style="width: 235px" onblur="changeQuestion(this.value)">
										<option style="color: #666" value="��ѡ��������ʾ����">
											��ѡ��������ʾ����
										</option>
										<option value="��ĸ�׵�������?">
											��ĸ�׵�������?
										</option>
										<option value="�����׵�������?">
											�����׵�������?
										</option>
										<option value="����ż��������?">
											����ż��������?
										</option>
										<option value="��ĸ�׵�������?">
											��ĸ�׵�������?
										</option>
										<option value="�����׵�������?">
											�����׵�������?
										</option>
										<option value="����ż��������?">
											����ż��������?
										</option>
										<option value="���ĳ�������?">
											���ĳ�������?
										</option>
										<option value="����СѧУ����?">
											����СѧУ����?
										</option>
										<option value="������ѧУ����?">
											������ѧУ����?
										</option>
										<option value="���Ĵ�ѧУ����?">
											���Ĵ�ѧУ����?
										</option>
									</select>
									<br />
									<span id="quesresult"></span>
								</td>
							</tr>
							<tr>
								<td class="td1">
									���뱣������𰸣�
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<input style="width: 235px" onblur="answerCheck(this.value)"
										type="text" name="answer" id="secanswer"
										class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus';" maxlength="30" />
									<br />
									<span id="ansresult"></span>
								</td>
							</tr>
							<tr>
								<td class="td1">
									�� ��
									<span class="nes">*</span>
								</td>
								<td class="td2" style="font-size: 14px" align="left">
									<label for="rd1">
										<input type="radio" name="sex" id="rd1" value="��"
											onclick="checkRadioValue()">
										��
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label for="rd2">
										<input type="radio" name="sex" id="rd2" value="Ů"
											onclick="checkRadioValue()">
										Ů
									</label>
									<br />
									<span id="sexresult"></span>
								</td>

							</tr>
							<tr>
								<td class="td1">
									�������ڣ�
									<span class="nes">*</span>
								</td>
								<td class="td2" style="font-size: 14px" align="left">
									<input type="text" name="year" id="year" value=""
										class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'" maxlength="4"
										style="width: 60px"
										onkeyup="this.value=this.value.replace(/\D/g,'')" />
									��
									<input type="text" name="month" id="month" value=""
										class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'" maxlength="2"
										style="width: 50px"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										onafterpaste="this.value=this.value.replace(/\D/g,'')" />
									��
									<input type="text" name="day" id="day" value=""
										class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'" maxlength="2"
										style="width: 50px"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										onafterpaste="this.value=this.value.replace(/\D/g,'')" />
									��
								</td>

							</tr>
							<tr>
								<td class="td1">
									�ֻ��ţ�
									<span class="unnes">*</span>
								</td>
								<td class="td2" align="left">
									<input onblur="CheckChinaMobileID(this.value)"
										style="width: 235px" type="text" name="tel" id="mobile"
										value="" class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'" maxlength="16" />
									<br />
									<span id="telresult"></span>
								</td>

								<td class="td3">

								</td>
							</tr>
						</table>
						<div class="main-cont-tit">
							<div class="arr"></div>
							<h2>
								ע����֤
							</h2>
						</div>

						<table class="cont-tab" cellspacing="0" cellpadding="0"
							style="table-layout: fixed">
							<tr>
								<td class="td1">
									&nbsp;
								</td>
								<td class="td2 codeImg" align="left">
									<img id="vcode_img" src="image.jsp" class="">
									&nbsp;&nbsp;
									<a href="javascript:loadimage();">�����������һ��</a>
								</td>
								<td class="td3">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td1">
									�������ϱߵ��ַ���
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<input style="width: 235px" type="text" name="checkcode"
										id="authcode" class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'"
										onBlur="checkCode(this.value)" maxlength="16" />
									<!--�����3��״̬ ��ipt-normal��-��������ipt-focus��-��ȡ���㣬��ipt-error��-������ -->
									<br />
									<span id="ccoderesult"></span>
								</td>

							</tr>
						</table>

						<div class="main-cont-tit">
							<div class="arr"></div>
							<h2>
								��������
							</h2>
						</div>
						<table class="cont-tab" cellspacing="0" cellpadding="0"
							style="table-layout: fixed">
							<tr>
								<td class="td1">
									&nbsp;
								</td>
								<td class="td2" align="left">
									<label>
										<input type="checkbox" name="servItems" id="servItems"
											checked="checked">
										�����Ķ�������
									</label>
									&ldquo;
									<a href="serviceitem.html">��������</a>&rdquo;
									<br />
									<span id="serviceitem"></span>
								</td>
								<td class="td3">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td1">
									&nbsp;
								</td>
								<td class="td2" align="left">
									<input name="" type="button" class="btn-submit"
										onFocus="this.className='btn-submit-act'"
										onBlur="this.className='btn-submit'"
										onClick="javascript:fsubmit(document.regform);" value=""
										title="�����ʺ�">
								</td>
								<td class="td3">
									&nbsp;
								</td>
							</tr>
						</table>

					</div>
				</form>
				<!-- �ֲ𲹳����ϲ���   End -->



			</div>
			<div class="mft"></div>
		</div>



		<div class="footer">
			<A href="#" target="_blank">����Mymail</A>&nbsp;&nbsp;
			<A href="#" target="_blank">����ٷ�����</A>&nbsp;&nbsp;
			<A href="http://www.188.com/" target="_blank">�Ƹ���</A>&nbsp;&nbsp;
			<A href="http://cards.163.com/" target="_blank">�����ؿ�</A>&nbsp;&nbsp;
			<A href="http://safesurf.china.cn/data_form.php" target="_blank">�ٱ�Υ����Ϣ</A>&nbsp;&nbsp;
			<A href="http://help.163.com/" target="_blank">�ͻ�����</A>
			<BR />
			<A href="http://corp.163.com/gb/legal/legal.html" target="_blank">��ط���</A>&nbsp;&nbsp;|&nbsp;&nbsp;��Ȩ����&nbsp;&copy;&nbsp;2009-2010
			<script language="javascript"
				src="http://mimg.163.com/copyright/year.js"></script>
		</div>

		<!-- START NetEase Devilfish 2006 -->
		<script src="http://analytics.163.com/ntes.js" type="text/javascript"></script>
		<script type="text/javascript">
_ntes_nacc = "163mail";
neteaseTracker();
</script>
		<!-- END NetEase Devilfish 2006 -->
	</body>
</html>