package serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import hello.Person;

import java.io.IOException;

public class PersonSerializer extends JsonSerializer<Person> {
    @Override
    public void serialize(Person person, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        ObjectMapper op = new ObjectMapper();
        String json = op.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        gen.writeString(json);
    }
}
