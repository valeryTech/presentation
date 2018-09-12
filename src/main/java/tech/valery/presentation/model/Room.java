package tech.valery.presentation.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import tech.valery.presentation.rest.JacksonCustomRoomDeserializer;
import tech.valery.presentation.rest.JacksonCustomRoomSerializer;

import javax.persistence.*;

@Entity
@Table(name = "room", uniqueConstraints = @UniqueConstraint(columnNames = "roomnumber"))
@JsonSerialize(using = JacksonCustomRoomSerializer.class)
@JsonDeserialize(using = JacksonCustomRoomDeserializer.class)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private int roomnumber;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getNumber() {
        return roomnumber;
    }

    public void setNumber(int number) {
        this.roomnumber = number;
    }

    @Override
    public String toString() {
        return String.valueOf(roomnumber);
    }
}
