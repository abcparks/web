package cn.alex.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by WCY on 2026/3/30
 */
@Component
public class UserRegisterEventListener {

    @EventListener
    public void onUserRegisterEvent(UserRegisterEvent event) {
        System.out.println("发送邮件给用户...");
    }

}
