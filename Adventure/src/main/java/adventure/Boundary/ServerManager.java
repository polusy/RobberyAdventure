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
 * 
 * 
 * La classe si occupa di gestire un qualsiasi server (http) e di permetterne l'avvio.
 * 
 * @author Paolo
 */
public class ServerManager implements Runnable {
    /**
     * Server http gestito dal {@link ServerManager}.
     */
    private HttpServer genericServer;
    
    /**
     *
     * Il metodo costruisce un server Http, attraverso l'URI, il numero di porta e il service specifico.
     * 
     * @param stringUri Stringa rappresentante l'URI del server da inizializzare.
     * 
     * @param portNumber Numero specifico di porta associato al servizio.
     * 
     * @param service Letterale di classe rappresentante la classe di servizio
     * per la configurazione del Server REST.
     * 
     */
    public ServerManager(String stringUri, int portNumber, Class<?> service){
        URI uri = UriBuilder.fromUri(stringUri).port(portNumber).build();
        ResourceConfig config = new ResourceConfig(service);
        genericServer = GrizzlyHttpServerFactory.createHttpServer(uri, config);
    }

    /**
     * Il metodo implementa il metodo {@code run()} dell'interfaccia {@link Runnable}
     * per permettere l'avvio del server in parallelo all'esecuzione del resto del gioco.
     */
    @Override
    public void run()
    {
        try{
            genericServer.start();
        } catch (IOException exception){};
    }

    /**
     *
     * @return Specifico httpServer salvato come attributo d'istanza
     * al momento dell'init. del ServerManager.
     */
    public HttpServer getServer()
    {
        return genericServer;
    }
    
}
