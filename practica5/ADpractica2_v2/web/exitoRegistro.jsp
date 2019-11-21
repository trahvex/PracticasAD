<%-- 
    Document   : exitoRegistro
    Created on : 04-oct-2019, 15:17:29
    Author     : ruben.sanz.garcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exito!</title>
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
        <h1>La imagen se ha registrado correctamente.</h1> 
        <a href="menu.jsp">
            <small> Volver al menú </small> <br>
        </a>
        <a href="registrarImagen.jsp">
            <small> Registrar otra imagen </small>
        </a>
        </div>
    </body>
</html>
