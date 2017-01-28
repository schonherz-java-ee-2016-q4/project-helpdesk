package hu.schonherz.training.helpdesk.service.api.service;

import java.util.Collection;

import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;

public interface ClientActivityService {
    Collection<ClientActivityVO> findAll();
    Collection<ClientActivityVO> finById(Long id);
    long save(ClientActivityVO activity);
}
