package hu.schonherz.training.helpdesk.rest.activity;

import hu.schonherz.training.helpdesk.service.api.service.ClientActivityService;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("/activities")
public class Activity {

    @EJB
    private ClientActivityService clientActivityService;

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getProductInJSON(final ClientActivityVO clientActivityVO) {
        //TODO: when the target string is empty  --> should return badrequest
        clientActivityVO.setCreatedAt(LocalDateTime.now());
        clientActivityService.save(clientActivityVO);

        return Response.accepted().build();
    }





}
