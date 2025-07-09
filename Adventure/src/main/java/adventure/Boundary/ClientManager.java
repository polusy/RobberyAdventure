/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Boundary;

import adventure.Entity.objects.AdvObject;
import adventure.identifiers.ObjectId;

import com.google.gson.Gson;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Paolo
 */
public class ClientManager {
    
    private final WebTarget target;
    
    public void addObjectRequest(AdvObject object){
        
        Gson gson = new Gson();
        String jsonObject = gson.toJson(object);
        target.path("object/add").request(MediaType.APPLICATION_JSON).put(Entity.entity(jsonObject, MediaType.APPLICATION_JSON));
    }
    
    public AdvObject getObjectRequest(ObjectId objectId){
        Response response = target.path("object/" + objectId.name()).request(MediaType.APPLICATION_JSON).get();
	AdvObject object = response.readEntity(AdvObject.class);
	
	response.close();
	return object;
    }
    
    public ClientManager(String target){
        Client client = ClientBuilder.newClient();
        this.target = client.target(target);
    }
    
    public void populate(){
        //inserire impl.
    }
    
    
    
}
