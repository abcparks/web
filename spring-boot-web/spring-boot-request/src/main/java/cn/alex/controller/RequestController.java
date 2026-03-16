package cn.alex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WCY on 2021/7/29
 */
@Controller
public class RequestController {
    @GetMapping("goto")
    public String gotoPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功了");
        request.setAttribute("code", "200");
        String userName = "";
        return "forward:success"; // 转发
    }

    @ResponseBody
    @GetMapping("success")
    public Map success(@RequestAttribute("msg") String msg,
                       @RequestAttribute("code") Integer code,
                       HttpServletRequest request) {
        Object msg2 = request.getAttribute("msg");
        Object code2 = request.getAttribute("code");

        Map map = new HashMap<String, Object>();
        map.put("annotation", msg);
        map.put("msg", msg2);
        return map;
    }
}
