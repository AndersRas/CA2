/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2_3semester;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import server.webServer;


/**
 *
 * @author Christoffer
 */
public class App {
    static int port = 8080;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        String url = "C:\\Users\\Fisk\\Documents\\NetBeansProjects\\CA2\\src\\htmlFiles\\index.html";
        webServer server = new webServer(port);
        server.start();
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));        
        System.out.println("Server started, listening on port: " + port);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ca2_3semesterPU");
        EntityManager em = emf.createEntityManager();
    }
}
