package com.mattbozelka;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mattbozelka.model.LitterCollection;
import com.mattbozelka.repository.LitterCollectionRepository;
import com.mattbozelka.repository.LitterCollectionListStub;

/**
 * Root resource (exposed at "litter-collection-list" path)
 */
@Path("litter-collection-list")
public class LitterCollectionResource {
	
	LitterCollectionRepository litterCollectionList = new LitterCollectionListStub();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LitterCollection> getIt() {
        return litterCollectionList.getLitterCollectionList();
    }
    

    @GET
    @Path("/{userid}/{eventid}/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LitterCollection> getUserEventList(@PathParam("userid") String userId, @PathParam("eventid") String eventId) {
    	
        return litterCollectionList.getLitterCollectionListByUserAndEvent(userId, eventId);
    }
    

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<LitterCollection>  postIt(InputStream incomingData) {

		StringBuilder jsonBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				jsonBuilder.append(line);
			}
		} catch (Exception e) {
			//ignore
		}
 
		JsonReader jsonReader = Json.createReader(new StringReader(jsonBuilder.toString()));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();

		litterCollectionList.updateEvent(
				Long.parseLong(jsonObject.getString("litterID")), 
				Long.parseLong(jsonObject.getString("volID")), 
				Long.parseLong(jsonObject.getString("teamID")), 
				Long.parseLong(jsonObject.getString("eventID")), 
				Long.parseLong(jsonObject.getString("tally")) );
		
    	System.out.println("end");
    	
        return litterCollectionList.getLitterCollectionList();
	}
    
    
    //TODO: Delete this method after testing
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<LitterCollection>  testIt() {
    	System.out.println("TEST");
    	
		try {
			//TODO: To test this, change the values below
			String json = "{\"litterID\":\"9\",\"volID\":\"2\",\"teamID\":\"1\",\"eventID\":\"2\",\"tally\":\"195\"}";
			JsonReader jsonReader = Json.createReader(new StringReader(json));
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			
			System.out.println(jsonObject);
 
			try {
				URL url = new URL("http://localhost:8080/litter-service-webapp/webapi/litter-collection-list/create/");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObject.toString());
				out.close();
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
				while (in.readLine() != null) {}
				System.out.println("\nREST Service Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling REST Service");
				System.out.println(e);
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}

        return litterCollectionList.getLitterCollectionList();
    }
    
    
    
    
    
    
}
