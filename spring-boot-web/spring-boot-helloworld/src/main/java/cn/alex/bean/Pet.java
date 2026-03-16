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
public class Pet {
    private String name;

    public Pet(String name) {
        this.name = name;
    }
}
