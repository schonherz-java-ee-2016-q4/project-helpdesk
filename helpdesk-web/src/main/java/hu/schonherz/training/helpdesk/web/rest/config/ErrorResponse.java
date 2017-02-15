package hu.schonherz.training.helpdesk.web.rest.config;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = -820362654604115689L;
    private int status;
    private String exception;

}
