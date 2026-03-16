package cn.alex.dao;

import cn.alex.bean.User;
import org.springframework.stereotype.Repository;

/**
 * Created by WCY on 2021/7/30
 */
@Repository
public class UserDao implements BaseDao<User> {
    @Override
    public void save() {
        System.out.println("保存用户");
    }
}
