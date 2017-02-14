package hu.schonherz.training.helpdesk.web.rest;

import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.web.domain.rest.agent.ClientDetailsRequest;
import hu.schonherz.training.helpdesk.web.domain.rest.agent.ConversationResponse;

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
public class AgentAPI {

    private static final int RANDOM_TRESHOLD = 25;

    @EJB
    private ConversationService conversationService;



    @POST
    @Path("/available")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
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
