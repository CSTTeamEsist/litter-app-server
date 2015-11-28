package com.mattbozelka;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mattbozelka.model.Event;
import com.mattbozelka.repository.EventListRepository;
import com.mattbozelka.repository.EventListStub;

/**
 * Root resource (exposed at "event-list" path)
 */
@Path("event-list")
public class EventResource {
	
	EventListRepository eventList = new EventListStub();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getIt() {
        return eventList.getEventList();
    }
}
