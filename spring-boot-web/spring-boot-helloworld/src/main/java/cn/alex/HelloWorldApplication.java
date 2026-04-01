package cn.alex;

import cn.alex.event.UserRegister;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by WCY on 2021/7/29
 */
//@MapperScan(basePackages = "cn.alex.mapper")
/*
    使用@MapperScan后, 对应包的DAO类就无需@Mapper了, 如果使用@Mapper, 就不需要@MapperScan
    @MapperScan的出现, 就是为了解决每个类都要写@Mapper这个繁琐步骤的
*/
@SpringBootApplication(scanBasePackages = "cn.alex")
public class HelloWorldApplication {

    public static void main(String[] args) throws IOException {

        ConfigurableApplicationContext context = SpringApplication.run(HelloWorldApplication.class, args);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 国际化
        System.out.println(context.getMessage("hi", null, Locale.CHINESE));
        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));
        System.out.println(context.getMessage("hi", null, Locale.JAPANESE));

        // 资源文件
        Resource[] resources = context.getResources("classpath:application.properties");
        for (Resource resource : resources) {
            System.out.println(resource);
        }

        resources = context.getResources("classpath*:META-INF/spring.factories");
        for (Resource resource : resources) {
            System.out.println(resource);
        }

        // 配置信息
        ConfigurableEnvironment environment = context.getEnvironment();
        String java_home = environment.getProperty("java_home");
        System.out.println("java_home = " + java_home);
        String server_port = environment.getProperty("server.port");
        System.out.println("server_port = " + server_port);

        // 监听器
        //context.publishEvent(new UserRegisterEvent(context));
        context.getBean(UserRegister.class).register();

    }

}
