function changeCC(){

    var cc=document.getElementById("cca");
    // alert(cc);

	 if(cc.innerText=="��ӳ���"){ 
	 
	   var qf=document.getElementById("qfa");
	   qf.innerText="ʹ��Ⱥ������";
	 	$.get("QFServlet?name=ɾ��Ⱥ������",null ,callbackqf);
	 
	 	cc.innerText="ɾ������";
	 	$.get("CCServlet?name=��ӳ���" ,null ,callbackcc);
	 } else {
	 	cc.innerText="��ӳ���";
	 	$.get("CCServlet?name=ɾ������",null ,callbackcc);
	 } 

    

}

function callbackcc(data) {
    //alert("�������˵����ݻ����ˣ�");
   // alert(data);
    var resultObj = document.getElementById("asd");
    resultObj.innerHTML = data;
}

function callbackqf(data) {
   // alert("�������˵����ݻ����ˣ�");
   // alert(data);
    var resultObj = document.getElementById("fig");
    resultObj.innerHTML = data;
}