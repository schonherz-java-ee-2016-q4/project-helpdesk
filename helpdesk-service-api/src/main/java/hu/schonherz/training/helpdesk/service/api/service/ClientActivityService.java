package hu.schonherz.training.helpdesk.service.api.service;

import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;

import java.util.Collection;

public interface ClientActivityService {
    Collection<ClientActivityVO> findAll();
    ClientActivityVO findById(Long id);
    long save(ClientActivityVO activity);
}
