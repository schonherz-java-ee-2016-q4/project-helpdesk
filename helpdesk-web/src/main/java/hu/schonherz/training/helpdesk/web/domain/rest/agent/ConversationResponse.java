package hu.schonherz.training.helpdesk.web.domain.rest.agent;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ConversationResponse implements Serializable {
    private static final long serialVersionUID = -4231703526682150385L;

    private Long conversationId;
}
