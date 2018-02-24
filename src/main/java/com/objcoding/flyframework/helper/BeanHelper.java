package com.objcoding.flyframework.helper;

import com.objcoding.flyframework.utils.ReflectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean容器助手类
 *
 * Created by chenghui.zhang on 2018/1/28.
 */
public class BeanHelper {

    private static Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
        for (Class<?> clazz : classSet) {
            Object obj = ReflectUtils.newInstance(clazz);
            BEAN_MAP.put(clazz, obj);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> clazz) {
        if (!BEAN_MAP.containsKey(clazz)) {
            throw new RuntimeException("Bean is not exits");
        }
        return (T) BEAN_MAP.get(clazz);
    }
}
