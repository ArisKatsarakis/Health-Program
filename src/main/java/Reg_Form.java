import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Reg_Form", value = "/Reg_Form")
public class Reg_Form extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        StringBuilder s_build = new StringBuilder();
        FileReader read = new FileReader("form.html");

        try{
            BufferedReader br = new BufferedReader(read);
            String val ;

            while((val = br.readLine()) != null){
                s_build.append(val);
            }

            String outcome = s_build.toString();
            out.print(outcome);

            br.close();


        }catch (Exception e ){
            System.out.println(e.toString());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ///TEST
    }
}
