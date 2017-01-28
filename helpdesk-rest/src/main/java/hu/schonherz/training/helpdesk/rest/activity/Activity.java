package hu.schonherz.training.helpdesk.rest.activity;

import javax.ws.rs.Consumes;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/activities")
public class Activity {

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getProductInJSON() throws NotSupportedException {

        return Response.accepted().build();
    }





}
