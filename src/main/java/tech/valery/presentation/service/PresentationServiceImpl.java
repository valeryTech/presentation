package tech.valery.presentation.service;

import org.springframework.beans.factory.annotation.Autowired;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.model.User;
import tech.valery.presentation.repository.PresentationRepository;

import java.util.List;

public class PresentationServiceImpl implements PresentationService {

    @Autowired
    PresentationRepository presentationRepository;

    @Override
    public void save(Presentation presentation) {
        presentationRepository.save(presentation);
    }

    @Override
    public List<Presentation> findAll() {
        return presentationRepository.findAll();
    }

    @Override
    public Presentation findOne(Long id) {
        return presentationRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        presentationRepository.deleteById(id);
    }

    @Override
    public List<Presentation> findAllByUsers(User user) {
        return presentationRepository.findAllByUsers(user);
    }

    @Override
    public List<Presentation> findAllByRoomId(Long roomId) {
        return presentationRepository.findAllInRoom(roomId);
    }

    @Override
    public List<Presentation> findAllIntersected(Presentation presentation) {
        return presentationRepository.findAllIntersected(presentation.getPerftime(), presentation.getRoom().getId());
    }

    @Override
    public Integer countAllIntersected(Presentation presentation) {
        return presentationRepository.countAllIntersected(presentation.getPerftime(), presentation.getRoom().getId());
    }
}
