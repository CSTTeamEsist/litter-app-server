package com.mattbozelka;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mattbozelka.model.Volunteer;
import com.mattbozelka.repository.VolunteerListRepository;
import com.mattbozelka.repository.VolunteerListStub;

/**
 * Root resource (exposed at "volunteer-list" path)
 */
@Path("volunteer-list")
public class VolunteerResource {
	
	VolunteerListRepository volunteerList = new VolunteerListStub();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Volunteer> getIt() {
        return volunteerList.getVolunteerList();
    }
}
