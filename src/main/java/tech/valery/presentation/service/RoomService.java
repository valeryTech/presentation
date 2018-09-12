package tech.valery.presentation.service;

import tech.valery.presentation.model.Room;

import java.util.List;

public interface RoomService {

    List<Room> findAll();

    Room save(Room room);

    Room findById(Long id);

    void deleteById(Long id);

    void delete(Room entity);

    void deleteAll();
}
