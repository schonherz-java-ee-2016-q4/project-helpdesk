package hu.schonherz.training.helpdesk.service.mapper;

import hu.schonherz.training.helpdesk.data.entity.ClientActivityEntity;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;
import hu.schonherz.training.helpdesk.service.common.Mappers;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

public final class ClientActivityMapper {
    private static DozerBeanMapper dozerBeanMapper = Mappers.createDozerBeanMapper();

    private ClientActivityMapper() {}


    public static ClientActivityVO toVO(final ClientActivityEntity activity) {
        if (activity == null) {
            return null;
        }
        return dozerBeanMapper.map(activity, ClientActivityVO.class);
    }

    public static ClientActivityEntity toEntity(final ClientActivityVO activityVO) {
        if (activityVO == null) {
            return null;
        }
        return dozerBeanMapper.map(activityVO, ClientActivityEntity.class);
    }

    public static List<ClientActivityVO> toVO(final List<ClientActivityEntity> activities) {
        List<ClientActivityVO> rv = new ArrayList<>();
        for (ClientActivityEntity activity : activities) {
            rv.add(toVO(activity));
        }
        return rv;
    }

    public static List<ClientActivityEntity> toEntity(final List<ClientActivityVO> activitiesVO) {
        List<ClientActivityEntity> rv = new ArrayList<>();
        for (ClientActivityVO activityVO : activitiesVO) {
            rv.add(toEntity(activityVO));
        }
        return rv;
    }

}
