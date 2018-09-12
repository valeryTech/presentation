package tech.valery.presentation.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import tech.valery.presentation.model.Room;

import java.io.IOException;

public class JacksonCustomRoomDeserializer extends StdDeserializer<Room> {

    public JacksonCustomRoomDeserializer() {
        this(null);
    }

    public JacksonCustomRoomDeserializer(Class<Room> t) {
        super(t);
    }

    @Override
    public Room deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        Room room = new Room();
        Long id = node.get("id").asLong();
        if(!(id == 0)){
            room.setId(id);
        }
        room.setNumber(node.get("roomnumber").asInt(0));
        return room;
    }
}
