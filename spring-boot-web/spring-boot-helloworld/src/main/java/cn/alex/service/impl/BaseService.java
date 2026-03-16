package cn.alex.service.impl;

import cn.alex.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by WCY on 2021/7/30
 */
public class BaseService<T>  {

    /*
        泛型注入, 这里不能使用@Resource注解,需要使用Spring @AutoWired
     */
    @Autowired
    private BaseDao<T> baseDao;

    public void save() {
        baseDao.save();
    }
}
