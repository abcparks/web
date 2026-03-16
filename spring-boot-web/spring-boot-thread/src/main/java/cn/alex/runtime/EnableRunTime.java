package cn.alex.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by WCY on 2020/4/22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableRunTime {
    // 方法执行时间单位
    TimeUnit unit() default TimeUnit.MILLISECOND;
}
