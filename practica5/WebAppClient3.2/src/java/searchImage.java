/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gestor1.GestorImagenes_Service;
import gestor1.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
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
@WebServlet(urlPatterns = {"/searchImage"})
public class searchImage extends HttpServlet {

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
            out.println("<title>Cerca imatge</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container-fluid\">");
            out.println("<h1>Imatge trobada: </h1>");
            
            String valorCerca = request.getParameter("valorCerca");
            Image img = new Image();
            Object image;
            List<Object> retList = new ArrayList<Object>();
            
            
            switch(request.getParameter("camp")) {
                case "ID":
                    img = searchbyId(Integer.parseInt(valorCerca));
                    if (img != null){
                        out.println("<p>");
                        out.println("<br>ID: " + img.getId());
                        out.println("<br>Titol: " + img.getTitle());
                        out.println("<br>Autor: " + img.getAuthor());
                        out.println("<br>Descripcio: " + img.getDescription());
                        out.println("<br>Keywords: " + img.getKeywords());
                        out.println("<br>Data creacio: " + img.getCreaDate());
                        out.println("<br>Data d'alta: " + img.getAltaDate());
                        out.println("<br>Filename: " + img.getFilename());
                        if(img.getAuthor().equals(request.getSession().getAttribute("usuario")))
                            out.println("<br><a href=\"modificarImagen.jsp?identifier="+ img.getId() +"\"> Modificar Imagen </a><br>");
                        out.println("</p>");
                        
                    }
                    else out.println("No s'ha trobat cap imatge");
                    break;
                    
                case "Autor":
                    retList = searchbyAuthor(valorCerca);
                    Iterator<Object> it = retList.iterator();
                    if (retList.isEmpty()) out.println("No s'ha trobat cap imatge");
                    while (it.hasNext()) {
                        image = it.next();
                        img = Image.class.cast(image);
                        out.println("<p>");
                        out.println("<br>ID: " + img.getId());
                        out.println("<br>Titol: " + img.getTitle());
                        out.println("<br>Autor: " + img.getAuthor());
                        out.println("<br>Descripcio: " + img.getDescription());
                        out.println("<br>Keywords: " + img.getKeywords());
                        out.println("<br>Data creacio: " + img.getCreaDate());
                        out.println("<br>Data d'alta: " + img.getAltaDate());
                        out.println("<br>Filename: " + img.getFilename());
                        if(img.getAuthor().equals(request.getSession().getAttribute("usuario")))
                            out.println("<br><a href=\"modificarImagen.jsp?identifier="+ img.getId() +"\"> Modificar Imagen </a><br>");
                        out.println("</p><br>");
                    }
                    break;
                    
                case "Titol":
                    retList = searchbyTitle(valorCerca);
                    Iterator<Object> itTitol = retList.iterator();
                    if (retList.isEmpty()) out.println("No s'ha trobat cap imatge");
                    while (itTitol.hasNext()) {
                        image = itTitol.next();
                        img = Image.class.cast(image);
                        out.println("<p>");
                        out.println("<br>ID: " + img.getId());
                        out.println("<br>Titol: " + img.getTitle());
                        out.println("<br>Autor: " + img.getAuthor());
                        out.println("<br>Descripcio: " + img.getDescription());
                        out.println("<br>Keywords: " + img.getKeywords());
                        out.println("<br>Data creacio: " + img.getCreaDate());
                        out.println("<br>Data d'alta: " + img.getAltaDate());
                        out.println("<br>Filename: " + img.getFilename());
                        if(img.getAuthor().equals(request.getSession().getAttribute("usuario")))
                            out.println("<br><a href=\"modificarImagen.jsp?identifier="+ img.getId() +"\"> Modificar Imagen </a><br>");
                        out.println("</p><br>");
                    }
                    break;
                    
                case "Data Creacio":
                    retList = searchbyCreaDate(valorCerca);
                    Iterator<Object> itDate = retList.iterator();
                    if (retList.isEmpty()) out.println("No s'ha trobat cap imatge");
                    while (itDate.hasNext()) {
                        image = itDate.next();
                        img = Image.class.cast(image);
                        out.println("<p>");
                        out.println("<br>ID: " + img.getId());
                        out.println("<br>Titol: " + img.getTitle());
                        out.println("<br>Autor: " + img.getAuthor());
                        out.println("<br>Descripcio: " + img.getDescription());
                        out.println("<br>Keywords: " + img.getKeywords());
                        out.println("<br>Data creacio: " + img.getCreaDate());
                        out.println("<br>Data d'alta: " + img.getAltaDate());
                        out.println("<br>Filename: " + img.getFilename());
                        if(img.getAuthor().equals(request.getSession().getAttribute("usuario")))
                            out.println("<br><a href=\"modificarImagen.jsp?identifier="+ img.getId() +"\"> Modificar Imagen </a><br>");
                        out.println("</p><br>");
                    }
                    break;
                    
                case "Keywords":
                    retList = searchbyKeywords(valorCerca);
                    Iterator<Object> itKW = retList.iterator();
                    if (retList.isEmpty()) out.println("No s'ha trobat cap imatge");
                    while (itKW.hasNext()) {
                        image = itKW.next();
                        img = Image.class.cast(image);
                        out.println("<p>");
                        out.println("<br>ID: " + img.getId());
                        out.println("<br>Titol: " + img.getTitle());
                        out.println("<br>Autor: " + img.getAuthor());
                        out.println("<br>Descripcio: " + img.getDescription());
                        out.println("<br>Keywords: " + img.getKeywords());
                        out.println("<br>Data creacio: " + img.getCreaDate());
                        out.println("<br>Data d'alta: " + img.getAltaDate());
                        out.println("<br>Filename: " + img.getFilename());
                        if(img.getAuthor().equals(request.getSession().getAttribute("usuario")))
                            out.println("<br><a href=\"modificarImagen.jsp?identifier="+ img.getId() +"\"> Modificar Imagen </a><br>");
                        out.println("</p><br>");
                    }
                    break;
            }
            out.println("<a href=\"menu.jsp\">\n" +
            "                <small> Volver al menú </small>\n" +
            "            </a>");
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

    private java.util.List<java.lang.Object> searchbyAuthor(java.lang.String author) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.searchbyAuthor(author);
    }

    private java.util.List<java.lang.Object> searchbyCreaDate(java.lang.String creaDate) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.searchbyCreaDate(creaDate);
    }

    private Image searchbyId(int id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.searchbyId(id);
    }

    private java.util.List<java.lang.Object> searchbyKeywords(java.lang.String keywords) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.searchbyKeywords(keywords);
    }

    private java.util.List<java.lang.Object> searchbyTitle(java.lang.String title) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.searchbyTitle(title);
    }

}
