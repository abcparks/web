package cn.alex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by WCY on 2021/7/29
 */
@ServletComponentScan // 配置过滤器
@SpringBootApplication
// 打包方式war 必须继承SpringBootServletInitializer
public class RequestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RequestApplication.class, args);
    }
}
