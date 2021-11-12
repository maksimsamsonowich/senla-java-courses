package com.github.dao;

import com.github.annotations.Transaction;
import com.github.database.ConnectionHolder;
import com.github.entity.Location;
import com.github.exceptions.database.DatabaseAccessException;
import com.github.mapper.IMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Component
@Repository
public class LocationDao {

    @Value("${database.access.error.message}")
    private String ERROR_MESSAGE;

    private ConnectionHolder connectionHolder;

    public LocationDao(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    @Transaction
    public void create(Location location) {
        execute("insert into locations ( id, title, address, capacity ) values ( " + location.getId() + ", '" + location.getTitle() + "', '" + location.getAddress() + "', " + location.getCapacity() + " )");
    }

    public Location read(Location location) {
        return executeForResult("select * from locations where id = " + location.getId());
    }

    public Location update(Location location) {
        execute("update locations SET title = '" + location.getTitle() + "', address = '" + location.getAddress() + "', capacity = " + location.getCapacity() + " where id = " + location.getId());
        return location;
    }

    public void delete(Location location) {
        execute("delete from locations where id = " + location.getId());
    }


    private Location locationMapper(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new Location(resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("address"),
                resultSet.getInt("capacity"));
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
}
