package hu.schonherz.training.helpdesk.service.api.service;


import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;

import java.util.Collection;

public interface MessageService {
    Long save(MessageVO message);

    Collection<MessageVO> findMessages(int agentId, String clientId);
}
