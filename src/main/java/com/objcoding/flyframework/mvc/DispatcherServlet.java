package com.objcoding.flyframework.mvc;

import com.alibaba.fastjson.JSONObject;
import com.objcoding.flyframework.helper.BeanHelper;
import com.objcoding.flyframework.helper.ControllerHelper;
import com.objcoding.flyframework.utils.ReflectUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发器
 * <p>
 * Created by chenghui.zhang on 2018/1/28.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();

        HandlerAdapter handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {

            // 封装请求参数
            Map<String, Object> paramMap = new HashMap<>();
            RequestParam requestParam = new RequestParam(paramMap);
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = request.getParameter(paramName);
                requestParam.put(paramName, paramValue);
            }

            // 执行handle方法
            Object obj = BeanHelper.getBean(handler.getClazz());
            Object result = ReflectUtils.invokeMethod(obj, handler.getMethod(), requestParam);
            if (request instanceof Data) {// 返回json数据
                Data data = (Data) result;
                Map<String, Object> dataMap = data.getData();
                String json = JSONObject.toJSONString(dataMap);
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
                writer.close();
            } else { // 返回视图数据
                View view = (View) result;
                // TODO

            }

        }

    }

}
