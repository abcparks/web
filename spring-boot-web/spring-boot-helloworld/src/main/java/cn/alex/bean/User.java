package cn.alex.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by WCY on 2021/7/30
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
