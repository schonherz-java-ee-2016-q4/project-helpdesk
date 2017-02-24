package hu.schonherz.training.helpdesk.service.common;

import hu.schonherz.training.helpdesk.data.enums.ActivityType;
import hu.schonherz.training.helpdesk.service.api.vo.ActivityTypeVO;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;


public final class Mappers {

    private static DozerBeanMapper dozerBeanMapper;


    static {
        dozerBeanMapper = new DozerBeanMapper();
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozerJdk8Converters.xml");

        dozerBeanMapper.setMappingFiles(mappingFiles);
    }

    private Mappers() {
    }

    public static DozerBeanMapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }

    public static class ActivityTypeConverter {
        public static ActivityTypeVO toVO(final ActivityType source) {
            switch (source) {
                case BUTTON_CLICK:
                    return ActivityTypeVO.BUTTON_CLICK;
                case NAVIGATION:
                    return ActivityTypeVO.NAVIGATION;
                case INPUT_FOCUSLOSS:
                    return ActivityTypeVO.INPUT_FOCUSLOSS;
                case FORM_SUBMIT:
                    return ActivityTypeVO.FORM_SUBMIT;
                default:
                    return null;
            }
        }


        public static ActivityType toEntity(final ActivityTypeVO source) {
            switch (source) {
                case BUTTON_CLICK:
                    return ActivityType.BUTTON_CLICK;
                case NAVIGATION:
                    return ActivityType.NAVIGATION;
                case INPUT_FOCUSLOSS:
                    return ActivityType.INPUT_FOCUSLOSS;
                case FORM_SUBMIT:
                    return ActivityType.FORM_SUBMIT;
                default:
                    return null;
            }
        }
    }
}
