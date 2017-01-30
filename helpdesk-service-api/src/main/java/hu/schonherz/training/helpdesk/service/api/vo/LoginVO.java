package hu.schonherz.training.helpdesk.service.api.vo;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginVO {
    private int agentId;
    private LocalDateTime loginDate;
}
