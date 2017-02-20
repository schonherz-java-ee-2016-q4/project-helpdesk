package hu.schonherz.training.helpdesk.service.impl;


import hu.schonherz.training.helpdesk.data.repository.ConversationRepository;
import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.service.mapper.ConversationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

@Stateless(mappedName = "ConversationService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ConversationBean implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public long save(final ConversationVO conversation) {
        return conversationRepository.save(ConversationMapper.toEntity(conversation)).getId();
    }

    @Override
    public ConversationVO findById(final Long id) {
        return ConversationMapper.toVO(conversationRepository.findById(id));
    }
}
