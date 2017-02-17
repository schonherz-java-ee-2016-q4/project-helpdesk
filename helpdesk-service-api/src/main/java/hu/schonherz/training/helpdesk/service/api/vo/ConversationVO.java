package hu.schonherz.training.helpdesk.service.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversationVO {
    private long id;
    private String clientId;

    private String clientEmail;

    private int agentId;

    private boolean closed;

    private LocalDateTime begindate;
}
