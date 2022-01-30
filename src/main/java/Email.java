/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
        //get the messages of the user
        //check if doctor or patient first
        String query = "select * from message where username_pat ='"
                + request.getParameter("username")
                + "';";
        PrintWriter out = response.getWriter();
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                System.out.println("Its a Doctor");
                query = "select * from message where username_doc = '"
                        + request.getParameter("username")
                        + "';";
                rs.close();
                rs = stmt.executeQuery(query);
                if(!rs.next()){
                    out.print("You have no messages");
                }
            }
            out.print("[");
            mail m1 = new mail();
            m1.set_id(rs.getInt("id"));
            m1.set_Subject(rs.getString("subject"));
            m1.set_Text(rs.getString("text"));
            m1.set_Username_doc(rs.getString("username_doc"));
            m1.set_Username_pat(rs.getString("username_pat"));
            Gson data = new Gson();
            String ret = data.toJson(m1);
            out.print(ret);
            while (rs.next()) {
                out.print(",");
                mail m2 = new mail();
                m2.set_id(rs.getInt("id"));
                m2.set_Subject(rs.getString("subject"));
                m2.set_Text(rs.getString("text"));
                m2.set_Username_doc(rs.getString("username_doc"));
                m2.set_Username_pat(rs.getString("username_pat"));
                ret = data.toJson(m2);
                out.print(ret);
            }
            out.print("]");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
