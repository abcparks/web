
import cn.alex.generic.GenericClass;
import cn.alex.generic.GenericInterfaceImpl1;
import cn.alex.generic.GenericInterfaceImpl2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by WCY on 2021/3/14
 * 泛型测试
 */
public class GenericTest {
    public static void main(String[] args) {
        GenericClass generic = new GenericClass();
        generic.setValue(11);
        Object value = generic.getValue();
        System.out.println("value = " + value);

        GenericClass<String> strGeneric = new GenericClass<>();
        strGeneric.setValue("Hello World!");
        String value2 = strGeneric.getValue();
        System.out.println("value2 = " + value2);

        GenericClass<Integer> integerGeneric = new GenericClass<>();
        integerGeneric.setValue(110);
        Integer value3 = integerGeneric.getValue();
        System.out.println("value3 = " + value3);
    }

    @Test
    public void t() {
        GenericClass generic = new GenericClass();
        generic.print("Hello World!");
        generic.print(110);
    }

    @Test
    public void t2() {
        GenericInterfaceImpl1 genericInterfaceImpl1 = new GenericInterfaceImpl1();
        genericInterfaceImpl1.abstractMethod("字符串");

        GenericInterfaceImpl2<Integer> genericInterfaceImpl2 = new GenericInterfaceImpl2<>();
        genericInterfaceImpl2.abstractMethod(110);
    }

    /**
     * 泛型通配符: ? 代表任意数据类型
     * 使用方式: 不能创建对象使用, 只能作为方法的参数使用
     */
    @Test
    public void t3() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayListPrint(arrayList);

        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("a");
        arrayList2.add("b");
        arrayListPrint(arrayList2);
    }

    // 定义一个方法遍历所有ArrayList集合
    public void arrayListPrint(ArrayList<?> arrayList) {
        for (Object o : arrayList) {
            System.out.println("o = " + o);
        }
    }

    // Number是Integer,Float,Double等类型的父类
    // 泛型的上限: 此时的泛型?必须时Number类型或者是Number类型的子类
    public static void getElement1(Collection<? extends Number> collection) {

    }

    // 泛型的下限: 此时的泛型?必须是Number类型或者是Number类型的父类
    public static void getElement2(Collection<? super Number> collection) {

    }
}

