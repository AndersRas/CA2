/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author Anders
 */
public class webServer 
{
    private final HttpServer server;
    
    public webServer(int port) throws IOException
    {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(port), 0);
        server.createContext("/connect", createContext());
    }

    public void start()
    {
        server.start();
    }

    public void stop()
    {
        server.stop(0);
    }
    
    private HttpHandler createContext()
    {
        return new HttpHandler()
        {
            @Override
            public void handle(HttpExchange exchange) throws IOException
            {
                exchange.sendResponseHeaders(200, 0);
            }
        };
    }    
}
