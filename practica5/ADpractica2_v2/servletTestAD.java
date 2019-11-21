/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author silviall
 */
@WebServlet(name = "servletTestAD", urlPatterns = {"/servletTestAD"})
public class servletTestAD extends HttpServlet {

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
        PrintWriter out = response.getWriter();

            
//sentencia.setString(1, "pepe");
//ResultSet rs = sentencia.executeQuery();        
        
        Connection connection = null;
        try {
            
            PreparedStatement statement;
            String query;
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");
            java.util.Date d = new java.util.Date();
            out.println("La fecha actual es " + d);

            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\silviall\\Desktop\\exemple.db");        
            
            // fill parameters for prepared statement
            // delete table usuarios
            query = "drop table if exists usuarios";
            statement = connection.prepareStatement(query);
            statement.setQueryTimeout(30);  // set timeout to 30 sec.                        
            statement.executeUpdate();

            // fill parameters for prepared statement            
            // delete table image            
            query = "drop table if exists image";
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            // fill parameters for prepared statement            
            // create and fill table usuarios            
            query = "create table usuarios (id_usuario string primary key, password string)";
            statement = connection.prepareStatement(query);                        
            statement.executeUpdate();
            
            query = "insert into usuarios values(?,?)";
            statement = connection.prepareStatement(query);    
            statement.setString(1, "Silvia");
            statement.setString(2, "12345");
            statement.executeUpdate();
            
            query = "insert into usuarios values(?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, "Pepito");
            statement.setString(2, "23456");                                    
            statement.executeUpdate();

            query = "create table image (id int NOT NULL, title varchar (256) NOT NULL, description varchar (1024) NOT NULL, keywords "
                    + "varchar (256) NOT NULL, author varchar (255) NOT NULL, creation_date varchar (10) NOT NULL, storage_date varchar (10) NOT NULL, filename varchar (512) NOT NULL, "
                    + "primary key (id), foreign key (author) references usuarios(id_usuario))";

            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            // With preparedStatement, SQL Injection and other problems when inserting values in the database can be avoided
                        
            query = "insert into image values(?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, 1);
            statement.setString(2, "test1");
            statement.setString(3, "This is image 1");
            statement.setString(4, "Keyword11, Keyword12");
            statement.setString(5, "Silvia");
            statement.setString(6, "2018/03/02");
            statement.setString(7, "2018/07/17");
            statement.setString(8, "file1.jpg");            
            statement.executeUpdate();      
            
            query = "insert into image values(?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, 2);
            statement.setString(2, "test2");
            statement.setString(3, "This is image 2");
            statement.setString(4, "Keyword21, Keyword22");
            statement.setString(5, "Silvia");
            statement.setString(6, "2018/03/02");
            statement.setString(7, "2018/07/17");
            statement.setString(8, "file2.jpg");            
            statement.executeUpdate();  
            
            query = "select * from usuarios";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();    

            while (rs.next()) {
                // read the result set
                out.println("<br>Id usuario = " + rs.getString("id_usuario"));
                out.println("Password = " + rs.getString("password"));
            }

            query = "select * from image";
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();                                    

            while (rs.next()) {
                // read the result set
                out.println("<br>Id image = " + rs.getString("id"));
                out.println("Titulo = " + rs.getString("title"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
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
        processRequest(request, response);
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
