/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.Part;
import javax.ws.rs.FormParam;


/**
 * REST Web Service
 *
 * @author ruben.sanz.garcia
 */
@Path("gestorImagenes")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    
    
    private String html;
    
    private String cabeceras (String title){
        return ("<html>\n" +
        "    <head>\n" +
        "    <meta charset=\"utf-8\">\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n\n" +
        "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
        "    <title>"+ title +"</title>\n" +
        "    </head><body>");
    }

 
    
    /**
    * POST method to register a new image
    * @param title
    * @param description
    * @param keywords
    * @param author
    * @param crea_date
    * @return
    */
    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String registerImage (@FormParam("title") String title, @FormParam("description") String description, @FormParam("keywords") String keywords,
            @FormParam("author") String author, @FormParam("creation") String crea_date, @FormParam("fileName") String fileName){
        
            html = cabeceras("Exito!");
        
        
            Connection connection = null;
            try {
                        
                PreparedStatement statement;
                String query;
                Class.forName("org.sqlite.JDBC");           

                connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\practica4.db");       
                query = "create table if not exists image (id integer primary key, title varchar (256) NOT NULL, description varchar (1024) NOT NULL, keywords "
                + "varchar (256) NOT NULL, author varchar (255) NOT NULL, creation_date varchar (10) NOT NULL, storage_date varchar (10) NOT NULL, fileName varchar (512) NOT NULL UNIQUE, "
                + "foreign key (author) references usuarios(id_usuario))";
                statement = connection.prepareStatement(query);
                statement.executeUpdate();              
                
                String storage_date;
                //cogemos decha actual
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime dateTime = LocalDateTime.now();
                storage_date = dateTime.format(formatter);
                
                
                /*String fileName = fileDetail.getFileName();
                String uploadedFileLocation = "C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\Imagenes" + fileName +"_"+ author;
                writeToFile(uploadedInputStream, uploadedFileLocation);*/
                
                query = "select max(id) from image";
                statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                int newId = 1;
                if(rs.next())
                    newId= rs.getInt(1) + 1;                 
                        
                query = "insert into image (id, title, description, keywords, author, creation_date, storage_date, filename)"
                        + "values(?, ?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, newId); //id imagen
                statement.setString(2, title); //titulo imagen
                statement.setString(3, description); //descripcion imagen
                statement.setString(4, keywords); //palabras clave
                statement.setString(5, author); //autor
                statement.setString(6, crea_date); //fecha creacion  
                statement.setString(7, storage_date); //fecha alta (actual)
                statement.setString(8, fileName);  //nombre fichero           
                statement.executeUpdate();
                
                html = html + "<h2> Se ha registrado la imagen correctamente </h2>  ";
                
            } catch (SQLException | ClassNotFoundException e) {
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
                }
            }
            
        html += "<a href='http://localhost:8080/practica4/'> <small> Volver al menú </small> </a>";    
        
        html += "</body> </html>";
                
        return html;
    }
    
    /**
    * POST method to register a new image
    * @param title
    * @param description
    * @param keywords
    * @param author
    * @param crea_date
    * @return
    */
    @Path("modify")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String modifyImage (@FormParam("id") int id, @FormParam("title") String title, @FormParam("description") String description,
            @FormParam("keywords") String keywords, @FormParam("author") String author, @FormParam("creation") String crea_date, @FormParam("fileName") String fileName){
        
        html = cabeceras("Modificado");
        
        Connection connection = null;
            try {                        
                PreparedStatement statement;
                String query;
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\practica4.db");       
                                       
                query = "update image set title=?, description=? , keywords=?, filename=? ,author=? where id=?";
                statement = connection.prepareStatement(query);
                statement.setString(1, title); //titulo imagen
                statement.setString(2, description); //descripcion imagen
                statement.setString(3, keywords); //palabras clave
                statement.setString(4, fileName); 
                statement.setString(5, author); 
                statement.setInt(6, id);
                statement.executeUpdate();
                
                //todo chido pos te presenta mensaje de exito
                html += "<h2> Modificación ejecutada con exito </h2>"
                        + "<b>Información actual:</b><br>"
                        + "<br>&nbsp&nbsp&nbsp&nbspTítulo: "+title+"<br>"
                        + "&nbsp&nbsp&nbsp&nbspDescripción: "+description+"<br>"
                        + "&nbsp&nbsp&nbsp&nbspPalabras clave: "+keywords+"<br>"
                        + "&nbsp&nbsp&nbsp&nbspNombre del fichero: "+fileName+"<br><br>"
                        + "<a href=\"menu.jsp\">"
                        + "<small> Volver al menú </small></a>";
                
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e.getMessage());
                }
           }
        
        return html;
    }

    /**
    * GET method to list images
    * http://localhost:8080/practica4/webresources/gestorImagenes/list
    * @return
    */
    @Path("list")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String listImages (){
        html = cabeceras("Lista");
        
        html = html + "<h1> Lista imágenes </h1>  ";
        
        //TODO cambiar paths
        
        //final File folder = new File("C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\Imagenes");
        PreparedStatement statement;
        String query;
        try {           
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
                Connection connection = null;
                List<String> authorPics = new ArrayList<String>();
                List<Pair<String,String>> nombresFotos = new ArrayList<Pair<String,String>>();
                try{
                    connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\practica4.db");
                       
                    //guardaremos tambien nombre y archivo de las fotos
                    query = "select * from image";
                    statement = connection.prepareStatement(query);
                    ResultSet rs = statement.executeQuery();
                    
                    while (rs.next()){
                        html += "id: " + rs.getString(1) + "title: " + rs.getString(2) + "description: " + rs.getString(3)
                                + "keywords: " + rs.getString(4) + "author: " + rs.getString(5) + "creation: " + rs.getString(6) + "<br>"
                                + "<a href=\\\"modificarImagen.jsp?id="+ rs.getString(1) +"\\\"> Modificar Imagen </a>\" <br>";
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
                
        html += "<a href='http://localhost:8080/practica4/'> <small> Volver al menú </small> </a>";    
        
        html += "</body> </html>";
                
        return html;
        
    }
    
    
      
    
    /**
    * GET method to search images by id
    * @param id
    * @return
    */
    @Path("searchID/{id}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByID (@PathParam("id") int id){
        Connection connection = null;
        html = cabeceras("Búsqueda ID");
        html += "<body><h2>Búsqueda por ID</h2>";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where id_imatge = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                html = html + "<b> ID: <\\b>" + String.valueOf(rs.getInt("id")) + "<br>";
                html = html + "<b> Título: <\\b>" + rs.getString("title") + "<br>";
                html = html + "<b> Descripción: <\\b>" + rs.getString("description") + "<br>";
                html = html + "<b> Keywords: <\\b>" + rs.getString("keywords") + "<br>";
                html = html + "<b> Autor: <\\b>" + rs.getString("author") + "<br>";
                html = html + "<b> Fecha creación: <\\b>" + rs.getString("creation_date") + "<br>";
                html += "<br>";
            }
            html += "<a href='http://localhost:8080/practica4/'> <small> Volver al menú </small> </a>";    
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
          try{
            if(connection != null)
              connection.close();
            System.out.println("closed connection");
          }
          catch(SQLException e){
            // connection close failed.
            System.err.println(e.getMessage());
          }
        } 
        
        html += "</body> </html>";
        return html;
    }
    
    
    /**
    * GET method to search images by title
    * @param title
    * @return
    */
    @Path("searchTitle/{title}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByTitle (@PathParam("title") String title){
        Connection connection = null;
        html = cabeceras("Búsqueda título");
        html += "<body><h2>Búsqueda por título</h2>";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where where titol_imatge like ?");
            statement.setString(1,title);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                html = html + "<b> ID: <\\b>" + String.valueOf(rs.getInt("id")) + "<br>";
                html = html + "<b> Título: <\\b>" + rs.getString("title") + "<br>";
                html = html + "<b> Descripción: <\\b>" + rs.getString("description") + "<br>";
                html = html + "<b> Keywords: <\\b>" + rs.getString("keywords") + "<br>";
                html = html + "<b> Autor: <\\b>" + rs.getString("author") + "<br>";
                html = html + "<b> Fecha creación: <\\b>" + rs.getString("creation_date") + "<br>";
                html += "<br>";
            }
            html += "<a href='http://localhost:8080/practica4/'> <small> Volver al menú </small> </a>";    
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
          try{
            if(connection != null)
              connection.close();
            System.out.println("closed connection");
          }
          catch(SQLException e){
            // connection close failed.
            System.err.println(e.getMessage());
          }
        } 
        
        html += "</body> </html>";
        return html;
    }
    
    
    /**
    * GET method to search images by creation date
    * @param creaDate
    * @return
    */
    @Path("searchCreationDate/{date}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByCreationDate (@PathParam("date") String date){
        Connection connection = null;
        html = cabeceras("Búsqueda fecha");
        html += "<body><h2>Búsqueda por fecha de creación</h2>";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where where creation_date like ?");
            statement.setString(1,date);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                html = html + "<b> ID: <\\b>" + String.valueOf(rs.getInt("id")) + "<br>";
                html = html + "<b> Título: <\\b>" + rs.getString("title") + "<br>";
                html = html + "<b> Descripción: <\\b>" + rs.getString("description") + "<br>";
                html = html + "<b> Keywords: <\\b>" + rs.getString("keywords") + "<br>";
                html = html + "<b> Autor: <\\b>" + rs.getString("author") + "<br>";
                html = html + "<b> Fecha creación: <\\b>" + rs.getString("creation_date") + "<br>";
                html += "<br>";
            }
            html += "<a href='http://localhost:8080/practica4/'> <small> Volver al menú </small> </a>";    
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
          try{
            if(connection != null)
              connection.close();
            System.out.println("closed connection");
          }
          catch(SQLException e){
            // connection close failed.
            System.err.println(e.getMessage());
          }
        } 
        
        html += "</body> </html>";
        return html;
    }
    
    
    /**
    * GET method to search images by author
    * @param author
    * @return
    */
    @Path("searchAuthor/{author}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByAuthor (@PathParam("author") String author){
        Connection connection = null;
        html = cabeceras("Búsqueda autor");
        html += "<body><h2>Búsqueda por autor</h2>";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where where author like ?");
            statement.setString(1,author);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                html = html + "<b> ID: <\\b>" + String.valueOf(rs.getInt("id")) + "<br>";
                html = html + "<b> Título: <\\b>" + rs.getString("title") + "<br>";
                html = html + "<b> Descripción: <\\b>" + rs.getString("description") + "<br>";
                html = html + "<b> Keywords: <\\b>" + rs.getString("keywords") + "<br>";
                html = html + "<b> Autor: <\\b>" + rs.getString("author") + "<br>";
                html = html + "<b> Fecha creación: <\\b>" + rs.getString("creation_date") + "<br>";
                html += "<br>";
            }
            html += "<a href='http://localhost:8080/practica4/'> <small> Volver al menú </small> </a>";    
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
          try{
            if(connection != null)
              connection.close();
            System.out.println("closed connection");
          }
          catch(SQLException e){
            // connection close failed.
            System.err.println(e.getMessage());
          }
        } 
        
        html += "</body> </html>";
        return html;
    }
    
    
    /**
    * GET method to search images by keyword
    * @param keywords
    * @return
    */
    @Path("searchKeywords/{keywords}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByKeywords (@PathParam("keywords") String keywords){
        Connection connection = null;
        html = cabeceras("Búsqueda palabras clave");
        html += "<body><h2>Búsqueda por palabras clave</h2>";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where where keywords like ?");
            statement.setString(1,keywords);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                html = html + "<b> ID: <\\b>" + String.valueOf(rs.getInt("id")) + "<br>";
                html = html + "<b> Título: <\\b>" + rs.getString("title") + "<br>";
                html = html + "<b> Descripción: <\\b>" + rs.getString("description") + "<br>";
                html = html + "<b> Keywords: <\\b>" + rs.getString("keywords") + "<br>";
                html = html + "<b> Autor: <\\b>" + rs.getString("author") + "<br>";
                html = html + "<b> Fecha creación: <\\b>" + rs.getString("creation_date") + "<br>";
                html += "<br>";
            }
            html += "<a href='http://localhost:8080/practica4/'> <small> Volver al menú </small> </a>";    
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
          try{
            if(connection != null)
              connection.close();
            System.out.println("closed connection");
          }
          catch(SQLException e){
            // connection close failed.
            System.err.println(e.getMessage());
          }
        } 
        
        html += "</body> </html>";
        return html;
    }
    
    /**
    * GET method to search images by title and keywords
    * @param keywords
    * @return
    */
    @Path("searchKeywords/{title},{keywords}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByTitleKeywords (@PathParam("title") String title, @PathParam("keywords") String keywords){
        Connection connection = null;
        html = cabeceras("Búsqueda combinada");
        html += "<body><h2>Búsqueda combinada título y palabras clave</h2>";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\fenix\\Desktop\\AD\\PracticasAD\\practica4\\ADpractica4-proyecto\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imatges where titol_imatge like ? and paraula_clau like ?");
            statement.setString(1,"%" + title + "%");
            statement.setString(2,"%" + keywords + "%");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                html = html + "<b> ID: <\\b>" + String.valueOf(rs.getInt("id")) + "<br>";
                html = html + "<b> Título: <\\b>" + rs.getString("title") + "<br>";
                html = html + "<b> Descripción: <\\b>" + rs.getString("description") + "<br>";
                html = html + "<b> Keywords: <\\b>" + rs.getString("keywords") + "<br>";
                html = html + "<b> Autor: <\\b>" + rs.getString("author") + "<br>";
                html = html + "<b> Fecha creación: <\\b>" + rs.getString("creation_date") + "<br>";
                html += "<br>";
            }
            html += "<a href='http://localhost:8080/practica4/'> <small> Volver al menú </small> </a>";    
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
          try{
            if(connection != null)
              connection.close();
            System.out.println("closed connection");
          }
          catch(SQLException e){
            // connection close failed.
            System.err.println(e.getMessage());
          }
        } 
        
        html += "</body> </html>";
        return html;
    }
    
    
    
    

}
