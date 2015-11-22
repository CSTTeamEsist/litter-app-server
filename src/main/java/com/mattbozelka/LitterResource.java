package com.mattbozelka;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mattbozelka.model.LitterPiece;
import com.mattbozelka.repository.LitterListRepository;
import com.mattbozelka.repository.LitterListStub;

/**
 * Root resource (exposed at "litter-list" path)
 */
@Path("litter-list")
public class LitterResource {
	
	LitterListRepository litterList = new LitterListStub();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LitterPiece> getIt() {
        return litterList.getLitterList();
    }
}
