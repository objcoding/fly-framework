package com.objcoding.flyframework.mvc;

import java.util.Map;

/**
 * Created by chenghui.zhang on 2018/1/23.
 */
public class View {

    private String viewPath;

    private Map<String, Object> model;

    public String getViewPath() {
        return viewPath;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
