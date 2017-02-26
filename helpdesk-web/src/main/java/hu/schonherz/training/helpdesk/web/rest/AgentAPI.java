package hu.schonherz.training.helpdesk.web.rest;

import hu.schonherz.project.admin.service.api.rpc.NoAvailableAgentFoundException;
import hu.schonherz.project.admin.service.api.rpc.NoSuchDomainException;
import hu.schonherz.project.admin.service.api.rpc.RpcAgentAvailabilityServiceRemote;
import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationStatusVO;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.web.rest.domain.ClientDetailsRequest;
import hu.schonherz.training.helpdesk.web.rest.domain.ConversationResponse;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Slf4j
@Path("/agents")
@Stateless(mappedName = "agentApi")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AgentAPI {

    @EJB
    private ConversationService conversationService;

    @EJB(lookup = "java:global/admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcAgentAvailabilityServiceBean!"
            + "hu.schonherz.project.admin.service.api.rpc.RpcAgentAvailabilityServiceRemote")
    private RpcAgentAvailabilityServiceRemote rpcAgentAvailabilityServiceRemote;

    @POST
    @Path("/available")
    public Response getAvailableAgent(final ClientDetailsRequest clientDetailsRequest) {
        ConversationResponse conversationResponse = new ConversationResponse();

        try {
            Long agentId = rpcAgentAvailabilityServiceRemote.getAvailableAgent(clientDetailsRequest.getSource());
            log.info("Got agentid from the admin team: {}", agentId);

            ConversationVO conversationVO = ConversationVO.builder()
                    .agentId(agentId)
                    .clientId(clientDetailsRequest.getClientId())
                    .clientEmail(clientDetailsRequest.getClientEmail())
                    .status(ConversationStatusVO.NEW)
                    .createdAt(LocalDateTime.now())
                    .build();

            log.info("Current conversationVO: {}", conversationVO);
            conversationResponse.setConversationId(conversationService.save(conversationVO));
            return Response.accepted(conversationResponse).build();
        } catch (NoAvailableAgentFoundException e) {
            log.error("No client is available right now for the requested domain {}", clientDetailsRequest.getSource(), e);
            conversationResponse.setConversationId(null);
            return Response.status(Response.Status.BAD_REQUEST).entity(conversationResponse).build();
        } catch (NoSuchDomainException e) {
            log.error("There is no such domain: {}", clientDetailsRequest.getSource(), e);
            conversationResponse.setConversationId(null);
            return Response.status(Response.Status.BAD_REQUEST).entity(conversationResponse).build();
        }
    }

}
