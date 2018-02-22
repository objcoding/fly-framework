package com.objcoding.flyframework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类加载器工具
 * <p>
 * Created by chenghui.zhang on 2018/1/22.
 */
public final class ClassUtils {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * 获取当前线程类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 根据类名加载类
     *
     * @param className     类名
     * @param isInitialized 是否序列化
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException cnfe) {
            logger.error("加载类失败 => {}", cnfe);
            throw new RuntimeException(cnfe);
        }
        return clazz;
    }

    /**
     * 获取指定包名下的所有类
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    // protocol://host:port/path?query#fragment
                    // protocol 可以是 http、https、ftp、file、jar
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) { // 如果是文件类型协议的url
                        String packagePath = url.getPath().replaceAll("%20", " ");// %20 是html的空格符
                        addClass(classSet, packagePath, packageName);
                    } else if (protocol.equals("jar")) { // 如果是 jar 类型协议url
                        // 获取连接 jar 文件的连接对象
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            // 获取jar文件对象
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                // 从 Jar 文件的manifest文件中获取jar属性
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    if (jarEntry != null) {
                                        String jarEntryname = jarEntry.getName();
                                        if (jarEntryname.endsWith(".class")) {
                                            // 需要将 "/" 替换成 "."
                                            String className = jarEntryname.substring(0, jarEntryname.lastIndexOf(".")).replaceAll("/", ".");
                                            addClass(classSet, className);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("get class failure", e);
            throw new RuntimeException(e);
        }

        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        // 获取指定包名下的所有类文件信息
        File[] files = new File(packagePath).listFiles(
                // 条件过滤
                (File pathname) -> (pathname.isFile() && pathname.getName().endsWith(".class")) || pathname.isDirectory()
        );
        if (files != null && files.length > 0) {
            for (File pathname : files) {
                if (pathname.isFile()) { // 文件
                    String fileName = pathname.getName().substring(0, pathname.getName().lastIndexOf("."));
                    String className = fileName;
                    if (packageName != null) {
                        className = packageName + fileName;
                    }
                    addClass(classSet, className);
                } else { // 文件夹
                    String subPackagePath = packagePath + "/" + pathname.getName();
                    String subPackageName = packageName + "." + pathname.getName();
                    // 递归遍历文件夹
                    addClass(classSet, subPackagePath, subPackageName);
                }
            }
        }
    }

    private static void addClass(Set<Class<?>> classSet, String className) {
        Class<?> clazz = loadClass(className, false);
        classSet.add(clazz);
    }
}
