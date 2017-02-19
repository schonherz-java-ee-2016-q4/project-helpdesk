package hu.schonherz.training.helpdesk.web.rest.api;

import hu.schonherz.project.admin.service.api.rpc.FailedRpcLoginAttemptException;
import hu.schonherz.project.admin.service.api.rpc.RpcLoginServiceRemote;
import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
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

@Path("/agents")
@Stateless(mappedName = "agentApi")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Slf4j
public class AgentAPI {

    private static final int RANDOM_TRESHOLD = 25;

    @EJB
    private ConversationService conversationService;

//    @EJB(lookup = "ejb:admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcAgentAvailabilityServiceBean")
//    RpcAgentAvailabilityServiceRemote rpcAgentAvailabilityServiceRemote;

    @EJB(lookup = "java:global/admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcLoginServiceBean")
    private RpcLoginServiceRemote rpcLoginServiceRemote;

    @POST
    @Path("/available")
    public Response getAvailableAgent(final ClientDetailsRequest clientDetailsRequest) {
        //half dummy implementation for testing the functionality of the js plugin

//        try {
//            Long agentId = rpcAgentAvailabilityServiceRemote.getAvailableAgent(clientDetailsRequest.getSource());
        try {
            rpcLoginServiceRemote.rpcLogin("bruce001");
        } catch (FailedRpcLoginAttemptException e) {
            e.printStackTrace();
        }
        ConversationVO conversationVO = ConversationVO.builder()
                .agentId(2L)
                .clientId(clientDetailsRequest.getClientId())
                .clientEmail(clientDetailsRequest.getClientEmail())
                .closed(false)
                .build();

            ConversationResponse conversationResponse = new ConversationResponse();
            conversationResponse.setConversationId(conversationService.save(conversationVO));

            return Response.accepted(conversationResponse).build();
//        } catch (NoAvailableAgentFoundException e) {
//            log.error("No client is available right now for the requested domain domain.");
//        }
//        return Response.status(Response.Status.BAD_REQUEST).build();

    }



}
