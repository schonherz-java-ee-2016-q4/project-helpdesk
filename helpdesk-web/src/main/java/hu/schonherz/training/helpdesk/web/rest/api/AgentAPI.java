package hu.schonherz.training.helpdesk.web.rest.api;

import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.web.rest.domain.ClientDetailsRequest;
import hu.schonherz.training.helpdesk.web.rest.domain.ConversationResponse;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/agents")
@Stateless(mappedName = "agentApi")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class AgentAPI {

    private static final int RANDOM_TRESHOLD = 25;

    @EJB
    private ConversationService conversationService;

    @POST
    @Path("/available")
    public Response getAvailableAgent(final ClientDetailsRequest clientDetailsRequest) {
        //half dummy implementation for testing the functionality of the js plugin

        //lookup for available agents implementation goes here
        //lookup ends here

        ConversationVO conversationVO = ConversationVO.builder()
            .agentId(2)
            .clientId(clientDetailsRequest.getClientId())
            .clientEmail(clientDetailsRequest.getClientEmail())
            .closed(false)
            .build();

        ConversationResponse conversationResponse = new ConversationResponse();
        conversationResponse.setConversationId(conversationService.save(conversationVO));

        return Response.accepted(conversationResponse).build();
    }



}
