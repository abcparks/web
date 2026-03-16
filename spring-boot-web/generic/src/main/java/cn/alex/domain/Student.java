package cn.alex.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by WCY on 2021/3/14
 */
@Getter
@Setter
@ToString
public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
