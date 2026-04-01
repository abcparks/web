package cn.alex.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by WCY on 2026/3/30
 */
@Component
public class UserRegister {

    @Autowired
    private ApplicationContext context;

    public void register() {
        System.out.println("用户注册成功");
        context.publishEvent(new UserRegisterEvent(this));
    }

}
