package cn.alex.conf;

import lombok.Data;

/**
 * 线程池基本参数
 * Created by WCY on 2021/9/16
 */
@Data
public class AsyncContent {
    /**
     * 核心线程数
     */
    private Integer corePoolSize = 10;

    /**
     * 最大线程数
     */
    private Integer maxPoolSize = 20;

    /**
     * 等待队列
     */
    private Integer queueCapacity = 50;

    /**
     * 线程池维护线程所允许的空闲时间,单位为秒
     */
    private Integer keepAliveSeconds = 120;

}
