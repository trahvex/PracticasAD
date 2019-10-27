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
        "    </head>");
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
            @FormParam("author") String author, @FormParam("creation") String crea_date){
        
        return "hola";
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
    public String modifyImage (@FormParam("id") String id, @FormParam("title") String title, @FormParam("description") String description,
            @FormParam("keywords") String keywords, @FormParam("author") String author, @FormParam("creation") String crea_date){
        
        return "hola";
    }

    /**
    * GET method to list images
    * @return
    */
    @Path("list")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String listImages (){
        html = cabeceras("Lista");
        
        html = html + "<body> <h1> Lista imágenes </h1> </body> </html>";
        
        //TODO cambiar paths
        
        final File folder = new File("C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\web\\Imagenes");
                //preparamos la conexion para saber que fotos pertenecen al usuario logueado
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
                    connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");
                       
                    //guardaremos tambien nombre y archivo de las fotos
                    query = "select * from image";
                    statement = connection.prepareStatement(query);
                    ResultSet rs = statement.executeQuery();
                    
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
                
                
        //TODO imprimir usando rs.next
        
                
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
        
        return "hola";
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
        
        return "hola";
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
        
        return "hola";
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
        
        return "hola";
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
        
        return "hola";
    }
    
}
