
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
@WebServlet(name = "cauta", urlPatterns = {"/Cauta"})
public class Cauta extends HttpServlet {

    String nume, prenume, nrmob, email;
    String nrfix, adresa, oras, judet, codp;
    Connection link;
    Statement statement;
    String sql;
    ResultSet rezultat;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //preluam datele din formular
        nume = request.getParameter("nume");
        if (nume.isEmpty()) {
            nume = "null";
        }

        prenume = request.getParameter("prenume");
        if (prenume.isEmpty()) {
            prenume = "null";
        }

            nrmob = request.getParameter("nrmob");
        if (nrmob.isEmpty()) {
            nrmob = "null";
        }

        nrfix = request.getParameter("nrfix");
        if (nrfix.isEmpty()) {
            nrfix = "null";
        }

        email = request.getParameter("email");
        if (email.isEmpty()) {
            email = "null";
        }

        adresa = request.getParameter("adresa");
        if (adresa.isEmpty()) {
            adresa = "null";
        }

        oras = request.getParameter("oras");
        if (oras.isEmpty()) {
            oras = "null";
        }

        judet = request.getParameter("judet");
        if (judet.isEmpty()) {
            judet = "null";
        }

        codp = request.getParameter("codp");
        if (codp.isEmpty()) {
            codp = "null";
        }

        //conectare la baza de date
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "august95");
            statement = (Statement) link.createStatement();

            sql = "select * from contacte where " + "nume = '" + nume + "' or prenume = '" + prenume + "' or nrmob = '" + nrmob + "' or nrfix = '" + nrfix + "' or email = '" + email
                    + "' or adresa = '" + adresa + "' or oras =  '" + oras + "' or judet = '" + judet + "' or codp = '" + codp + "'";

            rezultat = statement.executeQuery(sql);

            //afisare in caz de a mers
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Rezultat cautare</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<table border=\"1\" style=\"width:100%\">");

                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>Nume</th>");
                out.println("<th>Prenume</th>");
                out.println("<th>Telefon mobil</th>");
                out.println("<th>Telefon fix</th>");
                out.println("<th>Email</th>");
                out.println("<th>Adresa</th>");
                out.println("<th>Oras</th>");
                out.println("<th>Judet</th>");
                out.println("<th>Cod postal</th>");
                out.println("</tr>");

                while (rezultat.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rezultat.getString("contact_id") + "</td>");
                    out.println("<td>" + rezultat.getString("nume") + "</td>");
                    out.println("<td>" + rezultat.getString("prenume") + "</td>");
                    out.println("<td>" + rezultat.getString("nrmob") + "</td>");
                    out.println("<td>" + rezultat.getString("nrfix") + "</td>");
                    out.println("<td>" + rezultat.getString("email") + "</td>");
                    out.println("<td>" + rezultat.getString("adresa") + "</td>");
                    out.println("<td>" + rezultat.getString("oras") + "</td>");
                    out.println("<td>" + rezultat.getString("judet") + "</td>");
                    out.println("<td>" + rezultat.getString("codp") + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("<a href=\"index.html\">Inapoi la agenda</a>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (ClassNotFoundException | SQLException ex) {
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "august95");
            statement = (Statement) link.createStatement();

            sql = "select * from contacte";

            rezultat = statement.executeQuery(sql);

            //afisare in caz de a mers
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Rezultat cautare</title>");
                out.println("<style>a {margin: 25px 25px 25px 25px; padding-left: 20px;}</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<center><h2>Agenda ta personala</h2></center> <br>");
                out.println("<div> <a href=\"add.html\">Creare contact nou </a> <a href=\"edit.html\">Actualizare contact</a> <a href=\"delete.html\">Sterge contact</a> <a href=\"search.html\">Cauta contact</a> </div> <br>");
                out.println("<table border=\"1\" style=\"width:100%\">");

                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>Nume</th>");
                out.println("<th>Prenume</th>");
                out.println("<th>Telefon mobil</th>");
                out.println("<th>Telefon fix</th>");
                out.println("<th>Email</th>");
                out.println("<th>Adresa</th>");
                out.println("<th>Oras</th>");
                out.println("<th>Judet</th>");
                out.println("<th>Cod postal</th>");
                out.println("</tr>");

                while (rezultat.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rezultat.getString("contact_id") + "</td>");
                    out.println("<td>" + rezultat.getString("nume") + "</td>");
                    out.println("<td>" + rezultat.getString("prenume") + "</td>");
                    out.println("<td>" + rezultat.getString("nrmob") + "</td>");
                    out.println("<td>" + rezultat.getString("nrfix") + "</td>");
                    out.println("<td>" + rezultat.getString("email") + "</td>");
                    out.println("<td>" + rezultat.getString("adresa") + "</td>");
                    out.println("<td>" + rezultat.getString("oras") + "</td>");
                    out.println("<td>" + rezultat.getString("judet") + "</td>");
                    out.println("<td>" + rezultat.getString("codp") + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (ClassNotFoundException | SQLException ex) {
        }
    }
}
