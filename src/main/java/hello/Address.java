package hello;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import serialize.PersonSerializer;

public class Address {
    private String value;

    @JsonSerialize(using = PersonSerializer.class)
    private Person person;
}
