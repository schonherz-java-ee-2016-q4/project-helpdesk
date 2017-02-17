package hu.schonherz.training.helpdesk.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "conversation", schema = "public")
public class ConversationEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private int agentId;

    private String clientId;

    private String clientEmail;

    private boolean closed;

    private LocalDateTime begindate;
}
