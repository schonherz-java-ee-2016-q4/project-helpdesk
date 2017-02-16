package hu.schonherz.training.helpdesk.data.entity;

import hu.schonherz.training.helpdesk.data.enums.ConversationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "conversation", schema = "public")
public class ConversationEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private long agentId;

    private String clientId;

    private String clientEmail;

    @Enumerated(EnumType.STRING)
    private ConversationType type;
}
