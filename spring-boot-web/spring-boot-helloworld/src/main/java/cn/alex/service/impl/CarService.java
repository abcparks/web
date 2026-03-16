package cn.alex.service.impl;

import cn.alex.bean.Car;
import cn.alex.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WCY on 2021/7/30
 */
@Service
public class CarService extends BaseService<Car> {

    @Autowired
    private CarDao carDao;

    public void saveAndDoSomething() {
        super.save();
        doSomething();
    }

    public void doSomething() {
        System.out.println("doSomething!");
    }
}
