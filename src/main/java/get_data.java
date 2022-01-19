import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "get_data", value = "/get_data")
public class get_data extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String data ;
        String username = request.getParameter("username");
        String query  = "SELECT * from USERS where username = " +'"' +username +'"' +";";
        try{
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs  = stmt.executeQuery(query);

        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
