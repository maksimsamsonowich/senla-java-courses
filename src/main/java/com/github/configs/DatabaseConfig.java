package com.github.configs;

import com.github.database.ConnectionHolder;
import liquibase.integration.spring.SpringLiquibase;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@EnableAspectJAutoProxy
public class DatabaseConfig {

    @Value("${login}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${url}")
    private String url;

    @Value("${driver}")
    private String driver;

    @Value("${changeLogFile}")
    private String changeLogFile;

    @Bean
    @SneakyThrows
    public ConnectionHolder connectionHolder() {
        return new ConnectionHolder(
                DriverManager.getConnection(url, username, password)
        );
    }

    @Bean
    public DataSource dataSource()  {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setChangeLog(changeLogFile);
        liquibase.setDataSource(dataSource());

        return liquibase;
    }
}
