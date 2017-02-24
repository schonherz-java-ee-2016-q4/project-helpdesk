package hu.schonherz.training.helpdesk.service.impl;


import hu.schonherz.training.helpdesk.data.repository.ConversationRepository;
import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.service.mapper.ConversationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless(mappedName = "ConversationService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ConversationBean implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    //It is needed to create new connection because it can't use the Admin team provided
    //at the helpdesk.web.rest.api.AgentAPI.getAvailableAgent method. That's why, this method
    //is annotated like this.
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public long save(final ConversationVO conversation) {
        return conversationRepository.save(ConversationMapper.toEntity(conversation)).getId();
    }

    @Override
    public ConversationVO findById(final Long id) {
        return ConversationMapper.toVO(conversationRepository.findById(id));
    }

    @Override
    public ConversationVO findNotClosedConversation(final Long agentId) {
        return ConversationMapper.toVO(conversationRepository.findNotClosedConversation(agentId));
    }

    @Override
    public List<ConversationVO> findByAgentId(final Long agentId) {
        return ConversationMapper.toVO(conversationRepository.findByAgentId(agentId));
    }

}
