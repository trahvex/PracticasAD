<%-- 
    Document   : menu
    Created on : 20-sep-2019, 16:24:20
    Author     : ruben.sanz.garcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
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
            <h1>BIENVENIDO</h1> <!--{sessionScope.usuario}-->
            <br>
            <a href="registrarImagen.jsp">
                <h2>Registrar Imagen</h2>
            </a>
            <a href="list.jsp">
                <h2>Mostrar imágenes</h2>
            </a>
            <a href="buscarImagen.jsp">
                <h2>Buscar imagen</h2>
            </a>

            <a href="logout.jsp">
                <small> Logout </small>
            </a>
        </div>
    </body>
</html>
