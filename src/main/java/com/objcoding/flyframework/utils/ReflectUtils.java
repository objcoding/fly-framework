package com.objcoding.flyframework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射类工具
 *
 * Created by chenghui.zhang on 2018/1/28.
 */
public class ReflectUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

    /**
     * 创建对象
     *
     * @param clazz
     * @return
     */
    public static Object newInstance(Class<?> clazz) {
        Object instance;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            logger.error("clazz new instance failure => {}", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     *
     * @param object
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object object, Method method, Object ... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object, args);
        } catch (Exception e) {
            logger.error("invoke method failure => {}", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置属性
     *
     * @param object
     * @param field
     * @param value
     */
    public static void setField(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            logger.error("set field failure => {}", e);
            throw new RuntimeException(e);
        }
    }


}
