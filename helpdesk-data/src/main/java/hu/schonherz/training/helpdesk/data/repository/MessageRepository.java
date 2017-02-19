package hu.schonherz.training.helpdesk.data.repository;

import hu.schonherz.training.helpdesk.data.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query("Select m from MessageEntity m where (m.agentId=:agent and m.clientId=:client)")
    Collection<MessageEntity> findMessages(@Param("agent") Long agentId, @Param("client") String clientId);
}
