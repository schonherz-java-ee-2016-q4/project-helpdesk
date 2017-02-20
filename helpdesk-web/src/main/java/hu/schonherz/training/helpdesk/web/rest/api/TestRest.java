package hu.schonherz.training.helpdesk.web.rest.api;

import hu.schonherz.project.admin.service.api.rpc.NoAvailableAgentFoundException;
import hu.schonherz.project.admin.service.api.rpc.NoSuchDomainException;
import hu.schonherz.project.admin.service.api.rpc.RpcAgentAvailabilityServiceRemote;
import hu.schonherz.project.admin.service.api.rpc.UsernameNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestRest {

    @EJB(lookup = "java:global/admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcAgentAvailabilityServiceBean!"
        + "hu.schonherz.project.admin.service.api.rpc.RpcAgentAvailabilityServiceRemote")
    private RpcAgentAvailabilityServiceRemote rpcAgentAvailabilityServiceRemote;

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") final String msg) throws NoAvailableAgentFoundException, NoSuchDomainException {

        String result = "Restful example : " + msg;
        Long id = rpcAgentAvailabilityServiceRemote.getAvailableAgent("dcuniverse.com");
        return Response.status(Response.Status.OK).entity(id).build();

    }

    @GET
    @Path("/makeavailable")
    public Response printMessage() throws NoSuchDomainException, NoAvailableAgentFoundException, UsernameNotFoundException {

        rpcAgentAvailabilityServiceRemote.setAgentAvailabilityToTrue("bruce002");
        return Response.status(Response.Status.OK).entity("success").build();

    }



}