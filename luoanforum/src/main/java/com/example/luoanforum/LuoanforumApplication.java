package com.example.luoanforum;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.luoanforum.config.AnnotationBeansConfig;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Configuration
@Import({AnnotationBeansConfig.class})
@MapperScan("com.example.luoanforum.mapper")
@ServletComponentScan(value = {"com.example.luoanforum.filter"})
@PropertySource({"classpath:mail.properties"})
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class LuoanforumApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuoanforumApplication.class, args);
//        SpringApplication application = new SpringApplication(LuoanforumApplication.class);
        // 该设置方式
//        application.setWebApplicationType(WebApplicationType.REACTIVE);
//        application.run(args);
    }

    @Bean
    WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
        return (factory) -> factory.setRegisterDefaultServlet(true);
    }

//    @Bean
//    DataSource druidDataSource(
//            @Value("${driverClassName}") String driverClassName,
//            @Value("${url}") String url,
//            @Value("${account}") String username,
//            @Value("${password}") String password,
//            @Value("${initialSize}") Integer initialSize,
//            @Value("${minIdle}") Integer minIdle,
//            @Value("${maxActive}") Integer maxActive,
//            @Value("${maxWait}") Integer maxWait
//    ) {
////        Properties properties = new Properties();
////        properties.load(new FileInputStream("src/main/resources/druid.properties"));
////        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setInitialSize(initialSize);
//        dataSource.setMinIdle(minIdle);
//        dataSource.setMaxActive(maxActive);
//        dataSource.setMaxWait(maxWait);
//        return dataSource;
//    }
//    @Bean
//    SqlSessionFactoryBean sqlSession(DataSource dataSource) {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        return sqlSessionFactoryBean;
//    }
//    @Bean
//    DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dataSource);
//        return dataSourceTransactionManager;
//    }
//    @Bean
//    RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        return new RequestMappingHandlerMapping();
//    }
//    @Bean
//    RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
//        List<HttpMessageConverter<?>> arrayList = new ArrayList<>();
//        arrayList.add(new MappingJackson2HttpMessageConverter());
//        requestMappingHandlerAdapter.setMessageConverters(arrayList);
//        return requestMappingHandlerAdapter;
//    }
}
