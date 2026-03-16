package cn.alex.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by WCY on 2021/8/4
 */
@Getter
@Setter
@ToString
public class User {
    private String username;

    private Integer age;

    public User() {
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }
}
