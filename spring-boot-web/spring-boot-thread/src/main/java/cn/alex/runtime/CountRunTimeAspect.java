package cn.alex.runtime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by WCY on 2020/4/22
 */

@Aspect
@Component
public class CountRunTimeAspect {

    /**
     * 统计方法执行时间
     * @param joinPoint     切入点
     * @param enableRunTime 注解
     * @return Object 返回值
     * @throws Throwable 异常
     */
    @Around("@annotation(enableRunTime)") // 配置注解切入点
    public Object countRunTimeAround(ProceedingJoinPoint joinPoint, EnableRunTime enableRunTime) throws Throwable {
        // 方法执行开始时间
        double start = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        // 方法执行结束时间
        double end = System.currentTimeMillis();

        // 类名
        String className = joinPoint.getSignature().getDeclaringTypeName();
        // 方法名
        String methodName = joinPoint.getSignature().getName();

        // 获取注解时间参数值
        TimeUnit timeUnit = enableRunTime.unit();
        System.out.println(className + "." + methodName + "() 耗时: " + (end - start) / timeUnit.getUnit() + " " + timeUnit.name());
        return obj;
    }

    //  execution配置: execution(* cn.alex.test..*.*(..))
}
