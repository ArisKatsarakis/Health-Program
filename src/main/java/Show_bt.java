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
@WebServlet(name = "Show_bt", value = "/Show_bt")

public class Show_bt extends HttpServlet {

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
            out.println("<title>Servlet Show_bt</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Show_bt at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        String query = "select * from bloodtest where amka = (select amka from users where username = "
                + "'"
                + request.getParameter("username")
                + "');";
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            BloodTest bt = new BloodTest();
            out.print("[");
            bt.setAmka(rs.getString("amka"));
            bt.setBloodtest_id(rs.getInt("bloodtest_id"));
            bt.setMedical_center(rs.getString("medical_center"));
            bt.setTest_date(rs.getDate("test_date").toString());
            bt.setVitamin_b12(rs.getDouble("vitamin_b12"));
            bt.setVitamin_d3(rs.getDouble("vitamin_d3"));
            bt.setIron(rs.getDouble("iron"));
            bt.setBlood_sugar(rs.getDouble("blood_sugar"));
            bt.setCholesterol(rs.getDouble("cholesterol"));
            bt.setValues();
            EditBloodTestTable ebt = new EditBloodTestTable();
            String ret = ebt.bloodTestToJSON(bt);
            out.print(ret);
            while (rs.next()) {
                out.print(",");
                BloodTest nt = new BloodTest();
                nt.setTest_date(rs.getDate("test_date").toString());
                nt.setMedical_center(rs.getString("medical_center"));
                nt.setBloodtest_id(rs.getInt("bloodtest_id"));
                nt.setAmka(rs.getString("amka"));
                nt.setVitamin_b12(rs.getDouble("vitamin_b12"));
                nt.setVitamin_d3(rs.getDouble("vitamin_d3"));
                nt.setIron(rs.getDouble("iron"));
                nt.setBlood_sugar(rs.getDouble("blood_sugar"));
                nt.setCholesterol(rs.getDouble("cholesterol"));
                nt.setValues();
                ret = ebt.bloodTestToJSON(nt);
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
