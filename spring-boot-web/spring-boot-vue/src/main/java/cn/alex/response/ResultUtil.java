package cn.alex.response;

/**
 * Created by WCY on 2021/11/26
 */
public class ResultUtil {

    /**
     * 返回结果
     * @param data 数据
     * @return Result
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setData(data);
        result.setMessage("success");
        return result;
    }
}
