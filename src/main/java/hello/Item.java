package hello;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import serialize.LocalDateDeserializer;
import serialize.LocalDateSerializer;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Item {
    private String name;
    private Double price;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    private int discount;

    public Double getPriceAterDisount() {
        return price - (price * discount) / 100;
    }

    @Override
    public String toString() {
        return "{ "
            +"\"name\": "+ "\"" +name+ "\"" +", "
            +"\"price\": "+price+", "
            +"\"endDate\": "+ "\"" +endDate+"\""+", "
            +"\"discount\": "+discount+", "
            +"\"priceAfterDiscount\": "+getPriceAterDisount()
        +" }";
    }
}
