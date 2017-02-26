package hu.schonherz.training.helpdesk.web.rest;

import hu.schonherz.project.admin.service.api.rpc.RpcAgentAvailabilityServiceRemote;
import hu.schonherz.project.admin.service.api.rpc.UsernameNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;

@Path("/test")
public class TestRest {

    @EJB(lookup = "java:global/admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcAgentAvailabilityServiceBean!"
            + "hu.schonherz.project.admin.service.api.rpc.RpcAgentAvailabilityServiceRemote")
    private RpcAgentAvailabilityServiceRemote rpcAgentAvailabilityServiceRemote;

    @GET
    @Path("/resetagent/{username}")
    public Response printMessage(@PathParam("username") final String userName) {

        try {
            rpcAgentAvailabilityServiceRemote.setAgentAvailabilityToTrue(userName);
        } catch (UsernameNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(MessageFormat.format("Username {0} not found! Try again!", userName))
                    .build();
        }
        return Response.status(Response.Status.OK).entity("Success!").build();

    }


}
