/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ruben.sanz.garcia
 */
@WebServlet(urlPatterns = {"/registrarImagen"})
@MultipartConfig
public class registrarImagen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession sesion = request.getSession();
            PrintWriter out = response.getWriter();
            Connection connection = null;
            try {
                        
                PreparedStatement statement;
                String query;
                // load the sqlite-JDBC driver using the current class loader
                Class.forName("org.sqlite.JDBC");           

                connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");       
                query = "create table if not exists image (id integer primary key, title varchar (256) NOT NULL, description varchar (1024) NOT NULL, keywords "
                + "varchar (256) NOT NULL, author varchar (255) NOT NULL, creation_date varchar (10) NOT NULL, storage_date varchar (10) NOT NULL, filename varchar (512) NOT NULL UNIQUE, "
                + "foreign key (author) references usuarios(id_usuario))";
                statement = connection.prepareStatement(query);
                statement.executeUpdate();
                
                String title, description, keywords, author, fileName, creation_date, storage_date;
                title = request.getParameter("title");
                description = request.getParameter("description");
                keywords = request.getParameter("keyword");
                author = (String) sesion.getAttribute("usuario");
                //fileName = request.getParameter("filename");
                creation_date = (String)request.getParameter("creationDate");
                 
                //cogemos decha actual
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime dateTime = LocalDateTime.now();
                storage_date = dateTime.format(formatter);
                
                //recogemos archivo subido, guardamos su nombre en fileName
                Part filePart = request.getPart("picture"); // Retrieves <input type="file" name="file">
                fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                InputStream fileContent = filePart.getInputStream();
                //guardamos el fichero subido en nuestro directorio
                File uploads = new File("C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\web\\Imagenes");
                File fichero = new File(uploads, fileName);
                try (InputStream input = fileContent) {
                    Files.copy(input, fichero.toPath());
                }
                
                query = "select max(id) from image";
                statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                int newId = 1;
                if(rs.next())
                    newId= rs.getInt(1) + 1;
                 
                        
                query = "insert into image (id, title, description, keywords, author, creation_date, storage_date, filename)"
                        + "values(?, ?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, newId); //id imagen
                statement.setString(2, title); //titulo imagen
                statement.setString(3, description); //descripcion imagen
                statement.setString(4, keywords); //palabras clave
                statement.setString(5, author); //autor
                statement.setString(6, creation_date); //fecha creacion  
                statement.setString(7, storage_date); //fecha alta (actual)
                statement.setString(8, fileName);  //nombre fichero           
                statement.executeUpdate();
                
                response.sendRedirect("exitoRegistro.jsp");
                
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
        //processRequest(request, response);
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
