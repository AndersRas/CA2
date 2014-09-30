/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2_3semester;

import java.io.IOException;
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
        webServer server = new webServer(8080);
        server.start();
        System.out.println("Server started, listening on port: " + port);
    }
}
