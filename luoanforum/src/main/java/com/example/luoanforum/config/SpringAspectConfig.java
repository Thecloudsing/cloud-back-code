package com.example.luoanforum.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 落扶苏
 * @version 1.1
 */

@Component
@Aspect()
public class SpringAspectConfig {

    private final String msg = "execution(* com.luoan.service.*.*(..))";


    @Before(msg)
    void before(JoinPoint joinPoint) {
        System.out.println("前置通知");
    }

    @AfterReturning(msg)
    void afterReturning(JoinPoint joinPoint) {
        System.out.println("后置通知");
    }

//    @Around(msg)
//    void around(ProceedingJoinPoint proceedingJoinPoint) {}

    @AfterThrowing(pointcut = msg,throwing = "e")
    void afterThrowing(Throwable e) {
        System.out.println("异常通知");
    }

    @After(msg)
    void after() {
        System.out.println("最后通知");
    }
}
