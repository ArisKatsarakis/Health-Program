
function  Show_Reg() {
    $("#content").empty();
    $("#content").load("web/form.html");
}
function  Show_log() {
    $("#content").empty();
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
                    $("#content").empty();

                    $("#content").load("web/userpanel.html");
                    $("#username").html(form.username);
                } else {
                    $("#content").empty();

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
    $("#content").empty();
    $("#content").load("web/userpanel.html");
}
function  show_data() {
    $.ajax({
        url: 'get_data',
        data: {username: "Diaolos93"},
        type: 'get',
        success: function (data) {
            const d = JSON.parse(data);
            $("#data").append(data);

        }
    }
    );
}

//function  show_type() {
//    var type = $('input[name = "type"]:checked').val();
//    alert(type);
//}

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
    $("#data").empty();
    $.ajax({
        url: "book_ran",
        type: 'get',
        success: function (data) {

            var obj = $.parseJSON(data);
            for (x in obj) {
                $("#data").append("  <div class='form-check'>" +
                        "<input class= 'form-check-input' type='checkbox' value='" + obj[x].randevouz_id + "' name='" + obj[x].randevouz_id + "'>" +
                        "<label   class='form-check-label' for='" + obj[x].randevouz_id + "'>" +
                        "Doctor Info: " + obj[x].doctor_info + " Price: " + obj[x].price + " State: " + obj[x].status + " Date: " + obj[x].date_time +
                        "  </label>" +
                        " </div> ");
                $("#data").append("<button type = 'button' class = 'btn-primary' onclick = 'book_it(" + obj[x].randevouz_id + ")' >Book it! </button>");
            }
        }
    });
}

function book_it(pat) {

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
        data: {username: $("#username").text()},
        success: function (data) {
            //append the blood test
            var json = $.parseJSON(data);
            $("#data").empty();
            $("#data").append("Successfully Created\n");

        }
    });
}

