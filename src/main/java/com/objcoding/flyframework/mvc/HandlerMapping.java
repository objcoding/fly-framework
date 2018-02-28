package com.objcoding.flyframework.mvc;

/**
 * 请求信息载体
 * <p>
 * Created by chenghui.zhang on 2018/2/22.
 */
public class HandlerMapping {

    public HandlerMapping(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    // 请求方法
    private String requestMethod;

    // 请求路径
    private String requestPath;

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }
}
