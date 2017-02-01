package hu.schonherz.training.helpdesk.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "login", schema = "public")
public class LoginEntity extends BaseEntity {

    private static final long serialVersionUID = -6201828861327999500L;
    private int agentId;
    private LocalDateTime loginDate;

}
