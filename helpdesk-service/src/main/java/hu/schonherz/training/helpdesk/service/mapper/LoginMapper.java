package hu.schonherz.training.helpdesk.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

import hu.schonherz.training.helpdesk.data.entity.LoginEntity;
import hu.schonherz.training.helpdesk.service.api.vo.LoginVO;
import hu.schonherz.training.helpdesk.service.common.Mappers;

public final class LoginMapper {

    private static DozerBeanMapper mapper = Mappers.getDozerBeanMapper();

    private LoginMapper(){

    };

    public static LoginVO toVO(final LoginEntity login) {
        if (login == null) {
            return null;
        }
        return mapper.map(login, LoginVO.class);
    }

    public static LoginEntity toEntity(final LoginVO loginVO) {
        if (loginVO == null) {
            return null;
        }
        return mapper.map(loginVO, LoginEntity.class);
    }

    public static List<LoginVO> toVO(final List<LoginEntity> logins) {
        List<LoginVO> rv = new ArrayList<>();
        for (LoginEntity login : logins) {
            rv.add(toVO(login));
        }
        return rv;
    }

    public static List<LoginEntity> toEntity(final List<LoginVO> loginsVO) {
        List<LoginEntity> rv = new ArrayList<>();
        for (LoginVO loginVO : loginsVO) {
            rv.add(toEntity(loginVO));
        }
        return rv;
    }
}
