package cn.alex.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WCY on 2021/7/29
 */
@Slf4j
@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("helloworld")
    public String helloWorld() {
        log.warn("warn message");
        log.error("error message");
        return "Hello World!";
    }
}
