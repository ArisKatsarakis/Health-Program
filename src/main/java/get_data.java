import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "get_data", value = "/get_data")
public class get_data extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson data = new Gson();
        String username = request.getParameter("username");
        try{
            User us = User.getUser(username);
            String d  = data.toJson(us);
            out.print(d);
            out.flush();

        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
