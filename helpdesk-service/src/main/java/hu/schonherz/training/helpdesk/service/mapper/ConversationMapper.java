package hu.schonherz.training.helpdesk.service.mapper;


import hu.schonherz.training.helpdesk.data.entity.ConversationEntity;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ConversationMapper {

    private static Mapper mapper = new DozerBeanMapper();
    public ConversationMapper(){}

    public static ConversationVO toVO(final ConversationEntity conversation) {
        if (conversation == null) {
            return null;
        }
        return mapper.map(conversation, ConversationVO.class);
    }

    public static ConversationEntity toEntity(final ConversationVO conversationVO) {
        if (conversationVO == null) {
            return null;
        }
        return mapper.map(conversationVO, ConversationEntity.class);
    }

    public static List<ConversationVO> toVO(final Collection<ConversationEntity> conversations) {
        List<ConversationVO> rv = new ArrayList<>();
        for (ConversationEntity conversation : conversations) {
            rv.add(toVO(conversation));
        }
        return rv;
    }

    public static List<ConversationEntity> toEntity(final Collection<ConversationVO> conversationVOs) {
        List<ConversationEntity> rv = new ArrayList<>();
        for (ConversationVO conversationVO : conversationVOs) {
            rv.add(toEntity(conversationVO));
        }
        return rv;
    }
}
