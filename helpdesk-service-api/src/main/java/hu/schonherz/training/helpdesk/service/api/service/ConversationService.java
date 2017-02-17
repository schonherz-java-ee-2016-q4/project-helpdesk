package hu.schonherz.training.helpdesk.service.api.service;

import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;

public interface ConversationService {

    long save(ConversationVO conversation);

    ConversationVO findById(Long id);

    ConversationVO findNotClosedConversation(Long agentId);
}
