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
            price: $("#price").val(),
            hour: $("#hour").val().toString() + ":" + $("#min").val().toString()
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

function show_ran() {
    $.ajax({
        url: 'ren',
        type: 'GET',
        data: {username: $("#username").text()},
        success: function (data) {
            var obj = JSON.parse(data);
            var ele = "<table class = 'table'>";
            ele += "\n <thead> \n <tr> \n\
            <th scope = 'col'>Doctor's Username: </th>\n\
            <th scope = 'col'>Date:  </th>\n\
            <th scope = 'col'>Price: </th>\n\
             <th scope = 'col'>Status </th>\n\
            </tr> </thead>";
            ele += "<tbody> \n ";
            for (x in obj) {
                ele += "<tr>";
                ele += "<th scope = 'row'>  " + obj[x].doctor_info + "</th>";
                ele += "<td>" + obj[x].date_time + "</td>";
                ele += "<td>" + obj[x].price + "</td>";
                ele += "<td>" + obj[x].status + "</td>";
                ele += "</tr>";
            }
            ele += "</tbody>\n </table>";
            $("#forms").append(ele);
        }
    });
}




function book_ran() {
    //create new randevouz table with id 
    //finish randevouz 

    $.ajax({
        url: "book_ran",
        type: 'get',
        success: function (data) {
            alert(data);
            var obj = $.parseJSON(data);
//            for(var ob in obj)
            $("#data").append("  <div class='form-check'>" +
                    "<input class= 'form-check-input' type='checkbox' value='" + obj[0].randevouz_id + "' id='flexCheckDefault'>" +
                    "<label   class='form-check-label' for='flexCheckDefault'>" +
                    "Username: " + obj[0].doctor_info + " Price: " + obj[0].price + " State: " + obj[0].status +
                    "  </label>" +
                    " </div> ");
            $("#data").append("<button type = 'button' class = 'btn-primary' onclick = 'book_it()' >Book it! </button>");
        }
    });
}

function book_it() {
    alert($("#flexCheckDefault").val());
    var pat = $("#flexCheckDefault").val();
    $.ajax({
        url: 'book_ran',
        type: 'POST',
        data: {username: $("#username").text(), id: pat},
        success: function (data) {
            alert("Randevouz Booked")
        }
    }
    );
}


function get_bt() {
    $.ajax({
        url: "Cre_bt",
        type: 'GET',
        success: function (data) {
            //append the blood test
            var json = $.parseJSON(data);
            $("#data").append("Successfully Created\n");

        }
    });
}

function show_sel() {
    $.ajax({
        url: "change_ran",
        type: "GET",
        data: {username: $("#username").text()},
        success: function (data) {
            var obj = JSON.parse(data);
            var ele = "<table class = 'table'>";
            ele += "\n <thead> \n <tr> \n\
            <th scope = 'col'>Doctor's Username: </th>\n\
            <th scope = 'col'>Patient's Username: </th>\n\
            <th scope = 'col'>Date:  </th>\n\
            <th scope = 'col'>Price: </th>\n\
             <th scope = 'col'>Status: </th>\n\
            <th scope = 'col'>Actions: </th>\n\
            </tr> </thead>";
            ele += "<tbody> \n ";
            for (x in obj) {
                ele += "<tr>";
                ele += "<th scope = 'row'>" + obj[x].doctor_info + "</th>";
                ele += "<td>" + obj[x].user_info + "</td>";
                ele += "<td>" + obj[x].date_time + "</td>";
                ele += "<td>" + obj[x].price + "</td>";
                ele += "<td>" + obj[x].status + "</td>";
                ele += "<td>";
                ele += "<button class = 'btn-primary' onclick = cancel(" + obj[x].randevouz_id + ") > Cancel </button>"
                ele += "</td>";
                ele += "</tr> ";

            }
            ele += "</tbody>\n </table>";
            $("#forms").append(ele);
        }
    });
}

function cancel(id) {
    $.ajax({
        url: "change_ran",
        type: "post",
        data: {r_id: id},
        success: function (data) {
            alert("Successfully Canceled !");
        }
    });
}
