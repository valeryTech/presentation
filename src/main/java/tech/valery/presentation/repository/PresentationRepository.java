package tech.valery.presentation.repository;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.model.User;

import java.util.Date;
import java.util.List;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {

    @Override
    List<Presentation> findAll();

    @Override
    <S extends Presentation> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Presentation entity);

    List<Presentation> findAllByUsers(User user);

    List<Presentation> findByUsers_Id(Long id);

    @Query("SELECT rm FROM Room rm")
    List<Room> getRooms();

    //@Query("select p from Presentation p where p.room.id=:roomId order by p.perftime")
    @Query(value = "select * from presentation p where p.room_id = :roomId", nativeQuery = true)
    List<Presentation> findAllInRoom(@Param("roomId")Long roomId);

    @Query(value = "SELECT * FROM PRESENTATION p WHERE (p.perftime < DATEADD('HOUR', 1, :perftime)) AND (:perftime < DATEADD('HOUR', 1, p.perftime))  AND p.roomid = :roomid",
            nativeQuery = true)
    List<Presentation> findAllIntersected(@Param("perftime")Date perftime, @Param("roomid") Long roomid);

    @Query(value = "SELECT COUNT(*) FROM PRESENTATION p WHERE (p.perftime < DATEADD('HOUR', 1, :perftime)) AND (:perftime < DATEADD('HOUR', 1, p.perftime)) AND p.roomid = :roomid",
            nativeQuery = true)
    Integer countAllIntersected(@Param("perftime")Date perftime, @Param("roomid") Long roomid);
}
