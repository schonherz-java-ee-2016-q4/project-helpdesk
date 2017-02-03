package hu.schonherz.training.helpdesk.service.mapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;

import hu.schonherz.training.helpdesk.data.entity.MessageEntity;
import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;
import hu.schonherz.training.helpdesk.service.common.Mappers;

public final class MessageMapper {
    private static DozerBeanMapper mapper = Mappers.getDozerBeanMapper();

    private MessageMapper() {
    }

    public static MessageVO toVo(final MessageEntity message) {
        if (message == null) {
            return null;
        }
        return mapper.map(message, MessageVO.class);
    }

    public static MessageEntity toEntity(final MessageVO messageVO) {
        if (messageVO == null) {
            return null;
        }
        return mapper.map(messageVO, MessageEntity.class);
    }

    public static List<MessageVO> toVo(final Collection<MessageEntity> messages) {
        List<MessageVO> rv = new ArrayList<>();

        for (MessageEntity message : messages) {
            rv.add(toVo(message));
        }
        return rv;
    }

    public static List<MessageEntity> toEntity(final Collection<MessageVO> messageVOs) {
        List<MessageEntity> rv = new ArrayList<>();

        for (MessageVO messageVO : messageVOs) {
            rv.add(toEntity(messageVO));
        }
        return rv;
    }
}
