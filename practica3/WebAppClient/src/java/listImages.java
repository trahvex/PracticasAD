/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gestor1.GestorImagenes_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Carles
 */
@WebServlet(urlPatterns = {"/listImages"})
public class listImages extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Llista</title>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container-fluid\">");
            out.println("<h1>Llistat d'imatges: </h1> <br>");
            List<Object> retList = new ArrayList<Object>();
            retList = listImages();
            Iterator<Object> it = retList.iterator();
            while (it.hasNext()) {
                Object image = it.next();
                gestor1.Image img = gestor1.Image.class.cast(image);
                out.println("Id: " + img.getId() + "<br>");
                out.println("Títol: " + img.getTitle()+ "<br>");
                out.println("Descripció: " + img.getDescription()+ "<br>");
                out.println("Autor: " + img.getAuthor()+ "<br>");
                out.println("Data creació: " + img.getCreaDate()+ "<br>");
                out.println("Data d'alta: " + img.getAltaDate()+ "<br>");
                out.println("Keywords: " + img.getKeywords()+ "<br>");
                out.println("<br>");
            }
            out.println("</div>");
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Object> listImages() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.listImages();
    }

}
