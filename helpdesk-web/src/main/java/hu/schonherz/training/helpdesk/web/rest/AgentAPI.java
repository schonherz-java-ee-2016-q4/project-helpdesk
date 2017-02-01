package hu.schonherz.training.helpdesk.web.rest;

import hu.schonherz.training.helpdesk.service.api.vo.CompanyVO;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/agents")
@Stateless(mappedName = "agentApi")
public class AgentAPI {

    private static final int RANDOM_TRESHOLD = 25;

    private class AgentJSON {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(final Long id) {
            this.id = id;
        }
    }

    @POST
    @Path("/available")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getClientActivity(final CompanyVO company) {
        //dummy implementation for testing the functionality of the js plugin
        AgentJSON agent = new AgentJSON();

        Long randomNumber = Math.abs(new Random().nextLong() % RANDOM_TRESHOLD);

        randomNumber = (randomNumber % 2 == 0) ? randomNumber : null;

        agent.setId(randomNumber);

        return Response.accepted(agent).build();

    }

}
