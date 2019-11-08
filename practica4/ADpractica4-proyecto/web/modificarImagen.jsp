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
            <!--%
            if(session.getAttribute("usuario") == null)
                response.sendRedirect("login.jsp");
            %-->
            <%
                //escribe el formulario con los datos que tiene la foto actualmente
                out.write("<h2>Modifica:</h2>"
                        + "<form action=\"http:\\\\localhost:8080\\practica4\\webresources\\gestorImagenes\\modify\" method=\"POST\">"
                        + "Título:<input type=\"text\" name=\"title\"> <br><br>"
                        + "Descripción:<input type=\"text\" name=\"description\"> <br><br>"
                        + "Palabras clave (separadas por comas y sin espacios):<input type=\"text\" name=\"keywords\"> <br><br>"
                        + "Fichero de la imagen:<input type=\"text\" name=\"fileName\"/> <br><br>"
                        + "Autor:<input type=\"text\" name=\"author\" /> <br><br>"
                        + "<input type=\"hidden\" name=\"id\"\">"
                        + "<input type=\"submit\" value=\"Enviar\" /> <br><br>"
                        + "</form>");
            %>
            <a href="menu.jsp">
                <small> Volver al menú </small>
            </a>
        </div>
    </body>
</html>
