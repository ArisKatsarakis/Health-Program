/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 *
 * @author zaxariaskatsarakis
 */
import java.util.logging.Level;
import java.util.logging.Logger;
@WebServlet(name = "Cre_bt" , value = "/Cre_bt")
public class Cre_bt extends HttpServlet {

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
            out.println("<title>Servlet Cre_bt</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cre_bt at " + request.getContextPath() + "</h1>");
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
        double vitamin_d3;
        double vitamin_b12;
        double cholesterol;
        double blood_sugar;
        double iron;
        BloodTest bt = new BloodTest();
        EditBloodTestTable e_bt = new EditBloodTestTable();
        Date str = Date.valueOf(LocalDate.now());
        //create double randoms 
        Random rd = new Random();
        vitamin_d3 = (rd.nextDouble() * 200 );
        vitamin_b12 = (rd.nextDouble() * 1100);
        cholesterol = rd.nextDouble()* 250;
        blood_sugar = rd.nextDouble() * 300;
        iron = rd.nextDouble()* 220;
        bt.setAmka("22111993111");
        bt.setTest_date(str.toString());
        bt.setBlood_sugar(blood_sugar);
        bt.setCholesterol(cholesterol);
        bt.setVitamin_d3(vitamin_d3);
        bt.setVitamin_b12(vitamin_b12);
        bt.setIron(iron);
        bt.setMedical_center("Knossos Diagnosis");
        bt.setValues();
        try {
            e_bt.createNewBloodTest(bt);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cre_bt.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("BloodTest Sucessfully Created !");
        Gson data = new Gson();
        String ret = data.toJson(bt);
        response.setStatus(200);
        out.print(ret);
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
        //Book the Appointment
        
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
