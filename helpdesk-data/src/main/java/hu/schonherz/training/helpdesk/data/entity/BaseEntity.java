package hu.schonherz.training.helpdesk.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
