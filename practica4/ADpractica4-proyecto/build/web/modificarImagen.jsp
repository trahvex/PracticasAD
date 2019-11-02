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
            <!--%
            if(session.getAttribute("usuario") == null)
                response.sendRedirect("login.jsp");
            %-->
            <%
                Connection connection = null;
                String titulo, descripcion, keywords, author, fileName;
                titulo = descripcion = keywords = author= fileName = null;
                try {
                    PreparedStatement statement;
                    String query;
                    Class.forName("org.sqlite.JDBC");           
                    connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\practica4.db");       

                    //coger datos de la foto
                    query = "select * from image where fileName=?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, request.getParameter("imagen"));    
                    ResultSet rs = statement.executeQuery();          


                    if (rs.next() == true){                      
                        titulo = rs.getString(2);
                        descripcion = rs.getString(3);
                        keywords = rs.getString(4);
                        author = rs.getString(5);
                        fileName = rs.getString(8);
                    }

                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    out.println(e.getMessage());
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
                        + "Palabras clave (separadas por comas y sin espacios):<input type=\"text\" name=\"keywords\"value = "+keywords+"> <br><br>"
                        + "Fichero de la imagen:<input type=\"text\" name=\"fileName\" value ="+fileName+" /> <br><br>"
                        + "Autor:<input type=\"text\" name=\"author\" value ="+author+" /> <br><br>"
                        + "<input type=\"hidden\" name=\"id\" value=\""+ request.getParameter("id") +"\">"
                        + "<input type=\"submit\" value=\"Enviar\" /> <br><br>"
                        + "</form>");
            %>
            <a href="menu.jsp">
                <small> Volver al menú </small>
            </a>
        </div>
    </body>
</html>
