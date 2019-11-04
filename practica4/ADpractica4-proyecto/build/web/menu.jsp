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
            <a href="registrarImagen.jsp">Registrar imagen</a><br><br>
            
            <a href="/practica4/webresources/gestorImagenes/list">Listar imágenes</a><br><br>
            
           
            <form class= "card" id="buscarImatge" method="GET" onsubmit="addPath()">
                <div class="card-body">
                    <div class="card-title"><h4>Buscar una imatge</h4></div>
                    <div class="form-group">
                        <label for="camp">Selecciona camp a buscar</label>
                        <select class="form-control" id="camp" name="camp">
                          <option>ID</option>
                          <option>Autor</option>
                          <option>Titol</option>
                          <option>Data Creacio</option>
                          <option>Keywords</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="valorCerca">Introdueix cerca:</label>
                        <input type="text" class="form-control" id="valorCerca" name="valorCerca">
                    </div>
                    <button type="submit" class="btn btn-primary">Cerca</button> <br><br>
                </div>
            </form><br><br>
            
            <form class= "card" id="busquedaCombinada" method="GET" onsubmit="combinedSearch()">
                <div class="card-body">
                    <div class="card-title"><h4>Buscar per titol i autor</h4></div>
                    <div class="form-group">
                        <label for="titol">Introdueix titol:</label>
                        <input type="text" class="form-control" id="titol" name="titol">
                        <label for="keywords">Introdueix autor:</label>
                        <input type="text" class="form-control" id="autor" name="autor">
                    </div>
                    <button type="submit" class="btn btn-primary">Cerca</button> <br><br>
                </div>
            </form>

        </div>
        <script>
        function addPath(){
            var form = document.getElementById("buscarImatge");
            var camp = document.getElementById("camp").value;
            var action_src;
            switch (camp) {
                case 'ID':
                    action_src = "/practica4/webresources/gestorImagenes/searchID/" + document.getElementsByName("valorCerca")[0].value;
                    break;
                case 'Titol':
                    action_src = "/practica4/webresources/gestorImagenes/searchTitle/" + document.getElementsByName("valorCerca")[0].value;
                    break;
                case 'Autor':
                    action_src = "/practica4/webresources/gestorImagenes/searchAuthor/" + document.getElementsByName("valorCerca")[0].value;
                    break;
                case 'Data Creacio':
                    action_src = "/practica4/webresources/gestorImagenes/searchCreationDate/" + document.getElementsByName("valorCerca")[0].value;
                    break;
                case 'Keywords':
                    action_src = "/practica4/webresources/gestorImagenes/searchKeywords/" + document.getElementsByName("valorCerca")[0].value;
                    break;
            }
            form.action = action_src;
        }
        
        function combinedSearch(){
            var form = document.getElementById("busquedaCombinada");
            var titol = document.getElementsByName("titol")[0].value;
            var autor = document.getElementsByName("autor")[0].value;
            var action_src = "/practica4/webresources/gestorImagenes/searchTitleAuthor/" + titol + "/" + autor;
            form.action = action_src;
            
        }
        </script>
    </body>
</html>
