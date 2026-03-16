import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by WCY on 2021/9/22
 */
public class MyClassLoaderTest {

    private static final String basePath = System.getProperty("user.dir") + "/src/main/resources/";

    @Test
    public void classLoad() throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader(basePath, "myClassLoader");
        Class<?> clazz = myClassLoader.findClass("cn.alex.domain.Dog");
        Object object = clazz.newInstance();

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method.getName());
        }

        Method method = clazz.getDeclaredMethod("setAge", Integer.class);
        method.setAccessible(true);
        method.invoke(object, 5);

        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(object, "doge");
        System.out.println("object = " + object);
    }
}
