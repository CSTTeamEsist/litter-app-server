package com.mattbozelka;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}
