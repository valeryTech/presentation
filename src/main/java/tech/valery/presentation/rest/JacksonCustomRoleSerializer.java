package tech.valery.presentation.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import tech.valery.presentation.model.Role;

import java.io.IOException;

public class JacksonCustomRoleSerializer extends StdSerializer<Role> {

    public JacksonCustomRoleSerializer() {
        this(null);
    }

    public JacksonCustomRoleSerializer(Class<Role> t) {
        super(t);
    }

    @Override
    public void serialize(Role Role, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        if(Role.getId() == null){
            jgen.writeNullField("id");
        }else {
            jgen.writeNumberField("id", Role.getId());
        }

        jgen.writeStringField("role", Role.getRole());
        jgen.writeEndObject();
    }
}
