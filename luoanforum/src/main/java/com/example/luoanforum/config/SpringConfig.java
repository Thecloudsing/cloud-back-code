//package com.example.luoanforum.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.*;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
////import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author 落扶苏
// * @version 1.1
// */
//
//@Configuration
//@Import({AnnotationBeansConfig.class})
//@ComponentScan("com.example.luoanforum")
//@MapperScan("com.example.luoanforum.mapper")
//@PropertySource({"classpath:druid.properties","classpath:mail.properties","classpath:host.properties"})
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
//public class SpringConfig {
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
////    @Bean(name = "multipartResolver")
////    CommonsMultipartResolver multipartResolver() {
////        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//////        commonsMultipartResolver.setDefaultEncoding("UTF-8");
//////        commonsMultipartResolver.setMaxUploadSize(10000000);
//////        commonsMultipartResolver.setMaxUploadSizePerFile(2000000);
//////        commonsMultipartResolver.setMaxInMemorySize(1000000);
////        return commonsMultipartResolver;
////    }
//}
