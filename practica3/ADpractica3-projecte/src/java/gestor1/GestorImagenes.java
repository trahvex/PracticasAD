/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor1;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author ruben.sanz.garcia
 */
@WebService(serviceName = "GestorImagenes")
@Stateless()
public class GestorImagenes {


    /**
     * Web service operation
     * @param image
     * @return 
     */
    @WebMethod(operationName = "RegisterImage")
    public int RegisterImage(@WebParam(name = "image") Image image) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.now();
        String storage_date = dateTime.format(formatter);
        
        Connection connection = null;
        try {
            PreparedStatement statement;
            String query;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");
            
            query = "select max(id) from image";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            int newId = 1;
            if(rs.next()) newId= rs.getInt(1) + 1;
            
            
            query = "insert into image (id, title, description, keywords, author, creation_date, storage_date, filename)"
                        + "values(?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, newId); //id imagen
            statement.setString(2, image.getTitle()); //titulo imagen
            statement.setString(3, image.getDescription()); //descripcion imagen
            statement.setString(4, image.getKeywords()); //palabras clave
            statement.setString(5, image.getAuthor()); //autor
            statement.setString(6, image.getCreaDate()); //fecha creacion  
            statement.setString(7, storage_date); //fecha alta (actual)
            statement.setString(8, image.getFilename());  //nombre fichero           
            statement.executeUpdate();
            
            
        }catch (SQLException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
                out.println(e.getMessage());
                return 0;
            }finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e.getMessage());
                    return 0;
                }
            }
        return 1;
    }

    /**
     * Web service operation
     * @param image
     * @return 
     */
    @WebMethod(operationName = "ModifyImage")
    public int ModifyImage(@WebParam(name = "image") Image image) {
        Connection connection = null;
        try {
            PreparedStatement statement;
            String query;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");
            
            query = "update image set title=?, description=? , keywords=?, author=?, creation_date=?, filename=? where id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, image.getTitle()); //titulo imagen
            statement.setString(2, image.getDescription()); //descripcion imagen
            statement.setString(3, image.getKeywords()); //palabras clave
            statement.setString(4, image.getAuthor()); //palabras clave
            statement.setString(5, image.getCreaDate());
            statement.setString(6, image.getFilename());
            statement.setInt(7, image.getId());
            statement.executeUpdate();
            
        }catch (SQLException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
                out.println(e.getMessage());
                return 0;
            }finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e.getMessage());
                    return 0;
                }
            }
        return 1;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "ListImages")
    public List ListImages() {
        List<Image> ret = new ArrayList<Image>();
      
        //preparamos la conexion
        Connection connection = null;
        try{
            PreparedStatement statement;
            String query;
            Class.forName("org.sqlite.JDBC");  
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");


            //cogemos todas las imagenes
            query = "select * from image";
            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Image tmp = new Image();
                tmp.setId(rs.getInt("id"));
                tmp.setTitle(rs.getString("title"));
                tmp.setAuthor(rs.getString("author"));
                tmp.setDescription(rs.getString("description"));
                tmp.setKeywords(rs.getString("keywords"));
                tmp.setCreaDate(rs.getString("creation_date"));
                tmp.setAltaDate(rs.getString("storage_date"));
                tmp.setFilename(rs.getString("filename"));
                
                ret.add(tmp);
            }

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        
        return ret;
    }

    /**
     * Web service operation
     * @param id
     * @return 
     */
    @WebMethod(operationName = "SearchbyId")
    public Image SearchbyId(@WebParam(name = "id") int id) {
        Image retImage = new Image();
        //preparamos la conexion
                  
        Connection connection = null;
        try{
            PreparedStatement statement;
            String query;
            Class.forName("org.sqlite.JDBC"); 
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");


            //cogemos todas las imagenes
            query = "select * from image where id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                retImage.setId(rs.getInt("id"));
                retImage.setTitle(rs.getString("title"));
                retImage.setAuthor(rs.getString("author"));
                retImage.setDescription(rs.getString("description"));
                retImage.setKeywords(rs.getString("keywords"));
                retImage.setCreaDate(rs.getString("creation_date"));
                retImage.setAltaDate(rs.getString("storage_date"));
                retImage.setFilename(rs.getString("filename"));
            }else return null;

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return retImage;
    }

    /**
     * Web service operation
     * @param title
     * @return 
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List SearchbyTitle(@WebParam(name = "title") String title) {
        List<Image> retList = new ArrayList<Image>();
                  
        Connection connection = null;
        try{
            PreparedStatement statement;
            String query;
            Class.forName("org.sqlite.JDBC"); 
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");


            //cogemos todas las imagenes
            query = "select * from image where title like ?";
            title = '%' + title + '%';
            statement = connection.prepareStatement(query);
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Image tmp = new Image();
                tmp.setId(rs.getInt("id"));
                tmp.setTitle(rs.getString("title"));
                tmp.setAuthor(rs.getString("author"));
                tmp.setDescription(rs.getString("description"));
                tmp.setKeywords(rs.getString("keywords"));
                tmp.setCreaDate(rs.getString("creation_date"));
                tmp.setAltaDate(rs.getString("storage_date"));
                tmp.setFilename(rs.getString("filename"));
                
                retList.add(tmp);
            }

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
        return retList;
    }

    /**
     * Web service operation
     * @param creaDate
     * @return 
     */
    @WebMethod(operationName = "SearchbyCreaDate")
    public List SearchbyCreaDate(@WebParam(name = "creaDate") String creaDate) {
        List<Image> retList = new ArrayList<Image>();
                  
        Connection connection = null;
        try{
            PreparedStatement statement;
            String query;
            Class.forName("org.sqlite.JDBC"); 
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");


            //cogemos todas las imagenes
            query = "select * from image where creation_date = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, creaDate);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Image tmp = new Image();
                tmp.setId(rs.getInt("id"));
                tmp.setTitle(rs.getString("title"));
                tmp.setAuthor(rs.getString("author"));
                tmp.setDescription(rs.getString("description"));
                tmp.setKeywords(rs.getString("keywords"));
                tmp.setCreaDate(rs.getString("creation_date"));
                tmp.setAltaDate(rs.getString("storage_date"));
                tmp.setFilename(rs.getString("filename"));
                
                retList.add(tmp);
            }

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
        return retList;
    }

    /**
     * Web service operation
     * @param author
     * @return 
     */
    @WebMethod(operationName = "SearchbyAuthor")
    public List SearchbyAuthor(@WebParam(name = "author") String author) {
        List<Image> retList = new ArrayList<Image>();
                  
        Connection connection = null;
        try{
            PreparedStatement statement;
            String query;
            Class.forName("org.sqlite.JDBC"); 
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");


            //cogemos todas las imagenes
            query = "select * from image where author like ?";
            author = '%' + author + '%';
            statement = connection.prepareStatement(query);
            statement.setString(1, author);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Image tmp = new Image();
                tmp.setId(rs.getInt("id"));
                tmp.setTitle(rs.getString("title"));
                tmp.setAuthor(rs.getString("author"));
                tmp.setDescription(rs.getString("description"));
                tmp.setKeywords(rs.getString("keywords"));
                tmp.setCreaDate(rs.getString("creation_date"));
                tmp.setAltaDate(rs.getString("storage_date"));
                tmp.setFilename(rs.getString("filename"));
                
                retList.add(tmp);
            }

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
        return retList;
    }

    /**
     * Web service operation
     * @param keywords
     * @return
     */
    @WebMethod(operationName = "SearchbyKeywords")
    public List SearchbyKeywords(@WebParam(name = "keywords") String keywords) {
        List<Image> retList = new ArrayList<Image>();
                  
        Connection connection = null;
        try{
            PreparedStatement statement;
            String query;
            Class.forName("org.sqlite.JDBC"); 
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Carles\\Desktop\\ADpractica3\\ADpractica3-projecte\\practica3.db");


            //cogemos todas las imagenes
            query = "select * from image where keywords like ?";
            keywords = '%' + keywords + '%';
            statement = connection.prepareStatement(query);
            statement.setString(1, keywords);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Image tmp = new Image();
                tmp.setId(rs.getInt("id"));
                tmp.setTitle(rs.getString("title"));
                tmp.setAuthor(rs.getString("author"));
                tmp.setDescription(rs.getString("description"));
                tmp.setKeywords(rs.getString("keywords"));
                tmp.setCreaDate(rs.getString("creation_date"));
                tmp.setAltaDate(rs.getString("storage_date"));
                tmp.setFilename(rs.getString("filename"));
                
                retList.add(tmp);
            }

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }
        return retList;
    }    
}
