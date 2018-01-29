package com.objcoding.flyframework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 类加载器工具
 *
 * Created by chenghui.zhang on 2018/1/22.
 */
public final class ClassUtils {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException cnfe) {
            logger.error("加载类失败 => {}", cnfe);
            throw new RuntimeException(cnfe);
        }
        return clazz;
    }

    public static Set<Class<?>> getClassSet(String packageName) {

        return null;
    }
}
