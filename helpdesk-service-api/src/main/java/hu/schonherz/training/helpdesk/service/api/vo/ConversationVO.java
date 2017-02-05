package hu.schonherz.training.helpdesk.service.api.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ConversationVO {
    private long id;
    private String clientId;

    private int agentId;

    private boolean closed;

    private List<MessageVO> messages;
}
