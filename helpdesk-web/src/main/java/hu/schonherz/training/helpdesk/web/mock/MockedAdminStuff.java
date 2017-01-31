package hu.schonherz.training.helpdesk.web.mock;

import lombok.Data;

@Data
public class MockedAdminStuff {
    private MockedUser user;

    public MockedUser findByName(final String username) {
        if ("admin".equals(username)) {
            user.setUsername("admin");
            user.setPassword("admin");
            user.setRoles(new String[] { "USER", "ADMIN" });
            return user;
        }
        return null;
    }

}
