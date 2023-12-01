package org.example.mvc;


import com.alibaba.fastjson.JSONObject;
import org.example.DAObase.Druid;
import org.example.annotation.PassToken;
import org.example.annotation.UserLoginToken;
import org.example.controller.InitializeDatabaseController;
import org.example.ioc.ClassPathXmlApplicationContext;
import org.example.service.ServiceException;
import org.example.utils.Result;
import org.example.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

public class Interceptor {
    Logger logger = Logger.getLogger("处理日志");

    public static String rootPath = "";

    public Object preHandle(HttpServletRequest request, HttpServletResponse response, ClassPathXmlApplicationContext beanFactory) throws Exception {
        logger.info("这是前置处理器");
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        if (requestURI.indexOf("/",2) != -1)
            Interceptor.rootPath = requestURI.substring(0, requestURI.indexOf("/", 2)).replace("/install", "");

        String pathInfo = request.getPathInfo();
        String servletPath = request.getServletPath();
        String operate = request.getParameter("operate");

        if (!Druid.initState && !Objects.equals(operate, "initialize")) {
            response.getWriter().println(JSONObject.toJSONString(Result.Redirect(301, Interceptor.rootPath + "/install/index.html", "数据库还没有初始化")));
            return false;
        }


        if (StringUtil.isEmpty(operate)) throw DispatcherServletException.NOT_FOUND_PATH("operate=null");

        if (StringUtil.isEmpty(pathInfo) && StringUtil.isEmpty(servletPath)) throw DispatcherServletException.NOT_FOUND_PATH("path=null");

        String path = StringUtil.isNotEmpty(pathInfo) ? pathInfo : servletPath;

        path = path.substring(0,path.lastIndexOf(".do"));
        if (path.length() <= 1) throw DispatcherServletException.NOT_FOUND_PATH("path=null");

        path = path.substring(1);

        Object controllerBeanObj = beanFactory.getBean(path.substring(path.lastIndexOf("/")+1));
        if (controllerBeanObj == null) throw DispatcherServletException.NOT_FOUND_PATH("controllerBeanObj=null");
        if (Arrays.stream(controllerBeanObj.getClass().getDeclaredMethods()).noneMatch(methodName -> operate.equals(methodName.getName())))
            throw DispatcherServletException.NOT_FOUND_PATH("controllerBeanObj not found operate");

        return controllerBeanObj;
    }

    public void isUID(HttpServletRequest request, HttpServletResponse response, Method method) {
        String uid = request.getHeader("UID");
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (uid == null) {
                    throw new ServiceException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String[] split = uid.split("_\\.\\+-\\+\\._");
                if (split.length < 2) {
                    throw new ServiceException("用户不存在，请重新登录");
                }
                // 验证 token
                if (!(split[0].equals(InitializeDatabaseController.username) && split[1].equals(InitializeDatabaseController.password)))
                    throw new ServiceException("账号或密码不正确");
            }
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("这是后置处理器");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
        logger.info("这是异常处理完成方法");
    }
}