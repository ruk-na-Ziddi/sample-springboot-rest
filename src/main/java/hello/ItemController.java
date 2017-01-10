package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class ItemController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/items")
    public List<Item> getFromJson() {
        LocalDate jan_1_2018 = LocalDate.of(2018, 1, 1);
        return asList(new Item("Shoe", 100.0, jan_1_2018, 10), new Item("Bat", 5.0, jan_1_2018, 10));
    }
}