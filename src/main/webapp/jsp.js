function  Show_Reg() {
    $("#content").load("web/form.html");
}
function  Show_log() {
    $("#content").load("web/login.html");
}

function Login() {
    var xhr = new XMLHttpRequest();
    var form = 0;
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            if (xhr.response != null) {
                form = JSON.parse(xhr.responseText);

                //repas kala
                if (form != null) {
                    alert("Successful Login !");
                }
                if (form.type == "def") {
                    $("#content").load("web/userpanel.html");
                    $("#username").html(form.username);
                } else {
                    $("#content").load("web/docpanel.html");
                    $("#username").html(form.username);
                }


            }
        } else {
            $("#content").html("Insusccessfull Login");
        }
    };

    var data = $('#log').serialize();

    xhr.open('POST', "Login_Servlet");
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);

    // $("#username").text(form.username);
    // console.log(form.username);
    // $("#username").innerHTML  = form.username;


}
function Show_Panel(data) {
    $("#content").load("web/userpanel.html");
}
function  show_data() {
    $.ajax({
        url: 'get_data',
        data: {username: "Diaolos93"},
        type: 'get',
        success: function (data) {
            const d = JSON.parse(data);
            alert(data);

        }
    }
    );
}

function  show_type() {
    var type = $('input[name = "type"]:checked').val();
    alert(type);
}

function create_ren() {
    $.ajax({
        url: 'ren',
        data: {
            username: $("#username").text(),
            date: $("#date").val(),
            price: $("#price").val()
        },
        type: 'POST',
        success: function (data) {
            alert("Randevouz Successfully Created ");
        }
    }
    );

}

function create_date() {
    var date = new Date();
    var data = date.getDate()
    alert(data);

}
//create hours with var as int 
function generate_hours() {
    var time = "08:00";
    for (let i = 0; i < 1; i++) {
        //create multiple check boxes 
        //create an appointment per_hour checked
        
    }
}
function show_ran() {
    $.ajax({
        url: 'ren',
        type: 'GET',
        data: {username: $("#username").text()},
        success: function (data) {
            alert(data);
        }
    });
}




function book_ran() {
    $.ajax({
        url: "book_ran",
        type: 'get',
        success: function (data) {
            alert(data);
            var obj = $.parseJSON(data);
            
            $("#data").append("  <div class='form-check'>" +
                    "<input class= 'form-check-input' type='checkbox' value='"+obj[0].doctor_info+"' id='flexCheckDefault'>"+
            "<label   class='form-check-label' for='flexCheckDefault'>" +
                   "Username: "+ obj[0].doctor_info + " Price: " + obj[0].price + " State: " + obj[0].status +
                    "  </label>"+
            " </div> " );
            $("#data").append("<button type = 'button' class = 'btn-primary' onclick = 'book_it()' >Book it! </button>");
        }
    });
}

function book_it(){
    alert($("#flexCheckDefault").val());
    //book the randevouz according the checked value 
}


function get_bt(){
    $.ajax({
            url: "Cre_bt",
            type: 'GET',
            success: function(data){
                //append the blood test
                var json = $.parseJSON(data);
                $("#data").append("Successfully Created\n");
                
            }
    });
}