package com.github.dao;

import com.github.annotations.Transaction;
import com.github.database.ConnectionHolder;
import com.github.entity.Location;
import com.github.exceptions.database.DatabaseAccessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Repository
public class LocationDao {

    @Value("${database.access.error.message}")
    private String ERROR_MESSAGE;

    private final ConnectionHolder connectionHolder;

    public LocationDao(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    @Transaction
    public void create(Location location) {
        execute(String.format("insert into locations ( id, title, address, capacity ) values ( %d, '%s', '%s', %d )",
                location.getId(), location.getTitle(), location.getAddress(), location.getCapacity()));
    }

    public Location read(Location location) {
        return executeForResult(String.format("select * from locations where id = %d",
                location.getId()));
    }

    public Location update(Location location) {
        execute(String.format("update locations SET title = '%s', address = '%s', capacity = %d where id = %d",
                location.getTitle(), location.getAddress(), location.getCapacity(), location.getId()));
        return location;
    }

    public void delete(Location location) {
        execute(String.format("delete from locations where id = %d",
                location.getId()));
    }


    @Transaction
    private void execute(String sqlQuery) {
        try (PreparedStatement preparedStatement = connectionHolder
                .getConnection().prepareStatement(sqlQuery)) {
            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new DatabaseAccessException(ERROR_MESSAGE);
        }
    }

    @Transaction
    private Location executeForResult(String sqlQuery) {
        try (PreparedStatement preparedStatement = connectionHolder
                .getConnection().prepareStatement(sqlQuery)) {
            return Objects.requireNonNull(locationMapper(preparedStatement.executeQuery()));
        } catch (SQLException ex) {
            throw new DatabaseAccessException(ERROR_MESSAGE);
        }
    }

    private Location locationMapper(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new Location(resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("address"),
                resultSet.getInt("capacity"));
    }
}
