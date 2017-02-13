package hu.schonherz.training.helpdesk.web.config.spring.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProfileDetails {
    private String name;
    private String email;
    private String gender;
    private String company;
    private String phone;
    private String picture;
}
