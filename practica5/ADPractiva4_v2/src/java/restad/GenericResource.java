/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


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
        "    </head><body>" 
        //"     <% if(session.getAttribute(\"usuario\") == null)"
        //    + "response.sendRedirect(\"login.jsp\");%>  "
        );
    }
    
    
    private void printResultados (ResultSet rs){
        try {
            while(rs.next()){
                html = html + "<b> ID: </b>" + String.valueOf(rs.getInt("id")) + "<br>";
                html = html + "<b> Título: </b>" + rs.getString("title") + "<br>";
                html = html + "<b> Descripción: </b>" + rs.getString("description") + "<br>";
                html = html + "<b> Keywords: </b>" + rs.getString("keywords") + "<br>";
                html = html + "<b> Autor: </b>" + rs.getString("author") + "<br>";
                html = html + "<b> Fecha creación: </b>" + rs.getString("creation_date") + "<br>";
                html += "<br>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
            html += "<a href='/practica4/'> <small> Volver al menú </small> </a>"; 
    }
    
    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream,
            String uploadedFileLocation) {

            try {
                    OutputStream out = new FileOutputStream(new File(
                                    uploadedFileLocation));
                    int read = 0;
                    byte[] bytes = new byte[1024];

                    out = new FileOutputStream(new File(uploadedFileLocation));
                    while ((read = uploadedInputStream.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                    }
                    out.flush();
                    out.close();
            } catch (IOException e) {
        }
    }
    
    private void cerrarConexion(Connection connection) {
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

    
    /**
    * POST method to register a new image
    * @param title
    * @param description
    * @param keywords
    * @param author
    * @param crea_date
    * @param uploadedInputStream
    * @param fileDetail
    * @return
    */
    @Path("register")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String registerImage (@FormDataParam("title") String title, @FormDataParam("description") String description, @FormDataParam("keywords") String keywords,
            @FormDataParam("author") String author, @FormDataParam("creation") String crea_date, 
            @FormDataParam("imagen") InputStream uploadedInputStream, @FormDataParam("imagen") FormDataContentDisposition fileDetail){
        
        html = cabeceras("Exito!");

        Connection connection = null;
        try {
            //preparo conexion
            PreparedStatement statement;
            String query;
            Class.forName("org.sqlite.JDBC");           

            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\practica4.db");       
            //creo tabla de imagenes si no existe
            query = "create table if not exists image (id integer primary key, title varchar (256) NOT NULL, description varchar (1024) NOT NULL, keywords "
            + "varchar (256) NOT NULL, author varchar (255) NOT NULL, creation_date varchar (10) NOT NULL, storage_date varchar (10) NOT NULL, fileName varchar (512) NOT NULL UNIQUE, "
            + "foreign key (author) references usuarios(id_usuario))";
            statement = connection.prepareStatement(query);
            statement.executeUpdate();              
            
            //cogemos fecha actual
            String storage_date;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime dateTime = LocalDateTime.now();
            storage_date = dateTime.format(formatter);
            
            query = "select max(id) from image";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            int newId = 1;
            if(rs.next())
                newId= rs.getInt(1) + 1;                 
            //registro la imagen en la bd
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
            statement.setString(8, author+"_"+fileDetail.getFileName()); 
            statement.executeUpdate();
            
            String uploadedFileLocation = "C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\Imagenes\\" + author+"_"+fileDetail.getFileName();                         
            System.out.println("no es null"+ uploadedFileLocation);
            writeToFile(uploadedInputStream, uploadedFileLocation);


            html = html + "<h2> Se ha registrado la imagen correctamente </h2>  ";

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            out.println(e.getMessage());
        }
        finally{
            cerrarConexion(connection);
        } 
            
        html += "<a href='/practica4/'> <small> Volver al menú </small> </a>";
        html += "</body> </html>";
                
        return html;
    }
    
    /**
    * POST method to register a new image
    * @param id
    * @param camp
    * @param valor
    * @return
    */
    @Path("modify")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String modifyImage (@FormParam("id") int id, @FormParam("camp") String camp, @FormParam("valor") String valor ){
        
        html = cabeceras("Modificado");
        //if (img == null) out.println("No s'ha pogut modificar l'imatge");
        Connection connection = null;
        PreparedStatement statement;
        String query = null;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\practica4.db");    
            
            switch(camp) {
            case "Autor":
                query = "update image set author=? where id=?";
                break;                                        
            case "Titol":                
                query = "update image set title=? where id=?";
                break;                    
            case "Keywords":
                query = "update image set keywords=? where id=?";
                break;                    
            case "Descripcio":
                query = "update image set description=? where id=?";
                break;
            }
            
            statement = connection.prepareStatement(query);
            statement.setString(1, valor);           
            statement.setInt(2, id);            
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            cerrarConexion(connection);
        } 
         html += "<h2> Modificación ejecutada con exito </h2>"
                    + "<a href=\"/practica4/menu.jsp\">"
                    + "<small> Volver al menú </small></a>";
        
        
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
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\practica4.db");

            //guardaremos tambien nombre y archivo de las fotos
            query = "select * from image";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                html += "<b>id:</b> " + rs.getString(1) + "<br><b>title:</b> " + rs.getString(2) + "<br><b>description:</b> " + rs.getString(3)
                        + "<br><b>keywords:</b> " + rs.getString(4) + "<br><b>author:</b> " + rs.getString(5) + "<br><b>creation:</b> " + rs.getString(6) + "<br>"
                        + "<a href=/practica4/modificarImagen.jsp?id="+ rs.getString(1) +"> Modificar Imagen </a><br>";
            }

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            out.println(e.getMessage());
        }
        finally{
            cerrarConexion(connection);
        } 
                
        html += "<a href='/practica4/'> <small> Volver al menú </small> </a>";    
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
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from image where id = ?");
            statement.setInt(1,id);
            printResultados(statement.executeQuery());        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
            cerrarConexion(connection);
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
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from image where title like ?");
            statement.setString(1,'%' + title + '%');           
            printResultados(statement.executeQuery());      
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
            cerrarConexion(connection);
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
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from image where creation_date like ?");
            statement.setString(1,date);
            printResultados(statement.executeQuery());      
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
            cerrarConexion(connection);
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
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from image where author like ?");
            statement.setString(1, '%' + author + '%');
            printResultados(statement.executeQuery());      
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
            cerrarConexion(connection);
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
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from image where keywords like ?");
            statement.setString(1, '%' + keywords + '%');
            printResultados(statement.executeQuery());      
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
            cerrarConexion(connection);
        } 
        
        html += "</body> </html>";
        return html;
    }
    
    /**
    * GET method to search images by title and keywords
    * @param keywords
    * @return
    */
    @Path("searchTitleAuthor/{title}/{author}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByTitleKeywords (@PathParam("title") String title, @PathParam("author") String author){
        Connection connection = null;
        html = cabeceras("Búsqueda combinada");
        html += "<body><h2>Búsqueda combinada título y palabras clave</h2>";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select * from image where title like ? and author like ?");
            statement.setString(1,"%" + title + "%");
            statement.setString(2,"%" + author + "%");
            printResultados(statement.executeQuery());      
        
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
            cerrarConexion(connection);
        } 
        
        html += "</body> </html>";
        return html;
    }
    
    
    
    /**
    * POST method to register a new image
    * @param filename
    * @return
    */
    @Path("descarga")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descarga (@FormParam("id") String id){
        
       // html = cabeceras("Descarga con éxito");
        Connection connection = null;
        String filename = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\practica4.db");
            PreparedStatement statement = connection.prepareStatement("select filename from image where id=?");
            statement.setInt(1,Integer.parseInt(id));
            ResultSet rs = statement.executeQuery();    
            if (rs.next()) filename = rs.getString("filename");
        }
        catch(SQLException | ClassNotFoundException e ){
          System.err.println("SQL EXCEPTION: " + e.getMessage());
          return null;
        }
        finally{
            cerrarConexion(connection);
        } 
        
        
        String filePath = "C:\\Users\\ruben.sanz.garcia.STARK.000\\Desktop\\PracticasADgit\\practica5\\ADPractiva4_v2\\Imagenes\\"+ filename;
        System.out.println("Sending file: " + filePath);
        
        File file = new File(filePath);
        ResponseBuilder response = Response.ok((Object)file);
        response.header("Content-Disposition","attachment;filename="+filename);
        return response.build();
          
    }  
    
    
    

}
