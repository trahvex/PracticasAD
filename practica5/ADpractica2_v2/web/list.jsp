<%-- 
    Document   : list
    Created on : 20-sep-2019, 16:25:27
    Author     : ruben.sanz.garcia
--%>


<%@page import="java.lang.String"%>
<%@page import="javafx.util.Pair"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista</title>
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
            <h1> Lista imágenes </h1> 
            <%
                final File folder = new File("C:\\Users\\fenix\\Desktop\\AD\\ADpractica2\\ADpractica2\\web\\Imagenes");
                //preparamos la conexion para saber que fotos pertenecen al usuario logueado
                PreparedStatement statement;
                String query;
                Class.forName("org.sqlite.JDBC");           
                Connection connection = null;
                List<String> authorPics = new ArrayList<String>();
                List<Pair<String,String>> nombresFotos = new ArrayList<Pair<String,String>>();
                try{
                    connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica5\\ADpractica2_v2\\practica2.db");


                    //cogemos los nombres de las fotos del user
                    query = "select filename from image where author = ?";
                    statement = connection.prepareStatement(query);
                    String user = (String)session.getAttribute("usuario");
                    statement.setString(1, user);  
                    ResultSet rs = statement.executeQuery();
                    while(rs.next()){
                        authorPics.add(rs.getString(1));
                    }

                    //guardaremos tambien nombre y archivo de las fotos
                    query = "select title,filename from image";
                    statement = connection.prepareStatement(query); 
                    rs = statement.executeQuery();
                    //cogemos los nombres de las fotos del user
                    Pair<String,String> fotos = new Pair<String,String>(null,null);

                    while(rs.next()){
                        fotos = new Pair<String,String>(rs.getString(1),rs.getString(2));
                        nombresFotos.add(fotos);
                    }

                }catch (SQLException e) {
                    System.err.println(e.getMessage());
                    out.println(e.getMessage());
                }finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    }
                }
                /*List<String> names = new ArrayList<String>();    
                for (final File f : folder.listFiles()) {
                    if (f.isFile()) {
                            names.add(f.getName());
                    }
                }*/
                for (Pair<String,String> p : nombresFotos){  
                    out.write("&nbsp&nbsp -&nbsp <a href=\"Imagenes/" + p.getValue() + "\">" + p.getKey() + "</a>");

                    if(authorPics.contains(p.getValue()))
                        out.write ("&nbsp&nbsp&nbsp&nbsp <a href=\"modificarImagen.jsp?imagen="+ p.getValue() +"\"> Modificar Imagen </a>");

                    out.write("<br>");
                }
            %>
            <br>
            <a href="menu.jsp">
                <small> Volver al menú </small>
            </a>
        </div>
    </body>
</html>
