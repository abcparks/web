package cn.alex.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by WCY on 2021/7/30
 */
@Getter
@Setter
@ToString
public class UserInfo {
    private String name;

    private Integer age;

    private MultipartFile image;
}
