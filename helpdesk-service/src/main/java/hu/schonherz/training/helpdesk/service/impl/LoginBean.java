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
    public Collection<LoginVO> findById(Long Id) {
        return LoginMapper.toVO(loginRepository.findById(Id));
    }

    @Override
    public Collection<LoginVO> findByAgentId(int agentId) {
        return LoginMapper.toVO(loginRepository.findByAgentId(agentId));
    }

    @Override
    public Collection<LoginVO> findByDate(LocalDateTime loginDate) {
        return LoginMapper.toVO(loginRepository.findByLoginDate(loginDate));
    }

    @Override
    public Long save(LoginVO login) {
        return loginRepository.save(LoginMapper.toEntity(login)).getId();
    }
}
