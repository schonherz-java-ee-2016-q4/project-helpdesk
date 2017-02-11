package hu.schonherz.training.helpdesk.web.mock;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Agent {
    private String name;
    private String gender;
    private String company;
    private String phone;
    private String picture;
}
