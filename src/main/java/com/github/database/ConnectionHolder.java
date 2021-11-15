package com.github.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.sql.Connection;


@Getter
@AllArgsConstructor
@Component
public class ConnectionHolder {

    private final Connection connection;

    @SneakyThrows
    @PreDestroy
    protected void releaseConnection() {
        connection.close();
    }
}
