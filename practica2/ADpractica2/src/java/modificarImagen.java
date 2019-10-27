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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author fenix
 */
@WebServlet(name = "modificarImagen", urlPatterns = {"/modificarImagen"})
@MultipartConfig
public class modificarImagen extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Connection connection = null;
            try {
                        
                PreparedStatement statement;
                String query;
                // load the sqlite-JDBC driver using the current class loader
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\ADpractica2\\ADpractica2\\practica2.db");       
                
                //coge los campos del formulario
                String title, description, keywords, nuevaImagen, imageOg;
                title = request.getParameter("title");
                description = request.getParameter("description");
                keywords = request.getParameter("keyword");
                nuevaImagen = request.getParameter("imagen");
                imageOg = request.getParameter("imagen");
                
                //si la checkbox esta marcada queremos meter una nueva imagen
                if(request.getParameter("upload") != null){
                   //recogemos archivo subido, guardamos su nombre en fileName
                    Part filePart = request.getPart("picture"); // Retrieves <input type="file" name="file">
                    nuevaImagen = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                    InputStream fileContent = filePart.getInputStream();
                    //borramos el fichero antiguo
                    File exhumable = new File("C:\\Users\\fenix\\Desktop\\AD\\ADpractica2\\ADpractica2\\web\\Imagenes\\"+imageOg); 
                    exhumable.delete();
                    //guardamos el fichero subido en nuestro directorio
                    File uploads = new File("C:\\Users\\fenix\\Desktop\\AD\\ADpractica2\\ADpractica2\\web\\Imagenes");
                    File fichero = new File(uploads, nuevaImagen);
                    try (InputStream input = fileContent) {
                        Files.copy(input, fichero.toPath());
                    }                
                }
                
                        
                query = "update image set title=?, description=? , keywords=?, filename=? where filename=?";//, creation_date=?";
                statement = connection.prepareStatement(query);
                statement.setString(1, title); //titulo imagen
                statement.setString(2, description); //descripcion imagen
                statement.setString(3, keywords); //palabras clave
                statement.setString(4, nuevaImagen); //palabras clave
                statement.setString(5, imageOg);
                statement.executeUpdate();
                
                //todo chido pos te presenta mensaje de exito
                out.write("<h2> Modificación ejecutada con exito </h2>"
                        + "<b>Información actual:</b><br>"
                        + "<br>&nbsp&nbsp&nbsp&nbspTítulo: "+title+"<br>"
                        + "&nbsp&nbsp&nbsp&nbspDescripción: "+description+"<br>"
                        + "&nbsp&nbsp&nbsp&nbspPalabras clave: "+keywords+"<br>"
                        + "&nbsp&nbsp&nbsp&nbspNombre del fichero: "+nuevaImagen+"<br><br>"
                        + "<a href=\"menu.jsp\">"
                        + "<small> Volver al menú </small></a>");
                
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
        //processRequestGET(request, response);
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
