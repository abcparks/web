package cn.alex.dao;

import cn.alex.bean.Car;
import org.springframework.stereotype.Repository;

/**
 * Created by WCY on 2021/7/30
 */
@Repository
public class CarDao implements BaseDao<Car> {
    @Override
    public void save() {
        System.out.println("保存汽车");
    }
}
