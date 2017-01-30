package hu.schonherz.training.helpdesk.service.api.service;

import java.time.LocalDateTime;
import java.util.Collection;
import hu.schonherz.training.helpdesk.service.api.vo.LoginVO;

public interface LoginService {
    Collection<LoginVO> findAll();
    LoginVO findById(Long id);
    Collection<LoginVO> findByAgentId(int agentId);
    Collection<LoginVO> findByDate(LocalDateTime loginDate);
    Long save(LoginVO login);
}
