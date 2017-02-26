package hu.schonherz.training.helpdesk.data.entity;

import hu.schonherz.training.helpdesk.data.DatabaseConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "message", schema = DatabaseConstants.SCHEMA_NAME)
public class MessageEntity extends BaseEntity {
    private static final long serialVersionUID = 194867738L;

    private Long agentId;

    private String clientId;

    private String sentBy;

    private LocalDateTime sendDate;

    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conversation_id")
    private ConversationEntity conversation;

}
