package hu.schonherz.training.helpdesk.data.entity;

import hu.schonherz.training.helpdesk.data.additional.ActivityType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_activity", schema = "public")
@Data
@NoArgsConstructor
public class ClientActivityEntity extends BaseEntity {

    private String clientId;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Column(columnDefinition = "TEXT")
    private String target;

    private LocalDateTime createdAt;

}
