package hu.schonherz.training.helpdesk.rest.activity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/activity")
public class Activity {

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProductInJSON() {


        return Object.class.getSimpleName();
    }

}
