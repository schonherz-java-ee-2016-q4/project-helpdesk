package hu.schonherz.training.helpdesk.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message", schema = "public")
@Data
@NoArgsConstructor
public class MessageEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private int agentId;

    private String clientId;

    private String content;

    private LocalDateTime sendDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conversation_id")
    private ConversationEntity conv;

}
