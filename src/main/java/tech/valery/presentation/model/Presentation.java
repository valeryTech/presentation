package tech.valery.presentation.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import tech.valery.presentation.rest.JacksonCustomPresentationDeserializer;
import tech.valery.presentation.rest.JacksonCustomPresentationSerializer;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "presentation")
@JsonSerialize(using = JacksonCustomPresentationSerializer.class)
@JsonDeserialize(using = JacksonCustomPresentationDeserializer.class)
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(mappedBy = "presentations", fetch = FetchType.EAGER)
    Set<User> users = new HashSet<>();

    private String topic;

//    @OneToMany(mappedBy = "presentation", fetch = FetchType.EAGER)
//    protected Set<Performance> performances = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date perftime;

    public Presentation() {
    }

    public Presentation(String topic) {
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Set<User> getUsers(){
        return users;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getPerftime() {
        return perftime;
    }

    public void setPerftime(Date perftime) {
        this.perftime = perftime;
    }

    @Override
    public String toString() {
        return "Presentation{" +
                "id=" + id +
                //", users=" + users +
                ", topic='" + topic + '\'' +
                '}';
    }

    public void setId(Long presentationId) {
        this.id = presentationId;
    }
}
