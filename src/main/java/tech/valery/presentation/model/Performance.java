package tech.valery.presentation.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "performance")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date perftime;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;



//    // bidirectional or unidir-l
    @ManyToOne
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;


    public Performance() {
    }

    public Performance(Date perftime, Room room) {
        this.perftime = perftime;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPerftime() {
        return perftime;
    }

    public void setPerftime(Date perftime) {
        this.perftime = perftime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setPresentation(Presentation presentation){
        this.presentation = presentation;
    }
//
//    public Presentation getPresentation() {
//        return presentation;
//    }
//
//    public void setPresentation(Presentation presentation) {
//        this.presentation = presentation;
//    }
}
