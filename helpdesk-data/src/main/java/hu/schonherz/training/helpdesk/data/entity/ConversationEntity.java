package hu.schonherz.training.helpdesk.data.entity;

import hu.schonherz.training.helpdesk.data.DatabaseConstants;
import hu.schonherz.training.helpdesk.data.enums.ConversationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "conversation", schema = DatabaseConstants.SCHEMA_NAME)
public class ConversationEntity extends BaseEntity {
    private static final long serialVersionUID = 13243545L;

    private Long agentId;

    private String clientId;

    private String clientEmail;

    @Enumerated(EnumType.STRING)
    private ConversationStatus status;

    private LocalDateTime createdAt;
}
