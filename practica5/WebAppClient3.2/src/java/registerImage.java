/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gestor1.GestorImagenes_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import javax.servlet.annotation.MultipartConfig;
import javax.xml.ws.soap.MTOMFeature;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.http.Part;
import java.nio.file.Paths;

/**
 *
 * @author Carles
 */
@WebServlet(urlPatterns = {"/registerImage"})
@MultipartConfig
public class registerImage extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/GestorImagenes/GestorImagenes.wsdl")
    private GestorImagenes_Service service;

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
        HttpSession sesion = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registerImage</title>");            
            out.println("</head>");
            out.println("<body>");
            
            gestor1.Image img = new gestor1.Image();
            
            String username = (String) sesion.getAttribute("usuario");
            
            img.setTitle(request.getParameter("title"));
            img.setAuthor(username);
            img.setDescription(request.getParameter("description"));
            img.setKeywords(request.getParameter("keyword"));
            img.setCreaDate(request.getParameter("creationDate"));
           
            
            // uploads a file
            try {
                Part filePart = request.getPart("picture"); // Retrieves <input type="file" name="file">
                InputStream fileContent = filePart.getInputStream();
                byte[] imageBytes = new byte[(int) filePart.getSize()];
                fileContent.read(imageBytes);
                img.setImageBytes(imageBytes);
                String filename = username + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.;
                img.setFilename(filename);
                fileContent.close();
                
                System.out.println("File uploaded: " + filename);
            } catch (IOException ex) {
                System.err.println(ex);
            }      
            
            
       
            if (registerImage(img) == 0) out.println("No s'ha pogut registrar l'imatge");
            else {
                out.println("<h1> Imatge introduida correctament</h1>");
                out.println("<br>Titol: " + img.getTitle());
                out.println("<br>Autor: " + img.getAuthor());
                out.println("<br>Descripcio: " + img.getDescription());
                out.println("<br>Keywords: " + img.getKeywords());
                out.println("<br>Data creacio: " + img.getCreaDate());
                out.println("<br>Filename: " + img.getFilename());
                out.println("</p>");
            }
            out.println("<br> <a href=\"menu.jsp\"> <small> Volver al menú </small> </a> <br>");
            out.println("</body>");
            out.println("</html>");
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

    private int registerImage(gestor1.Image image) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        gestor1.GestorImagenes port = service.getGestorImagenesPort(new MTOMFeature());
        return port.registerImage(image);
    }

}
