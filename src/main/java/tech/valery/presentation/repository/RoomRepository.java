package tech.valery.presentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.valery.presentation.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Override
    List<Room> findAll();

    @Override
    <S extends Room> S save(S entity);

    @Override
    Optional<Room> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Room entity);

    @Override
    void deleteAll();
}
