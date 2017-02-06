package hu.schonherz.training.helpdesk.web.mock;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MockedAdminStuff {

    public Agent findByName(final String username) {
        if ("admin".equals(username)) {
            return createDummyUser();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public Agent createDummyUser() {
        Agent dummy = new Agent();
        dummy.setName("Bruce Wayne");
        dummy.setEmail("totalynotbatman@gmail.com");
        dummy.setGender("male");
        dummy.setCompany("Wayne Enterprises, Inc");
        dummy.setPhone("+36-30-1112367");
        dummy.setPicture("https://pbs.twimg.com/profile_images/649259478332784640/7Pjcfx_v_reasonably_small.jpg");
        dummy.setUsername("admin");
        dummy.setPassword("admin");
        dummy.setRoles("ROLE_USER");
        return dummy;
    }
}
