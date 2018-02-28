package com.objcoding.flyframework.mvc;

import java.util.Map;

/**
 * Created by chenghui.zhang on 2018/2/23.
 */
public class RequestParam {
    private Map<String, Object> paramMap;

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public RequestParam(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public void put(String key, Object value) {
        paramMap.put(key, value);
    }
}
