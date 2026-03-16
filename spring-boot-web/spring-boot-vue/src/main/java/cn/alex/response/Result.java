package cn.alex.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by WCY on 2021/11/26
 */
@Getter
@Setter
public class Result {

    private Object data;

    private String message;

    public Result() {

    }

    public Result(Object data, String message) {
        this.data = data;
        this.message = message;
    }
}
