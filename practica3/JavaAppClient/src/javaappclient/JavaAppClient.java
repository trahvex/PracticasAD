/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaappclient;

import gestor1.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author carles
 */
public class JavaAppClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int num;
        
        while (true) {
            System.out.println("Escull una opció:");
            System.out.println("1 - Registrar una imatge:");
            System.out.println("2 - Modificar una imatge");
            System.out.println("3 - Llistar totes les imatges");
            System.out.println("4 - Buscar una imatge");
            System.out.println("5 - Sortir del programa");
            
            num = input.nextInt();
            switch (num) {
                case 1: // Registrar imatge
                    System.out.println(" ");
                    Image tmp = new Image();
                    input.nextLine();
                    System.out.println("Introdueix titol:");
                    tmp.setTitle(input.nextLine());
                    System.out.println("Introdueix autor:");
                    tmp.setAuthor(input.nextLine());
                    System.out.println("Introdueix descripció:");
                    tmp.setDescription(input.nextLine());
                    System.out.println("Introdueix keywords:");
                    tmp.setKeywords(input.nextLine());
                    System.out.println("Introdueix data de creació:");
                    tmp.setCreaDate(input.nextLine());
                    System.out.println("Introdueix filename:");
                    tmp.setFilename(input.nextLine());
                    int ret = registerImage(tmp);
                    if (ret == 0) System.out.println("Operació fallida");
                    else System.out.println("Imatge registrada amb exit!");
                    break;

                case 2: // Modificar imatge
                    System.out.println(" ");
                    System.out.println("Introdueix ID d'imatge a modificar:");
                    int id = input.nextInt();
                    Image img_mod = new Image();
                    img_mod = searchbyId(id);
                    System.out.println("Escull el camp a modificar:");
                    System.out.println("1 - Títol: " + img_mod.getTitle());
                    System.out.println("2 - Descripció: " + img_mod.getDescription());
                    System.out.println("3 - Autor: " + img_mod.getAuthor());
                    System.out.println("4 - Creació: " + img_mod.getCreaDate());
                    System.out.println("5 - Keywords: " + img_mod.getKeywords());
                    int x = input.nextInt();
                    input.nextLine();
                    System.out.println("Introdueix nou valor:");
                    String newValue = input.nextLine();
                    switch (x) {
                        case 1:
                            img_mod.setTitle(newValue);
                            break;
                        case 2:
                            img_mod.setDescription(newValue);
                            break;
                        case 3:
                            img_mod.setAuthor(newValue);
                            break;
                        case 4:
                            img_mod.setCreaDate(newValue);
                            break;
                        case 5:
                            img_mod.setKeywords(newValue);
                            break;
                    }
                    modifyImage(img_mod);
                    break;

                case 3: // Llistar
                    System.out.println(" ");
                    List<Object> retList = new ArrayList<Object>();
                    retList = listImages();
                    Iterator<Object> it = retList.iterator();
                    while (it.hasNext()) {
                        Object image = it.next();
                        Image img = Image.class.cast(image);
                        System.out.println("Id: " + img.getId());
                        System.out.println("Títol: " + img.getTitle());
                        System.out.println("Descripció: " + img.getDescription());
                        System.out.println("Autor: " + img.getAuthor());
                        System.out.println("Data creació: " + img.getCreaDate());
                        System.out.println("Data d'alta: " + img.getAltaDate());
                        System.out.println("Keywords: " + img.getKeywords());
                        System.out.println("----------------------------------");
                    }
                    break;

                case 4: // Cerca
                    System.out.println(" ");
                    System.out.println("Escull tipus de cerca:");
                    System.out.println("1 - Cerca per ID");
                    System.out.println("2 - Cerca per titol");
                    System.out.println("3 - Cerca per data de creacio");
                    System.out.println("4 - Cerca per autor");
                    System.out.println("5 - Cerca per keywords");
                    int y = input.nextInt();
                    input.nextLine();
                    
                    switch (y){
                        case 1:
                            System.out.println("Introdueix ID:");
                            Image temporal_id = new Image();
                            temporal_id = searchbyId(input.nextInt());
                            input.nextLine();
                            System.out.println("Id: " + temporal_id.getId());
                            System.out.println("Títol: " + temporal_id.getTitle());
                            System.out.println("Descripció: " + temporal_id.getDescription());
                            System.out.println("Autor: " + temporal_id.getAuthor());
                            System.out.println("Data creació: " + temporal_id.getCreaDate());
                            System.out.println("Data d'alta: " + temporal_id.getAltaDate());
                            System.out.println("Keywords: " + temporal_id.getKeywords());
                            break;
                            
                        case 2:
                            System.out.println("Introdueix titol:");
                            List<Object> titolList = new ArrayList<Object>();
                            titolList = searchbyTitle(input.nextLine());
                            Iterator<Object> titolIt = titolList.iterator();
                             while (titolIt.hasNext()) {
                                Object image = titolIt.next();
                                Image img = Image.class.cast(image);
                                System.out.println("Id: " + img.getId());
                                System.out.println("Títol: " + img.getTitle());
                                System.out.println("Descripció: " + img.getDescription());
                                System.out.println("Autor: " + img.getAuthor());
                                System.out.println("Data creació: " + img.getCreaDate());
                                System.out.println("Data d'alta: " + img.getAltaDate());
                                System.out.println("Keywords: " + img.getKeywords());
                                System.out.println("----------------------------------");
                            }
                            break;
                            
                        case 3:
                            System.out.println("Introdueix data creació:");
                            List<Object> dateList = new ArrayList<Object>();
                            dateList = searchbyCreaDate(input.nextLine());
                            Iterator<Object> dateIt = dateList.iterator();
                             while (dateIt.hasNext()) {
                                Object image = dateIt.next();
                                Image img = Image.class.cast(image);
                                System.out.println("Id: " + img.getId());
                                System.out.println("Títol: " + img.getTitle());
                                System.out.println("Descripció: " + img.getDescription());
                                System.out.println("Autor: " + img.getAuthor());
                                System.out.println("Data creació: " + img.getCreaDate());
                                System.out.println("Data d'alta: " + img.getAltaDate());
                                System.out.println("Keywords: " + img.getKeywords());
                                System.out.println("----------------------------------");
                            }
                            break;
                            
                        case 4:
                            System.out.println("Introdueix autor:");
                            List<Object> authorList = new ArrayList<Object>();
                            authorList = searchbyAuthor(input.nextLine());
                            Iterator<Object> authorIt = authorList.iterator();
                             while (authorIt.hasNext()) {
                                Object image = authorIt.next();
                                Image img = Image.class.cast(image);
                                System.out.println("Id: " + img.getId());
                                System.out.println("Títol: " + img.getTitle());
                                System.out.println("Descripció: " + img.getDescription());
                                System.out.println("Autor: " + img.getAuthor());
                                System.out.println("Data creació: " + img.getCreaDate());
                                System.out.println("Data d'alta: " + img.getAltaDate());
                                System.out.println("Keywords: " + img.getKeywords());
                                System.out.println("----------------------------------");
                            }
                            break;
                            
                        case 5:
                            System.out.println("Introdueix titol:");
                            List<Object> kwList = new ArrayList<Object>();
                            kwList = searchbyKeywords(input.nextLine());
                            Iterator<Object> kwIt = kwList.iterator();
                             while (kwIt.hasNext()) {
                                Object image = kwIt.next();
                                Image img = Image.class.cast(image);
                                System.out.println("Id: " + img.getId());
                                System.out.println("Títol: " + img.getTitle());
                                System.out.println("Descripció: " + img.getDescription());
                                System.out.println("Autor: " + img.getAuthor());
                                System.out.println("Data creació: " + img.getCreaDate());
                                System.out.println("Data d'alta: " + img.getAltaDate());
                                System.out.println("Keywords: " + img.getKeywords());
                                System.out.println("----------------------------------");
                            }
                            break;
                    }
                    break;

                case 5: // Exit
                    System.exit(0);
            }
        }
    }

    private static java.util.List<java.lang.Object> listImages() {
        gestor1.GestorImagenes_Service service = new gestor1.GestorImagenes_Service();
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.listImages();
    }

    private static int modifyImage(gestor1.Image image) {
        gestor1.GestorImagenes_Service service = new gestor1.GestorImagenes_Service();
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.modifyImage(image);
    }

    private static int registerImage(gestor1.Image image) {
        gestor1.GestorImagenes_Service service = new gestor1.GestorImagenes_Service();
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.registerImage(image);
    }

    private static java.util.List<java.lang.Object> searchbyAuthor(java.lang.String author) {
        gestor1.GestorImagenes_Service service = new gestor1.GestorImagenes_Service();
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.searchbyAuthor(author);
    }

    private static java.util.List<java.lang.Object> searchbyCreaDate(java.lang.String creaDate) {
        gestor1.GestorImagenes_Service service = new gestor1.GestorImagenes_Service();
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.searchbyCreaDate(creaDate);
    }

    private static Image searchbyId(int id) {
        gestor1.GestorImagenes_Service service = new gestor1.GestorImagenes_Service();
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.searchbyId(id);
    }

    private static java.util.List<java.lang.Object> searchbyKeywords(java.lang.String keywords) {
        gestor1.GestorImagenes_Service service = new gestor1.GestorImagenes_Service();
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.searchbyKeywords(keywords);
    }

    private static java.util.List<java.lang.Object> searchbyTitle(java.lang.String title) {
        gestor1.GestorImagenes_Service service = new gestor1.GestorImagenes_Service();
        gestor1.GestorImagenes port = service.getGestorImagenesPort();
        return port.searchbyTitle(title);
    }
    
}
