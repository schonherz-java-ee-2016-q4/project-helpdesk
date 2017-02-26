package hu.schonherz.training.helpdesk.data.repository;

import hu.schonherz.training.helpdesk.data.entity.ClientActivityEntity;
import hu.schonherz.training.helpdesk.data.enums.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClientActivityRepository extends JpaRepository<ClientActivityEntity, Long> {
    ClientActivityEntity findById(Long id);
    List<ClientActivityEntity> findByClientIdOrderByCreatedAtDesc(String clientId);
    List<ClientActivityEntity> findByTargetContainingOrderByCreatedAtDesc(String searchString);
    List<ClientActivityEntity> findByTypeOrderByCreatedAtDesc(ActivityType activityType);
    List<ClientActivityEntity> findAllByOrderByCreatedAtDesc();
    List<ClientActivityEntity> findByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime from, LocalDateTime to);
    List<ClientActivityEntity> findByCreatedAtBetweenAndTypeOrderByCreatedAtDesc(LocalDateTime from,
                                                                                 LocalDateTime to, ActivityType activityType);

    default List<ClientActivityEntity> findByDateRangeAndActivityType(LocalDateTime from,
                                                                      LocalDateTime to, ActivityType activityType) {
        return findByCreatedAtBetweenAndTypeOrderByCreatedAtDesc(from, to, activityType);
    }
}
