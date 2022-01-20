function  Show_Reg(){
    $("#content").load("web/form.html");
}
function  Show_log(){
    $("#content").load("web/login.html");
}

function Login(){
    var xhr = new XMLHttpRequest();
    var form = 0;
    xhr.onload = function () {
        if(xhr.readyState === 4 && xhr.status === 200){
            if (xhr.response != null){
                 form = JSON.parse(xhr.responseText);
                if(form != null){
                    alert("Successful Login !");
                }
                $("#content").load("web/userpanel.html");
                $("#username").html(form.username);

            }
        }else{
            $("#content").html("Insusccessfull Login");
        }
    };

    var data = $('#log').serialize();

    xhr.open('POST',"Login_Servlet");
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);

    // $("#username").text(form.username);
    // console.log(form.username);
    // $("#username").innerHTML  = form.username;


}
function Show_Panel( data){
    $("#content").load("web/userpanel.html");
}
function  show_data(){
    $.ajax({
        url: 'get_data',
        data:{username:"Diaolos93"},
        type: 'get',
        success:function (data){
            const jsondata = JSON.parse(data);


        }
    }
    );
}


