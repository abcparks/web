package cn.alex.controller;

import cn.alex.response.Result;
import cn.alex.response.ResultUtil;
import cn.alex.service.VueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WCY on 2021/11/26
 */
@RestController
public class VueController {

    @Autowired
    private VueService vueService;

    @GetMapping("/home/multidata")
    public Result getHomeData() {
        return ResultUtil.success(vueService.getHomeData());
    }

    @GetMapping("/home/data")
    public Result getData(@RequestParam("type") String type) {
        return ResultUtil.success(vueService.getData(type));
    }

}
