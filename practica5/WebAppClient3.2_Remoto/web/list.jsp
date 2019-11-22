<%-- 
    Document   : list
    Created on : 20-sep-2019, 16:25:27
    Author     : ruben.sanz.garcia
--%>


<%@page import="java.util.Iterator"%>
<%@page import="java.lang.String"%>
<%@page import="javafx.util.Pair"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <%
            if(session.getAttribute("usuario") == null)
                response.sendRedirect("login.jsp");
            %>
            <h1> Lista imágenes </h1> 
            <%
            try {
                gestor1.GestorImagenes_Service service = new gestor1.GestorImagenes_Service();
                gestor1.GestorImagenes port = service.getGestorImagenesPort();
                java.util.List<java.lang.Object> result = port.listImages();
                Iterator<Object> it = result.iterator();
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
                    if(img.getAuthor().equals(session.getAttribute("usuario")))
                        out.print("<a href=\"modificarImagen.jsp?identifier="+ img.getId() +"\"> Modificar Imagen </a>");
                    
                    out.println("<form action=\"descargaImatge\" method=\"POST\">");
                    out.println("<input type=\"hidden\" name=\"filename\" value=\""+ img.getFilename() + "\">" );
                    out.println("<button type=\"submit\">Descarga imatge</button></form><br>");
                    
                }
                } catch (Exception ex) {
                    out.println("<p> No se ha podido acceder al web service! </p>");
                }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            %>
            <br>
            <a href="menu.jsp">
                <small> Volver al menú </small>
            </a>
        </div>
    </body>
</html>