function show_sel() {
    $("#forms").empty();
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
                ele += "<button class = 'btn-primary' onclick = cancel(" + obj[x].randevouz_id + ") > Cancel </button>";
                ele += "<button class = 'btn-primary' onclick = done_ren(" + obj[x].randevouz_id + ") > Done </button>"
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

function show_bt() {
    $("#data").empty();

    $.ajax({
        url: "Show_bt",
        type: "GET",
        data: {username: $("#username").text()},
        success: function (data) {
            var obj = JSON.parse(data);
            var cholesterol = [];
            for (x in obj) {
                var collapse_button = "<button class='btn btn-primary' type='button' data-toggle='collapse' data-target='#" + obj[x].bloodtest_id + "' \n\
                aria-expanded='false' aria-controls='" + obj[x].bloodtest_id + "'> " + obj[x].test_date + " </button> \n";
                collapse_button += "<div class ='collapse' id = '" + obj[x].bloodtest_id + "'> \n";
                collapse_button += "<div class = 'card card-body'> \n";
                collapse_button += "\n <table class = 'table'> \n<thead> <h2>AMKA: " + obj[0].amka + " Medical Center: " + obj[0].medical_center + " </h2> \n <tr> \n\
                <th scope = 'col'>Attribute: </th>\n\
                <th scope = 'col'> Value: </th>\n\
                <th scope = 'col'> Level: </th>\n\
                </tr> </thead> \n <tbody> \n";
                collapse_button += "<tr>";
                collapse_button += "<td> Iron </td>";
                collapse_button += "<td> " + obj[x].iron + "</td>";
                collapse_button += "<td> " + obj[x].iron_level + "</td>";
                collapse_button += "</tr>";
                collapse_button += "<tr>";
                collapse_button += "<td> Cholesterol </td>";
                collapse_button += "<td> " + obj[x].cholesterol + "</td>";
                collapse_button += "<td> " + obj[x].cholesterol_level + "</td>";
                collapse_button += "</tr>";
                collapse_button += "<tr>";
                collapse_button += "<td> Blood Sugar </td>";
                collapse_button += "<td> " + obj[x].blood_sugar + "</td>";
                collapse_button += "<td> " + obj[x].blood_sugar_level + "</td>";
                collapse_button += "</tr>";
                collapse_button += "<tr>";
                collapse_button += "<td> Vitamin D3 </td>";
                collapse_button += "<td> " + obj[x].vitamin_d3 + "</td>";
                collapse_button += "<td> " + obj[x].vitamin_d3_level + "</td>";
                collapse_button += "</tr>";
                collapse_button += "<tr>";
                collapse_button += "<td> Vitamin B12 </td>";
                collapse_button += "<td> " + obj[x].vitamin_b12 + "</td>";
                collapse_button += "<td> " + obj[x].vitamin_b12_level + "</td>";
                collapse_button += "</tr>";
                collapse_button += "\n</table> </div> \n </div> \n";
                var item = {};
                item ["date"] = obj[x].test_date;
                item ["value"] = obj[x].cholesterol;
                item ["level"] = obj[x].cholesterol_level;
                cholesterol.push(item);
                $("#data").append(collapse_button);
            }
            console.log(cholesterol);

            var chars = "<div class= 'form-control' id = 'charts'><h2> Charts</h2>\n\n\
<button class = 'btn-primary' onclick= 'show_charts (" + data + ")'> Show Charts </button> \n </div>";
            $("#data").append(chars);
//            show_charts(cholesterol);
        }
    });
}

function show_mess_panel() {
    $("#forms").empty();
    $("#forms").load("web/mail.html");
}
function show_messages(type) {

    $.ajax({
        url: "email",
        data: {username: $("#username").text(), type: type},
        type: "GET",
        success: function (data) {
            var obj = JSON.parse(data);
            var html;
            if (type === 'inbox') {
                html = "<div class = 'container-fluid'>";
                for (x in obj) {
                    html += "<div class = 'row' style = 'border-bottom: 1px solid black'> <p  onclick = 'show_text("+obj[x].id+")'  class = 'col-md-10'> From: " + obj[x].from;
                    html += "   Subject " + obj[x].subject;
                    html += "</p> \n <button type = 'button' onclick = 'send_message()' class = 'btn-secondary'> Reply </button> \n \n\
<button type = 'button' onclick = 'del_messages(" + obj[x].id + ")' class = 'btn-danger'>delete  </button> </div> \n ";

                }
                html += "</div>";
                html += "\n <div id = 'index'> </div>";
                $("#forms").append(html);
            } else {
                html = "<div class = 'container-fluid'>";
                for (x in obj) {
                    html += "<div class = 'row' style = 'border-bottom: 1px solid black'> <p  onclick = 'show_text("+obj[x].id+")' class = 'col-md-10'> To: " + obj[x].to;
                    html += "   Subject " + obj[x].subject;
                    html += "</p> \n <button type = 'button' onclick = 'send_message()' class = 'btn-secondary'> Reply </button> \n \n\
<button type = 'button' onclick = 'del_messages(" + obj[x].id + ")' class = 'btn-danger'>delete  </button> </div> \n ";

                }
                html += "</div>";
                html += "\n <div id = 'index'> </div>";
                $("#forms").append(html);
            }
        },
        error: function (request, status, error) {
            alert(request.responseText);
        }
    });
}

function del_messages(id) {
    $.ajax({
        url: "Del_Email",
        type: "GET",
        data: {num: id},
        success: function (data) {
            alert("Message Deleted !");
            $("#forms").empty();
            show_messages();
        }
    });
}

function mess_form() {
    $("#forms").empty();
    //make a html form for reply 
    $("#forms").load("web/reply.html");

}
function send() {
    $("#from").val($("#username").text());
    $("#from").hide();
    var xhr = new XMLHttpRequest();

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert(xhr.rersopseText)
        }

    };
    var data = $("#send").serialize();

    xhr.open("POST", "email");
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);

}

function show_doc() {
    var address;
    $.ajax({
        url: "Login_Servlet",
        type: "POST",
        data: {uname: $("#username").text(), pass: "123"},
        success: function (data) {
            var obj = JSON.parse(data);
            address = obj;
        }
    });

}

function done_ren(id) {
    $.ajax({
        url: "Show_History",
        type: "GET",
        data: {r_id: id},
        success: function (data) {
            alert("Rendevouz Successfully Done!");

        }
    });
}

