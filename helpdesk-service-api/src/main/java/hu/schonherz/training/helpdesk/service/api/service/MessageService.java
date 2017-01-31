package hu.schonherz.training.helpdesk.service.api.service;


import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;

public interface MessageService {
    Long save(MessageVO message);
}
