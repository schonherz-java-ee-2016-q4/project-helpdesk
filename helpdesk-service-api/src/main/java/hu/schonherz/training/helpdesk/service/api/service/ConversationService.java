package hu.schonherz.training.helpdesk.service.api.service;

import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;

import java.util.Collection;

public interface ConversationService {

    long save(ConversationVO conversation);

    ConversationVO findById(Long id);

    Collection<ConversationVO> findNotClosedConversations(Long agentId);
}
