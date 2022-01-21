import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
@WebServlet(name = "Login_Servlet", value = "/Login_Servlet")


public class Login_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");
        HttpSession ses = request.getSession(true);
        String rightpass ;
        System.out.println(username);
        Gson gson = new Gson();
        PrintWriter out  = response.getWriter();
        User test;
        int i  = 0;
//        if(ses.getAttribute("logged") != null){
//            response.setStatus(200);
//            test  = User.getUser(ses.getAttribute("logged").toString());
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            String ret = gson.toJson(test);
//            out.print(ret);
//            out.flush();
//        }else {
            try {
                Connection con = DB_Connection.getConnection();
                Statement stmt = con.createStatement();
                User one = new User();
                String query = "SELECT   `password`  FROM `USERS` WHERE username =  '" +
                        username + "';";
                ResultSet rs = stmt.executeQuery(query);
                try{
                    rs.next();
                    rightpass = rs.getString("password");
                    rs.close();
                    if (password.equals(rightpass)) {
                        System.out.println("Successful Login");
                    } else {
                        System.out.println("Wrong Credentials");
                    }
                    response.setStatus(200);
                    test = User.getUser(username);
                    test.setType("def");
                }catch(Exception e){
                    query = "select password from doctors where username = '" + username +"';";
                    rs = stmt.executeQuery(query);
                    rs.next();
                    rightpass = rs.getString("password");
                    rs.close();
                    if (password.equals(rightpass)) {
                        System.out.println("Successful Login");
                    } else {
                        System.out.println("Wrong Credentials");
                    }
                    response.setStatus(200);
                    test = User.getUser(username);
                    test.setType("doc");

                }
                System.out.println(query);

                //session enable
//                ses.setAttribute("logged", username);


                System.out.println(test.getUname());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String ret = gson.toJson(test);
                out.print(ret);
                out.flush();


            } catch (Exception e) {
                System.out.println(e.toString());
            }
//        }
        //response set status 200 Success
        //response set status 403 Success
        //get all resources from DB and put in session
        //get all table USERS


    }
}
