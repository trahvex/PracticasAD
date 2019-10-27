/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor1;

/**
 *
 * @author ruben.sanz.garcia
 */
public class Image {
    private int id;
    private String title;
    private String author;
    private String description;
    private String keywords;
    private String creaDate;
    private String altaDate;
    private String filename;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setCreaDate(String creaDate) {
        this.creaDate = creaDate;
    }

    public void setAltaDate(String altaDate) {
        this.altaDate = altaDate;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getCreaDate() {
        return creaDate;
    }

    public String getAltaDate() {
        return altaDate;
    }

    public String getFilename() {
        return filename;
    }
          
            
    
}
