package cn.alex.service;

import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WCY on 2021/11/26
 */
@Service
public class VueService {

    List<Object> dataList = Arrays.asList("");

    public Map<String, Object> getHomeData() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("banner", "你好呀");
        return resultMap;
    }

    public Map<String, Object> getData(String type) {
        System.out.println("type = " + type);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("context", "Hello World!");
        return resultMap;
    }
}
