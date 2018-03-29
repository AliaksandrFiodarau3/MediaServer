package com.epam.mediaserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"com.epam.mediaserver.dao.impl", "com.epam.mediaserver.service.impl"})
public class BeanConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSource.setUrl(environment.getRequiredProperty("db.jdbc_url"));
        dataSource.setUsername(environment.getRequiredProperty("db.user"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));
        return dataSource;
    }
}
