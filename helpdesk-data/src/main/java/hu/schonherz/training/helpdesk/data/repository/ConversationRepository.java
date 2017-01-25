package hu.schonherz.training.helpdesk.data.repository;

import hu.schonherz.training.helpdesk.data.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ConversationRepository extends JpaRepository<ConversationEntity, Integer> {

}
