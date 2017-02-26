package hu.schonherz.training.helpdesk.service.api.service;

import hu.schonherz.training.helpdesk.service.api.vo.ActivityTypeVO;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface ClientActivityService {
    Collection<ClientActivityVO> findAll();
    ClientActivityVO findById(Long id);
    long save(ClientActivityVO activity);
    Collection<ClientActivityVO> findByClientIdOrderByCreatedAtDesc(String clientId);
    Collection<ClientActivityVO> findByTargetContainingOrderByCreatedAtDesc(String searchString);

    List<ClientActivityVO> findByTypeOrderByCreatedAtDesc(ActivityTypeVO activityType);
    Collection<ClientActivityVO> findAllByOrderByCreatedAtDesc();
    Collection<ClientActivityVO> findByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime from, LocalDateTime to);
    Collection<ClientActivityVO> findByDateRangeAndActivityType(LocalDateTime from,
                                                                LocalDateTime to, ActivityTypeVO activityType);
}
