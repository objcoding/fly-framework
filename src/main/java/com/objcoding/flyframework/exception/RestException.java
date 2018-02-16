package com.objcoding.flyframework.exception;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 用于事务回滚
 * Created by zch on 2018/02/16.
 */
public class RestException extends RuntimeException {
    private int errcode;
    private String errmsg;
    private Map<String, Object> data;

    public RestException(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        data = new JSONObject();
    }

    public RestException(String errmsg) {
        this.errmsg = errmsg;
        data = new JSONObject();
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
