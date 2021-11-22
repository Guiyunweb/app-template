package org.example.framework.aspect;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //运行时调用日志
@Target({ElementType.METHOD}) //注解用于什么地方，我们定义为作用于方法上
@Documented //注解是否将包含在 JavaDoc 中；
public @interface WebLog {

    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";

}
