package org.dreams.weyun.common.factory;

import org.dreams.weyun.common.handlers.GlobalUncaughtExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */

@Slf4j
@AllArgsConstructor
public class CustomizationThreadFactory implements ThreadFactory {

    private final ThreadFactory factory;


    @Override
    public Thread newThread(Runnable r) {
        Thread thread = factory.newThread(r);
        log.debug("create thread: {}", thread.getName());
        thread.setUncaughtExceptionHandler(GlobalUncaughtExceptionHandler.getInstance());
        return thread;
    }
}
