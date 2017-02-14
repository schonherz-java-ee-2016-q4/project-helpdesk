package hu.schonherz.training.helpdesk.web.rest.config;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class DefaultExceptionHandler implements ExceptionMapper<WebApplicationException>{
    private static Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Override
    public Response toResponse(WebApplicationException e) {
        logger.error(e.getMessage());
        return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).type("application/json").build(); 
    }

}
