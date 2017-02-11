package hu.schonherz.training.helpdesk.web.mock;

import lombok.Getter;

public final class MockedAdminStuff {
    @Getter
    private static Agent dummy;

    static {
        dummy = new Agent();
        dummy.setName("Bruce Wayne");
        dummy.setGender("male");
        dummy.setCompany("Wayne Enterprises, Inc");
        dummy.setPhone("+36-30-1112367");
        dummy.setPicture("https://pbs.twimg.com/profile_images/649259478332784640/7Pjcfx_v_reasonably_small.jpg");
    }

    private MockedAdminStuff() {
    }

}
