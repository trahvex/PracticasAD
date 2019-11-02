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
            <a href="registrarImagen.jsp">Registrar imagen</a><br>
            
            <a href="/practica4/webresources/gestorImagenes/list">Listar imágenes</a><br>
            
            <a href="/practica4/webresources/gestorImagenes/searchID/1">Buscar imagen por ID</a><br>
            
            <a href="/practica4/webresources/gestorImagenes/searchTitle/i">Buscar imagen por título</a><br>
            
            <a href="/practica4/webresources/gestorImagenes/searchCreationDate/2018-11-10">Buscar imagen por fecha de creación</a><br>
            
            <a href="/practica4/webresources/gestorImagenes/searchAuthor/a">Buscar imagen por autor</a><br>
            
            <a href="/practica4/webresources/gestorImagenes/searchKeywords/i">Buscar imagen por palabras clave</a><br>
            
            <a href="/practica4/webresources/gestorImagenes/searchTitleKeywords/i,i">Buscar imagen por título y palabras clave</a>
            
        </div>
    </body>
</html>
