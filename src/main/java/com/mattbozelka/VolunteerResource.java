package com.mattbozelka;

import java.util.List;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import javax.json.*;
 

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import com.mattbozelka.model.Volunteer;
import com.mattbozelka.repository.VolunteerListRepository;
import com.mattbozelka.repository.VolunteerListStub;

/**
 * Root resource (exposed at "volunteer-list" path)
 */
@Path("volunteer-list")
public class VolunteerResource {
	//REQ1, REQ13
	VolunteerListRepository volunteerList = new VolunteerListStub();
	
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Volunteer> getIt() {
        return volunteerList.getVolunteerList();
    }
    
    //test method used to test json sending and receiving
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Volunteer testIt() {
    	System.out.println("TEST");
    	Volunteer v = new Volunteer();
		try {
			//TODO: To test this, change the values below
			String json = "{\"FName\":\"Joey\",\"LName\":\"Blowy\",\"emailAddress\":\"12zzzz@email.com\",\"password\":\"test1234\"}";
			JsonReader jsonReader = Json.createReader(new StringReader(json));
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			
			System.out.println(jsonObject);
			
			try {
				URL url = new URL("http://localhost:8080/litter-service-webapp/webapi/volunteer-list/create/");
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
				
		    	v = volunteerList.getVolunteer(jsonObject.getString("FName"), 
		    			jsonObject.getString("LName"), jsonObject.getString("emailAddress"));
				
				
			} catch (Exception e) {
				System.out.println("\nError while calling REST Service");
				System.out.println(e);
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return v;
        //return volunteerList.getVolunteerList();
    }
   
    //takes in a json object and stores it in the database
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Volunteer postIt(InputStream incomingData) {
    	
		StringBuilder jsonBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				jsonBuilder.append(line);
			}
		} catch (Exception e) {
			//System.out.println("Error Parsing: - ");
		}
 
		JsonReader jsonReader = Json.createReader(new StringReader(jsonBuilder.toString()));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
    	return volunteerList.addVolunteer(jsonObject.getString("FName"), 
    			jsonObject.getString("LName"), jsonObject.getString("emailAddress"), 
    			jsonObject.getString("password"));
    	
	}
    
    
}
