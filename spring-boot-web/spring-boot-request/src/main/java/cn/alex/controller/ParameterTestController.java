package cn.alex.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2021/7/29
 */
@RestController
public class ParameterTestController {
    @GetMapping("car/{id}/own/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String username,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("user-Agent") String userAgent,
                                      @RequestHeader Map<String, String> header,
                                      @RequestParam("age") Integer age,
                                      @RequestParam("interests") List<String> interests,
                                      @RequestParam Map<String, String> params,
                                      @CookieValue("Idea-8ed20a30") String cookieIdea,
                                      @CookieValue("Idea-8ed20a30") Cookie cookie) {
        Map map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("username", username);
        map.put("pv", pv); // {pv:{id:1,username:wcy}}
        //map.put("userAgent", userAgent);
        //map.put("header", header);
        map.put("age", age);
        map.put("interests", interests);
        map.put("params", params);
        System.out.println(cookieIdea);
        System.out.println(cookie);
        return map;
    }

    @PostMapping("save")
    public Map postMethod(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }

    /*
        1 语法: /cars/sell;low=3;brand=byd,aodi,yd
        2 springboot默认禁用了矩阵变量功能
        手动开启: 原理, 对于路径的处理, UrlPathHelper进行解析,
        removeSemicolonContent(移除分号内容) 支持矩阵变量
     */
    @GetMapping("cars/{path}")
    public Map<String, Object> carsSell(@MatrixVariable("low") Integer low,
                                        @MatrixVariable("brand") List<String> brand,
                                        @PathVariable("path") String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    // /boss/1;age=20/2;age=10
    @GetMapping("boss/{bossId}/{empId}")
    public Map<String, Object> boss(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                                    @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();
        map.put("bossId", bossAge);
        map.put("empId", empAge);
        return map;
    }
}
