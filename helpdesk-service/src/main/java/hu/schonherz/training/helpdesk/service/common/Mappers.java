package hu.schonherz.training.helpdesk.service.common;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

public final class Mappers {

    private Mappers() {
    }

    public static DozerBeanMapper createDozerBeanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");

        dozerBeanMapper.setMappingFiles(mappingFiles);

        return dozerBeanMapper;
    }
}
