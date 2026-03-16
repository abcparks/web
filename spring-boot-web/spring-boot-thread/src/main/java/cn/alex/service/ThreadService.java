package cn.alex.service;

import cn.alex.runtime.EnableRunTime;
import cn.alex.runtime.TimeUnit;
import org.springframework.stereotype.Service;

/**
 * Created by WCY on 2021/9/17
 */
@Service
public class ThreadService {

    @EnableRunTime(unit = TimeUnit.MILLISECOND)
    public void print() {
        System.out.println("Hello World!");
    }
}
