package hu.schonherz.training.helpdesk.data.repository;

import hu.schonherz.training.helpdesk.data.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {
    ConversationEntity findById(Long id);

    Collection<ConversationEntity> findByAgentIdAndClientId(int agentId, int clientId);
}
