package tech.valery.presentation.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.Role;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.model.User;


import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

public class JacksonCustomUserSerializer extends StdSerializer<User> {

    public JacksonCustomUserSerializer() {
        this(null);
    }

    public JacksonCustomUserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator jgen, SerializerProvider provider) throws IOException {


        jgen.writeStartObject();
        if (user.getId() == null) {
            jgen.writeNullField("id");
        } else {
            jgen.writeNumberField("id", user.getId());
        }

        jgen.writeStringField("username", user.getUsername());
        jgen.writeStringField("email", user.getEmail());

        //role
        Role role = user.getRole();
        jgen.writeObjectFieldStart("role");
        jgen.writeNumberField("id", role.getId());
        jgen.writeStringField("role", role.getRole());
        jgen.writeEndObject();

        //presentations
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        jgen.writeArrayFieldStart("presentations");
        for (Presentation presentation : user.getPresentations()) {
            jgen.writeStartObject();
            jgen.writeNumberField("id", presentation.getId());
            jgen.writeStringField("topic", presentation.getTopic());
            jgen.writeStringField("perftime", formatter.format(presentation.getPerftime()));

            //room
            Room room = presentation.getRoom();

            jgen.writeObjectFieldStart("room");
            jgen.writeNumberField("id", room.getId());
            jgen.writeNumberField("roomnumber", room.getNumber());
            jgen.writeEndObject();
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
        jgen.writeEndObject();
    }
}
