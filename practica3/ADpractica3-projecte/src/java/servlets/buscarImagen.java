/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fenix
 */
@WebServlet(urlPatterns = {"/buscarImagen"})
public class buscarImagen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection connection = null;
        PrintWriter out = response.getWriter();
        try{
            PreparedStatement statement;
            String query;
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");
            
            HttpSession sesion = request.getSession();
            List<String> authorPics = new ArrayList<String>();
            String user = (String)sesion.getAttribute("usuario");
            
            //cogemos los nombres de las fotos del user
            query = "select filename from image where author = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, user);   
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                authorPics.add(rs.getString(1));
            }
            
            
            //trata campo de busqueda
            String listaBusca = request.getParameter("palabras");
            String[] palabrasBusca = listaBusca.split(",");
            
            for (String s : palabrasBusca){
                // Query por titulo, descripcion y keywords
                out.write("Resultados relacionados con " + s + ":<br>");
                query = "select title, filename from image where title like '%"+s+"%' or description like '%"+s+"%' or "
                        + "keywords like '%"+s+"%'";
                statement = connection.prepareStatement(query);;  
                rs = statement.executeQuery();
                if(rs.next() == false){
                    out.write("&nbsp&nbspNo se han obtenido resultados :( <br>");
                }
                else{
                    do{
                        out.write("&nbsp&nbsp<a href=\"Imagenes/" + rs.getString(2) + "\">" 
                                + rs.getString(1) + "</a>");

                        //si es una imagen del autor, opcion a modificar
                        if(authorPics.contains(rs.getString(2)))
                            out.write ("&nbsp&nbsp&nbsp&nbsp<a href=\"modificarImagen.jsp?imagen="
                                    + rs.getString(2) +"\"> Modificar Imagen"
                                    + "</a>");
                        out.write("<br>");

                    }while(rs.next());
                }
                out.write("<br>");
            }
            
            out.write("<a href=\"buscarImagen.jsp\">"
                    + "<small> Nueva búsqueda </small></a><br>"
                    + "<a href=\"menu.jsp\">"
                    + "<small> Volver al menú </small></a>");
            
            
        } catch (SQLException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
                out.println(e.getMessage());
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
