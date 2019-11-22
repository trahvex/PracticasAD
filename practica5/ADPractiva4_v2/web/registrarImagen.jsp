<%-- 
    Document   : registrarImagen
    Created on : 20-sep-2019, 16:24:36
    Author     : ruben.sanz.garcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Imagen</title>
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
            <h2>Registra tu imagen:</h2>
            <form action="/practica4/webresources/gestorImagenes/register" method="POST" enctype="multipart/form-data">
                Título: 
                <input type="text" name="title" required/> <br><br>
                Descripción:
                <input type="text" name="description" required/> <br><br>
                Palabras clave (separadas por comas y sin espacios): 
                <input type="text" name="keywords" required/> <br><br>
                <%
                  out.println("<input type=\"hidden\" name=\"author\" value=\"" + session.getAttribute("usuario") + "\"required/>");  
                %>
                Fecha de creación:  
                <input type="date" name="creation" required/> <br><br>
                Fichero:
                <input type="file" name="imagen" required/> <br><br>
                
                <input type="submit" value="Enviar" required/> <br><br>
            </form>
            <a href="menu.jsp">
                <small> Volver al menú </small>
            </a>
        </div>
    </body>
</html>
