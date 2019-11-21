<%-- 
    Document   : modificarImagen
    Created on : 20-sep-2019, 16:25:03
    Author     : ruben.sanz.garcia
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar</title>
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
                <%-- start web service invocation --%>
            <%
            try {
                gestor1.GestorImagenes_Service service = new gestor1.GestorImagenes_Service();
                gestor1.GestorImagenes port = service.getGestorImagenesPort();
                int id;
                if(request.getParameter("identifier") != null) {
                    id= Integer.parseInt(request.getParameter("identifier"));
                    gestor1.Image img = port.searchbyId(id);
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
                        out.println("</p>");
                    }
                }
                else
                    response.sendRedirect("error.jsp");
                
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
            %>
            <%-- end web service invocation --%>
            <form class="card" action="modifyImage" method="POST">
                <div class="card-body">
                    <div class="card-title"><h4>Modifica l'imatge</h4></div>
                    <div class="form-group" >
                        <% out.println("<input type=\"hidden\" class=\"form-control\" id=\"id\" name=\"id\" value=\"" + request.getParameter("identifier") + "\">");%>
                    </div>
                    <div class="form-group">
                        <label for="camp">Selecciona camp a modificar</label>
                        <select class="form-control" id="camp" name="camp" required>
                          <option>Autor</option>
                          <option>Titol</option>
                          <option>Descripcio</option>
                          <option>Data Creacio</option>
                          <option>Keywords</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="valor">Introdueix nou valor:</label>
                        <input type="text" class="form-control" id="valor" name="valor" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Modifica</button>
                </div>
            </form><br><br>

            <a href="menu.jsp">
                <small> Volver al menú </small>
            </a>
        </div>
    </body>
</html>
