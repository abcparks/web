package cn.alex.runtime;


import lombok.Getter;

/**
 * Created by WCY on 2020/4/27
 */

@Getter
public enum TimeUnit {
    /**
     * SECOND 秒, MILLISECOND 毫秒, MINUTE 分钟, HOUR 小时
     */
    SECOND(Unit.SECOND),
    MILLISECOND(Unit.MILLISECOND),
    MINUTE(Unit.MINUTE),
    HOUR(Unit.HOUR);

    interface Unit {
        long MILLISECOND = 1;
        long SECOND = 1000 * MILLISECOND;
        long MINUTE = 60 * SECOND;
        long HOUR = 60 * MINUTE;
    }

    private final long unit;

    private TimeUnit(long unit) {
        this.unit = unit;
    }
}
