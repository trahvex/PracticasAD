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
        <form class= "card" action="searchImage" method="POST">
            <div class="card-body">
                <div class="card-title"><h4>Buscar una imatge</h4></div>
                <div class="form-group">
                    <label for="camp">Selecciona camp a buscar</label>
                    <select class="form-control" id="camp" name="camp" required>
                      <option>ID</option>
                      <option>Autor</option>
                      <option>Titol</option>
                      <option>Data Creacio</option>
                      <option>Keywords</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="valorCerca">Introdueix cerca:</label>
                    <input type="text" class="form-control" id="valorCerca" name="valorCerca" required>
                </div>
                <button type="submit" class="btn btn-primary">Cerca</button> <br><br>
            </div>
        </form>
        <a href="menu.jsp">
            <small> Volver al menú </small>
        </a>
        </div>  
    </body>
</html>
