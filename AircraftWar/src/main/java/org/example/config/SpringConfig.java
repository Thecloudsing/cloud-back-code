package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.example")
@MapperScan("org.example.mapper")
@PropertySource({"classpath:druid.properties","classpath:server.properties","classpath:mail.properties"})
@EnableAspectJAutoProxy
@EnableAsync
public class SpringConfig {
    @Bean
    DataSource druidDataSource(
            @Value("${driverClassName}") String driverClassName,
            @Value("${url}") String url,
            @Value("${account}") String username,
            @Value("${password}") String password,
            @Value("${initialSize}") Integer initialSize,
            @Value("${minIdle}") Integer minIdle,
            @Value("${maxActive}") Integer maxActive,
            @Value("${maxWait}") Integer maxWait
    ) {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("src/main/resources/druid.properties"));
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        return dataSource;
    }
    @Bean
    SqlSessionFactoryBean sqlSession(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
    @Bean
    DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
