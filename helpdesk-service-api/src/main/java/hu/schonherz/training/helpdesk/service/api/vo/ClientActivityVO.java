package hu.schonherz.training.helpdesk.service.api.vo;

import java.time.LocalDateTime;

import hu.schonherz.training.helpdesk.data.additional.ActivityType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ClientActivityVO {
    private long id;
    private int clientId;
    private ActivityType type;
    private String target;
    private LocalDateTime createdAt;
    }


