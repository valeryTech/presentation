package tech.valery.presentation.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.Room;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JacksonCustomPresentationDeserializer extends StdDeserializer<Presentation> {

    public JacksonCustomPresentationDeserializer() {
        this(null);
    }

    public JacksonCustomPresentationDeserializer(Class<Presentation> t) {
        super(t);
    }

    @Override
    public Presentation deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        Presentation presentation = new Presentation();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = parser.getCodec().readTree(parser);
        JsonNode room_node = node.get("room");
        Room room = mapper.treeToValue(room_node, Room.class);
        Long presentationId = node.get("id").asLong();
        String topic = node.get("topic").asText(null);
        Date perftime = null;
        try {
            perftime = formatter.parse(node.get("perftime").asText(null));
        }
        catch (ParseException e){
            e.printStackTrace();
            throw new IOException(e);
        }
        presentation.setId(presentationId);
        presentation.setTopic(topic);
        presentation.setPerftime(perftime);
        presentation.setRoom(room);

        return presentation;
    }
}
