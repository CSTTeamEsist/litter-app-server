package com.mattbozelka;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import com.mattbozelka.model.LitterEventCollection;
import com.mattbozelka.repository.LitterEventCollectionRepository;
import com.mattbozelka.repository.LitterEventCollectionListStub;
/**
 * Root resource (exposed at "litter-event-collection-list" path)
 */
@Path("litter-event-collection-list")
public class LitterEventCollectionResource {
	
	LitterEventCollectionRepository litterEventCollectionList = new LitterEventCollectionListStub();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LitterEventCollection> getIt() {
    	
    	
        return litterEventCollectionList.getLitterEventCollectionList();
    }
    
}
