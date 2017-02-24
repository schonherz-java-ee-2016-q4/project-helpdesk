package hu.schonherz.training.helpdesk.service.mapper;

import hu.schonherz.training.helpdesk.data.enums.ActivityType;
import hu.schonherz.training.helpdesk.service.api.vo.ActivityTypeVO;
import hu.schonherz.training.helpdesk.service.common.Mappers;

public final class ActivityTypeMapper {

    private ActivityTypeMapper() {}

    public static ActivityType toEntity(final ActivityTypeVO activityTypeVO) {
        return Mappers.ActivityTypeConverter.toEntity(activityTypeVO);
    }

    public static ActivityTypeVO toVO(final ActivityType activityType) {
        return Mappers.ActivityTypeConverter.toVO(activityType);
    }
}
