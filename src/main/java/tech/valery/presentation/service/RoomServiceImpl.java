package tech.valery.presentation.service;

import org.springframework.beans.factory.annotation.Autowired;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.repository.RoomRepository;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public void deleteAll() {
        roomRepository.deleteAll();
    }
}
