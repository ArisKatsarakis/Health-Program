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
@WebServlet(name = "change_ran", value = "/change_ran")
public class change_ran extends HttpServlet {

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
            out.println("<title>Servlet change_ran</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet change_ran at " + request.getContextPath() + "</h1>");
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
        //Get Scheduled Randevouz 
        String query = "select * from rendevouz where username_doc = '"
                + request.getParameter("username")
                + "' and state = 'selected' ;";
        PrintWriter out = response.getWriter();
        try {
            System.out.println(query);
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            out.print("[");
            Randevouz ra = new Randevouz();
            ra.setRandevouz_id(rs.getInt("id"));
            ra.setDate_time(rs.getDate("day").toString() + rs.getString("hour"));
            ra.setDoctor_info(rs.getString("username_doc"));
            ra.setUser_info(rs.getString("username_pat"));
            ra.setPrice(rs.getInt("price"));
            ra.setStatus(rs.getString("state"));
            Gson data = new Gson();
             String ret = data.toJson(ra);
            out.print(ret);
            while (rs.next()) {
                out.print(",");
                Randevouz rz = new Randevouz();
                rz.setRandevouz_id(rs.getInt("id"));
                rz.setDate_time(rs.getDate("day").toString() + rs.getString("hour"));
                rz.setDoctor_info(rs.getString("username_doc"));
                rz.setUser_info(rs.getString("username_pat"));
                rz.setPrice(rs.getInt("price"));
                rz.setStatus(rs.getString("state"));
                ret = data.toJson(rz);
                out.print(ret);
            }
            out.print("]");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
       out.flush();
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
        String query = "update rendevouz set state = 'canceled' where id = '"
                + request.getParameter("r_id")
                + "';";
        try{
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            stmt.execute(query);
            query = "insert into email (sender,receiver,subject,text) "
                    + "select username_doc,username_pat,day + 'Canceled' ,'Your apointment for the day Canceled' from rendevouz where id = '"
                    +request.getParameter("r_id")
                    +"';";
            System.out.println(query);
            stmt.execute(query);

        }catch(Exception e){
            System.out.println(e.toString());
        }
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
