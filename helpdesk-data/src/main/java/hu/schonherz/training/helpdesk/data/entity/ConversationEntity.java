package hu.schonherz.training.helpdesk.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "conversation", schema = "public")
public class ConversationEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private int clientId;

    private int agentId;

    private boolean closed;

}
