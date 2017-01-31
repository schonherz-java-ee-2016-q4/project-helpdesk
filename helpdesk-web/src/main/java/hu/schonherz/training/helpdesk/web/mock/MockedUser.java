package hu.schonherz.training.helpdesk.web.mock;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MockedUser {
    private String username;
    private String password;
    private String[] roles;
}
