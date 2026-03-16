package cn.alex.domain;

import lombok.*;

import java.io.Serializable;

/**
 * Created by WCY on 2021/9/5
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Animal /*implements Serializable*/ {

    private String name;

    private String type;
}
