
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "actualizeaza", urlPatterns = {"/Actualizeaza"})
public class Actualizeaza extends HttpServlet {

    String nume, prenume, nrmob, email;
    String nrfix, adresa, oras, judet, codp;
    String nume_cautat, prenume_cautat;
    Connection link;
    Statement statement;
    String sql;
    ResultSet rezultat;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //preluam datele din formular pentru a vedea ce cautam
        nume_cautat = request.getParameter("nume_cautat");
        prenume_cautat = request.getParameter("prenume_cautat");
        

        //ne conectam la server + inlocuire
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "august95");
            statement = (Statement) link.createStatement();

            sql = "select * from contacte where nume = '" + nume_cautat + "' and prenume='" + prenume_cautat + "'";
            rezultat = statement.executeQuery(sql);

            nume = request.getParameter("nume");
            if (nume.equals("-")) {
                nume = rezultat.getString("nume");
            }

            prenume = request.getParameter("prenume");
            if (prenume.equals("-")) {
                prenume = rezultat.getString("prenume");
            }
            
            nrmob = request.getParameter("nrmob");
            if (nrmob.equals("-")) {
                nrmob = rezultat.getString("nrmob");
            }

            nrfix = request.getParameter("nrfix");
            if (nrfix.equals("-")) {
                nrfix = rezultat.getString("nrfix");
            } 

            email = request.getParameter("email");
            if (email.equals("-")) {
                email = rezultat.getString("email");
            }

            adresa = request.getParameter("adresa");
            if (adresa.equals("-")) {
                adresa = rezultat.getString("adresa");
            }

            oras = request.getParameter("oras");
            if (oras.equals("-")) {
                oras = rezultat.getString("oras");
            }

            judet = request.getParameter("judet");
            if (judet.equals("-")) {
                judet = rezultat.getString("judet");
            }

            codp = request.getParameter("codp");
            if (codp.equals("-")) {
                codp = rezultat.getString("codp");
            }

            sql = "update contacte set "
                    + "nume='" + nume + "' , prenume='" + prenume + "' , nrmob= '" + nrmob + "' "
                    + ", nrfix='" + nrfix + "' , email='" + email + "' , oras='" + oras + "' "
                    + ", judet='" + judet + "' , codp='" + codp + "' , adresa='" + adresa + "' "
                    + "where nume='" + nume_cautat + "' and prenume ='" + prenume_cautat + "'";

            statement.executeUpdate(sql);

        } catch (ClassNotFoundException | SQLException exc) {
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Actualizare contact</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Contact actualizat!");
            out.println("<a href=\"index.html\">Inapoi la agenda</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
