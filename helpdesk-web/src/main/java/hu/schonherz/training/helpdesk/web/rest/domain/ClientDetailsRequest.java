package hu.schonherz.training.helpdesk.web.rest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientDetailsRequest implements Serializable {
    private static final long serialVersionUID = 7495610044458945081L;

    private String source;
    private String clientId;
    private String clientEmail;
}
