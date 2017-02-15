package hu.schonherz.training.helpdesk.web.rest.api;

import hu.schonherz.training.helpdesk.service.api.service.ClientActivityService;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Collection;

@Path("/activities")
@Stateless(mappedName = "activityApi")
public class ClientActivityAPI {

    @EJB
    private ClientActivityService clientActivityService;

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addClientActivity(final ClientActivityVO clientActivityVO) {

        if (clientActivityVO.getTarget() == null || "".equals(clientActivityVO.getTarget())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        clientActivityVO.setCreatedAt(LocalDateTime.now());
        clientActivityService.save(clientActivityVO);

        return Response.accepted().build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ClientActivityVO getClientActivity(@PathParam(value = "id") final Long id) {
        return clientActivityService.findById(id);

    }

    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<ClientActivityVO> getClientActivities() {
        return clientActivityService.findAll();
    }
}
