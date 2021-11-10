package com.github.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.sql.Connection;

@Getter
@AllArgsConstructor
public class ConnectionHolder {

    private final Connection connection;

    @SneakyThrows
    protected void releaseConnection() {
        connection.close();
    }
}
