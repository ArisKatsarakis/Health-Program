<html>
    <head>
        <meta charset="UTF-8">
        <title>Personalized Health API </title>
        <link rel="stylesheet" href="web/CSS/index.css">
        <link rel = "stylesheet" href = "web/CSS/style.css">
        <link rel = "stylesheet" href = "web/CSS/panel.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src = "jsp.js" ></script>
        <script src ='web/java.js'></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js">
        </script>
        <script src="https://www.google.com/jsapi"></script>
        <script>
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.load('current', {packages: ['corechart', 'line']});
            google.charts.load('current', {'packages':['bar']});
            google.charts.setOnLoadCallback("show_charts()");
        </script>
    </head>
    <body>
        <header>
            <h1 href="index.html"> Welcome to our HY 359 API </h1>
            <button onclick = "Show_Reg()" class = "btn btn-light">Register</button>
            <button onclick= "Show_log()" class = "btn btn-dark">Log in</button>

        </header>
        <h2 id="username">

        </h2>
        <div id = "content" class = "contaier-fluid" style = "text-align: center;">
            


        </div>
        <div id = "forms">

        </div>
        <footer>
            <div class ="contaier-fluid" style = "text-align: center;">
                <div class="col">

                    <a href="https://www.vrisko.gr/efimeries-farmakeion/irakleio" target="_blank"><img src="https://www.vrisko.gr/graphlink/Pharmacies/image/300x60_Banner_n/?Region=irakleio&NativeRegion=%ce%97%cf%81%ce%ac%ce%ba%ce%bb%ce%b5%ce%b9%ce%bf&" border="0" alt="???????????? ????????? ????????" /></a>
                    <a href="https://www.vrisko.gr/efimeries-nosokomeion?SelectedCity=hrakleio" target="_blank"><img src="https://www.vrisko.gr/graphlink/Hospitals/image/300x60_Banner_n/?Prefecture=hrakleio&NativePrefecture=%ce%97%ce%a1%ce%91%ce%9a%ce%9b%ce%95%ce%99%ce%9f&" border="0" alt="???????????? ?????????? ????????" /></a>
                </div>
                <div class ="col">
                    <h3>Heraklion Public Clinics</h3>
                    <a href ="https://www.pagni.gr/">Pagni General Population Hospital</a>
                    <a href = "https://www.venizeleio.gr/"> Venizeleio Public Hospital </a>
                </div>
                <div class ="col">
                    <h3> Useful Links</h3>
                    <a href ="https://www.ekab.gr/files/entypa/EKAB-protes-voithies.pdf">First Aid Instructions</a>
                    <a href = "https://covid19.gov.gr/"> Covid-19 Info In Greece </a>
                </div>
            </div>
        </footer>
    </body>
</html>