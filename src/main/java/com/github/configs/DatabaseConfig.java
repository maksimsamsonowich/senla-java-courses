package com.github.configs;

import com.github.database.ConnectionHolder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.sql.DriverManager;

@Configuration
@EnableAspectJAutoProxy
public class DatabaseConfig {

    @Value("${db.username.prop}")
    private String username;

    @Value("${db.password.prop}")
    private String password;

    @Value("${db.url.prop}")
    private String url;

    @Bean
    @SneakyThrows
    public ConnectionHolder connectionHolder() {
        return new ConnectionHolder(
                DriverManager.getConnection(url, username, password)
        );
    }
}
