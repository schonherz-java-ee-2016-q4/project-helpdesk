package hu.schonherz.training.helpdesk.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.training.helpdesk.data.repository.LoginRepository;
import hu.schonherz.training.helpdesk.service.api.service.LoginService;
import hu.schonherz.training.helpdesk.service.api.vo.LoginVO;
import hu.schonherz.training.helpdesk.service.mapper.LoginMapper;

@Stateless(mappedName = "LoginService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class LoginBean implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Collection<LoginVO> findAll() {
        return LoginMapper.toVO(loginRepository.findAll());
    }

    @Override
    public LoginVO findById(final Long id) {
        return LoginMapper.toVO(loginRepository.findById(id));
    }

    @Override
    public Collection<LoginVO> findByAgentId(final int agentId) {
        return LoginMapper.toVO(loginRepository.findByAgentId(agentId));
    }

    @Override
    public Collection<LoginVO> findByDate(final LocalDateTime loginDate) {
        return LoginMapper.toVO(loginRepository.findByLoginDate(loginDate));
    }

    @Override
    public Long save(final LoginVO login) {
        return loginRepository.save(LoginMapper.toEntity(login)).getId();
    }
}
