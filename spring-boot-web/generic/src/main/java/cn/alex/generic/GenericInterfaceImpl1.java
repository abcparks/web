package cn.alex.generic;

/**
 * Created by WCY on 2021/3/14
 * 含有泛型的接口
 * 1 定义接口的实现类, 实现接口指定接口的泛型
 */
public class GenericInterfaceImpl1 implements GenericInterface<String> {
    @Override
    public void abstractMethod(String s) {
        System.out.println("s = " + s);
    }
}
