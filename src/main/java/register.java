
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "register", value = "/web/register")
public class register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (request.getParameter("type").equals("def")) {
            User new_user = new User();
            new_user.setUsername(request.getParameter("uname"));
            new_user.setEmail(request.getParameter("email"));
            new_user.setPassword(request.getParameter("pass"));
            new_user.setGender(request.getParameter("sex"));
            new_user.setFirstname(request.getParameter("fname"));
            new_user.setLastname(request.getParameter("lname"));
            new_user.setBirthdate(request.getParameter("birthday"));
            new_user.setCountry(request.getParameter("country"));
            new_user.setCity(request.getParameter("city"));
            new_user.setHeight(Integer.parseInt(request.getParameter("height")));
            new_user.setAddress(request.getParameter("address"));
            new_user.setTelephone(request.getParameter("phone"));
            new_user.setAmka(request.getParameter("amka"));
            new_user.setWeight(Double.parseDouble(request.getParameter("weight")));
            new_user.setBloodtype(request.getParameter("b-type"));
            new_user.setBlooddonor(request.getParameter("donor"));
            if (request.getParameter("type") == "doc") {
                System.out.println("he is a doctor");
            }
            //New user Ready !!!
            out.println(new_user.getAmka());
            try {
                new_user.registerUser();
            } catch (Exception e) {
                out.println("register side" + e.toString());
            }
        }else{
            Doctor new_user = new Doctor();
            new_user.setUsername(request.getParameter("uname"));
            new_user.setEmail(request.getParameter("email"));
            new_user.setPassword(request.getParameter("pass"));
            new_user.setGender(request.getParameter("sex"));
            new_user.setFirstname(request.getParameter("fname"));
            new_user.setLastname(request.getParameter("lname"));
            new_user.setBirthdate(request.getParameter("birthday"));
            new_user.setCountry(request.getParameter("country"));
            new_user.setCity(request.getParameter("city"));
            new_user.setHeight(Integer.parseInt(request.getParameter("height")));
            new_user.setAddress(request.getParameter("address"));
            new_user.setTelephone(request.getParameter("phone"));
            new_user.setAmka(request.getParameter("amka"));
            new_user.setWeight(Double.parseDouble(request.getParameter("weight")));
            new_user.setBloodtype(request.getParameter("b-type"));
            new_user.setBlooddonor(request.getParameter("donor"));
            new_user.setCertified(0);
            //New user Ready !!!
            out.println(new_user.getAmka());
            try {
                new_user.reg_Doctor();
            } catch (Exception e) {
                out.println("register side" + e.toString());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
