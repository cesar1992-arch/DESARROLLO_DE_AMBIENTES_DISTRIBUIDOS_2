package com.tp8.tp8.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ReplicaDataSourceConfig {

    @Bean("replicaJdbcTemplate")
    public JdbcTemplate replicaJdbcTemplate(
            @Value("${app.datasource.replica.url}") String url,
            @Value("${app.datasource.replica.username}") String username,
            @Value("${app.datasource.replica.password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return new JdbcTemplate(dataSource);
    }
}
