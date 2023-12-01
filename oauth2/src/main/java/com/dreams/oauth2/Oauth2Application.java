package com.dreams.oauth2;

import com.dreams.oauth2.property.CustomSecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.TimeZone;

@SpringBootApplication
public class Oauth2Application {

    public static void main(String[] args) {
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(TimeZone.getDefault());
        ConfigurableApplicationContext run = SpringApplication.run(Oauth2Application.class, args);
        Object customSecurityProperties = run.getBean(CustomSecurityProperties.class);
        System.out.println(customSecurityProperties);

    }

}
