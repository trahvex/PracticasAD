<%-- 
    Document   : login
    Created on : 20-sep-2019, 16:21:03
    Author     : ruben.sanz.garcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if(session.getAttribute("usuario") != null){
                response.sendRedirect("menu.jsp");
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <h1>Login</h1>
                <form action="login" method="POST">
                    Usuari: 
                    <input type="text" name="user" /> <br>
                    Contrasenya:
                    <input type="password" name="password" /> <br>
                    <input type="submit" value="Enviar" /> <br><br>
                </form>
                <small>¿No tens usuari?</small>
                <a href="signup.jsp">
                    <small> Sign up </small>
                </a>
        </div>
    </body>
</html>
