package cn.alex.service;

import cn.alex.bean.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WCY on 2021/8/4
 */
@Service
public class SimpleService {

    public List<User> userList = Arrays.asList(new User("wcy", 18), new User("wxx", 20));

    // 更新用户年龄
    public void updateUser(String shardingParameter) {
        userList.forEach(user -> {
            if (shardingParameter.equals(user.getUsername()) && user.getAge() < 30) {
                user.setAge(user.getAge() + 1);
            }
        });
        // 打印用户年龄
        System.out.println(shardingParameter + " : " + userList);
    }
}
