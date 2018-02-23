package com.objcoding.flyframework.helper;

import com.objcoding.flyframework.annotation.Controller;
import com.objcoding.flyframework.annotation.Service;
import com.objcoding.flyframework.utils.ClassUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 类加载器助手类
 *
 * Created by chenghui.zhang on 2018/1/28.
 */
public class ClassHelper {

    private static final Set<Class<?>> CLASS_SET;

    // 静态加载指定包名下的所有类
    static {
        String packageName = PropsHelper.getJdbcAppBasepackage();
        CLASS_SET = ClassUtils.getClassSet(packageName);
    }

    /**
     * 获取所有类
     *
     * @return class set
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取 service 类
     *
     * @return service class set
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : CLASS_SET) {
            if (clazz.isAnnotationPresent(Service.class)) {
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    /**
     * 获取 controller 类
     *
     * @return controller class set
     */
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : CLASS_SET) {
            if (clazz.isAnnotationPresent(Controller.class)) {
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    /**
     * 获取 service controller 类
     *
     * @return service controller class set
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getServiceClassSet());
        classSet.addAll(getControllerClassSet());
        return classSet;
    }

}
