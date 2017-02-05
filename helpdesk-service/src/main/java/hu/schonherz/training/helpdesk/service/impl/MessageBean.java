package hu.schonherz.training.helpdesk.service.impl;


import hu.schonherz.training.helpdesk.data.repository.MessageRepository;
import hu.schonherz.training.helpdesk.service.api.service.MessageService;
import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;
import hu.schonherz.training.helpdesk.service.mapper.ConversationMapper;
import hu.schonherz.training.helpdesk.service.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.Collection;

@Stateless(mappedName = "MessageService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class MessageBean implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Long save(final MessageVO message) {
        return messageRepository.save(MessageMapper.toEntity(message)).getId();
    }

    @Override
    public Collection<MessageVO> findMessages(int agentId, String clientId) {
        return MessageMapper.toVo(messageRepository.findMessages(agentId, clientId));
    }
}
