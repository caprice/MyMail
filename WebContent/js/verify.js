function verify(){

    var jqueryObj = $("#inp_uname");
    var username = jqueryObj.val();
    // alert(username);

    $.get("AJAXServlet?name=" + username ,null ,callback);

}

function callback(data) {
    //alert("�������˵����ݻ����ˣ�");
    //alert(data);
    var resultObj = $("#result");
    resultObj.html(data);
}