package hu.schonherz.training.helpdesk.service.api.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

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
