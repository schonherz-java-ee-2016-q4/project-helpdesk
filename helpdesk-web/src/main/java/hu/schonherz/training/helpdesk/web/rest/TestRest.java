package hu.schonherz.training.helpdesk.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/message")
public class TestRest {

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") final String msg) {

        String result = "Restful example : " + msg;

        return Response.status(Response.Status.OK).entity(result).build();

    }

}
