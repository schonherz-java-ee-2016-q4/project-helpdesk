package hu.schonherz.training.helpdesk.service.api.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConversationVO {
    private long id;
    private int clientId;

    private int agentId;

    private boolean closed;
}
