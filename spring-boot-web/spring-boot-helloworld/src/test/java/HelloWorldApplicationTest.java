import cn.alex.HelloWorldApplication;
import cn.alex.bean.*;
import cn.alex.config.MyConfig;
import cn.alex.service.BookService;
import cn.alex.service.impl.CarService;
import cn.alex.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by WCY on 2021/7/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloWorldApplication.class)
public class HelloWorldApplicationTest {

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Book book;

    @Value("#{book.getName()}")
    private String bookName;

    @Value("#{T(java.util.UUID).randomUUID().toString()}")
    private String uuid;

    // 1 inn if != null 2 ifn if == null 3 inst instanceof

    @Test
    public void containsBean() {
        System.out.println(context == applicationContext); // true

        // 检测容器有没有组件
        boolean flag = context.containsBean("book");
        System.out.println(flag);

        // 获取容器组件
        Book book = context.getBean(Book.class);
        System.out.println("book = " + book);
    }

    @Test
    public void valueTest() {
        System.out.println("book = " + book);
        System.out.println("bookName = " + bookName);
        System.out.println("uuid = " + uuid);
    }

    /**
     * 打印IOC容器自动注入的Bean
     */
    @Test
    public void printBeanDefinitionName() {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        Pet tom1 = context.getBean("tomcat", Pet.class);
        Pet tom2 = context.getBean("tomcat", Pet.class);
        System.out.println(tom1 == tom2); // true

        // 配置类本身也是组件
        MyConfig bean = context.getBean(MyConfig.class);
        System.out.println(bean);

        // 如果@Configuration(proxyBeanMethods = true)代理对象调用方法, springboot总会检查这个组件是否在容器
        User zhangsan = bean.zhangsan();
        User zhangsan2 = bean.zhangsan();
        System.out.println(zhangsan == zhangsan2); // true
    }

    @Autowired
    private Car car;

    @Test
    public void configurationProperties() {
        System.out.println(car);
    }

    @Autowired
    private Person person;

    @Test
    public void propertySource() {
        System.out.println(person);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Test
    public void genericAutowired() {
        userService.saveAndDoSomething();
        carService.saveAndDoSomething();
    }
}
