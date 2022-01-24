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
@WebServlet(name = "book_ran", value = "/book_ran")
public class book_ran extends HttpServlet {

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
            out.println("<title>Servlet book_ran</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet book_ran at " + request.getContextPath() + "</h1>");
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
        String query = "select * from rendevouz where state = 'open' ;";
        PrintWriter out = response.getWriter();
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            out.print("[");
            rs.next();
            Randevouz ra = new Randevouz();
            ra.setRandevouz_id(rs.getInt("id"));
            ra.setDoctor_info(rs.getString("username_doc"));
            ra.setDate_time(rs.getString("day") + " " + rs.getString("hour"));
            ra.setPrice(rs.getInt("price"));
            ra.setStatus(rs.getString("state"));
            Gson data = new Gson();
            String ret = data.toJson(ra);
            out.print(ret );

            while (rs.next()) {
                out.print(",");
                Randevouz rz = new Randevouz();
                rz.setRandevouz_id(rs.getInt("id"));
                rz.setDoctor_info(rs.getString("username_doc"));
                rz.setDate_time(rs.getString("day") + " " + rs.getString("hour"));
                rz.setPrice(rs.getInt("price"));
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
        PrintWriter out = response.getWriter();
        String query = "update rendevouz set "
                + "state = 'selected', "
                + "username_pat = '"
                + request.getParameter("username")
                +"' where id = "
                + request.getParameter("id")
                + ";";
       try{
           Connection con = DB_Connection.getConnection();
           Statement stmt = con.createStatement();
           stmt.execute(query);
           System.out.println("Appointment Booked Successfully !");
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
