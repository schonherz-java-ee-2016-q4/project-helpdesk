package hu.schonherz.training.helpdesk.service.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ClientActivityVO {
    //Ignored some of the getters and setters from lombok code generation,
    //because Jackson couldn't ignore some fields from deserialization otherwise.
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private long id;

    private String clientId;
    private ActivityTypeVO type;
    private String target;

    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(final long id) {
        this.id = id;
    }

    @JsonProperty
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonIgnore
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
