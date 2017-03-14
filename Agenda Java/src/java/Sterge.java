
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author crisfast95
 */
@WebServlet(name = "sterge", urlPatterns = {"/Sterge"})
public class Sterge extends HttpServlet {

    String nume, prenume, nrmob, email;
    String id;
    Connection link;
    Statement statement;
    String sql;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //incarcam datele din formular
        nume = request.getParameter("nume");
        prenume = request.getParameter("prenume");
        nrmob = request.getParameter("nrmob");
        email = request.getParameter("email");
        id = request.getParameter("id");

        //verificam daca criteriul a fost completat si stergem in caz de e adevarat
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "august95");
            statement = (Statement) link.createStatement();

            if (!id.equals("")) {
                sql = "delete from contacte where contact_id = " + id;
                statement.executeUpdate(sql);
            } else if (!nume.equals("")) {
                if (!prenume.equals("")) {
                    sql = "delete from contacte where prenume='" + prenume + "' and nume='" + nume + "'";
                    statement.executeUpdate(sql);
                } else {
                    sql = "delete from contacte where nume='" + nume + "'";
                    statement.executeUpdate(sql);
                }

            } else if (!prenume.equals("")) {
                sql = "delete from contacte where prenume='" + prenume + "'";
                statement.executeUpdate(sql);
            }

            if (!nrmob.equals("")) {
                sql = "delete from contacte where nrmob='" + nrmob + "'";
                statement.executeUpdate(sql);
            }
            if (!email.equals("")) {
                sql = "delete from contacte where email='" + email + "'";
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
        }
        if (!nrmob.equals("") || !email.equals("") || !prenume.equals("") || !nume.equals("") || !id.equals("")) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Stergere contact</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("Contact sters!");
                out.println("<a href=\"index.html\">Inapoi la agenda</a>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Stergere contact</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("Nu au fost introduse date. Va rugam sa incercati iar. <br>");
                out.println("<a href=\"delete.html\">Inapoi la pagina pentru sters contacte!</a> <br>");
                out.println("<a href=\"index.html\">Inapoi la agenda</a>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }
    }
