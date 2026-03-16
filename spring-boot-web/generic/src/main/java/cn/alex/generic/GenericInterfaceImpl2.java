package cn.alex.generic;

/**
 * Created by WCY on 2021/3/14
 * 含有泛型的接口
 * 2 定义接口的实现类, 接口使用什么泛型, 实现类就使用什么泛型
 */
public class GenericInterfaceImpl2<I> implements GenericInterface<I> {

    @Override
    public void abstractMethod(I i) {
        System.out.println("i = " + i);
    }
}
