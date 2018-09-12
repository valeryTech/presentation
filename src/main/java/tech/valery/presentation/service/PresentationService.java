package tech.valery.presentation.service;

import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.model.User;

import java.util.List;

public interface PresentationService {
    void save(Presentation presentation);

    List<Presentation> findAll();

    Presentation findOne(Long id);

    void delete(Long id);

    List<Presentation> findAllByUsers(User user);

    List<Presentation> findAllByRoomId(Long roomId);

    List<Presentation> findAllIntersected(Presentation presentation);

    Integer countAllIntersected(Presentation presentation);
}
