package com.github.database;

import com.github.exceptions.database.DatabaseCloseConnectionException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.SQLException;


@Getter
@AllArgsConstructor
@Component
public class ConnectionHolder {

    private final Connection connection;

    @PreDestroy
    protected void releaseConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            throw new DatabaseCloseConnectionException("Something went wrong :/");
        }
    }
}
