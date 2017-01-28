package hu.schonherz.training.helpdesk.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.schonherz.training.helpdesk.data.entity.ClientActivityEntity;

@Repository
public interface ClientActivityRepository extends JpaRepository<ClientActivityEntity, Long> {
    List<ClientActivityEntity> findById(Long id);

}
