/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Boundary;

import org.glassfish.grizzly.http.server.HttpServer;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import java.io.IOException;


/**
 *
 * @author utente
 */
public class ServerManager implements Runnable {
    private String uri;
    HttpServer genericServer;
    
    public ServerManager(String stringUri, int portNumber, Class<?> service){
        URI uri = UriBuilder.fromUri(stringUri).port(portNumber).build();
        ResourceConfig config = new ResourceConfig(service);
        genericServer = GrizzlyHttpServerFactory.createHttpServer(uri, config);
    }

    @Override
    public void run()
    {
        try{
            genericServer.start();
        } catch (IOException exception){};
    }

    public HttpServer getServer()
    {
        return genericServer;
    }
    
}
