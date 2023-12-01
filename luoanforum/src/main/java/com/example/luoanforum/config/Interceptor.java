package com.example.luoanforum.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;



@Component
public class Interceptor implements HandlerInterceptor {
    Logger logger = Logger.getLogger("处理日志");

    private final String encoding = "UTF-8";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("这是前置处理器");
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("这是后置处理器");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("这是完成方法");
    }
}
