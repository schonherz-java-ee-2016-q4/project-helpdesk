package hu.schonherz.training.helpdesk.data.entity;

import hu.schonherz.training.helpdesk.data.DatabaseConstants;
import hu.schonherz.training.helpdesk.data.enums.ActivityType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client_activity", schema = DatabaseConstants.SCHEMA_NAME)
public class ClientActivityEntity extends BaseEntity {

    private String clientId;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Column(columnDefinition = "TEXT")
    private String target;

    private LocalDateTime createdAt;

}
