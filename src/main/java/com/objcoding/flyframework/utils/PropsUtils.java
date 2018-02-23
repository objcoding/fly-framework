package com.objcoding.flyframework.utils;

import com.objcoding.flyframework.helper.PropsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件工具
 * <p>
 * Created by chenghui.zhang on 2018/1/22.
 */
public final class PropsUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtils.class);

    private static Properties props;

    static {
        loadProps();
        JdbcUtils.initDataSource();
    }

    /**
     * 加载配置文件
     */
    synchronized private static void loadProps() {
        logger.info("===== 开始加载配置文件 =====");
        props = new Properties();
        InputStream in = null;
        try {
            in = PropsUtils.class.getClassLoader().getResourceAsStream(PropsHelper.getConfigFileName());
            props.load(in);
        } catch (FileNotFoundException fnfe) {
            logger.error("未找到配置文件 => {}", fnfe);
        } catch (IOException ioe) {
            logger.error("读取配置文件失败 => {}", ioe);
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException ioe) {
                logger.error("文件流关闭失败 => {}", ioe);
            }
        }
        logger.info("===== 配置文件已加载完成 =====");
        logger.info("配置文件内容 => {}", props);
    }

    /**
     * 获取配置信息
     */
    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }


}
