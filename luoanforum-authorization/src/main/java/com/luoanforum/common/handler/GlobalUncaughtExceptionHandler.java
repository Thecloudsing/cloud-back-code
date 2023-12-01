package com.luoanforum.common.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: Thread全局异常处理
 */
@Slf4j
public class GlobalUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    /**
     * 线程全局异常处理
     * @param t 线程
     * @param e 异常
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //TODO 这里可以做任何针对异常的处理,比如记录日志等等
        log.error("Exception in thread {} ", t.getName(), e);
        e.printStackTrace();
    }
}
