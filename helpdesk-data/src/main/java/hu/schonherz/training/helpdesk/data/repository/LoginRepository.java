package hu.schonherz.training.helpdesk.data.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.schonherz.training.helpdesk.data.entity.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    List<LoginEntity> findById(Long id);
    List<LoginEntity> findByAgentId(int agentId);
    List<LoginEntity> findByLoginDate(LocalDateTime loginDate);
}
