package hu.schonherz.training.helpdesk.service.impl;

import hu.schonherz.training.helpdesk.data.repository.ClientActivityRepository;
import hu.schonherz.training.helpdesk.service.api.service.ClientActivityService;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;
import hu.schonherz.training.helpdesk.service.mapper.ClientActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.Collection;

@Stateless(mappedName = "ClientActivityService")
@Local(ClientActivityService.class)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ClientActivityBean implements ClientActivityService {

    @Autowired
    private ClientActivityRepository clientActivityRepository;

    @Override
    public Collection<ClientActivityVO> findAll() {
        return ClientActivityMapper.toVO(clientActivityRepository.findAll());
    }

    @Override
    public ClientActivityVO findById(final Long id) {
        return ClientActivityMapper.toVO(clientActivityRepository.findById(id));
    }

    @Override
    public long save(final ClientActivityVO activity) {
        return clientActivityRepository.save(ClientActivityMapper.toEntity(activity)).getId();
    }

}
