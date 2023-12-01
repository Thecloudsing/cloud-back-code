package com.dreams;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("com.dreams.text.mapper")
@SpringBootApplication
public class DreamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamsApplication.class, args);
        log.trace("this is trace");
        log.debug("this is debug");
        log.info("this is info");
        log.warn("this is warn");
        log.error("this is error");
    }

}
