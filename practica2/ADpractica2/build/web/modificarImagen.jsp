<%-- 
    Document   : modificarImagen
    Created on : 20-sep-2019, 16:25:03
    Author     : ruben.sanz.garcia
--%>

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
            <%
            if(session.getAttribute("usuario") == null)
                response.sendRedirect("login.jsp");
            %>
            <%
                HttpSession sesion = request.getSession();
                Connection connection = null;
                String titulo, descripcion, keywords;
                titulo = descripcion = keywords = null;
                try {
                    PreparedStatement statement;
                    String query;
                    // load the sqlite-JDBC driver using the current class loader
                    Class.forName("org.sqlite.JDBC");           
                    connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\ADpractica2\\ADpractica2\\practica2.db");       

                    //coger datos de la foto
                    query = "select * from image where filename=?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, request.getParameter("imagen"));    
                    ResultSet rs = statement.executeQuery();          


                    if (rs.next() == true){
                        titulo = rs.getString(2);
                        descripcion = rs.getString(3);
                        keywords = rs.getString(4);                            
                    }

                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    out.println(e.getMessage());
                    response.sendRedirect("error.jsp");
                }finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        // connection close failed.
                        System.err.println(e.getMessage());
                        response.sendRedirect("error.jsp");
                    }
                }

                //escribe el formulario con los datos que tiene la foto actualmente
                out.write("<h2>Modifica:</h2>"
                        + "<form action=\"modificarImagen\" method=\"POST\" enctype=\"multipart/form-data\" >"
                        + "Título:<input type=\"text\" name=\"title\" value = "+titulo+"> <br><br>"
                        + "Descripción:<input type=\"text\" name=\"description\"value = "+descripcion+"> <br><br>"
                        + "Palabras clave (separadas por comas y sin espacios):<input type=\"text\" name=\"keyword\"value = "+keywords+"> <br><br>"
                        + "<input type=\"checkbox\" name=\"upload\" value=\"Subida\"> Marca esta casilla si quieres cambiar el fichero asociado"
                                + "a una imagen mediante el campo siguiente<br>"
                        + "Imagen sustituta:<input type=\"file\" name=\"picture\" /> <br><br>"
                        + "<input type=\"submit\" value=\"Enviar\" /> <br><br>"
                        + "<input type=\"hidden\" name=\"imagen\" value=\""+ request.getParameter("imagen") +"\">"
                        + "</form>");
            %>
            <a href="menu.jsp">
                <small> Volver al menú </small>
            </a>
        </div>
    </body>
</html>
