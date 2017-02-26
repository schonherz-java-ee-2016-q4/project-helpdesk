package hu.schonherz.training.helpdesk.service.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationVO {
    private long id;
    private Long agentId;
    private String clientId;
    private String clientEmail;
    private ConversationStatusVO status;
    private LocalDateTime createdAt;
    private String sourceURL;
}
