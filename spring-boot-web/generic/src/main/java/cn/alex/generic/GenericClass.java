package cn.alex.generic;

/**
 * Created by WCY on 2021/3/14
 */
public class GenericClass<E> { // 泛型类
    private E value;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public <M> void print(M m) { // 泛型方法
        System.out.println(m);
    }
}
