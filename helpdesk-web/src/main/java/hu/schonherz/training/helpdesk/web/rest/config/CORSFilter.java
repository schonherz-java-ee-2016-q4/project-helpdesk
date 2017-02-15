package hu.schonherz.training.helpdesk.web.rest.config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JAX-RS Provider for enabling CORS requests to our REST API endpoints.
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {
    private static final Map<String, String> CORS_HEADERS;

    static {
        CORS_HEADERS = new HashMap<>();
        CORS_HEADERS.put("Access-Control-Allow-Origin", "*");
        CORS_HEADERS.put("Access-Control-Allow-Headers", "Content-Type");
        CORS_HEADERS.put("Access-Control-Allow-Methods", "GET, POST");
    }

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext)
            throws IOException {

        for (final Map.Entry<String, String> entry : CORS_HEADERS.entrySet()) {
            responseContext.getHeaders().add(entry.getKey(), entry.getValue());
        }
    }

}
