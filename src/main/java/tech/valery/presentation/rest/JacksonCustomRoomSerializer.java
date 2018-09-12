package tech.valery.presentation.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import tech.valery.presentation.model.Room;

import java.io.IOException;

public class JacksonCustomRoomSerializer extends StdSerializer<Room> {

    public JacksonCustomRoomSerializer() {
        this(null);
    }

    public JacksonCustomRoomSerializer(Class<Room> t) {
        super(t);
    }

    @Override
    public void serialize(Room room, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        if(room.getId() == null){
            jgen.writeNullField("id");
        }else {
            jgen.writeNumberField("id", room.getId());
        }

        jgen.writeNumberField("roomnumber", room.getNumber());
        jgen.writeEndObject();
    }
}
