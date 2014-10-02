/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Entity.Person;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Anders
 */
public class webServer {

    private final HttpServer server;
    private final Gson gson = new Gson();

    public webServer(int port) throws IOException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(port), 0);
        server.createContext("/connect", createContext());
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
            public void handle(HttpExchange exchange) throws IOException {
                exchange.sendResponseHeaders(200, 0);
            }
        };
    }

    public void getPersonsAsJSON(HttpExchange he) throws IOException 
    {
        EntityManager em = Persistence.createEntityManagerFactory("Ca2_3semesterPU").createEntityManager();
        String response = "";
        int status = 200;
        String method = he.getRequestMethod().toUpperCase();
        switch (method) 
        {
            case "GET":
                try 
                {
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    if (lastIndex > 0) 
                    {
                        String idStr = path.substring(lastIndex + 1);
                        int id = Integer.parseInt(idStr);
                        List<Person> persons = em.createQuery("select p from Person p").getResultList();
                    }
                } catch (NumberFormatException nfe) 
                {
                    response = "Id is not a number";
                    status = 404;
                }
                break;
        }
    }

    public void getPersonAsJSON(long id, HttpExchange he) throws IOException 
    {
        EntityManager em = Persistence.createEntityManagerFactory("Ca2_3semesterPU").createEntityManager();
        String response = "";
        int status = 200;
        String method = he.getRequestMethod().toUpperCase();
        switch (method) {
            case "GET":
                try 
                {
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    if (lastIndex > 0) 
                    {
                        String idStr = path.substring(lastIndex + 1);
                        id = Integer.parseInt(idStr);
                        List<Person> persons = em.createQuery("select p from Person p where id == " + id).getResultList();
                    }
                } catch (NumberFormatException nfe) 
                {
                    response = "Id is not a number";
                    status = 404;
                }
                break;
        }
    }
    
    public void addPersonFromJson(String json, HttpExchange he) throws IOException 
    {
        EntityManager em = Persistence.createEntityManagerFactory("Ca2_3semesterPU").createEntityManager();
        String response = "";
        int status = 200;
        String method = he.getRequestMethod().toUpperCase();
        switch (method) {
          case "POST":
          try{
          InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
          BufferedReader br = new BufferedReader(isr);
          String jsonQuery = br.readLine();
          if(jsonQuery.contains("<") || jsonQuery.contains(">")){
            throw new IllegalArgumentException("Illegal characters in input");
          }
          
          response = new Gson().toJson(p);
          }catch(IllegalArgumentException iae){
            status = 400;
            response = iae.getMessage();
          }
          catch(IOException e){
            status = 500;
            response = "Internal Server Problem";
          }
          break;
    }
    }
    
    
    public void addRoleFromGson(String json, long id)
    {
        
    }
}
