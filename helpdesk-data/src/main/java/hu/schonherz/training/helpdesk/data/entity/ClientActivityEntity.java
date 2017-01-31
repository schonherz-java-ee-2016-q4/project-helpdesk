package hu.schonherz.training.helpdesk.data.entity;

import hu.schonherz.training.helpdesk.data.additional.ActivityType;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client_activity", schema = "public")
@Data
@NoArgsConstructor
public class ClientActivityEntity extends BaseEntity {

    private String clientId;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Lob
    private String target;

    private LocalDateTime createdAt;

}
