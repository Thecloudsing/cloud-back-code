package org.dreams.weyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.dreams.weyun.mapper")
@SpringBootApplication
public class WeyunApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeyunApplication.class, args);
    }

}
