<%-- 
    Document   : signup
    Created on : 20-sep-2019, 17:10:45
    Author     : ruben.sanz.garcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sing Up</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <h1>Sign Up</h1>
            <form action="signup" method="POST">
                Usuari: 
                <input type="text" name="user" /> <br>
                Contrasenya:
                <input type="password" name="password" /> <br>
                Repeteix contrasenya:
                <input type="password" name="password2" /> <br>
                <input type="submit" value="Crea usuari" />
            </form>
        </div>
    </body>
</html>
