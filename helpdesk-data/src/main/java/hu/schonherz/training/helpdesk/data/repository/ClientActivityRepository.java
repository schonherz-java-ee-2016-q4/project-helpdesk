package hu.schonherz.training.helpdesk.data.repository;

import hu.schonherz.training.helpdesk.data.entity.ClientActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientActivityRepository extends JpaRepository<ClientActivityEntity, Long> {
    ClientActivityEntity findById(Long id);

}
