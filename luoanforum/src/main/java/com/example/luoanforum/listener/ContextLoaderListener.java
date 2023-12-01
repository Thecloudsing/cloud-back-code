package com.example.luoanforum.listener;

//import com.example.luoanforum.config.SpringConfig;
import com.example.luoanforum.LuoanforumApplication;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;

/**
 * @author 落扶苏
 * @version 1.1
 */


public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ServletContextListener.super.contextInitialized(sce);
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(LuoanforumApplication.class);
        //ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        sce.getServletContext().setAttribute("app",app);
//        LoginService loginService = app.getBean(LoginService.class);
//        String show = loginService.show();
//        System.out.println(show);
        System.out.println("=====================");
    }
}
