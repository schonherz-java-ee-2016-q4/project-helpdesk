package hu.schonherz.training.helpdesk.service.impl;

import hu.schonherz.training.helpdesk.data.enums.ActivityType;
import hu.schonherz.training.helpdesk.data.repository.ClientActivityRepository;
import hu.schonherz.training.helpdesk.service.api.service.ClientActivityService;
import hu.schonherz.training.helpdesk.service.api.vo.ActivityTypeVO;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;
import hu.schonherz.training.helpdesk.service.mapper.ActivityTypeMapper;
import hu.schonherz.training.helpdesk.service.mapper.ClientActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

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

    @Override
    public Collection<ClientActivityVO> findByClientIdOrderByCreatedAtDesc(final String clientId) {
        return  ClientActivityMapper.toVO(clientActivityRepository.findByClientIdOrderByCreatedAtDesc(clientId));
    }

    @Override
    public Collection<ClientActivityVO> findByTargetContainingOrderByCreatedAtDesc(final String searchString) {
        return ClientActivityMapper.toVO(clientActivityRepository.findByTargetContainingOrderByCreatedAtDesc(searchString));
    }

    @Override
    public List<ClientActivityVO> findByTypeOrderByCreatedAtDesc(final ActivityTypeVO activityType) {
        ActivityType dataActivityType = ActivityTypeMapper.toEntity(activityType);
        return ClientActivityMapper.toVO(clientActivityRepository.findByTypeOrderByCreatedAtDesc(dataActivityType));
    }

    @Override
    public Collection<ClientActivityVO> findAllByOrderByCreatedAtDesc() {
        return ClientActivityMapper.toVO(clientActivityRepository.findAllByOrderByCreatedAtDesc());
    }

    @Override
    public Collection<ClientActivityVO> findByCreatedAtBetweenOrderByCreatedAtDesc(final LocalDateTime from, final LocalDateTime to) {
        return ClientActivityMapper.toVO(clientActivityRepository.findByCreatedAtBetweenOrderByCreatedAtDesc(from, to));
    }

    @Override
    public Collection<ClientActivityVO> findByDateRangeAndActivityType(final LocalDateTime from, final LocalDateTime to, final ActivityTypeVO activityType) {
        ActivityType dataActivityType = ActivityTypeMapper.toEntity(activityType);
        return ClientActivityMapper.toVO(clientActivityRepository.findByDateRangeAndActivityType(from, to, dataActivityType));
    }
}
