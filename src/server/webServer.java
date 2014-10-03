/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import exceptions.NotFoundException;
import facade.Facade;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anders
 */
public class webServer {

    private final HttpServer server;
    private final Gson gson = new Gson();
    private Facade facade;

    public webServer(int port) throws IOException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(port), 0);
        server.createContext("/person", createContext());
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }

    private HttpHandler createContext() {
        return new HttpHandler() {
            @Override
            public void handle(HttpExchange he) throws IOException {
                
                he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                String path = he.getRequestURI().getPath();
                int lastIndexOf = path.lastIndexOf("/");
                
                if("GET".equalsIgnoreCase(he.getRequestMethod())){
                    if (lastIndexOf > 0){
                        try {
                            he.sendResponseHeaders(200, 0);
                            int id = Integer.parseInt(path.substring(lastIndexOf + 1));
                            OutputStream responseBody = he.getResponseBody();
                            responseBody.write(facade.getPersonAsJSON(id).getBytes());
                            return;
                        } catch (NotFoundException ex) {
                            Logger.getLogger(webServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    facade.getPersonsAsJSON();
                }
                
                if("DELETE".equalsIgnoreCase(he.getRequestMethod())){
                    if (lastIndexOf > 0){
                        try {
                            int id = Integer.parseInt(path.substring(lastIndexOf + 1));
                            facade.delete(id);
                        } catch (NotFoundException ex) {
                            Logger.getLogger(webServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                if("POST".equalsIgnoreCase(he.getRequestMethod())){
                    String message = "";
                    Scanner scanner = new Scanner(he.getRequestBody());
                    while (scanner.hasNextLine())
                    {
                        message += scanner.nextLine();
                    }
                    if(path.substring(lastIndexOf + 4).equalsIgnoreCase("Role")){
                        facade.addRoleFromGson(path, lastIndexOf);
                        return;
                    }
                    facade.addPersonFromGson(message);
                }
            }
        };
    }
}
