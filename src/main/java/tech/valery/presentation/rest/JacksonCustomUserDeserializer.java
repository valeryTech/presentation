package tech.valery.presentation.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import tech.valery.presentation.model.User;
import tech.valery.presentation.model.Role;

import java.io.IOException;

public class JacksonCustomUserDeserializer extends StdDeserializer<User> {

    public JacksonCustomUserDeserializer() {
        this(null);
    }

    public JacksonCustomUserDeserializer(Class<User> t) {
        super(t);
    }

    @Override
    public User deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        User user = new User();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = parser.getCodec().readTree(parser);
        JsonNode role_node = node.get("role");
        Role role = mapper.treeToValue(role_node, Role.class);
        
        Long userId = node.get("id").asLong();
        
        String username = node.get("username").asText(null);
        String email = node.get("email").asText(null);

        user.setId(userId);
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);

        return user;
    }
}
