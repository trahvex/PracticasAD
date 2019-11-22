/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gestor1.GestorImagenes_Service;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author carles.salvador
 */
@WebServlet(urlPatterns = {"/descargaImatge"})
public class descargaImatge extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/GestorImagenes.wsdl")
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
        try (PrintWriter out = response.getWriter()) {
            byte[] fileContent = download(request.getParameter("filename"));
            String home = System.getProperty("user.home");
            String filePath = home + "\\Downloads\\" + request.getParameter("filename");
            try {
                    FileOutputStream fos = new FileOutputStream(filePath);
                    BufferedOutputStream outputStream = new BufferedOutputStream(fos);
                    outputStream.write(fileContent);
                    outputStream.close();

                    System.out.println("Received file: " + filePath);
                    out.println("<h1>Imatge descarregada amb exit!</h1>");
                    out.println("Path:"+ filePath);

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
        }}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**

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

    private byte[] download(java.lang.String arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.download(arg0);
    }

}
