package tech.valery.presentation.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import tech.valery.presentation.model.Role;

import java.io.IOException;

public class JacksonCustomRoleDeserializer extends StdDeserializer<Role> {

    public JacksonCustomRoleDeserializer() {
        this(null);
    }

    public JacksonCustomRoleDeserializer(Class<Role> t) {
        super(t);
    }

    @Override
    public Role deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        Role Role = new Role();
        Long id = node.get("id").asLong();
        if(!(id == 0)){
            Role.setId(id);
        }
        Role.setRole(node.get("role").asText("USER"));
        return Role;
    }
}
