package cn.alex.config;

import cn.alex.bean.Book;
import cn.alex.bean.Pet;
import cn.alex.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by WCY on 2021/7/29
 */
/*
    1 配置类中使用@Bean标注在方法上给容器注册组件, 默认也是单实例的
    2 配置类本身也是组件
    3 proxyBeanMethods: 是否代理bean的方法
        Full(proxyBeanMethods = true)
        Lite(proxyBeanMethods = false)
        组件依赖
        最佳实战: 配置类组件之间无依赖关系用Lite模式加速容器启动过程, 减少判断
                 配置类组件之间有依赖关系, 方法会被调用得到之前的单例组件, 用Full模式

    4 @Import({User.class, DBHelper.class})
        给容器中自动创建出这两个类型的组件, 默认组件的名字就是全限定类名

    5 @ImportResource("classpath:beans.xml")导入Spring的配置文件

    6 @PropertySource("classpath:person.properties") 导入自定properties配置文件
 */
@Configuration(proxyBeanMethods = true) // 告诉springboot这是一个配置类
@ImportResource("classpath:beans.xml")
@EnableTransactionManagement // 开启事务
/*
    @EnableConfigurationProperties(Car.class)
    1 开启Car配置绑定功能
    2 把这个Car组件自动注册到容器中
    适用于第三方类中, 没有标注@Component进行配置绑定
 */
public class MyConfig {

    @Bean
    public Book book() {
        Book book = new Book();
        book.setName("西游记");
        book.setType("中国玄幻");
        book.setPrice(18d);
        return book;
    }

    /*
        外部无论对配置类中这个组件注册方法调用多少次, 获取的都是之前注册到容器中的单实例对象
     */
    // 给容器中添加组件, 以方法名作为组件id, 返回值类型就是组件类型, 返回值就是组件在容器中的实例
    @Bean
    public User zhangsan() {
        return new User("zhangsan", 18);
    }

    @Bean
    public Pet tomcat() {
        return new Pet("tomcat");
    }
}
