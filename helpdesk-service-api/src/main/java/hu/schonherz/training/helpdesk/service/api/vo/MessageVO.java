package hu.schonherz.training.helpdesk.service.api.vo;

import hu.schonherz.training.helpdesk.data.entity.ConversationEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageVO {
    private long id;

    private String content;

    private LocalDateTime sendDate;

    private ConversationEntity conversation;
}
