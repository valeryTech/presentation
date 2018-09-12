package tech.valery.presentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.valery.presentation.model.Performance;
import tech.valery.presentation.model.Room;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    @Override
    List<Performance> findAll();

    @Override
    <S extends Performance> S save(S entity);

    @Override
    void deleteById(Long aLong);

    List<Performance> findByPresentationId(Long presentationId);

    @Query("SELECT rm FROM Room rm")
    List<Room> getRooms();
}
