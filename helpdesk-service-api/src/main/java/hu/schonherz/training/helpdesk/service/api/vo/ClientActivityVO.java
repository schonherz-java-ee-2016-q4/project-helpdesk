package hu.schonherz.training.helpdesk.service.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ClientActivityVO {
    @JsonIgnore
    private long id;
    private String clientId;
    private ActivityTypeVO type;
    private String target;
    @JsonIgnore
    private LocalDateTime createdAt;
}
