
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author crisfast95
 */
@WebServlet(name = "adauga", urlPatterns = {"/Adauga"})
public class Adauga extends HttpServlet {

    String nume, prenume, nrmob, email;
    String nrfix, adresa, oras, judet, codp;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection link;
        Statement statement;
        String sql;

        //preluam datele trimise de formular
        nume = request.getParameter("nume");
        prenume = request.getParameter("prenume");
        nrmob = request.getParameter("nrmob");
        email = request.getParameter("email");
        nrfix = request.getParameter("nrfix");
        adresa = request.getParameter("adresa");
        oras = request.getParameter("oras");
        judet = request.getParameter("judet");
        codp = request.getParameter("codp");

        //ne conectam la baza de date
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "august95");
            statement = (Statement) link.createStatement();

            //formam comanda pentru a introduce contactul nou + executare
            sql = "insert into contacte" + "(nume,prenume,nrmob,nrfix,email,adresa,oras,judet,codp)" + "values" + "('" + nume + "','" + prenume + "','" + nrmob + "','" + nrfix + "','"
                    + email + "','" + adresa + "','" + oras + "','" + judet + "','" + codp + "')";

            statement.executeUpdate(sql);
        } catch (Exception ex) {
        }

        //mesaj daca totul merge ok
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Adaugare contact</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Contact adaugat!");
            out.println("<a href=\"index.html\">Inapoi la agenda</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     *
     * @param request
     * @param response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
