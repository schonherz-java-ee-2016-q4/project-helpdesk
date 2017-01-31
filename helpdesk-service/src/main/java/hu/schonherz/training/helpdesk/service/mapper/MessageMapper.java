package hu.schonherz.training.helpdesk.service.mapper;


import hu.schonherz.training.helpdesk.data.entity.MessageEntity;
import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class MessageMapper {
    private static Mapper mapper = new DozerBeanMapper();

    public MessageMapper(){}

    public static MessageVO toVo(final MessageEntity message){
        if(message == null){
            return null;
        }
        return mapper.map(message,MessageVO.class);
    }

    public static MessageEntity toEntity(final MessageVO messageVO){
        if(messageVO == null){
            return null;
        }
        return mapper.map(messageVO,MessageEntity.class);
    }

    public static List<MessageVO> toVo(final Collection<MessageEntity> messages){
        List<MessageVO> rv = new ArrayList<>();

        for(MessageEntity message:messages){
            rv.add(toVo(message));
        }
        return rv;
    }

    public static List<MessageEntity> toEntity(final Collection<MessageVO> messageVOs){
        List<MessageEntity> rv = new ArrayList<>();

        for(MessageVO messageVO:messageVOs){
            rv.add(toEntity(messageVO));
        }
        return rv;
    }
}
