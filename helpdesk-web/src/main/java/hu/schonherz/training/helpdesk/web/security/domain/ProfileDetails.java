package hu.schonherz.training.helpdesk.web.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDetails {
    private long id;
    private String name;
    private String email;
    private String gender;
    private String company;
    private String phone;
    private String picture;
}
