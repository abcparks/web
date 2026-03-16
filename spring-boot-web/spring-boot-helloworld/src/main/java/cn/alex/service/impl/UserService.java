package cn.alex.service.impl;

import cn.alex.bean.User;
import cn.alex.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WCY on 2021/7/30
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDao userDao;

    public void saveAndDoSomething() {
        super.save();
        doSomething();
    }

    public void doSomething() {
        System.out.println("doSomething!");
    }
}
