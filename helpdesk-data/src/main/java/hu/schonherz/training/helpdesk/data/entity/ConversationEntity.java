package hu.schonherz.training.helpdesk.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "conversation", schema = "public")
public class ConversationEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private int agentId;

    private String clientId;

    private boolean closed;

    @OneToMany(mappedBy = "conv")
    private List<MessageEntity> messages;

}
