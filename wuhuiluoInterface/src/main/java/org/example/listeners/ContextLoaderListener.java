package org.example.listeners;

import org.example.ioc.ClassPathXmlApplicationContext;
import org.example.mvc.Interceptor;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//监听上下文启动，在上下文启动的时候去创建IOC容器,然后将其保存到application作用域
//后面中央控制器再从application作用域中去获取IOC容器
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取ServletContext对象
        ServletContext application = servletContextEvent.getServletContext();
        //获取上下文的初始化参数
        String path = application.getInitParameter("contextConfigLocation");
        //创建IOC容器
        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext(path,"bean");
        //创建增强方法
        Interceptor interceptor = new Interceptor();
        //将IOC容器保存到application作用域
        application.setAttribute("beanFactory",beanFactory);
        application.setAttribute("interceptor",interceptor);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
