function changeQF(){

    var cc=document.getElementById("qfa");
     //alert(cc);

	 if(cc.innerText=="ʹ��Ⱥ������"){ 
	 	cc.innerText="ɾ��Ⱥ������";
	 	
	 	 var cc=document.getElementById("cca");
	 	 cc.innerText="��ӳ���";
	 	 $.get("CCServlet?name=ɾ������",null ,callbackcc);
	 	 
	 	  var bcc=document.getElementById("bcca");
	 	  bcc.innerText="�������";
	 	  $.get("BCCServlet?name=ɾ������",null ,callbackbcc);
	 	
	 	
	 	$.get("QFServlet?name=ʹ��Ⱥ������" ,null ,callbackqf);
	 } else {
	 	cc.innerText="ʹ��Ⱥ������";
	 	$.get("QFServlet?name=ɾ��Ⱥ������",null ,callbackqf);
	 } 

    

}

function callbackqf(data) {
   // alert("�������˵����ݻ����ˣ�");
   // alert(data);
    var resultObj = document.getElementById("fig");
    resultObj.innerHTML = data;
}


function callbackcc(data) {
    //alert("�������˵����ݻ����ˣ�");
   // alert(data);
    var resultObj = document.getElementById("asd");
    resultObj.innerHTML = data;
}


function callbackbcc(data) {
    //alert("�������˵����ݻ����ˣ�");
    //alert(data);
    var resultObj = document.getElementById("def");
    resultObj.innerHTML = data;
}