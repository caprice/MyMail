function changeBCC(){

    var bcc=document.getElementById("bcca");
    // alert(cc);

	 if(bcc.innerText=="�������"){ 
	 	bcc.innerText="ɾ������";
	 	
	 	var qf=document.getElementById("qfa");
	    qf.innerText="ʹ��Ⱥ������";
	 	$.get("QFServlet?name=ɾ��Ⱥ������",null ,callbackqf);
	 
	 	
	 	$.get("BCCServlet?name=�������" ,null ,callbackbcc);
	 } else {
	 	bcc.innerText="�������";
	 	$.get("BCCServlet?name=ɾ������",null ,callbackbcc);
	 } 

    

}

function callbackbcc(data) {
    //alert("�������˵����ݻ����ˣ�");
    //alert(data);
    var resultObj = document.getElementById("def");
    resultObj.innerHTML = data;
}

function callbackqf(data) {
   // alert("�������˵����ݻ����ˣ�");
   // alert(data);
    var resultObj = document.getElementById("fig");
    resultObj.innerHTML = data;
}