package cn.alex.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by WCY on 2021/7/29
 */
@Getter
@Setter
@ToString
public class Book {
    private String id;

    private String name;

    private String type;

    private Double price;
}