function show_history() {
    $.ajax({
        url: "Show_History",
        type: "POST",
        data: {username: $("#username").text()},
        success: function (data) {
            var obj = JSON.parse(data);
            var amka = obj[0].amka;
            $("#forms").append(" <div> \n <h2 class= 'form-control'  >" + amka + "</h2> \n");

            for (x in obj) {
                if (amka !== obj[x].amka) {
                    amka = obj[x].amka;
                    $("#forms").append("</div> \n <div> \n <h2 class= 'form-control'  >" + amka + "</h2> \n");
                }
                var collapse_button = "<button class='btn btn-primary' type='button' data-toggle='collapse' data-target='#" + obj[x].bloodtest_id + "' \n\
                aria-expanded='false' aria-controls='" + obj[x].bloodtest_id + "'> " + obj[x].test_date + " </button> \n";
                collapse_button += "<div class ='collapse' id = '" + obj[x].bloodtest_id + "'> \n";
                collapse_button += "<div class = 'card card-body'> \n";
                collapse_button += "\n <table class = 'table'> \n<thead> <h2>AMKA: " + obj[0].amka + " Medical Center: " + obj[0].medical_center + " </h2> \n <tr> \n\
                <th scope = 'col'>Attribute: </th>\n\
                <th scope = 'col'> Value: </th>\n\
                <th scope = 'col'> Level: </th>\n\
                </tr> </thead> \n <tbody> \n";
                collapse_button += "<tr>";
                collapse_button += "<td> Iron </td>";
                collapse_button += "<td> " + obj[x].iron + "</td>";
                collapse_button += "<td> " + obj[x].iron_level + "</td>";
                collapse_button += "</tr>";
                collapse_button += "<tr>";
                collapse_button += "<td> Cholesterol </td>";
                collapse_button += "<td> " + obj[x].cholesterol + "</td>";
                collapse_button += "<td> " + obj[x].cholesterol_level + "</td>";
                collapse_button += "</tr>";
                collapse_button += "<tr>";
                collapse_button += "<td> Blood Sugar </td>";
                collapse_button += "<td> " + obj[x].blood_sugar + "</td>";
                collapse_button += "<td> " + obj[x].blood_sugar_level + "</td>";
                collapse_button += "</tr>";
                collapse_button += "<tr>";
                collapse_button += "<td> Vitamin D3 </td>";
                collapse_button += "<td> " + obj[x].vitamin_d3 + "</td>";
                collapse_button += "<td> " + obj[x].vitamin_d3_level + "</td>";
                collapse_button += "</tr>";
                collapse_button += "<tr>";
                collapse_button += "<td> Vitamin B12 </td>";
                collapse_button += "<td> " + obj[x].vitamin_b12 + "</td>";
                collapse_button += "<td> " + obj[x].vitamin_b12_level + "</td>";
                collapse_button += "</tr>";
                collapse_button += "\n</table> </div> \n </div> \n";
                $("#forms").append(collapse_button);
            }

        }


    });
}

function show_charts(obj) {
    var test = JSON.stringify(obj);

    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Date');
    data.addColumn('number', 'Cholesterol');
    data.addColumn('number', 'iron');
    data.addColumn('number', 'Vitamin B12');
    data.addColumn('number', 'Vitamin D3');
    data.addColumn('number', 'blood_sugar');
    for (x in obj) {
        data.addRow([obj[x].test_date, obj[x].cholesterol, obj[x].iron, obj[x].vitamin_b12, obj[x].vitamin_d3, obj[x].blood_sugar])
    }
    var options = {
        width: 1000,
        height: 600,
        hAxis: {
            title: 'Month'
        },
        vAxis: {
            title: 'Cholesterol Value'
        },
        colors: ['#e0440e', '#000000', '#17FF00', '#002EFF', '#FF0000']
    };
    var chart = new google.visualization.LineChart(document.getElementById('charts'));
    chart.draw(data, options);
    $("#charts").append("<div id ='more_charts' > \n\
<button onclick= 'more_charts("+test+")'>More Charts </button> </div>");
    

}
function show_text(id){
    $.ajax({
        url:"Check_Email",
        type: "GET",
        data:{e_id: id},
        success:function(data){
            $("#index").empty();
            $("#index").append(data);
        }
            
    })
}

function more_charts(obj){
    data = new google.visualization.DataTable();
    data.addColumn('string', 'Date');
    data.addColumn('number','Cholesterol');
    
    for(x in obj){
        data.addRow([obj[x].test_date, obj[x].cholesterol]);
    }
    
    var options = {
          chart: {
            title: 'Cholesterol Process',
            subtitle: 'Depending the Dates of the Blood Test',
          },
          bars: 'horizontal' ,// Required for Material Bar Charts.,
          width:900,
          height:500
        };
        var chart = new google.charts.Bar(document.getElementById('more_charts'));
    chart.draw(data, google.charts.Bar.convertOptions(options));
    
}