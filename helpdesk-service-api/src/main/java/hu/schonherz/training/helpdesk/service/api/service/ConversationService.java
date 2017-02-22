package hu.schonherz.training.helpdesk.service.api.service;

import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;

import java.util.List;

public interface ConversationService {
    long save(ConversationVO conversation);
    ConversationVO findById(Long id);
    ConversationVO findNotClosedConversation(Long agentId);
    List<ConversationVO> findByAgentId(Long agentId);
}
