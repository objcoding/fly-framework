package com.objcoding.flyframework.helper;

import com.objcoding.flyframework.constants.PropsConstant;
import com.objcoding.flyframework.utils.PropsUtils;

/**
 * 配置属性助手类
 *
 * Created by chenghui.zhang on 2018/1/29.
 */
public class PropsHelper {

    public static String getConfigFileName() {
        return PropsConstant.CONFIG_FILE;
    }

    public static String getJdbcDriver() {
        return PropsUtils.getProperty(PropsConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return PropsUtils.getProperty(PropsConstant.JDBC_URL);
    }

    public static String getJdbcUsername() {
        return PropsUtils.getProperty(PropsConstant.JDBC_USERNAME);
    }

    public static String getJdbcPassword() {
        return PropsUtils.getProperty(PropsConstant.JDBC_PASSWORD);
    }

    public static String getJdbcPoolMinpoolsize() {
        return PropsUtils.getProperty(PropsConstant.JDBC_POOL_MINPOOLSIZE);
    }

    public static String getJdbcPoolMaxpoolsize() {
        return PropsUtils.getProperty(PropsConstant.JDBC_POOL_MAXPOOLSIZE);
    }

    public static String getJdbcPoolAcquireincrement() {
        return PropsUtils.getProperty(PropsConstant.JDBC_POOL_ACQUIREINCREMENT);
    }

    public static String getJdbcPoolInitialPoolSize() {
        return PropsUtils.getProperty(PropsConstant.JDBC_POOL_INITIALPOOLSIZE);
    }

    public static String getJdbcAppBasepackage() {
        return PropsUtils.getProperty(PropsConstant.APP_BASEPACKAGE);
    }
}
