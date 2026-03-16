package cn.alex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
