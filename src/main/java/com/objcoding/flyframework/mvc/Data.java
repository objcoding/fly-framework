package com.objcoding.flyframework.mvc;

import java.util.Map;

/**
 * Created by chenghui.zhang on 2018/1/23.
 */
public class Data {
    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Data(Map<String, Object> data) {
        this.data = data;
    }

    public void put(String key, Object obj) {
        data.put(key, obj);
    }
}
