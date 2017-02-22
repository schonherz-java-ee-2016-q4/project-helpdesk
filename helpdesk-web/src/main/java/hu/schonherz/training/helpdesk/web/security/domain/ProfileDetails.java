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
    private Long id;
    private String name;
    private String email;
    private Gender gender;
    private String company;
    private String phone;
    private String picture;


}
