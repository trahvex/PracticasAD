<%-- 
    Document   : modificarImagen
    Created on : 20-sep-2019, 16:25:03
    Author     : ruben.sanz.garcia
--%>

<%//@page import="javax.ws.rs.client.Entity.form(Form)"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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
            
            <br>
            <h2>
            <% out.println("Imatge seleccionada:"+ request.getParameter("id"));%>
            </h2>
            <br>
                              
                 
            <form action="\practica4\webresources\gestorImagenes\modify" method="POST" >
                <div class="card-body">
                    <div class="card-title"><h4>Modifica l'imatge</h4></div>
                    <div class="form-group" >
                        <% out.println("<input type=\"hidden\" class=\"form-control\" id=\"id\" name=\"id\" value=\"" + request.getParameter("id") + "\">");%>
                    </div>
                    <div class="form-group">
                        <label for="camp">Selecciona camp a modificar</label>
                        <select class="form-control" id="camp" name="camp" required>
                          <option>Autor</option>
                          <option>Titol</option>
                          <option>Descripcio</option>
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
