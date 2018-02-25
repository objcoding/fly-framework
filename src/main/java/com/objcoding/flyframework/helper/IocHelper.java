package com.objcoding.flyframework.helper;

import com.objcoding.flyframework.annotation.Inject;
import com.objcoding.flyframework.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * 依赖注入助手类
 *
 * Created by chenghui.zhang on 2018/1/28.
 */
public class IocHelper {

    static {
        // bean 容器
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        Set<Map.Entry<Class<?>, Object>> entrySet = beanMap.entrySet();
        for (Map.Entry<Class<?>, Object> entry : entrySet) {
            Class<?> clazz = entry.getKey();
            Object obj = entry.getValue();
            // 获取所有字段，包括私有字段
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Inject.class)) {
                        // 获取属性类型 class 对象
                        Class<?> fieldClass = field.getType();
                        // 从 bean 容器中获取实例对象
                        Object fieldObj = BeanHelper.getBean(fieldClass);
                        // 注入属性
                        ReflectUtils.setField(obj, field, fieldObj);
                    }
                }
            }
        }
    }
}
