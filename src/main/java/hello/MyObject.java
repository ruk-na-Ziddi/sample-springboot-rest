package hello;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MyObject {
    private String name;
    private List<MyChildObject> myChildObject;

}
