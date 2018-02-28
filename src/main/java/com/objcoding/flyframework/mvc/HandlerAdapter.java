package com.objcoding.flyframework.mvc;

import java.lang.reflect.Method;

/**
 * 处理类和方法载体
 * <p>
 * Created by chenghui.zhang on 2018/2/22.
 */
public class HandlerAdapter {

    public HandlerAdapter(Class<?> clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
    }

    private Class<?> clazz;
    private Method method;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
