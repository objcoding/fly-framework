package cn.zhangchenghui.flyframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器处理方法注解
 *
 * Created by chenghui.zhang on 2018/1/22.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Handle {

    // 请求类型路径
    String value();
}
