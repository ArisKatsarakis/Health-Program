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
@WebServlet(name = "ren", value = "/ren")
public class ren extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //It gets all the randevouz of a doctor
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            PrintWriter out = response.getWriter();

            Randevouz r = new Randevouz();
            String query = "select * from rendevouz where username_doc = '"
                    + request.getParameter("username")
                    + "';";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(query);
            response.setStatus(200);
            out.print("[");
            rs.next();
            Gson data = new Gson();
                String ret ;
                r.setDoctor_info(rs.getString("username_doc"));
                r.setDate_time(rs.getDate("day").toString()+ rs.getString("hour"));
                r.setPrice(rs.getInt("price"));
                r.setStatus(rs.getString("state"));
                ret = data.toJson(r);
                out.print(ret);
            while (rs.next()) {
                out.print(",");
                Randevouz r1 = new Randevouz();
                r1.setDoctor_info(rs.getString("username_doc"));
                r1.setDate_time(rs.getDate("day").toString()  +" "+ rs.getString("hour"));
                r1.setPrice(rs.getInt("price"));
                r1.setStatus(rs.getString("state"));
                ret = data.toJson(r1);
                out.print(ret);
            }
            
             out.print("]");
            
            out.flush();

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
        String q = "insert into rendevouz (username_doc, username_pat, day, hour, state, price) values ( '"
                + request.getParameter("username")
                + "', 'null', '"
                + request.getParameter("date")
                + "',"
                + "'"
                + request.getParameter("hour")
                + "', 'open"
                + "',"
                + request.getParameter("price")
                + "); ";
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            PrintWriter out = response.getWriter();
            System.out.println(q);
            stmt.execute(q);
            response.setStatus(200);
            out.print("Sucess Creatiaon \n");
            out.flush();
//              /check the query
        } catch (Exception e) {
               System.out.print(e.toString());
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
