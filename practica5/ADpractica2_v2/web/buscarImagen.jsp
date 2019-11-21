<%-- 
    Document   : buscarImagen
    Created on : 20-sep-2019, 16:25:42
    Author     : ruben.sanz.garcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <%
        if(session.getAttribute("usuario") == null)
            response.sendRedirect("login.jsp");
    %>
    <body>
        <div class="container-fluid">
        <h3>Introduce términos relacionados con la imagen:</h3>
        <small>- Pueden estar relacionados con el nombre, la descripción o las palabras clave <br>
            - Separa los términos por comas y sin espacios entre ellos</small><br><br>
        <form action="buscarImagen" method="POST">
            &nbsp&nbsp&nbsp&nbsp
            <input type="text" name="palabras" required/> 
            &nbsp&nbsp&nbsp&nbsp
            <input type="submit" value="Buscar"/> <br><br>
        </form>
        <a href="menu.jsp">
            <small> Volver al menú </small>
        </a>
        </div>  
    </body>
</html>
