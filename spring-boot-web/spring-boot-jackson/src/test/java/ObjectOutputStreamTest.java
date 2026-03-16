import cn.alex.domain.Animal;
import org.junit.Test;

import java.io.*;

/**
 * Created by WCY on 2021/9/5
 */
public class ObjectOutputStreamTest {
    private static final String basePath = System.getProperty("user.dir") +
            "/src/main/resources/static/";

    private final String filepath = basePath + "user.txt";

    @Test
    public void writeObject() {
        try (OutputStream outputStream = new FileOutputStream(filepath);
             ObjectOutputStream objectStream = new ObjectOutputStream(outputStream)
        ) {
            Animal cat = new Animal("喵喵", "猫");
            objectStream.writeObject(cat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readObject() {
        try (InputStream inputStream = new FileInputStream(filepath);
             ObjectInputStream objectStream = new ObjectInputStream(inputStream)
        ) {
            Animal cat = (Animal) objectStream.readObject();
            System.out.println("cat = " + cat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
