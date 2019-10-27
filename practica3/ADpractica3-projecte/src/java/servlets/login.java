/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ruben.sanz.garcia
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequestPOST(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        sesion.invalidate();
        sesion = request.getSession();
        String usu, pass;
        usu = request.getParameter("user");
        pass = request.getParameter("password");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        try {
                
            PreparedStatement statement;
            String query;
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");           
            
            Path path = Paths.get("C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");
            
            if (Files.exists(path)) {
               connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");   
            }

            if (Files.notExists(path)) {
               connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");
                query = "create table if not exists usuarios (id_usuario string primary key not null, password string)";
                statement = connection.prepareStatement(query);                        
                statement.executeUpdate();
            } 
        
            query = "select * from usuarios where id_usuario=?";
            statement = connection.prepareStatement(query);    
            statement.setString(1, usu);
            ResultSet rs = statement.executeQuery();
            if(rs.next() == false)
                response.sendRedirect("error.jsp");
            else{
                if((rs.getString("password")).equals(pass)){
                    sesion.setAttribute("usuario", usu);
                    response.sendRedirect("menu.jsp");
                }
                else{
                    response.sendRedirect("error.jsp");
                }
            }               
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            out.println(e.getMessage());
            response.sendRedirect("error.jsp");
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                response.sendRedirect("error.jsp");
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
        response.sendRedirect("login.jsp");
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
        processRequestPOST(request, response);
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
