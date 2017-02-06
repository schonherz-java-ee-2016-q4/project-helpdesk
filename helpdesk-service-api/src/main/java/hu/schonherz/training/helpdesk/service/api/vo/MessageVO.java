package hu.schonherz.training.helpdesk.service.api.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageVO {
    private long id;
    private String clientId;
    private int agentId;
    private String content;
    private LocalDateTime sendDate;
    private String sentBy;
    private ConversationVO conv;
}
