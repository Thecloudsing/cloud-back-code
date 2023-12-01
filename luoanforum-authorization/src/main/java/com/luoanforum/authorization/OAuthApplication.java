package com.luoanforum.authorization;

import lombok.val;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@MapperScan("com.luoanforum.authorization.mapper")
public class OAuthApplication
{

    public static void main( String[] args ) {
        long start = System.currentTimeMillis();
        val run = SpringApplication.run(OAuthApplication.class, args);
        long end = System.currentTimeMillis();
        System.out.printf("start is { %s } ; end is { %s } ; time is = %dms\n", start, end, end - start);
    }
}
