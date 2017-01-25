package hu.schonherz.training.helpdesk.data.entity;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TestEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String content;
}
