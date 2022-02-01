


import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zaxariaskatsarakis
 */
@WebServlet(name = "email", value = "/email")
public class Email extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Email</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Email at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get received messages
        System.out.println(request.getParameter("type"));
        if("inbox".equals(request.getParameter("type"))){
        String query = "select * from email where receiver ='"
                + request.getParameter("username")
                + "';";
        PrintWriter out = response.getWriter();
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            mail m1 = new mail();

            rs.next();
            out.print("[");
            m1.set_From(rs.getString("sender"));
            m1.set_Subject(rs.getString("subject"));
            m1.set_Text(rs.getString("text"));
            m1.set_id(rs.getInt("id"));
            m1.set_To(request.getParameter("username"));
            Gson data = new Gson();
            String ret = data.toJson(m1);
            out.print(ret);
            while (rs.next()) {
                out.print(",");
                mail m2 = new mail();
                m2.set_From(rs.getString("sender"));
                m2.set_Subject(rs.getString("subject"));
                m2.set_Text(rs.getString("text"));
                m2.set_id(rs.getInt("id"));
                m2.set_To(request.getParameter("username"));
                ret = data.toJson(m2);
                out.print(ret);
            }
            out.print("]");
        } catch (Exception e) {
            System.out.println(e.toString());
            response.setStatus(404);
            out.print("No Messages was Found!");
        }
      }else{
        String query = "select * from email where sender ='"
                + request.getParameter("username")
                + "';";
        PrintWriter out = response.getWriter();
        System.out.println(query);
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            mail m1 = new mail();

            rs.next();
            out.print("[");
            m1.set_From(request.getParameter("username"));
            m1.set_Subject(rs.getString("subject"));
            m1.set_Text(rs.getString("text"));
            m1.set_id(rs.getInt("id"));
            m1.set_To(rs.getString("receiver"));
            Gson data = new Gson();
            String ret = data.toJson(m1);
            out.print(ret);
            while (rs.next()) {
                out.print(",");
                mail m2 = new mail();
                m2.set_From(request.getParameter("username"));
                m2.set_Subject(rs.getString("subject"));
                m2.set_Text(rs.getString("text"));
                m2.set_id(rs.getInt("id"));
                m2.set_To(rs.getString("receiver"));
                ret = data.toJson(m2);
                out.print(ret);
            }
            out.print("]");
        } catch (Exception e) {
            System.out.println(e.toString());
            response.setStatus(404);
            out.print("No Messages was Found!");
        }
      
      }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = "insert into email values \n ("
                + "'" + request.getParameter("from") + "',"
                + "'" + request.getParameter("to") + "',"
                + "'" + request.getParameter("sjc") + "',"
                + "'" + request.getParameter("txt") + "',"
                + "null );";
        System.out.println(query);
        PrintWriter out = response.getWriter();
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            stmt.execute(query);
            response.setStatus(200);
            out.println("Message Send Successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
            out.println("Message Send Failed!");
            response.setStatus(500);

        }

        out.flush();
    }

   


}
