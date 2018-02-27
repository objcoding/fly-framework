package com.objcoding.flyframework.helper;

import com.objcoding.flyframework.annotation.Handle;
import com.objcoding.flyframework.mvc.HandlerAdapter;
import com.objcoding.flyframework.mvc.HandlerMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenghui.zhang on 2018/1/28.
 */
public class ControllerHelper {

    private static final Map<HandlerMapping, HandlerAdapter> HANDLER_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (controllerClassSet != null && controllerClassSet.size() > 0) {
            for (Class<?> clazz : controllerClassSet) {
                // 获取所有方法
                Method[] methods = clazz.getDeclaredMethods();
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Handle.class)) {
                            Handle handle = method.getAnnotation(Handle.class);
                            // 请求路径
                            String mapping = handle.value();
                            // 校验路径（"get:/user/age"）
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] mappingArr = mapping.split(":");
                                String requestMethod = mappingArr[0];
                                String requestPath = mappingArr[1];
                                HandlerMapping request = new HandlerMapping(requestMethod, requestPath);
                                HandlerAdapter handler = new HandlerAdapter(clazz, method);
                                HANDLER_MAP.put(request, handler);
                            }
                        }
                    }
                }
            }
        }
    }

    public static HandlerAdapter getHandler(String requestMethod, String requestPath) {
        HandlerMapping request = new HandlerMapping(requestMethod, requestPath);
        return HANDLER_MAP.get(request);
    }
}
