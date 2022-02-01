function checkpass(form) {
    pwd = form.pass.value;
    pwdr = form.passr.value;
    uname = form.uname.value;
    
    if(uname.length <= 8){
       
       document.getElementById("uname-check").style.visibility  = "visible";
    }else{
               document.getElementById("uname-check").style.visibility  = "hidden";

    }
    if (pwd == ' ') {


    } else if (pwd != pwdr) {
        form.pass.style.color = "red";
        form.passr.style.color = "red";
        document.getElementById("pass-check").style.visibility = "hidden";

    } else {
        form.pass.style.color = "green";
        form.passr.style.color = "green";
        document.getElementById("pass-check").style.color = "green";
        document.getElementById("pass-check").innerHTML = "<p>Password Match </p>";
        document.getElementById("pass-check").style.visibility = "visible";
    }
}
function pass_security(pass) {
    var nmbr = 0;
    var lett = 0;
    if (pass.value == ' ') {

    } else {
        for (let index = 0; index < pass.value.length; index++) {

            if (pass.value[0] <= "9") {
                nmbr++;
            } else {
                lett++;
            }

        }
        if (nmbr > (pass.value.length / 2)) {
            document.getElementById("pass-check").innerHTML = "<p>Password is weak! </p>"
            document.getElementById("pass-check").style.visibility = "visible";
 
       }
    }
}

function doctor_details(doc) {
    if (doc.value == "doc") {
        document.getElementById("doctor_details").style.visibility = "visible";
        document.getElementById("lbl").innerHTML = "Doctor's Office Address:";
    }else{
        document.getElementById("doctor_details").style.visibility = "hidden";
        document.getElementById("lbl").innerHTML = "Address:" ;
    }
    // finish on change 
}

function amka_check(amka) {
    var birth = document.getElementById("birthday");
    var index_amka = 0;
    let index_bir = 0;
    var birth_reinitialization = [];
    //birthday new format
    birth_reinitialization[0] = birth.value[8];
    birth_reinitialization[1] = birth.value[9];
    birth_reinitialization[2] = birth.value[5];
    birth_reinitialization[3] = birth.value[6];
    birth_reinitialization[4] = birth.value[0];
    birth_reinitialization[5] = birth.value[1];
    birth_reinitialization[6] = birth.value[2];
    birth_reinitialization[7] = birth.value[3];

    for (index_amka = 0; index_amka < 8; index_amka++) {
        if (amka.value[index_amka] != birth_reinitialization[index_amka]) {
            break;
        }
    }
    if (index_amka <= 7) {
        document.getElementById("amka_inv").innerHTML = "AMKA is invalid";
        document.getElementById("amka_inv").style.visibility = "visible";


    } else {
        document.getElementById("amka_inv").innerHTML = "AMKA is valid";
        document.getElementById("amka_inv").style.visibility = "visible";

    }
}

function checkbox(){}
//checkbox agreement check

