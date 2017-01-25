package hu.schonherz.training.helpdesk.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.schonherz.training.helpdesk.data.entity.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

}
