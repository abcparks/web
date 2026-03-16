package cn.alex.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WCY on 2021/7/29
 */
@RestController
public class HelloController {

    @RequestMapping("helloworld")
    public String helloworld() {
        return "Hello World!";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String getUser() {
        return "GET-张三";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveUser() {
        return "POST-张三";
    }

    @RequestMapping(value = "user", method = RequestMethod.PUT)
    public String putUser() {
        return "PUT-张三";
    }

    @RequestMapping(value = "user", method = RequestMethod.DELETE)
    public String deleteUser() {
        return "DELETE-张三";
    }
}
