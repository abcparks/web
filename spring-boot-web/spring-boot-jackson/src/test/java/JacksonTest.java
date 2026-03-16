import cn.alex.domain.Animal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WCY on 2021/9/5
 */
public class JacksonTest {
    final List<Animal> animalList = Arrays.asList(
            new Animal("喵喵", "猫"),
            new Animal("汪汪", "狗"),
            new Animal("叽叽", "鸡")
    );

    @Test
    public void object2Json() throws JsonProcessingException {
        Animal cat = new Animal("喵喵", "猫");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cat);
        System.out.println("json = " + json);
    }

    @Test
    public void json2Object() throws JsonProcessingException {
        String json = "{\"name\":\"喵喵\",\"type\":\"猫\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Animal cat = objectMapper.readValue(json, Animal.class);
        System.out.println("cat = " + cat);

        ObjectNode objectNode = new ObjectMapper().readValue(json, ObjectNode.class);
        if (objectNode.has("name")) {
            System.out.println(objectNode.get("name"));
        }
    }

    @Test
    public void array2Json() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(animalList);
        System.out.println("json = " + json);
    }

    @Test
    public void json2Array() throws JsonProcessingException {
        String json = "[{\"name\":\"喵喵\",\"type\":\"猫\"},{\"name\":\"汪汪\",\"type\":\"狗\"},{\"name\":\"叽叽\",\"type\":\"鸡\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Animal> catList = objectMapper.readValue(json, new TypeReference<List<Animal>>() {
        });
        System.out.println("catList = " + catList);
    }
}
