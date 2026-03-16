package cn.edu.service;

import cn.edu.bean.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public String login(User user) {
        return "登录失败";
    }

}
